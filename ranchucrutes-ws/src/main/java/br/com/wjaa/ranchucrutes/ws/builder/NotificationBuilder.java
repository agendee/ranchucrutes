package br.com.wjaa.ranchucrutes.ws.builder;

import br.com.wjaa.ranchucrutes.commons.utils.DateUtils;
import br.com.wjaa.ranchucrutes.commons.vo.NotificationVo;
import br.com.wjaa.ranchucrutes.ws.entity.AgendamentoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;

/**
 * Created by wagner on 24/01/16.
 */
public class NotificationBuilder {
    private boolean cancelation = false;
    private boolean confirmation = false;
    private PacienteEntity paciente;
    private ProfissionalEntity profissional;
    private AgendamentoEntity agendamento;



    public static NotificationBuilder create() {
        return new NotificationBuilder();
    }

    public NotificationBuilder cancelation(boolean cancelation) {
        this.cancelation = cancelation;
        return this;
    }

    public NotificationBuilder confirmation(boolean confirmation) {
        this.confirmation = confirmation;
        return this;
    }

    public NotificationBuilder setPaciente(PacienteEntity pacienteEntity) {
        this.paciente = pacienteEntity;
        return this;
    }

    public NotificationBuilder setProfissional(ProfissionalEntity profissionalEntity) {
        this.profissional = profissionalEntity;
        return this;
    }

    public NotificationBuilder setAgendamento(AgendamentoEntity agendamento) {
        this.agendamento = agendamento;
        return this;
    }

    public NotificationVo build() {

        if (profissional == null || agendamento == null){
            new NotificationVo(NotificationVo.StatusNotification.UNKNOW,"unknow");
        }

        if (cancelation){
            return new NotificationVo(NotificationVo.StatusNotification.CANCELLATION,
                    "Sua consulta com o Dr(a) " + profissional.getNome() + " marcada para " +
                            DateUtils.formatddMMyyyy(agendamento.getDataAgendamento()) + ", foi cancelada!");
        }else if (confirmation){
            return new NotificationVo(NotificationVo.StatusNotification.CONFIRMATION,
                    "O Dr(a) " + profissional.getNome() + " confirmou a sua consulta para " +
                            DateUtils.formatddMMyyyy(agendamento.getDataAgendamento()));
        }

        return new NotificationVo(NotificationVo.StatusNotification.UNKNOW,"unknow");
    }
}
