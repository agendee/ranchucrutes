var Agenda = function() {
    return {
        init: function (calendario) {
            for (var i = 0; i < calendario.calendariosClinicas.length; i++){
                var calendarioClinica = calendario.calendariosClinicas[i];
                $('#calendar' + calendario.calendariosClinicas[i].clinicaVo.id).fullCalendar({
                    header: {
                        left: 'prev,next,today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    defaultView: 'agendaWeek',
                    defaultDate: calendario.dataIni,
                    lang: "pt-br",
                    buttonIcons: false, // show the prev/next text
                    weekNumbers: true,
                    editable: false,
                    slotDuration:"00:" + calendarioClinica.tempoConsultaEmMin,
                    eventLimit: true, // allow "more" link when too many events
                    events: Agenda.getEvents(calendarioClinica),
                    eventClick: function(calEvent, jsEvent, view) {

                        //alert('EventID: ' + calEvent.agendamento.id);
                        // change the border color just for fun
                        //$(this).css('border-color', 'red');
                        $("#dataAgenda").html(calEvent.agendamento.dataAgendamento);
                        $("#nomePaciente").html(calEvent.agendamento.paciente.nome);
                        $("#email").html(calEvent.agendamento.paciente.email);
                        $("#telefone").html(calEvent.agendamento.paciente.telefone);
                        $("#categoriaPlano").html(calEvent.agendamento.paciente.convenioCategoria.nome);
                        $("#modalDetalhes").modal();

                    },
                    eventRender: function(event, element) {
                       /* if (event.agendamento.dataConfirmacaoProfissional == null){
                            $(element).css('color', 'yellow');
                        }
                        if (event.agendamento.dataConfirmacao == null){
                            $(element ).css('color', 'red');
                        }
                        if (event.agendamento.dataConfirmacaoProfissional != null){
                            $(element).css('color', 'blue');
                        }*/
                    }
                });
            }



        },
        getEvents: function (calendarioClinica){
            var events = [];
            for (var j = 0; j < calendarioClinica.agendamento.length; j++){
                var agendamento = calendarioClinica.agendamento[j];
                var backgroudColor = "#0079bf";
                var textColor = "#fff";

                if (agendamento.dataConfirmacao == null){
                    backgroudColor = "#e02f2f";
                    textColor = "#fff";
                }else if (agendamento.dataConfirmacaoProfissional == null){
                     backgroudColor = "#dbd11a";
                     textColor = "#000";
                }else if (agendamento.dataConfirmacaoProfissional != null){
                     backgroudColor = "#1fa214";
                     textColor = "#000";
                }
                events.push({

                    agendamento:agendamento,
                    title: agendamento.paciente.nome,
                    start: agendamento.dataInicioConsulta,
                    end: agendamento.dataFimConsulta,
                    backgroundColor:backgroudColor,
                    textColor:textColor
                });
            }
            return events;
        }
    }
}();























