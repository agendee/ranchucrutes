<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags"%>
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
             <c:if test="${errorMessage != null}">
                            <div class="alert alert-danger" role="alert">${errorMessage}</div>
              </c:if>
            <c:forEach var="agendamento" items="${agendamentos}">
            <div id="modalDetalhes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            
                <div id="agendamento${agendamento.id}">
            
                <div class="" role="document">
                    <div class="">
                        <div class="" >
                            <div class="date">
                            <i class="fa fa-calendar-o" aria-hidden="true"></i><span id="dataAgenda">${agendamento.profissional.nome}</span></div>
                            <p class="dadosPaciente">Nome do Paciente: ${agendamento.paciente.nome} <span id="nomePaciente"></span></p>
                            <p class="dadosPaciente">Data: ${agendamento.dataAgendamento} <span id="nomePaciente"></span></p>
                            <p class="dadosPaciente">Telefone: ${agendamento.paciente.telefone}<span id="telefone"></span></p></h6>
                            <p class="dadosPaciente">Especialidade: ${agendamento.profissional.espec}<span id="email"></span></p></h6>
                        </div>
                        <div class="">
                            <button type="button"  class="btnRejeitar btn btn-danger "  value=${agendamento.id} >Rejeitar Solicitação</button>
                            <button type="button"  class="btnAprovar btn btn-success " value=${agendamento.id}; >Aprovar Solicitação</button>
                        </div>
                    </div>
                </div>
            </div>
				<br/><br/><br/>
				</div>
            </c:forEach>
            

          <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src='/static/js/libs/moment.min.js'></script>
    <script src='/static/js/libs/fullcalendar.min.js'></script>
	<script src='/static/js/libs/locale/pt-br.js'></script>
    <script src="/static/js/profissional/solicitacao.js"></script>
</body>
<script>
$(document).ready(function() {
	
	Solicitacao.init();
    var viewport = document.querySelector("meta[name=viewport]");
    if($(window).width() < 480){
        viewport.setAttribute('content', 'width=480, maximum-scale=1.0, user-scalable=no');
    }else{
        viewport.setAttribute('content', 'width=device-width, maximum-scale=1, user-scalable=no');
    }
    
    
});







</script>
</html>