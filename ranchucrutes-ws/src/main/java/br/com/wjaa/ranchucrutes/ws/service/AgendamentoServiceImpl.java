package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.form.RejeicaoSolicitacaoForm;
import br.com.wjaa.ranchucrutes.commons.helper.DiaSemana;
import br.com.wjaa.ranchucrutes.commons.vo.*;
import br.com.wjaa.ranchucrutes.framework.exception.GcmServiceException;
import br.com.wjaa.ranchucrutes.framework.service.GcmService;
import br.com.wjaa.ranchucrutes.framework.service.GenericServiceImpl;
import br.com.wjaa.ranchucrutes.ws.adapter.ProfissionalAdapter;
import br.com.wjaa.ranchucrutes.ws.builder.NotificationBuilder;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import br.com.wjaa.ranchucrutes.ws.exception.AgendamentoServiceException;
import br.com.wjaa.ranchucrutes.ws.adapter.AgendamentoAdapter;
import br.com.wjaa.ranchucrutes.ws.dao.AgendamentoDao;
import br.com.wjaa.ranchucrutes.ws.exception.ParceiroIntegracaoServiceException;
import br.com.wjaa.ranchucrutes.ws.integracao.service.ParceiroIntegracaoService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by wagner on 15/10/15.
 */
@Service("AgendamentoServiceImpl")
public class AgendamentoServiceImpl extends GenericServiceImpl<AgendamentoEntity, Long> implements  AgendamentoService{

    private static final Log LOG = LogFactory.getLog(AgendamentoServiceImpl.class);


    private AgendamentoDao agendamentoDao;
    private AgendamentoService agendamentoService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private GcmService gcmService;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Public constructor for creating a new GenericServiceImpl.
     *
     * @param agendamentoDao the GenericDao to use for persistence
     */
    @Autowired
    public AgendamentoServiceImpl(AgendamentoDao agendamentoDao) {
        super(agendamentoDao);
        this.agendamentoDao = agendamentoDao;
    }


    /**
     * LISTAGEM DE AGENDAMENTOS
     * ###############################################################################3
     */


    /**
     * Lista os agendamentos do profissional em um determinado periodo de tempo
     * @param idProfissional
     * @param iniDate
     * @param endDate
     * @return
     * @throws AgendamentoServiceException
     */
    @Override
    public CalendarioAgendamentoVo getAgendamentosProfissional(Long idProfissional, Date iniDate, Date endDate) throws AgendamentoServiceException {

        ProfissionalEntity profissionalEntity = this.profissionalService.get(idProfissional);
        if (profissionalEntity == null){
            throw new AgendamentoServiceException("Profissional não encontrado!");
        }

        if ( profissionalEntity.getClinicas() == null){
            throw new AgendamentoServiceException("Profissional não possui agenda!");
        }

        List<CalendarioClinicaVo> listCalendarioClinica = new ArrayList<>();
        for (ProfissionalClinicaEntity c : profissionalEntity.getClinicas()){
            //verificando se a clinica do profissional tem agenda online
            AgendaEntity agenda = c.getClinica().getAgenda();
            if (agenda == null){
                continue;
            }
            ClinicaEntity clinica = c.getClinica();
            CalendarioClinicaVo calendarioClinica = this.getCalendarioClinicaVo(iniDate, endDate, profissionalEntity, agenda, clinica);
            listCalendarioClinica.add(calendarioClinica);
        }
        CalendarioAgendamentoVo calendarioAgendamentoVo = new CalendarioAgendamentoVo();
        calendarioAgendamentoVo.setDataIni(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.formatyyyyMMdd(iniDate));
        calendarioAgendamentoVo.setDataFim(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.formatyyyyMMdd(endDate));
        calendarioAgendamentoVo.setCalendariosClinicas(listCalendarioClinica);
        return calendarioAgendamentoVo;
    }


    /**
     * Lista os agendamento de um profissional em uma determinada clinica dentro de um periodo.
     * @param idProfissional
     * @param idClinica
     * @param dateIni
     * @param dateFim
     * @return
     * @throws AgendamentoServiceException
     */
    @Override
    public CalendarioAgendamentoVo getAgendamentosProfissional(Long idProfissional, Long idClinica, Date dateIni, Date dateFim) throws AgendamentoServiceException {
        ProfissionalEntity profissionalEntity = this.profissionalService.get(idProfissional);
        if (profissionalEntity == null){
            throw new AgendamentoServiceException("Profissional não encontrado!");
        }

        if ( profissionalEntity.getClinicas() == null){
            throw new AgendamentoServiceException("Profissional não possui agenda!");
        }

        List<CalendarioClinicaVo> listCalendarioClinica = new ArrayList<>();

        for (ProfissionalClinicaEntity c : profissionalEntity.getClinicas()){
            //adicionando apenas a clinica solicitada.
            if (idClinica.equals(c.getClinica().getId())){
                AgendaEntity agenda = c.getClinica().getAgenda();
                if (agenda == null){
                    continue;
                }
                ClinicaEntity clinica = c.getClinica();
                CalendarioClinicaVo calendarioClinica = this.getCalendarioClinicaVo(dateIni, dateFim, profissionalEntity, agenda, clinica);
                listCalendarioClinica.add(calendarioClinica);
            }
        }
        CalendarioAgendamentoVo calendarioAgendamentoVo = new CalendarioAgendamentoVo();
        calendarioAgendamentoVo.setDataIni(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.formatyyyyMMdd(dateIni));
        calendarioAgendamentoVo.setDataFim(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.formatyyyyMMdd(dateFim));
        calendarioAgendamentoVo.setCalendariosClinicas(listCalendarioClinica);
        return calendarioAgendamentoVo;
    }

    /**
     * Lista os agendamentos de um paciente.
     * @param idPaciente
     * @return
     * @throws AgendamentoServiceException
     */
    @Override
    public List<AgendamentoVo> getAgendamentosPaciente(Long idPaciente) throws AgendamentoServiceException {
        PacienteEntity pacienteEntity = pacienteService.get(idPaciente);
        if (pacienteEntity == null){
            throw new AgendamentoServiceException("Paciente não encontrado!");
        }

        List<AgendamentoVo> agendamentoVos = new ArrayList<>();

        List<AgendamentoEntity> agendamentos = agendamentoDao.getAgendamentosPaciente(idPaciente);

        for(AgendamentoEntity a : agendamentos){
            ProfissionalEntity profissionalEntity = profissionalService.get(a.getIdProfissional());
            ProfissionalOrigemEntity po = profissionalService.getParceiro(a.getIdProfissional(),a.getIdClinica());
            agendamentoVos.add(AgendamentoAdapter.toAgendamentoVo(a, pacienteEntity, profissionalEntity,
                    a.getIdClinica(), po.getIdParceiro()));
        }

        return agendamentoVos;
    }

    /**
     * Retorna um agendamento pelo seu ID
     * @param idAgendamento
     * @return
     * @throws AgendamentoServiceException
     */
    @Override
    public AgendamentoVo getAgendamento(Long idAgendamento) throws AgendamentoServiceException {
        AgendamentoEntity agendamento = get(idAgendamento);

        if (agendamento == null){
            throw new AgendamentoServiceException("Agendamento não encontrado!");
        }
        PacienteEntity pacienteEntity = pacienteService.get(agendamento.getIdPaciente());
        ProfissionalEntity profissionalEntity = profissionalService.get(agendamento.getIdProfissional());
        ProfissionalOrigemEntity po = profissionalService.getParceiro(agendamento.getIdProfissional(),agendamento.getIdClinica());
        return AgendamentoAdapter.toAgendamentoVo(agendamento,pacienteEntity,profissionalEntity,
                agendamento.getIdClinica(), po.getIdParceiro());
    }

    /**
     * Lista a Agenda com horarios livres de um profissional em uma deteminada clinica, no agendee ou no parceiro.
     *
     * @param idProfissional
     * @param idClinica
     * @return
     * @throws AgendamentoServiceException
     * @throws ParceiroIntegracaoServiceException
     */
    @Override
    public AgendaVo getAgendaProfissional(Long idProfissional, Long idClinica) throws AgendamentoServiceException, ParceiroIntegracaoServiceException {

        log.info("m=getAgendaProfissional");
        if (idProfissional == null || idClinica == null){
            throw new AgendamentoServiceException("Profissional ou Clínica não encontrado!");
        }


        log.info("Buscando agenda de um profissional = " + idProfissional + " na clinica = " + idClinica);
        ProfissionalOrigemEntity po = profissionalService.getParceiro(idProfissional,idClinica);
        if ( po != null ){
            ParceiroEmpresaEntity parceiro = po.getParceiro();
            log.info("Profissional é de um parceiro, iniciando integracao...");
            ParceiroIntegracaoEntity parceiroIntegracao = parceiro.getParceiroIntegracao();
            if (parceiroIntegracao == null){
                throw new ParceiroIntegracaoServiceException("Integracao não configurada para o parceiro: "
                        + po.getParceiro().getNome());
            }


            ParceiroIntegracaoService integracaoService = (ParceiroIntegracaoService) applicationContext
                    .getBean(parceiro.getParceiroIntegracao().getBean());

            log.info("Buscando agenda do profissional...");
            return integracaoService.getAgenda(idProfissional,idClinica);

        }

        log.info("Profissional é do agendee!");

        //SE O PROFISSIONAL FOR NOSSO COMEÇA AQUI
        AgendaEntity agendaConfig = this.agendamentoDao.getAgendaConfig(idProfissional,idClinica);

        if (agendaConfig == null){
            throw new AgendamentoServiceException("Profissional não possuí agenda cadastrada!");
        }
        Date agora = br.com.wjaa.ranchucrutes.commons.utils.DateUtils.now();
        List<AgendaCanceladaEntity> listAgendaCancelada = this.agendamentoDao
                .getAgendaCanceladaPosterior(idProfissional, idClinica, agora);

        List<AgendamentoEntity> listAgendamentos = this.agendamentoDao.getAgendamentos(idProfissional, idClinica, agora );

        ProfissionalBasicoVo profissionalBasico = profissionalService.getProfissionalBasico(idProfissional);

        AgendaVo agendaVo = this.createAgenda(agendaConfig, listAgendaCancelada, listAgendamentos, profissionalBasico);

        return agendaVo;
    }

    /**
     * FIM DAS LISTAGEM DE AGENDAMENTOS
     * ########################################################################################
     */


    /**
     * Cria um agendamento no agendee ou em um parceiro.
     * @param form
     * @return
     * @throws AgendamentoServiceException
     */
    @Override
    public ConfirmarAgendamentoVo criarAgendamento(AgendamentoForm form) throws AgendamentoServiceException {

        if (form.getIdPaciente() == null || form.getIdProfissional() == null || form.getIdClinica() == null ||
                form.getDataAgendamento() == null){
            throw new AgendamentoServiceException("Faltam informações para completar o agendamento.");
        }


        //verificando se o dia nao está cancelado.
        if (this.diaEstaCancelado(form.getIdClinica(), form.getIdProfissional(), form.getDataAgendamento())){
            throw new AgendamentoServiceException("Horário não está mais disponível, escolha outra data.");
        }


        //verificando se o médico tem agenda
        AgendaEntity agenda = this.agendamentoDao.getAgendaConfig(form.getIdProfissional(),form.getIdClinica());
        if (agenda == null){
            throw new AgendamentoServiceException("O profissional selecionado não possui agenda online!");
        }

        //verificando se já existe um agendamento para essa data.
        AgendamentoEntity agendamento = this.agendamentoDao
                .getAgendamento(form.getIdProfissional(), form.getIdClinica(),
                        form.getDataAgendamento());

        if (agendamento != null){
            //verificando se o agendamento é do proprio paciente, isso nunca irá acontecer, mas é um bloqueio a mais.
            if (agendamento.getIdPaciente().equals(form.getIdPaciente()) ){
                throw new AgendamentoServiceException("Você já tem um agendamento para esse horário!");
            }else{
                throw new AgendamentoServiceException("Esse horário não está mais disponível!");
            }
        }

        //verificando se o paciente já tem agendamento com esse profissional
        List<AgendamentoEntity> agendamentosPosteriores = this.agendamentoDao
                .getAgendamentosPosteriores(form.getIdProfissional(), form.getIdClinica(), form.getIdPaciente(),
                        br.com.wjaa.ranchucrutes.commons.utils.DateUtils.now());
        if (agendamentosPosteriores.size() > 0){
            AgendamentoEntity agendamentoEntity = agendamentosPosteriores.get(0);
            throw new AgendamentoServiceException("Você já tem uma consulta marcada com esse profissional para o dia "
                    + DateFormatUtils.format(agendamentoEntity.getDataAgendamento(),"dd/MM/yyyy HH:mm") + ", veja sua agenda!");
        }

        PacienteEntity pacienteEntity = pacienteService.get(form.getIdPaciente());

        //se consulta nao for no particular, verificamos se o profissional aceita o plano de saude do paciente.
        if (!form.getConsultaParticular()){

            if ( !profissionalService.profissionalAceitaCategoria(form.getIdProfissional(),pacienteEntity.getIdCategoria()) ){
                throw new AgendamentoServiceException("Profissional não aceita a categoria do seu plano de saúde, tente marcar no particular.");
            }

        }

        ProfissionalEntity profissionalEntity = profissionalService.get(form.getIdProfissional());
        //usando uma nova instancia para tentar pegar o erro de UniqueKey, caso no mesmo instante algum paciente
        //conseguiu agendar a consulta no mesmo horário do paciente corrente.
        try {
            return agendamentoService.criarAgendamentoNovaTransaction(form,pacienteEntity,profissionalEntity);
        } catch (SQLException e) {
            LOG.error("Horario nao está disponivel:", e);
            throw new AgendamentoServiceException("Desculpe, horário não está mais disponível!");
        } catch (Exception e){
            LOG.error("Erro no agendamento:", e);
            throw new AgendamentoServiceException("Ocorreu um erro no agendamento, tente novamente mais tarde!");
        } catch (ParceiroIntegracaoServiceException e) {
            LOG.error("Erro na integracao:", e);
            throw new AgendamentoServiceException("Ocorreu um erro na integração com nosso parceiro.");
        }
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ConfirmarAgendamentoVo criarAgendamentoNovaTransaction(AgendamentoForm form, PacienteEntity pacienteEntity,
                                                                   ProfissionalEntity profissionalEntity)
            throws SQLException, ParceiroIntegracaoServiceException {

        LOG.info("m=criarAgendamentoNovaTransaction");

        LOG.info("Antes de criar agenda, verificando se profissional é de um parceiro para iniciar a integracao.");
        ParceiroAgendamentoVo parceiroAgendamentoVo = null;
        ProfissionalOrigemEntity po = profissionalService.getParceiro(form.getIdProfissional(),form.getIdClinica());
        if (po != null){

            LOG.info("Profissional é de um parceiro, iniciando integracao para criar agendamento");

            ParceiroEmpresaEntity parceiro = po.getParceiro();

            ParceiroIntegracaoEntity parceiroIntegracao = parceiro.getParceiroIntegracao();

            if (parceiroIntegracao == null){
                throw new ParceiroIntegracaoServiceException("Integracao não configurada!");
            }

            ParceiroIntegracaoService parceiroIntegracaoService = (ParceiroIntegracaoService) applicationContext
                    .getBean(parceiroIntegracao.getBean());


            LOG.info("Criando agendamento no parceiro....");

            parceiroAgendamentoVo = parceiroIntegracaoService.criarAgendamento(form, profissionalEntity, pacienteEntity);
            LOG.info("Agendamento no parceiro realizado com sucesso!!!!");
        }



        LOG.info("Gravando agendamento no Agendee...");
        AgendamentoEntity ae = new AgendamentoEntity();
        ae.setCancelado(false);
        ae.setCodigoConfirmacao(this.getCodigoConfirmacao(form));
        ae.setDataAgendamento(form.getDataAgendamento());
        ae.setDataCriacao(new Date());
        ae.setIdClinica(form.getIdClinica());
        ae.setIdPaciente(form.getIdPaciente());
        ae.setIdProfissional(form.getIdProfissional());
        ae = agendamentoDao.save(ae);

        //verificando se houve um agendamento em algum parceiro
        if (parceiroAgendamentoVo != null){
            log.info("Vinculando o agendamento com o parceiro ao nosso agendamento...");
        }

        ConfirmarAgendamentoVo confirmarAgendamentoVo = new ConfirmarAgendamentoVo();
        confirmarAgendamentoVo.setCodigoConfirmacao(ae.getCodigoConfirmacao());
        confirmarAgendamentoVo.setAgendamentoVo(AgendamentoAdapter.toAgendamentoVo(ae, pacienteEntity, profissionalEntity,
                ae.getIdClinica(),po.getIdParceiro()));
        LOG.info("Agendamento realizado com sucesso!!!");
        return confirmarAgendamentoVo;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AgendamentoVo confirmarSolicitacao(Long idAgendamento, String codigo) throws AgendamentoServiceException {

        AgendamentoEntity agendamento = agendamentoDao.get(idAgendamento);
        if (agendamento == null){
            throw new AgendamentoServiceException("Agendamento não encontrado!");
        }

        if ( !agendamento.getCodigoConfirmacao().equalsIgnoreCase(codigo) ){
            throw new AgendamentoServiceException("Código de confirmação inválido!");
        }

        agendamento.setDataConfirmacao(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.now());
        agendamento = agendamentoDao.save(agendamento);

        PacienteEntity pacienteEntity = pacienteService.get(agendamento.getIdPaciente());
        ProfissionalEntity profissionalEntity = profissionalService.get(agendamento.getIdProfissional());
        ProfissionalOrigemEntity po = profissionalService.getParceiro(agendamento.getIdProfissional(),agendamento.getIdClinica());
        return AgendamentoAdapter.toAgendamentoVo(agendamento,pacienteEntity,profissionalEntity, agendamento.getIdClinica(),
                po.getIdParceiro());
    }

    /**
     * Confirma ou Cancela uma consulta no agendee ou em algum parceiro.
     * @param idAgendamento
     * @param confirma
     * @return
     * @throws AgendamentoServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AgendamentoVo confirmarConsulta(Long idAgendamento, Boolean confirma) throws AgendamentoServiceException,
            ParceiroIntegracaoServiceException {
        log.info("m=confirmarConsulta idAgendamento=" + idAgendamento + " confirma="+confirma);
        AgendamentoEntity agendamento = agendamentoDao.get(idAgendamento);
        if (agendamento == null){
            throw new AgendamentoServiceException("Agendamento não encontrado!");
        }

        log.info("Verificando se agendamento é de um parceiro");
        ProfissionalOrigemEntity po = profissionalService.getParceiro(agendamento.getIdProfissional(),agendamento.getIdClinica());
        if ( po != null){
            log.info("Agendamento é de um parceiro!");
            ParceiroIntegracaoEntity parceiroIntegracao = po.getParceiro().getParceiroIntegracao();

            if (parceiroIntegracao == null){
                throw new ParceiroIntegracaoServiceException("Integraçao não foi configurada.");
            }

            ParceiroIntegracaoService integracaoService = (ParceiroIntegracaoService) applicationContext.getBean(parceiroIntegracao.getBean());
            log.info("Notificando parceiro da confirmacaoConsulta.confirma = " + confirma);
            integracaoService.confirmarAgendamento(agendamento,confirma);
            log.info("Confirmação realizada com sucesso!");
        }


        if (confirma != null && confirma){
            LOG.info("Confirmando agendamento " + agendamento);
            agendamento.setDataConfirmacaoConsulta(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.now());
        }else{
            LOG.info("Cancelando agendamento " + agendamento);
            agendamento.setCancelado(true);
        }
        agendamentoDao.save(agendamento);

        PacienteEntity pacienteEntity = pacienteService.get(agendamento.getIdPaciente());

        ProfissionalEntity profissionalEntity = profissionalService.get(agendamento.getIdProfissional());
        return AgendamentoAdapter.toAgendamentoVo(agendamento,pacienteEntity,profissionalEntity, agendamento.getIdClinica(),
                po.getIdParceiro());


    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public AgendamentoVo aprovarSolicitacao(Long idAgendamento) throws AgendamentoServiceException {
        AgendamentoEntity agendamento = this.get(idAgendamento);
        agendamento.setDataConfirmacaoProfissional(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.now());
        agendamento = agendamentoDao.save(agendamento);
        PacienteEntity pacienteEntity = pacienteService.get(agendamento.getIdPaciente());
        ProfissionalEntity profissionalEntity = profissionalService.get(agendamento.getIdProfissional());

        LOG.info("Enviando notificação de confirmacao.");
        this.sendConfirmationNotification(pacienteEntity,profissionalEntity,agendamento);
        ProfissionalOrigemEntity po = profissionalService.getParceiro(agendamento.getIdProfissional(),agendamento.getIdClinica());
        return AgendamentoAdapter.toAgendamentoVo(agendamento,pacienteEntity,profissionalEntity,
                agendamento.getIdClinica(), po.getIdParceiro());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public AgendamentoVo rejeitarSolicitacao(RejeicaoSolicitacaoForm rejeitacaoSolicitacao) {
        AgendamentoEntity agendamento = this.get(rejeitacaoSolicitacao.getIdAgendamento());
        agendamento.setCancelado(true);
        agendamento.setDataCancelamento(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.now());
        agendamento = agendamentoDao.save(agendamento);
        PacienteEntity pacienteEntity = pacienteService.get(agendamento.getIdPaciente());
        ProfissionalEntity profissionalEntity = profissionalService.get(agendamento.getIdProfissional());

        LOG.info("Enviando notificação de cancelamento.");

        this.sendCancelationNotification(pacienteEntity, profissionalEntity, agendamento);
        ProfissionalOrigemEntity po = profissionalService.getParceiro(agendamento.getIdProfissional(),agendamento.getIdClinica());
        return AgendamentoAdapter.toAgendamentoVo(agendamento,pacienteEntity,profissionalEntity,
                agendamento.getIdClinica(), po.getIdParceiro());
    }

    private void sendCancelationNotification(PacienteEntity pacienteEntity, ProfissionalEntity profissionalEntity, AgendamentoEntity agendamento) {
        NotificationVo vo = NotificationBuilder
                .create()
                .cancelation(true)
                .setPaciente(pacienteEntity)
                .setProfissional(profissionalEntity)
                .setAgendamento(agendamento)
                .build();
        try {
            gcmService.sendNotification(vo, pacienteEntity.getKeyDeviceGcm());
        } catch (GcmServiceException e) {
            //TODO CASO DE ALGUM ERRO PRECISA ENVIAR PARA UMA FILA OU GRAVAR NO BANCO PARA TENTAR NOVAMENTE MAIS TARDE.
            LOG.error("Erro ao enviar notificacao de cancelamento", e);

        }
    }


    private void sendConfirmationNotification(PacienteEntity pacienteEntity, ProfissionalEntity profissionalEntity, AgendamentoEntity agendamento) {
        NotificationVo vo = NotificationBuilder
                .create()
                .confirmation(true)
                .setPaciente(pacienteEntity)
                .setProfissional(profissionalEntity)
                .setAgendamento(agendamento)
                .build();
        try {
            gcmService.sendNotification(vo,  pacienteEntity.getKeyDeviceGcm());
        } catch (GcmServiceException e) {
            //TODO CASO DE ALGUM ERRO PRECISA ENVIAR PARA UMA FILA OU GRAVAR NO BANCO PARA TENTAR NOVAMENTE MAIS TARDE.
            LOG.error("Erro ao enviar notificacao de cancelamento", e);

        }
    }

    private CalendarioClinicaVo getCalendarioClinicaVo(Date iniDate, Date endDate, ProfissionalEntity profissionalEntity, AgendaEntity agenda, ClinicaEntity clinica) {
        CalendarioClinicaVo calendarioClinica = new CalendarioClinicaVo();
        calendarioClinica.setClinicaVo(ProfissionalAdapter.toClinicaVo(clinica));
        calendarioClinica.setDiasAberturaAgenda(agenda.getAberturaAgenda().getDias());
        calendarioClinica.setHoraFuncionamentoIni(agenda.getHoraFuncionamentoIni());
        calendarioClinica.setHoraFuncionamentoFim(agenda.getHoraFuncionamentoFim());
        calendarioClinica.setIdAgenda(agenda.getId());
        calendarioClinica.setTempoConsultaEmMin(agenda.getTempoConsultaEmMin());

        List<AgendamentoVo> agendamentoVos = new ArrayList<>();
        calendarioClinica.setAgendamento(agendamentoVos);
        List<AgendamentoEntity> agendamentos = agendamentoDao.getAgendamentosProfissional(profissionalEntity.getIdLogin(), clinica.getId(), iniDate, endDate );

        //TODO MELHORAR ISSO AQUI ELE ESTÁ FAZENDO UMA QUERY PARA CADA PACIENTE...PERFORMANCE HORRIVEL!!!
        for(AgendamentoEntity a : agendamentos){
            PacienteEntity pacienteEntity = this.pacienteService.get(a.getIdPaciente());
            ProfissionalOrigemEntity po = profissionalService.getParceiro(a.getIdProfissional(),a.getIdClinica());
            AgendamentoVo agendamentoVo = AgendamentoAdapter.toAgendamentoVo(a, pacienteEntity, profissionalEntity,
                    a.getIdClinica(),po.getIdParceiro());
            agendamentoVo.setDataInicioConsulta(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.formatyyyyMMddTHHmmss(a.getDataAgendamento()));
            Calendar dataFimConsulta = Calendar.getInstance();
            dataFimConsulta.setTime(a.getDataAgendamento());
            dataFimConsulta.add(Calendar.MINUTE, agenda.getTempoConsultaEmMin());
            agendamentoVo.setDataFimConsulta(br.com.wjaa.ranchucrutes.commons.utils.DateUtils.formatyyyyMMddTHHmmss(dataFimConsulta.getTime()));
            agendamentoVos.add(agendamentoVo);
        }
        return calendarioClinica;
    }

    private String getCodigoConfirmacao(AgendamentoForm form) {
        /*GERANDO UM MD5 COM AS 6 PRIMEIRAS POSICOES EM LOWERCASE*/
        return br.com.wjaa.ranchucrutes.commons.utils.StringUtils.createMD5(
                (form.getIdProfissional() + "|" + form.getIdPaciente() + "|" + new Date().getTime()))
                .substring(0,6)
                .toLowerCase();
    }

    private boolean diaEstaCancelado(Long idClinica, Long idProfissional, Date dataAgendamento) {

        List<AgendaCanceladaEntity> listAgendaCancelada = this.agendamentoDao.getAgendaCancelada(idClinica,
                idProfissional,dataAgendamento);

        return listAgendaCancelada.size() > 0;
    }

    private AgendaVo createAgenda(AgendaEntity agendaConfig, List<AgendaCanceladaEntity> listAgendaCancelada,
                                  List<AgendamentoEntity> listAgendamentos, ProfissionalBasicoVo profissionalBasico) throws AgendamentoServiceException {

        AgendaVo agenda = new AgendaVo();
        agenda.setProfissional(profissionalBasico);

        //limite de dias definido pelo profissional, mostraremos os horários disponíveis até esse limite.
        AberturaAgendaEnum aberturaAgendaEnum = agendaConfig.getAberturaAgenda();
        int limiteAbertura = aberturaAgendaEnum.getDias();

        //QUANTIDADE DE DIAS
        List<Date> horariosDisponiveis = new ArrayList<>();
        for (int i = 0; i < limiteAbertura; i++){
            Calendar day = Calendar.getInstance();
            day.add(Calendar.DATE,i);
            //QUANTIDADE DE HORARIOS POR DIA
            horariosDisponiveis.addAll(this.getHorariosDisponiveis(agendaConfig,listAgendaCancelada,listAgendamentos ,
                    day));
        }
        if (!CollectionUtils.isEmpty(horariosDisponiveis)){
            agenda.setHorariosDisponiveis(horariosDisponiveis.toArray(new Date[horariosDisponiveis.size()]));
        }
        return agenda;
    }

    private List<Date> getHorariosDisponiveis(AgendaEntity agendaConfig, List<AgendaCanceladaEntity> listAgendaCancelada,
                                              List<AgendamentoEntity> listAgendamentos, Calendar day) throws AgendamentoServiceException {

        Integer consultaMin = agendaConfig.getTempoConsultaEmMin();

        if (CollectionUtils.isEmpty(agendaConfig.getAgendaHorarios())){
            throw new AgendamentoServiceException("Profissional não possui horários cadastrados em sua agenda!");
        }

        //verificando se o dia que estamos criando os horários não foi cancelado pelo profissional.
        if (!CollectionUtils.isEmpty(listAgendaCancelada)){
            for (AgendaCanceladaEntity agendaCancelada : listAgendaCancelada){
                //se o dia que etamos olhando foi cancelado pelo profissional entao sai da funcao.
                if ( DateUtils.isSameDay(agendaCancelada.getDataCancelada(),day.getTime()) ){
                    return null;
                }
            }
        }

        List<Date> diasDisponiveis = new ArrayList<>();
        for ( AgendaHorarioEntity agendaHorario : agendaConfig.getAgendaHorarios() ){

            //se for segunda e tiver o dia cadastrado para abrir agenda.
            if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && DiaSemana.temDomingo(agendaHorario.getDiaSemana())){
                diasDisponiveis.addAll(this.getHorariosDisponiveis(agendaHorario, listAgendamentos, day, consultaMin));

            }else if (day.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && DiaSemana.temSegunda(agendaHorario.getDiaSemana())){
                diasDisponiveis.addAll(this.getHorariosDisponiveis(agendaHorario,listAgendamentos,day, consultaMin));

            }else if (day.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && DiaSemana.temTerca(agendaHorario.getDiaSemana())){
                diasDisponiveis.addAll(this.getHorariosDisponiveis(agendaHorario,listAgendamentos,day, consultaMin));

            }else if (day.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && DiaSemana.temQuarta(agendaHorario.getDiaSemana())){
                diasDisponiveis.addAll(this.getHorariosDisponiveis(agendaHorario,listAgendamentos,day, consultaMin));

            }else if (day.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && DiaSemana.temQuinta(agendaHorario.getDiaSemana())){
                diasDisponiveis.addAll(this.getHorariosDisponiveis(agendaHorario,listAgendamentos,day, consultaMin));

            }else if (day.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && DiaSemana.temSexta(agendaHorario.getDiaSemana())){
                diasDisponiveis.addAll(this.getHorariosDisponiveis(agendaHorario,listAgendamentos,day, consultaMin));

            }else if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && DiaSemana.temSabado(agendaHorario.getDiaSemana())){
                diasDisponiveis.addAll(this.getHorariosDisponiveis(agendaHorario,listAgendamentos,day, consultaMin));
            }

        }

        return diasDisponiveis;
    }

    private List<Date> getHorariosDisponiveis(AgendaHorarioEntity agendaHorario, List<AgendamentoEntity> listAgendamentos,
                                              Calendar day, Integer consultaMin) throws AgendamentoServiceException {

        List<Date> horarios = new ArrayList<>();
        String horaIni = agendaHorario.getHoraIni();
        String horaFim = agendaHorario.getHoraFim();

        if (StringUtils.isBlank(horaIni) || StringUtils.isBlank(horaFim) || !horaIni.contains(":") ||
                !horaFim.contains(":")){
            throw new AgendamentoServiceException("Profissional não possui horários cadastrados em sua agenda.");
        }

        //se nao tiver o tempo da consulta ou for menor que 5, entao envio uma msg amigavel para o paciente.
        if ( consultaMin == null || consultaMin < 5 ){
            throw new AgendamentoServiceException("Profissional não possui horários cadastrados em sua agenda.");
        }

        Calendar dataHoraIni = br.com.wjaa.ranchucrutes.commons.utils.DateUtils.nowCalendar();
        dataHoraIni.setTime(day.getTime());
        dataHoraIni.set(Calendar.HOUR_OF_DAY, Integer.valueOf(horaIni.split(":")[0]));
        dataHoraIni.set(Calendar.MINUTE, Integer.valueOf(horaIni.split(":")[1]));
        dataHoraIni.set(Calendar.SECOND,0);

        Calendar dataHoraFim = br.com.wjaa.ranchucrutes.commons.utils.DateUtils.nowCalendar();
        dataHoraFim.setTime(day.getTime());
        dataHoraFim.set(Calendar.HOUR_OF_DAY, Integer.valueOf(horaFim.split(":")[0]));
        dataHoraFim.set(Calendar.MINUTE, Integer.valueOf(horaFim.split(":")[1]));
        dataHoraFim.set(Calendar.SECOND,0);


        //verificando se data e hora já esta agendanda
        if (this.naoTemAgendamento(listAgendamentos, dataHoraIni) && this.ehMaiorQueHorarioLimite(dataHoraIni)){
            horarios.add(dataHoraIni.getTime());
        }
        dataHoraIni.add(Calendar.MINUTE, consultaMin);

        //interando em cada hora aberta
        while (dataHoraIni.before(dataHoraFim)){
            //verificando se data e hora já esta agendanda
            if (this.naoTemAgendamento(listAgendamentos, dataHoraIni) && this.ehMaiorQueHorarioLimite(dataHoraIni)){
                horarios.add(dataHoraIni.getTime());
            }

            dataHoraIni.add(Calendar.MINUTE, consultaMin);
        }

        return horarios;
    }

    /**
     * Horario limite é data do momento mais 2 hora, ou seja, é um tempo limite onde o paciente consegue marcar uma consulta
     * no mesmo dia.
     * @param dataHoraIni
     * @return
     */
    private boolean ehMaiorQueHorarioLimite(Calendar dataHoraIni) {
        Calendar agora = br.com.wjaa.ranchucrutes.commons.utils.DateUtils.nowCalendar();
        agora.add(Calendar.HOUR_OF_DAY,2);

        return agora.before(dataHoraIni);
    }

    private boolean naoTemAgendamento(List<AgendamentoEntity> listAgendamentos, Calendar dataHora) {

        if (CollectionUtils.isEmpty(listAgendamentos)){
            return true;
        }
        for (AgendamentoEntity agendamento : listAgendamentos){
            if ( DateUtils.isSameDay(agendamento.getDataAgendamento(),dataHora.getTime()) &&
                 DateUtils.truncatedEquals(agendamento.getDataAgendamento(),dataHora.getTime(),Calendar.HOUR_OF_DAY) &&
                 DateUtils.truncatedEquals(agendamento.getDataAgendamento(),dataHora.getTime(),Calendar.MINUTE)){
                return false;
            }
        }
        return true;
    }


    @PostConstruct
    private void init() {
        agendamentoService = (AgendamentoService) applicationContext.getBean("AgendamentoServiceImpl");
    }
}

