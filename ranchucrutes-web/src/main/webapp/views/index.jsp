<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">

<head>
    <meta name="google-site-verification" content="9fRlro40UeOx7DXPoC-xzH1vS0ulV9mJAad3lLBCI6k" />
    <!-- Custom CSS -->
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
</head>

<body id="page-top" class="index">
<jsp:include page="/views/commons/menu_home.jsp" />
<!-- Header -->


<header>
    <div class="container">
        <div class="intro-text">
            <div class="intro-lead-in"><h1>Bem-vindo ao </h1> <br/> <img src="/static/img/logonome.png" height="110px" style="margin-top: -25px"></div>
            <div class="intro-heading">Seu portal de agendamentos online. Assista a animação abaixo e veja como funciona.</div>

                <span class="fa-stack fa-4x" style="cursor: pointer" data-toggle="modal" data-target="#modalVideoInstitucional">
                     <i class="fa fa-circle fa-stack-2x text-primary"></i>
                     <i class="fa fa-play fa-stack-1x fa-inverse"></i>
                </span>

        </div>
    </div>
</header>

<!--
<div class="homepage-hero-module">
    <div class="video-container">
        <div class="title-container">
            <div class="headline">


            </div>
            <div class="description">
                <div class="inner">Seu portal de agendamentos online. Baixe agora nosso aplicativo e viva uma nova experiência. <br/><br/><br/><br/></div>

                <a href="http://www.agendee.com.br/apk/agendee.apk">
                    <img alt="Get it on Google Play"
                         src="https://developer.android.com/images/brand/pt-br_generic_rgb_wo_45.png" />
                </a>
            </div>
        </div>
        <div class="filter">
            <img src="static/img/backgroud-home.jpg" alt="">
        </div>
        <div class="poster hidden">

        </div>
    </div>
</div>

-->

<!-- Portfolio Grid Section
<section id="logar" class="bg-light-gray">
   <div class="container">
       <div class="row">
           <div class="col-lg-12 text-center">
               <h2 class="section-heading">Acesse o portal de agendamentos</h2>
               <h3 class="section-subheading text-muted">Veja abaixo qual é o seu perfil e acesse ao portal.</h3>
           </div>
       </div>
       <div class="row">
           <div class="col-md-6 col-sm-6 portfolio-item">
               <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">

                   <img src="/static/img/portfolio/patient.png" class="img-responsive center-block" alt="">
               </a>
               <div class="portfolio-caption">
                   <h4>Você é paciente?</h4>
                   <p class="text-muted">Acesse aqui</p>
               </div>
           </div>
           <div class="col-md-6 col-sm-6 portfolio-item">
               <a href="/profissional/login" class="portfolio-link" data-toggle="modal">
                   <img src="/static/img/portfolio/doctor.png" class="img-responsive center-block" alt="">
               </a>
               <div class="portfolio-caption">
                   <h4>Você é profissional?</h4>
                   <p class="text-muted">Acesse aqui</p>
               </div>
           </div>

       </div>
   </div>
</section>-->

<!-- Services Section -->
<section id="services" style="background-color: #c7ddef">
    <!--<div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Como funciona:</h2>
                <h3 class="section-subheading text-muted">Veja como é facil marcar um agendamento</h3>
            </div>
        </div>
        <div class="row text-center">
            <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa fa-android fa-stack-1x fa-inverse"></i>
                    </span>
                <h4 class="service-heading">Baixe o aplicato</h4>
                <p class="text-muted">Clique no link abaixo e faça download do aplicativo.</p>
                <a href="http://www.agendee.com.br/apk/agendee.apk">
                    <img alt="Get it on Google Play"
                         src="https://developer.android.com/images/brand/pt-br_generic_rgb_wo_45.png" />
                </a>
            </div>
            <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa fa-user-md fa-stack-1x fa-inverse"></i>
                    </span>
                <h4 class="service-heading">Procure Profissionais</h4>
                <p class="text-muted">Procure profissionais da sua escolha mais próximo de você.</p>

            </div>
            <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa fa-calendar fa-stack-1x fa-inverse"></i>
                    </span>
                <h4 class="service-heading">Faça o agendamento</h4>
                <p class="text-muted">Depois de encontrar o profissional escolha a melhor data e agende sua consulta.</p>
            </div>

        </div>
    </div>-->

    <div class="container">

        <div class="row text-center">
            <div class="col-md-6">
                <img src="/static/img/mobile_home.png" class="image_responsive">
            </div>
            <div class="col-md-6">

                <h3 class="service-heading">Baixe nosso aplicativo</h3>
                <p class="text-muted">Use o QRcode abaixo ou se preferir acesse a playstore.</p>
                <div class="row text-center">

                    <img src="/static/img/qrcode_apk.png" class="image_responsive" style=""/>
                </div>

                <div class="row text-center" style="margin-top: 40px">
                    <a href="http://www.agendee.com.br/apk/agendee.apk" >
                        <img alt="Get it on Google Play"
                             src="https://developer.android.com/images/brand/pt-br_generic_rgb_wo_45.png" />
                    </a>
                </div>

            </div>


        </div>
    </div>

</section>



<!-- About Section -->
<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Sobre</h2>
                <h3 class="section-subheading text-muted">História da Empresa</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <ul class="timeline">
                    <li>
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/1.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4>Nov-2014</h4>
                                <h4 class="subheading">Diogenes teve uma ideia.</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Diogenes teve a ideia de cria um aplicativo para agendamento médico, e começa sua busca por um parceiro.</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/2.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4>Março 2015</h4>
                                <h4 class="subheading">Diogenes e Wagner começam a trabalhar na mesma empresa.</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Diogenes e Wagner se reencontram na mesma empresa</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/3.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4>December 2012</h4>
                                <h4 class="subheading">Transition to Full Service</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <img class="img-circle img-responsive" src="/static/img/about/4.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4>July 2014</h4>
                                <h4 class="subheading">Phase Two Expansion</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <h4>Be Part
                                <br>Of Our
                                <br>Story!</h4>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>

<!-- Team Section
<section id="team" class="bg-light-gray">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Nossa Fantastica Equipe</h2>
                <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4">
                <div class="team-member">
                    <img src="/static/img/team/1.jpg" class="img-responsive img-circle" alt="">
                    <h4>Diogenes Dorete</h4>
                    <p class="text-muted">Lead Marketer and Analyst</p>
                    <ul class="list-inline social-buttons">
                        <li><a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="team-member">
                    <img src="/static/img/team/2.jpg" class="img-responsive img-circle" alt="">
                    <h4>Fernanda Araujo</h4>
                    <p class="text-muted">Lead Designer and Analyst</p>
                    <ul class="list-inline social-buttons">
                        <li><a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="team-member">
                    <img src="/static/img/team/3.jpg" class="img-responsive img-circle" alt="">
                    <h4>Wagner Jeronimo</h4>
                    <p class="text-muted">Lead Developer</p>
                    <ul class="list-inline social-buttons">
                        <li><a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center">
                <p class="large text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut eaque, laboriosam veritatis, quos non quis ad perspiciatis, totam corporis ea, alias ut unde.</p>
            </div>
        </div>
    </div>
</section>
-->

<!-- Clients Aside -->
<aside class="clients">
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-sm-6">
                <a href="#">
                    <img src="/static/img/logos/envato.jpg" class="img-responsive img-centered" alt="">
                </a>
            </div>
            <div class="col-md-3 col-sm-6">
                <a href="#">
                    <img src="/static/img/logos/designmodo.jpg" class="img-responsive img-centered" alt="">
                </a>
            </div>
            <div class="col-md-3 col-sm-6">
                <a href="#">
                    <img src="/static/img/logos/themeforest.jpg" class="img-responsive img-centered" alt="">
                </a>
            </div>
            <div class="col-md-3 col-sm-6">
                <a href="#">
                    <img src="/static/img/logos/creative-market.jpg" class="img-responsive img-centered" alt="">
                </a>
            </div>
        </div>
    </div>
</aside>

<!-- Contact Section -->
<section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Entre em contato conosco</h2>
                <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form name="sentMessage" id="contactForm" novalidate>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Your Name *" id="name" required data-validation-required-message="Please enter your name.">
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" placeholder="Your Email *" id="email" required data-validation-required-message="Please enter your email address.">
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <input type="tel" class="form-control" placeholder="Your Phone *" id="phone" required data-validation-required-message="Please enter your phone number.">
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <textarea class="form-control" placeholder="Your Message *" id="message" required data-validation-required-message="Please enter a message."></textarea>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="col-lg-12 text-center">
                            <div id="success"></div>
                            <button type="submit" class="btn btn-xl">Send Message</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <span class="copyright">Copyright &copy; Your Website 2014</span>
            </div>
            <div class="col-md-4">
                <ul class="list-inline social-buttons">
                    <li><a href="#"><i class="fa fa-twitter"></i></a>
                    </li>
                    <li><a href="#"><i class="fa fa-facebook"></i></a>
                    </li>
                    <li><a href="#"><i class="fa fa-linkedin"></i></a>
                    </li>
                </ul>
            </div>
            <div class="col-md-4">
                <ul class="list-inline quicklinks">
                    <li><a href="#">Privacy Policy</a>
                    </li>
                    <li><a href="#">Terms of Use</a>
                    </li>
                </ul>
            </div>
        </div>
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
<script src="/static/js/home.js"></script>
<script src="/static/js/ws/ranchucrutes-ws-client.js"></script>

</body>
</html>
