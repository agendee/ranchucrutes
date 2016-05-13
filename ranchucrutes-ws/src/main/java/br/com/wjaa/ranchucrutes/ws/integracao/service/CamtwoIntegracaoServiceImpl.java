package br.com.wjaa.ranchucrutes.ws.integracao.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.commons.utils.ObjectUtils;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo.*;
import br.com.wjaa.ranchucrutes.ws.integracao.vo.ParceiroAgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.adapter.ProfissionalAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import br.com.wjaa.ranchucrutes.ws.exception.ParceiroIntegracaoServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.RestException;
import br.com.wjaa.ranchucrutes.ws.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.ws.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.ws.rest.RestUtils;
import br.com.wjaa.ranchucrutes.ws.service.ProfissionalService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wagner on 22/04/16.
 */
@Service("camtwoIntegracaoService")
public class CamtwoIntegracaoServiceImpl implements ParceiroIntegracaoService{

    private static final Log LOG = LogFactory.getLog(CamtwoIntegracaoServiceImpl.class);

    @Autowired
    private ProfissionalService profissionalService;

    private static final String END_POINT_GET_HORARIOS = "/profissional/horariosDisponiveis/";
    private static final String END_POINT_GET_AGENDAR = "/agendar/";

    @Override
    public AgendaVo getAgenda(Long idProfissional, Long idClinica) throws ParceiroIntegracaoServiceException {
        LOG.info("Criando paramentros para buscar agenda no parceiro camtwo");
        ProfissionalOrigemEntity po = profissionalService.getParceiro(idProfissional,idClinica);
        ParceiroIntegracaoEntity parceiroIntegracao = po.getParceiro().getParceiroIntegracao();

        String host = parceiroIntegracao.getHost();

        String url = host + END_POINT_GET_HORARIOS + po.getTokenProfissional();

        Map<String,String> params = new HashMap<String,String>();
        params.put("start", DateUtils.formatDateISO8601(new Date()));

        try {
            LOG.info("Iniciando a comunicação com o servidor");
            AgendaCamtwoVO [] agendas = RestUtils.getJsonWithParamPathAndParam(AgendaCamtwoVO[].class, url, params);
            if (agendas != null && agendas.length > 0){
                for (AgendaCamtwoVO a : agendas){
                    if (a.getClinica().getToken().equals(po.getTokenClinica())){
                        ProfissionalEntity p = profissionalService.get(idProfissional);
                        AgendaVo agendaVo = a.toAgendaVO();
                        agendaVo.setProfissional(ProfissionalAdapter.toProfissionalBasico(p,po.getIdParceiro()));
                        return agendaVo;
                    }
                }

                LOG.warn("NENHUMA AGENDA ESTÁ CONFIGURADA NO AGENDEE");

            }else{
                LOG.warn("AGENDA NÃO CONFIGURADA NO CAMTWO...");
            }

            return null;
        } catch (RestException | RestResponseUnsatisfiedException | RestRequestUnstable e) {
            LOG.error("Erro ao buscar a agenda do profissional ", e);
            throw new ParceiroIntegracaoServiceException(e.getMessage());
        }
    }

    @Override
    public ParceiroAgendamentoVo criarAgendamento(AgendamentoForm form, ProfissionalEntity profissionalEntity,
                                                  PacienteEntity pacienteEntity) throws ParceiroIntegracaoServiceException {

        LOG.info("Criando objetos para iniciar agendamento no parceiro");
        ProfissionalOrigemEntity po = profissionalService.getParceiro(form.getIdProfissional(),form.getIdClinica());
        ParceiroIntegracaoEntity parceiroIntegracao = po.getParceiro().getParceiroIntegracao();

        AgendaProfissionalExternaVO agendaProfissional = this.createAgendaProfissionalExterna(form, pacienteEntity, po);


        String host = parceiroIntegracao.getHost();
        String json = ObjectUtils.toJson(agendaProfissional);

        try {
            LOG.info("Iniciando a comunicação com o servidor");
            AgendaProfissionalExternaVO agendaProfissionalRetorno = RestUtils.postJson(AgendaProfissionalExternaVO.class, ErrorCamtwoVO.class
                    ,host,END_POINT_GET_AGENDAR + po.getTokenClinica(),json);

            LOG.info("AGENDAMENTO NO PARCEIRO REALIZADO COM SUCESSO!");
            return ParceiroAgendamentoVo.toParceiroAgendamento(agendaProfissionalRetorno);
        } catch (RestResponseUnsatisfiedException e) {
            LOG.info("Erro ao tentar realizar o agendamento no parceiro",e);
            throw new ParceiroIntegracaoServiceException("Erro na comunicação com nosso parceiro!");
        } catch (RestException e) {
            LOG.info("Erro ao tentar realizar o agendamento no parceiro",e);
            throw new ParceiroIntegracaoServiceException(e.getMessage());
        } catch (RestRequestUnstable e) {
            LOG.info("Erro ao tentar realizar o agendamento no parceiro",e);
            throw new ParceiroIntegracaoServiceException("Erro na comunicação com nosso parceiro!");
        }

    }

    @Override
    public void confirmarAgendamento(AgendamentoEntity agendamento, Boolean confirma) throws ParceiroIntegracaoServiceException {
        throw new ParceiroIntegracaoServiceException("Integracao nao implementada!");
    }

    private AgendaProfissionalExternaVO createAgendaProfissionalExterna(AgendamentoForm form,
                                                                        PacienteEntity pacienteEntity,
                                                                        ProfissionalOrigemEntity po)
            throws ParceiroIntegracaoServiceException {
        AgendaProfissionalExternaVO agendaProfissional = new AgendaProfissionalExternaVO();
        TokenVO tokenProfissional = new TokenVO();
        tokenProfissional.setToken(po.getTokenProfissional());
        agendaProfissional.setToken(tokenProfissional);

        agendaProfissional.setData(DateUtils.formatDateISO8601(form.getDataAgendamento()));
        agendaProfissional.setHoraInicio(DateUtils.formatHourISO8601(form.getDataAgendamento()));

        PacienteAgendaExternaVO pacienteAgenda = new PacienteAgendaExternaVO();
        TokenVO tokenClinica = new TokenVO();
        tokenClinica.setToken(po.getTokenClinica());
        pacienteAgenda.setToken(tokenClinica);

        this.validatePaciente(pacienteEntity);

        pacienteAgenda.setEmail(pacienteEntity.getEmail());
        pacienteAgenda.setNome(pacienteEntity.getNome());
        pacienteAgenda.setTelefone(pacienteEntity.getTelefone());
        pacienteAgenda.setCpf(pacienteEntity.getCpf());
        pacienteAgenda.setSexo(pacienteEntity.getSexo().toString());
        pacienteAgenda.setDataNascimento(DateUtils.formatDateISO8601(pacienteEntity.getDataNascimento()));

        agendaProfissional.setPaciente(pacienteAgenda);
        return agendaProfissional;
    }

    private void validatePaciente(PacienteEntity pacienteEntity) throws ParceiroIntegracaoServiceException {
        if (pacienteEntity.getDataNascimento() == null){
            throw new ParceiroIntegracaoServiceException("Data de nascimento do paciente é obrigatória!");
        }
        if (StringUtils.isEmpty(pacienteEntity.getCpf())){
            throw new ParceiroIntegracaoServiceException("CPF do paciente é obrigatória!");
        }
        if (StringUtils.isEmpty(pacienteEntity.getNome())){
            throw new ParceiroIntegracaoServiceException("Nome do paciente é obrigatória!");
        }
        if (StringUtils.isEmpty(pacienteEntity.getEmail())){
            throw new ParceiroIntegracaoServiceException("Email do paciente é obrigatória!");
        }
        if (StringUtils.isEmpty(pacienteEntity.getTelefone())){
            throw new ParceiroIntegracaoServiceException("Telefone do paciente é obrigatória!");
        }
        if (pacienteEntity.getSexo() == null ){
            throw new ParceiroIntegracaoServiceException("Sexo do paciente é obrigatória!");
        }
    }


}
