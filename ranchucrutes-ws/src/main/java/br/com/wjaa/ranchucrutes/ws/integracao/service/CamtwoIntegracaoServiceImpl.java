package br.com.wjaa.ranchucrutes.ws.integracao.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.commons.vo.ParceiroAgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import br.com.wjaa.ranchucrutes.ws.exception.ParceiroIntegracaoServiceException;
import br.com.wjaa.ranchucrutes.ws.exception.RestException;
import br.com.wjaa.ranchucrutes.ws.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.ws.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.ws.integracao.camtwo.vo.AgendaCamtwoVO;
import br.com.wjaa.ranchucrutes.ws.rest.RestUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wagner on 22/04/16.
 */
@Service
public class CamtwoIntegracaoServiceImpl implements ParceiroIntegracaoService{

    @Override
    public AgendaVo getAgenda(ProfissionalEntity profissionalEntity) throws ParceiroIntegracaoServiceException {

        ParceiroEmpresaEntity parceiroEmpresa = profissionalEntity.getParceiroEmpresa();
        ParceiroIntegracaoEntity parceiroIntegracao = parceiroEmpresa.getParceiroIntegracao();

        String host = parceiroIntegracao.getHost();

        Map<String,String> params = new HashMap<String,String>();
        params.put("start", DateUtils.formatISO8601(new Date()));

        try {
            RestUtils.getJsonWithParamPathAndParam(AgendaCamtwoVO.class, host, params);
        } catch (RestResponseUnsatisfiedException e) {
            e.printStackTrace();
        } catch (RestException e) {
            e.printStackTrace();
        } catch (RestRequestUnstable restRequestUnstable) {
            restRequestUnstable.printStackTrace();
        }

        return null;
    }

    @Override
    public ParceiroAgendamentoVo criarAgendamento(AgendamentoForm form, ProfissionalEntity profissionalEntity,
                                                  PacienteEntity pacienteEntity) throws ParceiroIntegracaoServiceException {
        return null;
    }

    @Override
    public void confirmarAgendamento(AgendamentoEntity agendamento, Boolean confirma) throws ParceiroIntegracaoServiceException {

    }
}
