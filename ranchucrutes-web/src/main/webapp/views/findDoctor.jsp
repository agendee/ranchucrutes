<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">

<head>

    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
    <link href="/static/css/profissional/find.css" rel="stylesheet">

</head>
<body>

    <jsp:include page="/views/commons/menu.jsp" />
    <div class="col-xs-12 content">
        <div id="panel" style="margin-left:-100px">
    </div>
    <div id="map-canvas"></div>


    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>

    <jsp:include page="/views/commons/footer.jsp" />
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="/static/js/agency.js"></script>
    <script src="/static/js/profissional/profissional.js"></script>
    <script>
        var you = new google.maps.LatLng(${result.latitude}, ${result.longitude});
        var profissionais = [];

            <c:forEach var="m" items="${result.profissionais}">
                profissionais.push({latlng: new google.maps.LatLng(${m.latitude}, ${m.longitude}),
                    nome: "${m.nome}",
                    crm: '${m.crm}',
                    espec: '${m.espec}',
                    endereco: '${m.endereco}',
                    telefone: '${m.telefone}'});
            </c:forEach>
    </script>
    <script src="/static/js/profissional/find.js"></script>

</body>

</html>