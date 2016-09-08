<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
    <link href='/static/css/profissional/agenda.css' rel='stylesheet' />
    <link href='/static/css/libs/jquery-ui.min.css' rel='stylesheet' />
    <link href='/static/css/libs/fullcalendar.css' rel='stylesheet' />
    <link href='/static/css/libs/fullcalendar.print.css' rel='stylesheet' media='print' />
</head>
<body>
    <jsp:include page="/views/commons/menu_admin.jsp" />
    <div class="col-xs-12 container content">
        <section style="margin-top:80px;">
            <ul class="nav nav-tabs">
                <c:set var="primeira" value="true"></c:set>
                <c:forEach var="calendario" items="${calendario.calendariosClinicas}">
                    <li class="<c:if test="${primeira}">active</c:if>"><a href="#calendar${calendario.clinicaVo.id}" data-toggle="tab">${calendario.clinicaVo.nome}</a></li>
                    <c:set var="primeira" value="false"></c:set>
                </c:forEach>
            </ul>
               <hr>
            <div class="tab-content clearfix">
                <c:set var="primeira" value="true"></c:set>
                <c:forEach var="calendario" items="${calendario.calendariosClinicas}">
                    <div class="tab-pane active" id="calendar${calendario.clinicaVo.id}"></div>
                    <c:set var="primeira" value="false"></c:set>
                </c:forEach>
            </div>


            <div class="modal fade" id="modalDetalhes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="modalTitle">Detalhes da Consulta</h4>
                        </div>
                        <div class="modal-body" >
                            <p class="dadosPaciente">Consulta agendada para: <span id="dataAgenda"></span></p>
                            <p class="dadosPaciente">Nome do Paciente: <span id="nomePaciente"></span></p>
                            <p class="dadosPaciente">Email: <span id="email"></span></p></h6>
                            <p class="dadosPaciente">Telefone: <span id="telefone"></span></p></h6>
                            <p class="dadosPaciente">Categoria do Plano: <span id="categoriaPlano"></span></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal" style="float: left;">Fechar</button>
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
    <script src='/static/js/libs/lang-all.js'></script>
    <script src="/static/js/profissional/agenda.js"></script>
    <script src="/static/js/libs/cbpAnimatedHeader.js"></script>
</body>
<script>
    idProfissional = ${idProfissional};
    var calendario = jQuery.parseJSON('${calendarioJson}');
    Agenda.init(calendario);
    $('li > a').filter(function(index){
        if (this.href.indexOf("agenda") > 0 ) {
            $(this).parent().addClass('active');
        }
    });

</script>
</html>