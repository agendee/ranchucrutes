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
    <link href="/static/css/profissional/horario.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/views/commons/menu_admin.jsp" />
    <div class="col-xs-12 container">

        <form:form id="formCadastro" class="form-horizontal" action="/profissional/horario/save" method="POST" role="form" modelAtribute="form">
            <input type="hidden" name="profissional.idLogin" value="${form.profissional.idLogin}"/>
            <div class="col-md-offset-3 col-md-6">
                <section class="form">
                    <!-- transformar isso aqui em uma tag -->
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert">${errorMessage}</div>
                    </c:if>

                    <c:if test="${successMessage != null}">
                         <div class="alert alert-success" role="alert">${successMessage}</div>
                    </c:if>


                     <c:if test="${form.clinicas.size() == 0}">
                         <h:info-panel title="Nenhum clinica cadastrada" msg="Nenhuma clínica cadastrada. <br> Acesse seu cadastro e adicione suas clínicas, acesse seu cadastro <a class='btn btn-success' href='/profissional/admin'>aqui</a>"/>
                     </c:if>
                     <c:set var="countClinica" value="0"/>
                     <c:set var="countHorario" value="0"/>
                     <c:forEach var="clinica" items="${form.clinicas}">

                        <input type="hidden" name="clinicas[${countClinica}].idAgenda" value="${clinica.idAgenda}"/>
                        <div id="accordionHorario" class="panel-group">
                             <div id="horario${countClinica}${countHorario}" class="panel panel-default" >
                                 <div class="panel-heading">
                                     <h4 class="panel-title">
                                         <a data-toggle="collapse" data-parent="#accordionHorario" href="#collapseHorario${countClinica}" aria-expanded="true" class="collapsed nomeTitle">
                                             ${clinica.nome}
                                         </a>
                                     </h4>
                                 </div>
                                 <div id="collapseHorario${countClinica}" class="form-group collapse in" style="margin-top:15px;" <c:if test="${oculto}"> style="display:none;" </c:if>>
                                     <div class="form-group">
                                        <label class="col-md-8 control-label">Quais horários você quer abrir? <a id="btnAddHorario${countClinica}" class="btn btn-success" ><i class="fa fa-plus"></i></a></label>
                                     </div>

                                     <c:forEach var="h" items="${clinica.agendaHorarios}">
                                         <h:horario indexClinica="${countClinica}" indexHorario="${countHorario}" horario="${h}"/>
                                         <c:set var="countHorario" value="${countHorario + 1}"/>
                                     </c:forEach>
                                     <c:if test="${clinica.agendaHorarios == null || clinica.agendaHorarios.size() == 0}">
                                         <div id="accordionHorario" class="panel-group">
                                             <h:horario indexClinica="${countClinica}" indexHorario="0"/>
                                             <c:set var="countHorario" value="${countHorario + 1}"/>
                                         </div>
                                     </c:if>
                                     <c:set var="countClinica" value="${countClinica+1}"/>
                                 </div>
                             </div>
                        </div>
                     </c:forEach>





                    <div class="form-group">
                        <div class="col-md-9 col-sm-offset-3">
                            <button type="submit" class="btn btn-primary">Enviar</button>
                        </div>
                    </div>
                </section>
            </div>
        </form:form>

        <div id="templateDivHorario" style="display:none">
                <h:horario indexClinica="__INDEX" indexHorario="__2INDEX" oculto="true"/>
        </div>

        <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script>
        var countClinica = ${countClinica};
        var countHorario = ${countHorario};
    </script>

    <script src="/static/js/ws/ranchucrutes-ws-client.js"></script>
    <script src="/static/js/commons/utils.js"></script>
    <script src="/static/js/profissional/horario.js"></script>
    <script src="/static/js/libs/cbpAnimatedHeader.js"></script>

    <script>
        $('li > a').filter(function(index){
            if (this.href.indexOf("horario") > 0 ) {
                $(this).parent().addClass('active');
            }
        });
    </script>

</body>
</html>