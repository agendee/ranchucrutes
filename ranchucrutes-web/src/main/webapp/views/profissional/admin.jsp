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
    <link href="/static/css/profissional/admin.css" rel="stylesheet"/>
	<link href="/static/css/profissional/painel.css" rel="stylesheet"/>
    <link href="/static/css/libs/jasny-bootstrap.css" rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <jsp:include page="/views/commons/menu_admin_custom.jsp" />
    <div class="fluid-container">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/profissional/agenda">Painel</a></li>
			<li class="breadcrumb-item active">Meu cadastro</li>
		</ol>

        <form:form id="formCadastro" class="form-horizontal" action="/profissional/update" method="POST" role="form" modelAtribute="form" enctype="multipart/form-data" acceptcharset="UTF-8">
            <div class="">
                <section class="form">
                    <!-- transformar isso aqui em uma tag -->
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert">${errorMessage}</div>
                    </c:if>

                    <c:if test="${successMessage != null}">
                         <div class="alert alert-success" role="alert">${successMessage}</div>
                    </c:if>
					
                    <!-- DADOS DO PROFISSIONAL -->
                    <div class="panel-group" id="accordionDadosPessoais">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <h4>
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionDadosPessoais" href="#collapseDadosPessoais">Dados Pessoais</a>
                                    <input type="hidden" name="profissional.idLogin" value="${form.profissional.idLogin}"/>
                                    <input type="hidden" name="profissional.foto" value="${form.profissional.foto}"/>
                                     <input type="hidden" name="profissional.atendente" value="${form.profissional.atendente}"/>
                                    	
                                </h4>
                            </div>
                            <div class="panel-collapse collapse in" id="collapseDadosPessoais">
                                <div class="panel-footer">


                                    <div class="row">
                                        <div class="fileinput fileinput-new center-block" data-provides="fileinput">
                                          <div class="fileinput-new thumbnail center-block" style="width: 200px; height: 150px;">
                                            <img class="onerror"  onerror="this.onerror=null;this.src='/static/img/doctor/user.png';" src="/f/${form.profissional.foto}" alt="Sua foto aparecerá na pesquisa de profissionais.">
                                          </div>
                                          <div class="fileinput-preview fileinput-exists thumbnail center-block" style="max-width: 200px; max-height: 200px;"></div>
                                          <div>
                                            <center>
                                            <span class="btn btn-default btn-file"><span class="fileinput-new">Adicionar Foto</span><span class="fileinput-exists">Alterar Foto</span><input type="file" name="file"></span>
                                            <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remover</a>
                                            </center>
											<div class="space"></div>
                                          </div>
                                        </div>
                                    </div>


								<div class="row">
									
									<div class="col-md-6">
                                    <div class="form-group">
                                        <label class="col-md-12">Nome:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" name="profissional.nome" placeholder="Nome Completo" maxlength="80" value="${form.profissional.nome}" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">Número Registro:<font style="color: rgb(169, 68, 66);">*</font></label>
                                        <div class="col-md-12">
                                            <input type="number" class="form-control" name="profissional.numeroRegistro" placeholder="0000" maxlength="10" value="${form.profissional.numeroRegistro}" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">CPF:<font style="color: rgb(169, 68, 66);">*</font></label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" name="profissional.cpf" placeholder="CPF" maxlength="11" value="${form.profissional.cpf}" required/>
                                        </div>
                                    </div>
									</div>

									<div class="col-md-6">
                                    <div class="form-group">
                                        <label class="col-md-12">Especialidade:<font style="color: rgb(169, 68, 66);">*</font></label>
                                        <div class="col-md-12">
                                            <select id="idEspecialidade" name="profissional.idEspecialidade" data-placeholder="Selecione suas especialidades" multiple class="form-control chosen-select" required></select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">Celular:</label>
                                        <div class="col-md-2">
                                            <input type="number" class="form-control" name="profissional.ddd" placeholder="DDD" maxlength="2" value="${form.profissional.ddd}" />
                                        </div>
                                        <div class="col-md-10">
                                            <input type="number" class="form-control" name="profissional.celular" placeholder="Celular" maxlength="9" value="${form.profissional.celular}" />
                                        </div>
                                    </div>

                                   <div class="form-group">
                                       <label class="col-md-12">Email:<font style="color: rgb(169, 68, 66);">*</font></label>
                                       <div class="col-md-6">
                                           <input type="text" class="form-control" name="profissional.email" placeholder="Email" maxlength="100" value="${form.profissional.email}" required/>
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label class="col-md-12">Senha:<font style="color: rgb(169, 68, 66);">*</font></label>
                                       <div class="col-md-6">
                                		  <input type="password" id="senha" class="form-control" name="profissional.senha" value="${form.profissional.senha}" placeholder="Senha"  required />
                                     
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label class="col-md-12">Confirmação:<font style="color: rgb(169, 68, 66);">*</font></label>
                                       <div class="col-md-6">
                                          <input type="password" class="form-control" name="confirmacao" placeholder="Repita a Senha"  value="${form.profissional.senha}" required/>
                                       </div>
                                   </div>
                                   
                                   
                                   
								   </div>
								   
								</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- FIM DADOS DO PROFISSIONAL -->
				<div class="text-right" style="margin-bottom:50px;">
                    <button type="submit" class="btn btn-primary"><i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar</button>
                </div>


                    <!-- CLINICAS -->
                    <div class="add-clinica">	
						<h4>Minhas Clinicas <a id="btnAddClinica" class="btn btn-success btn-plus" ><i class="fa fa-plus"></i></a></h4>
					</div>
					
					<div class="space"></div>

                    <div id="accordionClinica" class="panel-group">
                        <c:set var="countClinica" value="0"/>
                        <c:forEach var="clinica" items="${form.clinicas}">
                            <h:clinica index="${countClinica}" clinica="${clinica}"/>
                            <c:set var="countClinica" value="${countClinica+1}"/>
                        </c:forEach>
                    </div>
                    <!-- FIM DADOS DA CLINICA -->

                </section>
				
				<div class="text-right" style="margin-bottom:50px;">
                    <button type="submit" class="btn btn-primary"><i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar</button>
                </div>
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
    <script src="/static/js/profissional/admin.js"></script>
    <script src="/static/js/libs/jasny-bootstrap.min.js"></script>
    <script src="/static/js/libs/cbpAnimatedHeader.js"></script>
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
        var especSelected = [];
        <c:forEach var="esp" items="${form.profissional.idEspecialidade}">
            especSelected.push("${esp}");
        </c:forEach>
        var countClinica = ${countClinica};
        $('li > a').filter(function(index){
            if (this.href.indexOf("admin") > 0 ) {
                $(this).parent().addClass('active');
            }
        });
    </script>

</body>
</html>