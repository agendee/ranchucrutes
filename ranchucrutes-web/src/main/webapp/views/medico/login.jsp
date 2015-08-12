<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
</head>
<body>
    <jsp:include page="/views/commons/menu.jsp" />

    <div class="col-xs-12">
        <!-- Portfolio Modal 2 -->
        <section id="medicoLogin">
            <div class="col-sm-offset-2 col-lg-9">
                <div class="col-sm-6">
                    <h4>Login do Médico</h4>
                      <form class="form-signin">
                            <label for="inputEmail" class="sr-only">CRM</label>
                            <input type="number" id="inputCrm" class="form-control" placeholder="CRM" required="" autofocus="">
                            <label for="inputPassword" class="sr-only">Senha</label>
                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                            <div class="checkbox">
                              <label>
                                <input type="checkbox" value="remember-me"> Lembrar senha
                              </label>
                            </div>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
                      </form>
                </div>
                <div class="col-sm-6">
                    <h4>Sou novo e quero usar o marcmed</h4>
                    <a href="/medico/cadastro" class="btn btn-primary" type="button">Cadastrar</a>
                </div>
            </div>

        </section>
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
</body>
</html>