<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
    <link href='/static/css/medico/agenda.css' rel='stylesheet' />
    <link href='/static/css/libs/jquery-ui.min.css' rel='stylesheet' />
    <link href='/static/css/libs/fullcalendar.css' rel='stylesheet' />
    <link href='/static/css/libs/fullcalendar.print.css' rel='stylesheet' media='print' />
</head>
<body>
    <jsp:include page="/views/commons/menu_admin.jsp" />
    <div class="col-xs-12 container">
        <section>
               <hr>
               <div id="calendar"></div>
        </section>

        <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src='/static/js/libs/moment.min.js'></script>
    <script src='/static/js/libs/fullcalendar.min.js'></script>
    <script src='/static/js/libs/lang-all.js'></script>
    <script src="/static/js/medico/agenda.js"></script>
</body>
</html>