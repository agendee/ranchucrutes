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
                    <a class="page-scroll" href="/profissional/agenda">Agenda</a>
                </li>
                  <li>
                    <a class="page-scroll" href="/profissional/solicitacao">Solicitacões <i class="fa fa-plus" aria-hidden="true"></i></a>
                </li>
                
                <li>
                    <a class="page-scroll" href="/profissional/admin">Cadastro</a>
                </li>
                <li>
                    <a class="page-scroll" href="/profissional/horario">Horários</a>
                </li>

                <li>
                    <a class="page-scroll" href="/auth/sair">Sair</a>
                </li>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>