<%-- 
    Document   : creacion
    Created on : 24 nov 2021, 9:13:37
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="inser.web.jardineria.Producto" scope="request" id="producto" />
<% producto.iniciar_javabean(request, response, out, session);%>
<div>
    <h2>${producto.getMensaje()}</h2>
    <form action="${pageContext.request.contextPath}/index/producto/${producto.getCreacion()}">
        <table width="100%">
        <tr><td style="text-align: right">
            <label>Codigo de Producto* </label></td><td>
                <input type="text" name="<%=innui.modelo.jardineria.Producto.codigo_producto_tex%>" value="${producto.producto.codigo_producto}"/>
        </td></tr><tr><td style="text-align: right">
            <label>Nombre* </label></td><td>
                <input type="text" name="<%=innui.modelo.jardineria.Producto.nombre_tex%>" value="${producto.producto.nombre}"/>
        </td></tr><tr><td style="text-align: right">
            <label>Gama </label></td><td>
                <input type="text" name="<%=innui.modelo.jardineria.Producto.gama_tex%>" value="${producto.producto.gama}"/>
        </td></tr><tr><td style="text-align: right">
            <label>Dimensiones </label></td><td>
                <input type="text" name="<%=innui.modelo.jardineria.Producto.dimensiones_tex%>" value="${producto.producto.dimensiones}"/>
        </td></tr><tr><td style="text-align: right">
            <label>Proveedor </label></td><td>
                <input type="text" name="<%=innui.modelo.jardineria.Producto.proveedor_tex%>" value="${producto.producto.proveedor}"/>
        </td></tr><tr><td style="text-align: right">
            <label>Descripcion </label></td><td>
                <textarea rows="10" name="<%=innui.modelo.jardineria.Producto.descripcion_tex%>">${producto.producto.descripcion}</textarea>
        </td></tr><tr><td style="text-align: right">
            <label>Cantidad en stock </label></td><td>
                <input type="number" name="<%=innui.modelo.jardineria.Producto.cantidad_en_stock_tex%>" value="${producto.producto.cantidad_en_stock}"/>
        </td></tr><tr><td style="text-align: right">
            <label>Precio de Venta </label></td><td>
                <input type="number" name="<%=innui.modelo.jardineria.Producto.precio_venta_tex%>" value="${producto.producto.precio_venta}"/>
        </td></tr><tr><td style="text-align: right">
            <label>Precio de Proveedor </label></td><td>
                <input type="number" name="<%=innui.modelo.jardineria.Producto.precio_proveedor_tex%>" value="${producto.producto.precio_proveedor}"/>
        </td></tr>
        <tr><td style="text-align: right">
        <input type="submit" name="${producto.getCreacion()}" value="Enviar"/>
        </td></td><td>
            Los campos con * son obligatorios. 
        </td></tr>
        </table>
    </form>
</div>

<div>
