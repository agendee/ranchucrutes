<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
</head>
<body>
    <jsp:include page="/views/commons/menu.jsp" />
    <section id="resultado">
        <div class="col-xs-12 container">
            ${errorMessage}


            <div class="panel panel-primary">
                  <div class="panel-heading">
                    <h3 class="panel-title">Obrigado por se cadastrar</h3>
                  </div>
                  <div class="panel-body">
                    Em pouco instantantes você receberá um email.
                    Clique no link do email para confirmar o cadastro.
                   </div>
            </div>

        </div>
    </section>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
</body>
</html>