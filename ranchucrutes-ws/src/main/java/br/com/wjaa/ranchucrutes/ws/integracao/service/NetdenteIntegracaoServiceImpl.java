package br.com.wjaa.ranchucrutes.ws.integracao.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.ws.integracao.vo.ParceiroAgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.exception.ParceiroIntegracaoServiceException;
import org.springframework.stereotype.Service;

/**
 * Created by wagner on 22/04/16.
 */
@Service("netdenteIntegracaoService")
public class NetdenteIntegracaoServiceImpl  implements ParceiroIntegracaoService {


    @Override
    public AgendaVo getAgenda(Long idProfissional, Long idClinica) throws ParceiroIntegracaoServiceException {
        return null;
    }

    @Override
    public ParceiroAgendamentoVo criarAgendamento(AgendamentoForm form, ProfissionalEntity profissionalEntity, PacienteEntity pacienteEntity) throws ParceiroIntegracaoServiceException {
        return null;
    }

    @Override
    public void confirmarAgendamento(AgendamentoEntity agendamento, Boolean confirma) throws ParceiroIntegracaoServiceException {

    }
}

