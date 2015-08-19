<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
</head>
<body>
    <jsp:include page="/views/commons/menu.jsp" />
    <section id="resultado">
        <h:info-panel title="Obrigado por se cadastrar!"
        msg="Em pouco instantantes você receberá um email.<br/>Clique no link do email para confirmar o cadastro."/>
    </section>
    <jsp:include page="/views/commons/rodape.jsp" />
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
</body>
</html>