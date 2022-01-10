<%-- 
    Document   : index
    Created on : 9 nov 2021, 11:56:57
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/WEB-INF/errores/excepcion.jsp" %>
<jsp:useBean class="inser.web.jardineria.Index" scope="page" id="index" />
<% index.iniciar_javabean(request, response, out, session);%>
<% if (index.configurar_pagina() == false) return;  %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/index/encabezamiento.jsp" />
    <body>
    <% index.tratar_error_texto(); %> 
    <jsp:include page="/WEB-INF/index/cabecera.jsp" />
    <jsp:include page="/WEB-INF/controladores/${index.controlador()}.jsp" />
    <jsp:include page="/WEB-INF/index/pie.jsp" />
    </body>
</html>
