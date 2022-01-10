<%-- 
    Document   : excepcion
    Created on : 30 nov 2021, 17:04:49
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excepción en la aplicación</title>
    </head>
    <body>
        <h1>Excepción en la aplicación</h1>
        <table width="100%" border="1">
        <tr valign="top">
            <td><b>URI:</b></td>
            <td>${pageContext.errorData.requestURI}</td>
        </tr>
        <tr valign="top">
            <td><b>Status code:</b></td>
            <td>${pageContext.errorData.statusCode}</td>
        </tr>
        </table>
        <%=pageContext.getException().toString().replace("\n", "<br>")%>
        <table>
        <tr valign="top">
            <td>
                <%-- for (StackTraceElement stackTraceElement: pageContext.getException().getStackTrace()) { %>
                    <%=stackTraceElement.toString()%><br>
                <% } --%>
                <c:forEach var="stackTraceElement" items="${pageContext.getException().getStackTrace()}">
                    <c:out value="${stackTraceElement}"/>
                </c:forEach>
            </td>
        </tr>
        </table>
    </body>
</html>
