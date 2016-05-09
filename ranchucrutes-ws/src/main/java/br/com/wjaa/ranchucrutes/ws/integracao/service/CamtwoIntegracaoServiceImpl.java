package br.com.wjaa.ranchucrutes.ws.integracao.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.commons.vo.ParceiroAgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.adapter.ProfissionalAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import br.com.wjaa.ranchucrutes.ws.exception.ParceiroIntegracaoServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.RestException;
import br.com.wjaa.ranchucrutes.ws.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.ws.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo.AgendaCamtwoVO;
import br.com.wjaa.ranchucrutes.ws.rest.RestUtils;
import br.com.wjaa.ranchucrutes.ws.service.ProfissionalService;
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

    private static final String END_POINT_GET_AGENDA = "/profissional/horariosDisponiveis/";

    @Override
    public AgendaVo getAgenda(Long idProfissional, Long idClinica) throws ParceiroIntegracaoServiceException {

        ProfissionalOrigemEntity po = profissionalService.getParceiro(idProfissional,idClinica);
        ParceiroIntegracaoEntity parceiroIntegracao = po.getParceiro().getParceiroIntegracao();

        String host = parceiroIntegracao.getHost();

        String url = host + END_POINT_GET_AGENDA + po.getTokenProfissional();

        Map<String,String> params = new HashMap<String,String>();
        params.put("start", DateUtils.formatISO8601(new Date()));

        try {
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
        throw new ParceiroIntegracaoServiceException("Integracao nao implementada!");
        //return null;
    }

    @Override
    public void confirmarAgendamento(AgendamentoEntity agendamento, Boolean confirma) throws ParceiroIntegracaoServiceException {
        throw new ParceiroIntegracaoServiceException("Integracao nao implementada!");
    }
}
