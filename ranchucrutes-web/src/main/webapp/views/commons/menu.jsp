<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top navbar-shrink">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="../" class="navbar-brand" style="margin-top:-10px"><img src="../static/img/logo_mini_white.png" class="img-responsive" alt="Agendee"> </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <li>
                    <a class="page-scroll" href="../">Home</a>
                </li>
                <c:if test="${sessionScope.loginSession == null}">
                    <li>
                        <a class="page-scroll" href="/profissional/login">Acessar</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="/profissional/cadastro" >Cadastro</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loginSession != null}">
                    <li>
                        <a class="page-scroll" href="/profissional/agenda"><i class="fa fa-user-md"></i> ${sessionScope.loginSession.primeiroNome}</a>
                    </li>
                </c:if>
                <li>
                    <a class="page-scroll" href="/#paciente">Paciente</a>
                </li>
                <li>
                    <a class="page-scroll" href="/#profissional">Profissional</a>
                </li>
                <!--
                <li>
                    <a class="page-scroll" href="/#parceiros">Parceiros</a>
                </li>
                -->
                <li>
                    <a class="page-scroll" href="/#contatos">Contatos</a>
                </li>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>