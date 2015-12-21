<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
    <link href="/static/css/profissional/login.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/views/commons/menu.jsp" />
    <div class="col-xs-12 container">
        <section>
            <div>
                <div class="row">
                    <div class="col-sm-6 col-md-3 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <strong> Acesso ao portal do profissional</strong>
                            </div>
                            <div class="panel-body">
                                <form id="formLogin" role="form" action="/auth/profissional" method="POST">
                                    <fieldset>
                                        <div class="row">
                                            <div class="center-block img-login">
                                                <img class="profile-img" src="/static/img/doctor.png" alt="">
                                                <c:if test="${errorMessage != null}">
                                                    <div class="alert alert-danger" role="alert" style="margin:10px;">${errorMessage}</div>
                                                </c:if>

                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-user-md"></i>
                                                        </span>
                                                        <input class="form-control" placeholder="CRM ou Email" name="login" value="${form.login}" type="text" required autofocus>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-key"></i>
                                                        </span>
                                                        <input class="form-control" placeholder="Senha" name="senha" type="password" required value="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Entrar">
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="panel-footer ">
                                Você não tem cadastro? <a href="/profissional/cadastro" > Cadastre-se aqui </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src="/static/js/commons/utils.js"></script>
    <script src="/static/js/profissional/login.js"></script>
    <script src="/static/js/libs/cbpAnimatedHeader.js"></script>
    <script>
        $('li > a').filter(function(index){
            if (this.href.indexOf("login") > 0 ) {
                $(this).parent().addClass('active');
            }
        });
    </script>
</body>
</html>