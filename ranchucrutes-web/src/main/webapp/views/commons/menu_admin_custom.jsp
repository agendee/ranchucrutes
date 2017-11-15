<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
	<div class="menu-admin menu-admin-open">
		<div class="head">
			<img src="/static/img/logo_mini_white.png" alt="Agendee">
			<div class="menu-toggle fade-hover">
				<i class="fa fa-bars fade-hover" aria-hidden="true"></i>
			</div>
		</div>
		<div class="content">
			<div class="picture">
			<c:if test="${form.profissional.foto != null}">
				<img src="/f/${form.profissional.foto}">
			</c:if>
			<c:if test="${form.profissional.foto == null}">
				<img src="/static/img/doctor/user.png">
			</c:if>
			</div>
			<div class="info">
			<c:if test="${sessionScope.loginSession != null}">
				${sessionScope.loginSession.primeiroNome}
			</c:if>
			</div>
            <ul class="nav-menu">
                <li>
                    <a class="page-scroll" href="/profissional/agenda">Agenda <i class="fa fa-calendar" aria-hidden="true"></i></a>
                </li>
                <li>
                    <a class="page-scroll" href="/profissional/admin">Cadastro<i class="fa fa-user" aria-hidden="true"></i></a>
                </li>
                <li>
                    <a class="page-scroll" href="/profissional/horario">Horários <i class="fa fa-clock-o" aria-hidden="true"></i></a>
                </li>

                <li>
                    <a class="page-scroll" href="/auth/sair">Sair <i class="fa fa-sign-out" aria-hidden="true"></i></a>
                </li>

            </ul>
		</div>
	</div>