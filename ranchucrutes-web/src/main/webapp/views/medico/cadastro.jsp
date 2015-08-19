<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
    <link rel="stylesheet" href="/static/css/libs/chosen.css">
</head>
<body>
    <jsp:include page="/views/commons/menu.jsp" />
    <div class="col-md-12 cadastro container">
        <form id="formCadastro" class="form-horizontal" action="/medico/save" method="POST">
            <div class="col-md-offset-2 col-md-8">
                <section id="dadosBasicos">

                    <div class="panel panel-default">
                      <div class="panel-body">
                        <h4>Cadastre-se</h4>
                        <c:if test="${errorMessage != null}">
                            <div class="alert alert-danger" role="alert">${errorMessage.errorMessage}</div>
                        </c:if>
                      </div>

                      <div class="panel-footer">

                            <div class="form-group">
                                <label class="col-md-3 control-label">Nome(*): </label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="nome" placeholder="Nome Completo" maxlength="80" value="${form.nome}" required/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-3 control-label">CRM(*):</label>
                                <div class="col-md-6">
                                    <input type="number" class="form-control" name="crm" placeholder="CRM" maxlength="10" value="${form.crm}" required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">CPF(*):</label>
                                <div class="col-md-6">
                                    <input type="number" class="form-control" name="cpf" placeholder="CPF" maxlength="11" value="${form.cpf}" required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">Especialidade(*):</label>
                                <div class="col-md-8">
                                    <select id="idEspecialidade" name="idEspecialidade" data-placeholder="Selecione suas especialidades" multiple class="form-control chosen-select" required></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">Celular:</label>
                                <div class="col-md-3">
                                    <input type="number" class="form-control" name="ddd" placeholder="DDD" maxlength="2" value="${form.ddd}" />
                                </div>
                                <div class="col-md-5">
                                    <input type="number" class="form-control" name="celular" placeholder="Celular" maxlength="9" value="${form.celular}" />
                                </div>
                            </div>

                           <div class="form-group">
                               <label class="col-md-3 control-label">Email(*):</label>
                               <div class="col-md-9">
                                   <input type="text" class="form-control" name="email" placeholder="Email" maxlength="100" value="${form.email}" required/>
                               </div>
                           </div>

                           <div class="form-group">
                              <label class="col-md-3 control-label">Senha(*):</label>
                              <div class="col-md-4">
                                  <input type="password" class="form-control" name="senha" placeholder="Senha" maxlength="10" required />
                              </div>
                          </div>
                           <div class="form-group">
                              <label class="col-md-3 control-label">Confirmação(*):</label>
                              <div class="col-md-4">
                                  <input type="password" class="form-control" name="confirmacao" placeholder="Repita a Senha" maxlength="10" required/>
                              </div>
                          </div>

                            <div class="form-group">
                                <div class="col-md-9 col-sm-offset-3">
                                    <button type="submit" class="btn btn-primary">Enviar</button>
                                </div>
                            </div>

                      </div>
                    </div>
                </session>
            </div>
        </form>
    <jsp:include page="/views/commons/rodape.jsp" />
    </div>

    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src="/static/js/ws/ranchucrutes-ws-client.js"></script>
    <script src="/static/js/commons/utils.js"></script>
    <script src="/static/js/medico/cadastro.js"></script>
    <script>
        var especSelected = [];
        <c:forEach var="esp" items="${form.idEspecialidade}">
            especSelected.push("${esp}");
        </c:forEach>
    </script>

</body>
</html>