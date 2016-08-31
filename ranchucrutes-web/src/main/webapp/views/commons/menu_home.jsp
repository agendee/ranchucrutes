<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
<!-- navigation section -->
<section class="navbar navbar-fixed-top custom-navbar" role="navigation" style="backgroud:black">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon icon-bar"></span>
				<span class="icon icon-bar"></span>
				<span class="icon icon-bar"></span>
			</button>
			<a href="#" class="navbar-brand" style="margin-top:-10px"><img src="static/img/logo_mini_white.png" class="img-responsive" alt="Agendee"> </a>




		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
			    <li><a href="#" class="navbar-brand" style="margin-top:-10px"><img src="static/img/home/googleplay.png" class="img-responsive" alt="about img"> </a></li>
				<li><a href="#home" class="smoothScroll">HOME</a></li>
				<c:if test="${sessionScope.loginSession == null}">
                    <li>
                        <a class="page-scroll" href="/profissional/login">ACESSAR</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="/profissional/cadastro">CADASTRO</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loginSession != null}">
                    <li>
                        <a class="page-scroll" href="/profissional/agenda"><i class="fa fa-user-md"></i> ${sessionScope.loginSession.primeiroNome}</a>
                    </li>
                </c:if>
				<li><a href="#profissional" class="smoothScroll">PROFISSIONAL</a></li>
				<li><a href="#paciente" class="smoothScroll">PACIENTE</a></li>
				<li><a href="#parceiros" class="smoothScroll">PARCEIROS</a></li>
				<li><a href="#contatos" class="smoothScroll">CONTATO</a></li>
			</ul>
		</div>

	</div>

</section>