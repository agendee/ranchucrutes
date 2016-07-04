<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">

<head>
    <meta name="google-site-verification" content="9fRlro40UeOx7DXPoC-xzH1vS0ulV9mJAad3lLBCI6k" />
    <!-- Custom CSS -->

	<link rel="stylesheet" href="static/css/font-awesome.min.css">
	<link rel="stylesheet" href="static/css/animate.min.css">
	<link rel="stylesheet" href="static/css/et-line-font.css">

    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
    <link href="/static/css/style.css" rel="stylesheet">


</head>

<body id="page-top" class="index">
<jsp:include page="/views/commons/menu_home.jsp" />
<!-- Header -->


<header id="home">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12">


                <h1> BEM VINDO AO <span style="color:#00A4E6">AGENDEE<span> </h1>

                <h1> ONDE PROFISSIONAIS E PACIENTES SE ENCONTRAM </h1>
                <hr>


                <a href="#profissional" class="smoothScroll btn btn-default">PROFISSIONAL</a>
                <a href="#paciente" class="smoothScroll btn btn-default">PACIENTE</a>

            </div>

        </div>

    </div>

</header>

<section id="profissional" >
    <div class="container">
    		<div class="row">
    		    <div class="col-lg-12 text-center">
                    <h2 class="section-heading">PROFISSIONAL</h2>
                </div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.6s">
    				<i class="icon-cloud medium-icon"></i>
    					<h3>Faça um cadastro em nosso sistema</h3>
    					<hr>
    					<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteu sunt in culpa qui officia.</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.9s">
    				<i class="icon-mobile medium-icon"></i>
    					<h3>Acesse o portal</h3>
    					<hr>
    					<p>Digital Team is free responsive Bootstrap v3.3.5 layout from <a rel="nofollow" href="http://www.tooplate.com" target="_parent">Tooplate</a>. Images are from <a rel="nofollow" href="http://pixabay.com" target="_parent">Pixabay</a> free photo website.</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="icon-laptop medium-icon"></i>
    					<h3>Gerencie a agenda de suas clinicas</h3>
    					<hr>
    					<p>You can edit and use this template for your websites. Please tell your friends about Tooplate. Thank you for visiting our website.</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="icon-compass medium-icon"></i>
    					<h3>Agenda mensal</h3>
    					<hr>
    					<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteu sunt in culpa qui officia.</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="icon-chat medium-icon"></i>
    					<h3>Agenda semanal</h3>
    					<hr>
    					<p>Tenha uma visão semanal de como anda sua agenda </p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="icon-browser medium-icon"></i>
    					<h3>Integrado aos melhores sistemas do mercado</h3>
    					<hr>
    					<p>Clinica nas nuvens e NetDente.</p>
    			</div>
    		</div>
    	</div>
</section>

<!-- About Section -->
<section id="paciente">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Paciente, veja como funciona</h2>
                <h3 class="section-subheading text-muted">É muito simples</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <ul class="timeline">
                    <li>
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/1.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">1 - Encontre um profissional</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Encontre o profissional mais próximo de você </p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/2.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">2 - Veja a sua qualificação</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Antes de marcar a consulta veja a qualificação do médico.</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/3.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">3 - Escolha o melhor horário</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Veja todos os horário disponíveis do profissional e solicite um agendemanto.</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/4.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">4 - Pronto!</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Agendamento realizado com sucesso.</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/5.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">5 - Calcule sua rota</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Use o aplicativo integrado ao google maps para criar uma rota até seu profissional.</p>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>


<!-- Contact Section -->

<section id="contatos">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 ">
                <div>
                     <img src="/static/img/contact.jpg" class="img-responsive"/>
                </div>
            </div>

            <div class="col-sm-6 ">
                <ul class="row">

                        <address>
                             <h5>Wagner Jeronimo</h5>
                            <p><i class="fa fa-phone"></i> (11) 98377-7633</p>
                            <p><i class="fa fa-envelope-o"></i> wagner@agendee.com.br</p>
                        </address>

                        <address>
                            <h5>Diogenes Dorete</h5>
                            <p><i class="fa fa-phone"></i> (11) 99954-8148</p>
                            <p><i class="fa fa-envelope-o"></i> diogenes@agendee.com.br</p>
                        </address>


                </ul>
            </div>
        </div>
    </div>
</section>


<footer>
    <div id="rodapePrincipal" class="col-sm-12 text-center">

        <img src="/static/img/logo_mini.png" />&nbsp;&nbsp;&nbsp; Seu portal de agendamento online</i>

    </div>
</footer>
<div class="modal fade" id="modalVideoInstitucional" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Saiba como funciona</h4>
            </div>
            <div class="modal-body">
                <iframe width="560" height="315" src="https://www.youtube.com/embed/NZiwUl8DhFQ" frameborder="0" allowfullscreen></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>



<jsp:include page="/views/commons/footer.jsp" />
<jsp:include page="/views/commons/footer-components.jsp" />
<script src="/static/js/libs/cbpAnimatedHeader.js"></script>
<script src="/static/js/smoothscroll.js"></script>
<script src="/static/js/jquery.backstretch.min.js"></script>
<script src="/static/js/home.js"></script>



</body>
</html>
