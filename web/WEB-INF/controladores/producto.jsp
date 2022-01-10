<%-- 
    Document   : producto
    Created on : 23 nov 2021, 9:44:40
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="inser.web.jardineria.Producto" scope="request" id="producto" />
<% producto.iniciar_javabean(request, response, out, session);%>
<% producto.procesar(); %>
<jsp:include page="/WEB-INF/vistas/${producto.controlador()}/${producto.vista()}.jsp" />


