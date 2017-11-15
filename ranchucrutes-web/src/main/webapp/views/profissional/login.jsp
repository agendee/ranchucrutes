<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <link rel="stylesheet" href="static/css/font-awesome.min.css">
	<link rel="stylesheet" href="static/css/animate.min.css">
	<link rel="stylesheet" href="static/css/et-line-font.css">


    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
	<link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/profissional/login.css" rel="stylesheet">
    <link href="/static/css/menu.css" rel="stylesheet">
	
</head>
<body class="login-bg">
    <jsp:include page="/views/commons/menu_interna.jsp" />
    <div class="col-xs-12 container content">
        <section>
            <div>
                <div class="row">
                    <div class="col-md-12 login">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form id="formLogin" role="form" action="/auth/profissional" method="POST">
                                    <fieldset>
                                        <div class="row">
                                            <div class="center-block img-login">
												<div class="text-center">
													<img src="/static/img/password.png" alt="">
												</div>
                                                <div class="space"></div>
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
        <footer>
		<div class="space"></div>
		<div id="rodape" class="text-center">
			<img src="/static/img/logo_mini_white.png" />&nbsp;&nbsp;&nbsp; Seu portal de agendamento online</i>
		</div>
	</footer>
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