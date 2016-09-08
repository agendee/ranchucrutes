
var idProfissional = 0;
var idAgendamentoSelecionado = 0;
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
                    //events: Agenda.buildEvents(calendarioClinica),
                    eventClick: function(calEvent, jsEvent, view) {
                        idAgendamentoSelecionado = calEvent.agendamento.id;
                        //alert('EventID: ' + calEvent.agendamento.id);
                        // change the border color just for fun
                        //$(this).css('border-color', 'red');
                        $("#dataAgenda").html(calEvent.agendamento.dataAgendamento);
                        $("#nomePaciente").html(calEvent.agendamento.paciente.nome);
                        $("#email").html(calEvent.agendamento.paciente.email);
                        $("#telefone").html(calEvent.agendamento.paciente.telefone);
console.log(calEvent.agendamento.paciente.conveniosCategorias);

                        calEvent.agendamento.paciente.conveniosCategorias.forEach(function(item,index){
                          $("#categoriaPlano").append(item.nome + "<br>");
                        });


                        $("#btnRejeitar").confirm({
                            title:"Rejeitar Solicitação",
                            text:"Deseja realmente rejeitar essa consulta ?",
                            confirm: function(button) {
                                Agenda.rejeitar(idAgendamentoSelecionado);
                            },
                            cancel: function(button) {

                            },
                            confirmButton: "Sim",
                            cancelButton: "Não"
                        });

                        if (calEvent.agendamento.dataConfirmacaoProfissional != null){
                            $("#btnAprovar").hide();
                        }else{
                            $("#btnAprovar").show();
                            $("#btnAprovar").confirm({
                                title:"Confirmar Solicitação",
                                text:"Confirma essa consulta ?",
                                confirm: function(button) {
                                    Agenda.aprovar(idAgendamentoSelecionado);
                                },
                                cancel: function(button) {

                                },
                                confirmButton: "Sim",
                                cancelButton: "Não"
                            });
                        }


                        $("#modalDetalhes").modal();

                    },
                    viewRender: function( view, element ){
                        Agenda.refreshEvents(view);
                    }
                });

            }

            $(".tab-content .tab-pane").removeClass("active");
            $(".tab-content .tab-pane").first().addClass("active");
           /* $( ".fc-next-button" ).click(function() {
                Agenda.cleanEvents();
                Agenda.getDate();

            });
            $( ".fc-prev-button" ).click(function() {
                Agenda.cleanEvents();
                Agenda.getDate();
            });*/

        },
        buildEvents: function (calendarioClinica){
            var events = [];
            if (calendarioClinica != null){

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
            }
            return events;
        },
        cleanEvents: function() {
            var idClinicaAtiva = Agenda.getIdClinicaAtiva();
            console.log("removendo todos os enventos da clinica = " + idClinicaAtiva);
            Agenda.getCalendar().fullCalendar('removeEvents');
        },
        getIdClinicaAtiva: function () {
            return $(".nav-tabs .active a ").attr("href").replace("#calendar", "");
        },
        getCalendar: function(){
            return $('#calendar' + Agenda.getIdClinicaAtiva());
        },
        getDate: function(){
            var moment = Agenda.getCalendar().fullCalendar('getDate');
            console.log("The current date of the calendar is " + moment.format());
        },
        getEvents: function(idProfissional,idClinica,dataIni, dataFim){
            Utils.waiting();
            $.ajax({
                url: '/profissional/agenda/json',
                type: 'post',
                data: "idProfissional="+idProfissional+"&idClinica="+idClinica+"&dataIni="+dataIni+"&dataFim="+dataFim,
                success: function(data){
                    Agenda.cleanEvents();
                    Agenda.getCalendar().fullCalendar('addEventSource',  Agenda.buildEvents(data.calendariosClinicas[0]));
                    Utils.waitingClose();
                },
                error: function(){
                    console.log("Erro ao buscar os eventos");
                    Utils.waitingClose();
                    throw "Erro ao buscar os eventos";
                }
            });
        },
        rejeitar: function (idAgendamento) {
            $.ajax({
                url: '/profissional/agenda/rejeitar',
                type: 'post',
                data: "idAgendamento="+idAgendamento+"&msg=",
                success: function(data){
                    var view = Agenda.getCalendar().fullCalendar('getView');
                    Agenda.refreshEvents(view);
                },
                error: function(data){
                    console.log("Error: " + data);
                }
            });


        },
        aprovar: function (idAgendamento) {
            $.ajax({
                url: '/profissional/agenda/confirmar',
                type: 'post',
                data: "idAgendamento="+idAgendamento,
                success: function(data){
                    var view = Agenda.getCalendar().fullCalendar('getView');
                    Agenda.refreshEvents(view);
                },
                error: function(data){
                    console.log("Error: " + data);
                }
            });


        },
        refreshEvents: function (view) {
            var idClinica = Agenda.getIdClinicaAtiva();
            Agenda.getEvents(idProfissional, idClinica, view.start.format("YYYY-MM-DD"), view.end.format("YYYY-MM-DD"));
        },
    }
}();























