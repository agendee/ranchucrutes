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
    <div class="col-xs-12 container">
        <section id="resultado" style="margin-top:80px;">
            <h:info-panel title="Obrigado!"
            msg="Em poucos instantantes você receberá um email.<br/>Com as instruções para recuperar sua senha."/>
        </section>
        <jsp:include page="/views/commons/rodape.jsp" />
    </div>

    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
</body>
</html>