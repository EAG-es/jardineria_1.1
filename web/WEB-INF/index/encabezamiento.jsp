<%-- 
    Document   : cabecera
    Created on : 19 nov 2021, 9:52:00
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Locale" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="<%= Locale.getDefault().toLanguageTag()%>" scope="session"/>
<fmt:setBundle basename="in.inser.web.jardineria.in"/>
<head>
    <meta charset="utf-8" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imagenes/LogoCMTransparente-Icono.png"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><fmt:message key="Jardineria"/></title>
</head>
