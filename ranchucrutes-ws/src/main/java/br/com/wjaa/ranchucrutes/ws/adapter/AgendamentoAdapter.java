package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.vo.AgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import org.springframework.beans.BeanUtils;

/**
 * Created by wagner on 17/10/15.
 */
public class AgendamentoAdapter {
    public static AgendamentoVo toAgendamentoVo(AgendamentoEntity ae, PacienteEntity pacienteEntity,
                                                ProfissionalEntity profissionalEntity, Long idClinica) {
        AgendamentoVo agendamentoVo = new AgendamentoVo();
        BeanUtils.copyProperties(ae,agendamentoVo);
        agendamentoVo.setPaciente(PacienteAdapter.toPacienteVo(pacienteEntity));
        agendamentoVo.setProfissional(ProfissionalAdapter.toProfissionalBasico(profissionalEntity));
        agendamentoVo.getProfissional().setIdClinicaAtual(idClinica);
        return agendamentoVo;
    }
}
