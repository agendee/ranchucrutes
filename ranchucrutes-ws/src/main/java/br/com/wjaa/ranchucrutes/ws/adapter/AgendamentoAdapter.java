package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.vo.AgendamentoVo;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;

/**
 * Created by wagner on 17/10/15.
 */
public class AgendamentoAdapter {
    public static AgendamentoVo toAgendamentoVo(AgendamentoEntity ae, PacienteEntity pacienteEntity,
                                                ProfissionalEntity profissionalEntity) {
        AgendamentoVo agendamentoVo = new AgendamentoVo();
        agendamentoVo.setDataAgendamento(ae.getDataAgendamento());
        agendamentoVo.setId(ae.getId());
        agendamentoVo.setPaciente(PacienteAdapter.toPacienteVo(pacienteEntity));
        agendamentoVo.setProfissional(ProfissionalAdapter.toProfissionalBasico(profissionalEntity));
        return agendamentoVo;
    }
}
