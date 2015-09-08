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
    <link href="/static/css/medico/admin.css" rel="stylesheet"/>
    <link href="/static/css/libs/jasny-bootstrap.css" rel="stylesheet"/>
</head>
<body>
    <jsp:include page="/views/commons/menu_admin.jsp" />
    <div class="col-xs-12 container">

        <form:form id="formCadastro" class="form-horizontal" action="/medico/update" method="POST" role="form" modelAtribute="form" enctype="multipart/form-data" acceptcharset="UTF-8">
            <div class="col-md-offset-3 col-md-6">
                <section class="form">

                    <!-- transformar isso aqui em uma tag -->
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert">${errorMessage}</div>
                    </c:if>

                    <c:if test="${successMessage != null}">
                         <div class="alert alert-success" role="alert">${successMessage}</div>
                    </c:if>

                    <!-- DADOS DO MÉDICO -->
                    <h4>Meu cadastro</h4>
                    <div class="panel-group" id="accordionDadosPessoais">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionDadosPessoais" href="#collapseDadosPessoais">Dados Pessoais</a>
                                    <input type="hidden" name="medico.idLogin" value="${form.medico.idLogin}"/>
                                    <input type="hidden" name="medico.foto" value="${form.medico.foto}"/>
                                </h4>
                            </div>
                            <div class="panel-collapse collapse in" id="collapseDadosPessoais">
                                <div class="panel-body">


                                    <div class="row">
                                        <div class="fileinput fileinput-new center-block" data-provides="fileinput">
                                          <div class="fileinput-new thumbnail center-block" style="width: 200px; height: 150px;">
                                            <img class="onerror"  onerror="this.onerror=null;this.src='/static/img/doctor/user.png';" src="/f/${form.medico.foto}" alt="Sua foto aparecerá na pesquisa de médicos.">
                                          </div>
                                          <div class="fileinput-preview fileinput-exists thumbnail center-block" style="max-width: 200px; max-height: 200px;"></div>
                                          <div>
                                            <center>
                                            <span class="btn btn-default btn-file"><span class="fileinput-new">Adicionar Foto</span><span class="fileinput-exists">Alterar Foto</span><input type="file" name="file"></span>
                                            <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remover</a>
                                            </center>
                                          </div>
                                        </div>
                                    </div>



                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Nome:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="medico.nome" placeholder="Nome Completo" maxlength="80" value="${form.medico.nome}" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-3 control-label">CRM:<font style="color: rgb(169, 68, 66);">*</font></label>
                                        <div class="col-md-6">
                                            <input type="number" class="form-control" name="medico.crm" placeholder="CRM" maxlength="10" value="${form.medico.crm}" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-3 control-label">CPF:<font style="color: rgb(169, 68, 66);">*</font></label>
                                        <div class="col-md-6">
                                            <input type="number" class="form-control" name="medico.cpf" placeholder="CPF" maxlength="11" value="${form.medico.cpf}" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Especialidade:<font style="color: rgb(169, 68, 66);">*</font></label>
                                        <div class="col-md-8">
                                            <select id="idEspecialidade" name="medico.idEspecialidade" data-placeholder="Selecione suas especialidades" multiple class="form-control chosen-select" required></select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Celular:</label>
                                        <div class="col-md-3">
                                            <input type="number" class="form-control" name="medico.ddd" placeholder="DDD" maxlength="2" value="${form.medico.ddd}" />
                                        </div>
                                        <div class="col-md-5">
                                            <input type="number" class="form-control" name="medico.celular" placeholder="Celular" maxlength="9" value="${form.medico.celular}" />
                                        </div>
                                    </div>

                                   <div class="form-group">
                                       <label class="col-md-3 control-label">Email:<font style="color: rgb(169, 68, 66);">*</font></label>
                                       <div class="col-md-9">
                                           <input type="text" class="form-control" name="medico.email" placeholder="Email" maxlength="100" value="${form.medico.email}" required/>
                                       </div>
                                   </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- FIM DADOS DO MEDICO -->



                    <!-- CLINICAS -->
                    <h4>Minhas Clinicas <a id="btnAddClinica" class="btn btn-success" ><i class="fa fa-plus"></i></a></h4>

                    <div id="accordionClinica" class="panel-group">
                        <c:set var="countClinica" value="0"/>
                        <c:forEach var="clinica" items="${form.clinicas}">
                            <h:clinica index="${countClinica}" clinica="${clinica}"/>
                            <c:set var="countClinica" value="${countClinica+1}"/>
                        </c:forEach>
                    </div>
                    <!-- FIM DADOS DA CLINICA -->


                    <div class="form-group" style="margin-bottom:100px;">
                        <div class="col-md-9 col-sm-offset-3">
                            <button type="submit" class="btn btn-primary">Enviar</button>
                        </div>
                    </div>

                </section>
            </div>
        </form:form>


        <!-- TEMPLATE DE CLINICA -->
        <h:clinica index="__INDEX" oculto="true"/>
        <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src="/static/js/ws/ranchucrutes-ws-client.js"></script>
    <script src="/static/js/commons/utils.js"></script>
    <script src="/static/js/medico/admin.js"></script>
    <script src="/static/js/libs/jasny-bootstrap.min.js"></script>

    <script>
        var especSelected = [];
        <c:forEach var="esp" items="${form.medico.idEspecialidade}">
            especSelected.push("${esp}");
        </c:forEach>
        var countClinica = ${countClinica};
    </script>

</body>
</html>