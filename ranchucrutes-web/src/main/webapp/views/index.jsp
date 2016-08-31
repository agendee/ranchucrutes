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
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="fa fa-calendar medium-icon"></i>
    					<h3>Agenda online</h3>
    					<hr>
    					<p>Gerencie a agenda do seu consultório de maneira simples e rápida.</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="fa fa-plug medium-icon"></i>
    					<h3>Integrado</h3>
    					<hr>
    					<p>Agendee é integrado com os melhores sistemas do mercado, <a href="#parceiros">veja nossos parceiros aqui</a> </p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="fa fa-eye medium-icon"></i>
    					<h3>Visibilidade</h3>
    					<hr>
    					<p>Faça sua clínica aparecer para milhares de pacientes em todo o Brasil.</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="1s">
    				<i class="fa fa-line-chart medium-icon"></i>
    					<h3>Maximize</h3>
    					<hr>
    					<p>Aumente seus lucros atraindo mais pacientes para sua clínica.</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.6s">
    				<i class="fa fa-pencil-square-o medium-icon"></i>
    					<h3>Cadastre-se</h3>
    					<hr>
    					<p><a href="/profissional/cadastro">Faça um cadastro em nosso sistema</a> e fique disponível para milhares de pacientes</p>
    			</div>
    			<div class="col-lg-4 col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.9s">
    				<i class="fa fa-question-circle medium-icon"></i>
    					<h3>Dúvidas?</h3>
    					<hr>
    					<p> <a href="#contatos">Clique aqui</a> e fale conosco, teremos prazer em tirar todas as suas dúvidas.</p>
    			</div>
    		</div>
    	</div>
</section>

<!-- About Section -->
<section id="paciente">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">PACIENTE</h2>
                <h3 class="section-subheading text-muted">Veja como é facil agendar uma consulta</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <ul class="timeline">
                     <li>&nbsp;</li>
                     <li>&nbsp;</li>
                     <li>&nbsp;</li>
                    <li>
                        <div class="timeline-panel">
                            <div class="timeline-body">
                                <p class="text-muted">Escolha o especialista e sua localidade</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="timeline-image">
                            <img class="img-responsive center-block" src="/static/img/home/passo1.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Agendee localizará os profissionais mais próximos da sua região</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Escolha o profissional e o horário da consulta</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <img class="center-block img-responsive" src="/static/img/home/passo2.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Ou se preferir, clique no telefone para ligar para o profissional.</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Acesse com suas redes sociais.</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="timeline-image">
                            <img class="center-block img-responsive" src="/static/img/home/passo3.png" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Entre com seu login do Facebook ou Google Plus e pronto sua solicitação de consulta foi feita com sucesso!</h4>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</section>


<!-- Parceiros Section -->
<section id="parceiros">
    <div class="container">
        <div class="col-lg-12 text-center">
            <h2 class="section-heading">Conheça abaixo nossos parceiros</h2>
        </div>
        <ul class="timeline">
             <li>&nbsp;</li>
             <li>&nbsp;</li>
             <li>&nbsp;</li>
        </ul>
        <div class="row">
          <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
              <img class="img-responsive center-block" src="/static/img/home/parceiro1.png"  width="150px" alt="Clínica nas Nuvens">
              <div class="caption text-center">
                <p>Sistema online completo para clínicas e consultórios</p>

              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-3">
              <div class="thumbnail">
                <img class="img-responsive center-block" src="/static/img/home/parceiro2.png" width="150px" alt="shosp">
                <div class="caption text-center">
                  <p>GESTÃO DE SUA CLÍNICA para você focar na estratégia</p>
                </div>
              </div>
          </div>
          <div class="col-sm-6 col-md-3">
              <div class="thumbnail">
                <img class="img-responsive center-block" src="/static/img/home/parceiro4.png" width="150px" alt="">
                <div class="caption">

                  <p>Sistema Completo de Gestão Odontológica</p>

                </div>
              </div>
            </div>
          <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
              <img class="img-responsive center-block" src="/static/img/home/parceiro3.png" width="150px" alt="MicroPlant">
              <div class="caption text-center">

                <p>Melhores soluções e melhores resultados em sitemas de implantes ondontológicos</p>

              </div>
            </div>
          </div>
        </div>
    </div>
</section>
<!-- Parceiros Section -->


<!-- Contact Section -->
<section id="contatos">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 ">
                <div>
                     <img src="/static/img/contato.png" class="img-responsive"/>
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
