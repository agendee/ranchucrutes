package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.FindMedicoForm;
import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.adapter.MedicoAdapter;
import br.com.wjaa.ranchucrutes.ws.dao.MedicoDao;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import br.com.wjaa.ranchucrutes.ws.exception.*;
import br.com.wjaa.ranchucrutes.ws.vo.DistanceVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaMedicoVo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 12/06/15.
 */
@Service
public class MedicoServiceImpl extends GenericServiceImpl<MedicoEntity, Long> implements MedicoService {

    private static final int MAX_RAIO = 50;
    private static final Log LOG = LogFactory.getLog(MedicoServiceImpl.class);

    @Autowired
    private CepService cepService;

    @Autowired
    private GoogleMapsService googleMapsService;

    private MedicoDao medicoDao;

    @Autowired
    private RanchucrutesService ranchucrutesService;


    @Autowired
    private LoginService loginService;

    @Autowired
    private EmailService emailService;

    @Autowired
    public MedicoServiceImpl(MedicoDao medicoDao) {
        super(medicoDao);
        this.medicoDao = medicoDao;
    }

    @Override
    public ResultadoBuscaMedicoVo find(FindMedicoForm form) throws CepNotFoundException,
            LocationDuplicateFoundException, LocationNotFoundException {
        ResultadoBuscaMedicoVo resultado = new ResultadoBuscaMedicoVo();

        //pegando o do form caso o usuário usou a sua localizacao
        LocationVo location = form.getLocation();

        //se usuario nao usou sua localizao tentando pegar o cep no cache
        if (location == null){
            location = this.cepService.getLocationByCep(form.getCep());
        }

        //se nao existir no cache pegando o location na api do google
        if (location == null){
            EnderecoEntity endereco = this.cepService.find(form.getCep());
            location = this.googleMapsService.getLatLngByAddress(this.googleMapsService.patternAddress(endereco));
            this.cepService.saveLocation(location,form.getCep());
        }

        resultado.setLatitude(location.getLatitude());
        resultado.setLongitude(location.getLongitude());
        //TODO PENSAR EM UMA SOLUCAO PARA PAGINACAO.
        //TODO CRIAR UMA TABELA DE CONFIGURACAO DO SITEMA, ESSES 50 KM TEM QUE ESTAR NO BANCO.
        //raio de 50 kilometros
        List<MedicoEntity> medicosProximos = this.medicoDao.findMedico(form.getIdEspecialidade(),
                form.getIdCategoria(),location,MAX_RAIO);

        /*
        Se os médicos encontrados forem menor que 10, então adicionamos na lista mais medicos que nao aceitam o
        convenio mas aceitam consultas particulares
        */
        if (medicosProximos.size() < 10 ){
            medicosProximos.addAll(this.medicoDao.findMedicoByEspecialidade(form.getIdEspecialidade(),
                    location,MAX_RAIO));
        }

        //TODO DESLIGANDO A BUSCA DE DISTANCIAS
        /*if (medicosProximos.size() > 0){
            try{
                //adicionando as distancias em cada endereco.
                this.addDistances(location,medicosProximos);
            } catch (DistanceNotFoundException e) {
                LOG.error("Erro ao buscar as distancias para " + form, e);
            }
        }*/
        resultado.setMedicos(MedicoAdapter.toListMedicoBasico(medicosProximos));
        return resultado;
    }

    private void addDistances(LocationVo origin, List<MedicoEntity> medicosProximos) throws DistanceNotFoundException {
        LocationVo [] locations = {};
        for (MedicoEntity m : medicosProximos){
            for (MedicoClinicaEntity c : m.getClinicas()){
                EnderecoEntity endereco = c.getClinica().getEndereco();
                if (endereco != null && endereco.hasLocation()){
                    locations = (LocationVo[]) ArrayUtils.add(locations, endereco.getLocationVo());
                }
            }
        }

        List<DistanceVo> distanceVoList = this.googleMapsService.getDistance(origin, locations);
        int index = 0;
        for (MedicoEntity m : medicosProximos){
            for (MedicoClinicaEntity c : m.getClinicas()){
                EnderecoEntity endereco = c.getClinica().getEndereco();
                if (endereco != null && endereco.hasLocation()){
                    endereco.setDistanceVo(distanceVoList.get(index++));
                }
            }
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MedicoEntity save(MedicoEntity medico) throws MedicoServiceException {
        this.validate(medico);
        LOG.info("Novo médico, criando....");
        return this.insertMedico(medico);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MedicoEntity update(MedicoEntity medico) throws MedicoServiceException {

        if (medico.getIdLogin() == null){
            throw new MedicoServiceException("Impossivel atualizar um médico sem ID");
        }

        this.saveClinicas(medico.getIdLogin(),medico.getClinicas());

        MedicoEntity medicoExists = this.medicoDao.get(medico.getIdLogin());
        LOG.info("Atualizando medico [" + medico.getIdLogin() + "]");
        return this.mergeMedico(medicoExists,medico);
    }

    private MedicoEntity mergeMedico(MedicoEntity medicoPersisted, MedicoEntity medico) {
        BeanUtils.copyProperties(medico,medicoPersisted,"idLogin",
                "dataUltimoAcesso",
                "codeConfirmacao",
                "dataCriacao",
                "dataConfirmacao",
                "senha","ativo");
        medicoDao.save(medicoPersisted);
        return this.medicoDao.get(medicoPersisted.getIdLogin());
    }

    private MedicoEntity insertMedico(MedicoEntity medico) throws MedicoServiceException {

        medico.setDataCriacao(new Date());
        medico.setDataUltimoAcesso(new Date());
        medico.setAtivo(false);
        try {
            medico.setCodeConfirmacao(loginService.createCodeConfirmation(medico.getEmail(),medico.getCrm()));
            medico.setSenha(loginService.createHashPass(medico.getSenha()));
        } catch (Exception e) {
            throw new MedicoServiceException("Erro ao gerar senha|codigo de confirmacao do cliente");
        }
        medico = medicoDao.save(medico);
        LOG.info("Enviando email de confirmacao para " + medico.getEmail());
        emailService.sendEmailNovoMedico(medico.getEmail(), medico.getNome(), medico.getCodeConfirmacao());
        return medico;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveClinicas(Long idMedico, List<MedicoClinicaEntity> clinicas) throws MedicoServiceException {
        for (MedicoClinicaEntity medicoClinica : clinicas){
            ClinicaEntity clinica = medicoClinica.getClinica();
            EnderecoEntity endereco = clinica.getEndereco();
            endereco = this.saveEndereco(endereco);
            AgendaEntity agenda = clinica.getAgenda();
            clinica.setEndereco(endereco);
            clinica = this.ranchucrutesService.saveWithRequied(clinica);

            medicoClinica.setClinica(clinica);
            medicoClinica.setIdMedico(idMedico);
            this.ranchucrutesService.saveWithRequied(medicoClinica);
            //salvando a agenda.
            if (agenda != null){
                List<AgendaHorarioEntity> agendaHorarios = agenda.getAgendaHorarios();
                agenda.setIdClinica(clinica.getId());
                agenda = this.ranchucrutesService.saveWithRequied(agenda);
                this.saveAgendaHorarios(agendaHorarios,agenda);

            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeClinica(Long idClinica) throws MedicoServiceException {
        this.ranchucrutesService.removeByProperties(ClinicaEntity.class, "id", idClinica);
    }

    private EnderecoEntity saveEndereco(EnderecoEntity endereco) throws MedicoServiceException {
        try {
            //validando os dados do cep
            this.validateEndereco(endereco);

            LocationVo location = cepService.getLocationByCep(endereco.getCep());
            if (location == null){
                LOG.debug("Cep nao encontrado no cache, busca latitude e longitude no google");
                location = this.googleMapsService.getLatLngByAddress(this.googleMapsService.patternAddress(endereco));
                cepService.saveLocation(location,endereco.getCep());
            }
            endereco.setLatitude(location.getLatitude());
            endereco.setLongitude(location.getLongitude());
        } catch (LocationNotFoundException | LocationDuplicateFoundException e) {
            log.error("Erro ao buscar a latitude/longitude do endereco do medico", e);
        }

        endereco = this.ranchucrutesService.saveWithRequied(endereco);
        return endereco;
    }

    private void validateEndereco(EnderecoEntity endereco) throws MedicoServiceException {
        if (StringUtils.isBlank(endereco.getCep())){
            throw new MedicoServiceException("Cep do endereço não pode ser nulo.");
        }
    }



    private void validate(MedicoEntity medico) throws MedicoServiceException {
        if (medico.getCrm() == null){
            LOG.error("Medico sem crm...");
            throw new MedicoServiceException("CRM não pode ser null");
        }
        MedicoEntity medicoExists = this.medicoDao.getMedicoByCrm(medico.getCrm());
        if (medicoExists != null){
            throw new MedicoServiceException("Já existe um médico cadastrado com esse CRM [" + medico.getCrm() + "]");
        }

        medicoExists = this.medicoDao.getMedicoByEmail(medico.getEmail());
        if (medicoExists != null){
            throw new MedicoServiceException("Já existe um médico cadastrado com esse Email [" + medico.getEmail() + "]");
        }
    }

    private void saveAgendaHorarios(List<AgendaHorarioEntity> agendaHorarios, AgendaEntity agenda) {
        if (!CollectionUtils.isEmpty(agendaHorarios)){
            for(AgendaHorarioEntity ah : agendaHorarios){
                ah.setIdAgenda(agenda.getId());
                this.ranchucrutesService.saveWithRequied(ah);
            }
        }
    }




}
