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
        <div class="col-sm-6 portfolio-item">
            <div class="icon-link">
                <a href="#profissional" class="portfolio-link">
                    <div class="caption">
                        <div class="caption-content">
                            Sou um profissional
                        </div>
                    </div>
                    <img src="static/img/home/profissional.png" class="img-circle img-responsive" alt="">
                </a>
            </div>
        </div>
        <div class="col-sm-6 portfolio-item">
            <div class="icon-link">
                <a href="#paciente" class="portfolio-link">
                    <div class="caption">
                        <div class="caption-content">
                            Sou um paciente
                        </div>
                    </div>
                    <img src="static/img/home/paciente.png" class="img-circle img-responsive" alt="">
                </a>
            </div>
        </div>

    </div>
</header>

<!-- Services Section -->
<section id="download" style="background-color: #c7ddef">

    <div class="container">

        <div class="row text-center">
            <div class="col-md-6">
                <img src="/static/img/mobile_home.png" class="image_responsive">
            </div>
            <div class="col-md-6">

                <h3 class="service-heading">Baixe nosso aplicativo</h3>
                <p class="text-muted">Use o QRcode abaixo ou se preferir acesse a Play Store.</p>
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
<section id="profissional" style="background-color: #c7ddef">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Profissional, veja como é fácil</h2>
                <h3 class="section-subheading text-muted">Veja como é fácil</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="row featurette">
                    <div class="col-md-7">
                        <h2 class="featurette-heading">Faça um cadastro em nosso sistema.</h2>
                        <p class="lead">O cadastro é bem simples, </p>
                    </div>
                    <div class="col-md-5">
                        <img class="featurette-image img-responsive center-block" src="static/img/about/p1.png" alt="Generic placeholder image">
                    </div>
                </div>

                <hr class="featurette-divider">

                <div class="row featurette">
                    <div class="col-md-7 col-md-push-5">
                        <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
                        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
                    </div>
                    <div class="col-md-5 col-md-pull-7">
                        <img class="featurette-image img-responsive center-block" src="static/img/about/p2.png" alt="Generic placeholder image">
                    </div>
                </div>

                <hr class="featurette-divider">

                <div class="row featurette">
                    <div class="col-md-7">
                        <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
                        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
                    </div>
                    <div class="col-md-5">
                        <img class="featurette-image img-responsive center-block" src="static/img/about/p3.png" alt="Generic placeholder image">
                    </div>
                </div>

                <hr class="featurette-divider">

                <div class="row featurette">
                    <div class="col-md-7 col-md-push-5">
                        <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
                        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
                    </div>
                    <div class="col-md-5 col-md-pull-7">
                        <img class="featurette-image img-responsive center-block" src="static/img/about/p4.png" alt="Generic placeholder image">
                    </div>
                </div>

                <hr class="featurette-divider">

                <div class="row featurette">
                    <div class="col-md-7">
                        <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
                        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
                    </div>
                    <div class="col-md-5">
                        <img class="featurette-image img-responsive center-block" src="static/img/about/p5.png" alt="Generic placeholder image">
                    </div>
                </div>

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
<script src="/static/js/home.js"></script>
<script src="/static/js/ws/ranchucrutes-ws-client.js"></script>

</body>
</html>
