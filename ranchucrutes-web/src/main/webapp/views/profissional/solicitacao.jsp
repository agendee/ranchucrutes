<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
	<link href="/static/css/profissional/painel.css" rel="stylesheet"/>
    <link href='/static/css/profissional/agenda.css' rel='stylesheet' />
    <link href='/static/css/libs/jquery-ui.min.css' rel='stylesheet' />
    <link href='/static/css/libs/fullcalendar.css' rel='stylesheet' />
    <link href='/static/css/libs/fullcalendar.print.css' rel='stylesheet' media='print' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <jsp:include page="/views/commons/menu_admin_custom.jsp" />
    <div class="fluid-container">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/profissional/solicitacao">Painel</a></li>
			<li class="breadcrumb-item active">Solicitações de Agendamentos</li>
		</ol>
        <section>
            <div id="modalDetalhes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="" role="document">
                    <div class="">
                        <div class="" >
                            <div class="date">
                            <i class="fa fa-calendar-o" aria-hidden="true"></i><span id="dataAgenda">Drº Andre Ferreira</span></div>
                            <p class="dadosPaciente">Nome do Paciente: Claudio Soares <span id="nomePaciente"></span></p>
                            <p class="dadosPaciente">Data: 09/06/2018 - 08:00 <span id="nomePaciente"></span></p>
                            <p class="dadosPaciente">Telefone: (11)99698959<span id="telefone"></span></p></h6>
                            <p class="dadosPaciente">Especialidade: Cardiologia<span id="email"></span></p></h6>
                        </div>
                        <div class="">
                            <button type="button" id="btnRejeitar" class="btn btn-danger" data-dismiss="modal">Rejeitar Solicitação</button>
                            <button type="button" id="btnAprovar" class="btn btn-success" data-dismiss="modal">Aprovar Solicitação</button>
                        </div>
                    </div>
                </div>
            </div>
				</br></br></br>
               <div id="modalDetalhes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="" role="document">
                    <div class="">
                        <div class="" >
                            <div class="date">
                            <i class="fa fa-calendar-o" aria-hidden="true"></i><span id="dataAgenda">Drº Claudio Moraes</span></div>
                            <p class="dadosPaciente">Nome do Paciente: Andre Xavier<span id="nomePaciente"></span></p>
                            <p class="dadosPaciente">Data: 09/06/2018 - 09:00 <span id="nomePaciente"></span></p>
                            <p class="dadosPaciente">Telefone: (11)999857489<span id="telefone"></span></p></h6>
                            <p class="dadosPaciente">Especialidade: Endocrinologia<span id="email"></span></p></h6>
                        </div>
                        <div class="">
                            <button type="button" id="btnRejeitar" class="btn btn-danger" data-dismiss="modal">Rejeitar Solicitação</button>
                            <button type="button" id="btnAprovar" class="btn btn-success" data-dismiss="modal">Aprovar Solicitação</button>
                        </div>
                    </div>
                </div>
            </div>
            
            

        </section>

        <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src='/static/js/libs/moment.min.js'></script>
    <script src='/static/js/libs/fullcalendar.min.js'></script>
	<script src='/static/js/libs/locale/pt-br.js'></script>
    <script src="/static/js/profissional/agenda.js"></script>
    <script src="/static/js/libs/cbpAnimatedHeader.js"></script>
</body>
<script>
$(document).ready(function() {
    var viewport = document.querySelector("meta[name=viewport]");
    if($(window).width() < 480){
        viewport.setAttribute('content', 'width=480, maximum-scale=1.0, user-scalable=no');
    }else{
        viewport.setAttribute('content', 'width=device-width, maximum-scale=1, user-scalable=no');
    }
});
</script>
<script>
    idProfissional = ${idProfissional};
    var calendario = jQuery.parseJSON('${calendarioJson}');
    Agenda.init(calendario);
    $('li > a').filter(function(index){
        if (this.href.indexOf("agenda") > 0 ) {
            $(this).parent().addClass('active');
        }
    });
	$.ajax({
		url: "http://rest.agendee.com.br/profissional/" + idProfissional,
		jsonp: "callback",
		dataType: "jsonp",
		success: function( response ) {
			$('.picture img').attr('src','http://agendee.com.br/f/' + response.profissional.foto);
		}
	});
</script>
</html>