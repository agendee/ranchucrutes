package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.FindClinicaForm;
import br.com.wjaa.ranchucrutes.commons.vo.ClinicaVo;
import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaClinicaVo;
import br.com.wjaa.ranchucrutes.framework.service.GenericServiceImpl;
import br.com.wjaa.ranchucrutes.ws.dao.ClinicaDao;
import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.ws.exception.ClinicaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wagner on 4/12/16.
 */
@Service
public class ClinicaServiceImpl extends GenericServiceImpl<ClinicaEntity,Long> implements ClinicaService {

    private static final int MAX_RAIO = 50;

    private ClinicaDao dao;

    @Autowired
    private CepService cepService;

    @Autowired
    private GoogleMapsService googleMapsService;

    /**
     * Public constructor for creating a new GenericServiceImpl.
     *
     * @param genericDao the GenericDao to use for persistence
     */
    @Autowired
    public ClinicaServiceImpl(ClinicaDao genericDao) {
        super(genericDao);
        this.dao = genericDao;
    }


    @Override
    public ResultadoBuscaClinicaVo find(FindClinicaForm form) throws ClinicaServiceException {

        if (form.getIdEspecialidade() == null){
            throw new ClinicaServiceException("Especialidade é obrigatória para essa busca");
        }

        //pegando o do form caso o usuário usou a sua localizacao
        LocationVo location = form.getLocation();

        //se usuario nao usou sua localizao tentando pegar o cep no cache
        if (location == null){
            location = this.cepService.getLocationByCep(form.getCep());
        }

        try{
            //se nao existir no cache pegando o location na api do google
            if (location == null){
                EnderecoEntity endereco = this.cepService.find(form.getCep());
                location = this.googleMapsService.getLatLngByAddress(this.googleMapsService.patternAddress(endereco));
                this.cepService.saveLocation(location,form.getCep());
            }

        }catch(Exception ex){
            throw new ClinicaServiceException(ex.getMessage());
        }



        //pesquisando primeiro por plano de saude
        List<ClinicaVo> listResult = dao.findClinicaByConvenio(form.getIdEspecialidade(),form.getIdCategoria(),location, MAX_RAIO);

        //se nao encontrar nada procuramos por qualquer profissional que atenda no particular.
        if (CollectionUtils.isEmpty(listResult)){
            listResult = dao.findClinicaParticular(form.getIdEspecialidade(),location, MAX_RAIO);
        }
        ResultadoBuscaClinicaVo rb = this.groupResult(listResult);
        rb.setLongitude(location.getLongitude());
        rb.setLatitude(location.getLatitude());

        return rb;
    }


    /**
     * Agrupa profissionais que estao na mesma localidade como prédios ou clinicas.
     * @param listResult
     * @return
     */
    private ResultadoBuscaClinicaVo groupResult(List<ClinicaVo> listResult) {
        ResultadoBuscaClinicaVo resultadoBuscaClinicaVo = new ResultadoBuscaClinicaVo();

        for (ClinicaVo clinica : listResult){
            ClinicaVo clinicaVo = this.getResultadoBuscaInList(clinica,resultadoBuscaClinicaVo.getClinicas());
            if (clinicaVo != null){
                //se caiu aqui é porque o profissional está em um predio com profissionais da mesma especialidade ou
                //trabalha em uma clinica com varios medicos da mesma especialidade.
                clinicaVo.appendClinica(clinica);
            }else{
                //se entrou aqui provavelmente é um profissional que trabalha sozinho
                // ou o loop ainda nao achou profissionais no mesmo endereco ou clinica.
                resultadoBuscaClinicaVo.addClinica(clinicaVo);
            }
        }

        return resultadoBuscaClinicaVo;
    }

    private ClinicaVo getResultadoBuscaInList(ClinicaVo clinica, List<ClinicaVo> clinicas) {
        for (ClinicaVo c : clinicas) {
            if (c.isMesmoEndereco(clinica) || c.isMesmaClinica(clinica)){
                return c;
            }
        }
        return null;
    }
}
