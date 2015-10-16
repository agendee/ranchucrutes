package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.AgendamentoForm;
import br.com.wjaa.ranchucrutes.commons.form.HorarioForm;
import br.com.wjaa.ranchucrutes.commons.helper.DiaSemana;
import br.com.wjaa.ranchucrutes.commons.vo.AgendaVo;
import br.com.wjaa.ranchucrutes.commons.vo.ConfirmarAgendamentoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ProfissionalBasicoVo;
import br.com.wjaa.ranchucrutes.ws.dao.AgendamentoDao;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import br.com.wjaa.ranchucrutes.ws.exception.AgendamentoServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 15/10/15.
 */
@Service
public class AgendamentoServiceImpl extends GenericServiceImpl<AgendamentoEntity, Long> implements  AgendamentoService{

    @Autowired
    private AgendamentoDao agendamentoDao;

    @Autowired
    private RanchucrutesService ranchucrutesService;

    @Autowired
    private ProfissionalService profissionalService;

    /**
     * Public constructor for creating a new GenericServiceImpl.
     *
     * @param agendamentoDao the GenericDao to use for persistence
     */
    public AgendamentoServiceImpl(AgendamentoDao agendamentoDao) {
        super(agendamentoDao);
        this.agendamentoDao = agendamentoDao;
    }


    @Override
    public ConfirmarAgendamentoVo criarAgendamento(AgendamentoForm form) throws AgendamentoServiceException {

        AgendamentoEntity agendamento = this.agendamentoDao
                .getAgendamento(form.getIdProfissional(), form.getIdPaciente(), form.getIdClinica(),
                        form.getDataAgendamento());

        if (agendamento != null){
            throw new AgendamentoServiceException("Horário não está mais disponível, escolha outra data.");
        }


        

        return null;
    }

    @Override
    public AgendaVo getAgendaProfissional(Long idProfissional, Long idClinica) throws AgendamentoServiceException {

        if (idProfissional == null || idClinica == null){
            throw new AgendamentoServiceException("Profissional ou Clínica não encontrado!");
        }

        AgendaEntity agendaConfig = this.agendamentoDao.getAgendaConfig(idProfissional,idClinica);

        if (agendaConfig == null){
            throw new AgendamentoServiceException("Profissional não possuí agenda cadastrada!");
        }

        List<AgendaCanceladaEntity> listAgendaCancelada = this.agendamentoDao
                .getAgendaCancelada(idProfissional, idClinica, new Date());

        List<AgendamentoEntity> listAgendamentos = this.agendamentoDao.getAgendamentos(idProfissional,idClinica,new Date());

        ProfissionalBasicoVo profissionalBasico = profissionalService.getProfissionalBasico(idProfissional);

        AgendaVo agendaVo = this.createAgenda(agendaConfig, listAgendaCancelada, listAgendamentos, profissionalBasico );



        return agendaVo;
    }

    private AgendaVo createAgenda(AgendaEntity agendaConfig, List<AgendaCanceladaEntity> listAgendaCancelada,
                                  List<AgendamentoEntity> listAgendamentos, ProfissionalBasicoVo profissionalBasico) throws AgendamentoServiceException {

        AgendaVo agenda = new AgendaVo();
        agenda.setProfissional(profissionalBasico);

        //limite de dias definido pelo profissional, mostraremos os horários disponíveis até esse limite.
        AberturaAgendaEnum aberturaAgendaEnum = agendaConfig.getAberturaAgenda();
        int limiteAbertura = aberturaAgendaEnum.getDias();

        //QUANTIDADE DE DIAS
        for (int i = 0; i < limiteAbertura; i++){
            Calendar day = Calendar.getInstance();
            day.add(Calendar.DATE,i);
            //QUANTIDADE DE HORARIOS POR DIA
            List<Date> horariosDisponiveis = this.getHorariosDisponiveis(agendaConfig,listAgendaCancelada,listAgendamentos ,
                    day);
            if (!CollectionUtils.isEmpty(horariosDisponiveis)){
                agenda.putHorariosDisponiveis(horariosDisponiveis);
            }
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

        Calendar dataHoraIni = Calendar.getInstance();
        dataHoraIni.setTime(day.getTime());
        dataHoraIni.set(Calendar.HOUR, Integer.valueOf(horaIni.split(":")[0]));
        dataHoraIni.set(Calendar.MINUTE, Integer.valueOf(horaIni.split(":")[1]));

        Calendar dataHoraFim = Calendar.getInstance();
        dataHoraFim.setTime(day.getTime());
        dataHoraFim.set(Calendar.HOUR, Integer.valueOf(horaFim.split(":")[0]));
        dataHoraFim.set(Calendar.MINUTE, Integer.valueOf(horaFim.split(":")[1]));


        //interando em cada hora aberta
        while (dataHoraIni.before(dataHoraFim)){
            dataHoraIni.add(Calendar.MINUTE, consultaMin);

            //verificando se data e hora já esta agendanda
            if (this.naoTemAgendamento(listAgendamentos, dataHoraIni)){
                horarios.add(dataHoraIni.getTime());
            }

        }

        return horarios;
    }

    private boolean naoTemAgendamento(List<AgendamentoEntity> listAgendamentos, Calendar dataHora) {
        if (CollectionUtils.isEmpty(listAgendamentos)){
            return true;
        }
        for (AgendamentoEntity agendamento : listAgendamentos){
            if ( agendamento.getDataAgendamento().equals(dataHora.getTime()) ){
                return false;
            }
        }
        return true;
    }
}

