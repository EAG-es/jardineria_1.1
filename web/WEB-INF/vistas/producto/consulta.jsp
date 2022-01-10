<%-- 
    Document   : consulta
    Created on : 23 nov 2021, 9:52:23
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="inser.web.jardineria.Producto" scope="request" id="producto" />
<% producto.iniciar_javabean(request, response, out, session);%>
<form action="${pageContext.request.contextPath}/index/producto/${producto.getConsulta()}" method="post">
    <table style="background-color: aquamarine;">
    <tr><td>
        <button type="submit" 
                name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.pagina_retroceso_tex%>"
                value="${producto.paginacion_busquedas_ordenaciones.pagina_fila_inicio}"> &lt; </button>
        ${producto.paginacion_busquedas_ordenaciones.pagina_fila_inicio} - ${producto.paginacion_busquedas_ordenaciones.pagina_fila_fin}
        <button type="submit" 
                name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.pagina_avance_tex%>"
                value="${producto.paginacion_busquedas_ordenaciones.pagina_fila_fin}"> &gt; </button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="submit" 
                name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.pagina_fila_ir_tex%>">Ver fila</button>
        <input type="number" style="width:9ch; text-align: right" name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.pagina_fila_inicio_tex%>" value="${producto.paginacion_busquedas_ordenaciones.pagina_fila_inicio}" >
        de ${producto.paginacion_busquedas_ordenaciones.pagina_total_filas_num} 
    </td><td>
        (
        <input type="number" style="width:9ch; text-align: right" name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.pagina_filas_num_tex%>" value="${producto.paginacion_busquedas_ordenaciones.pagina_filas_num}" >
        filas/página)
    </td></tr>
    <tr><td>
        <input type="text" style="width: 30ch" name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.filtro_tex%>"
        value="${producto.paginacion_busquedas_ordenaciones.filtro}"/>  
        <button type="submit" 
                name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.pagina_fila_ir_tex%>">Buscar en descripcion</button>
    </td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="submit" formaction="${pageContext.request.contextPath}/index/producto/${producto.getCreacion()}">Crear nuevo</button> <br>
    </td></tr>
    </table>
    <h2>${producto.mensaje}</h2>
    <table>
        <thead>
            <tr>
                <th>
                    <input type="hidden" 
                    name="${producto.paginacion_busquedas_ordenaciones.orden}"
                    value="${producto.paginacion_busquedas_ordenaciones.leer_campo_ordenacion()}"
                    >
                    <button type="submit" 
                    name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.orden_futuro_tex%>"
                    value="${producto.paginacion_busquedas_ordenaciones.orden_futuro}-><%=innui.modelo.jardineria.Producto.codigo_producto_tex%>"
                    > Código producto <%=producto.paginacion_busquedas_ordenaciones.leer_marca(innui.modelo.jardineria.Producto.codigo_producto_tex)%>
                    </button>
                </th>
                <th>
                    Nombre
                </th>
                <th>
                    <button type="submit" 
                    name="<%=inser.web.utilidades.Paginacion_busquedas_ordenaciones.orden_futuro_tex%>"
                    value="${producto.paginacion_busquedas_ordenaciones.orden_futuro}-><%=innui.modelo.jardineria.Producto.gama_tex%>"
                    > Gama <%=producto.paginacion_busquedas_ordenaciones.leer_marca(innui.modelo.jardineria.Producto.gama_tex)%>
                    </button>                    
                </th>
                <th>
                    Dimensiones
                </th>
                <th>
                    Proveedor
                </th>
                <th>
                    Descripción
                </th>
                <th>
                    Cantidad en stock
                </th>
                <th>
                    Precio de venta
                </th>
                <th>
                    Precio de proveedor
                </th>
            </tr>
        </thead>
        <tbody>
            <% while (producto.presentar_siguiente()) { %>
            <tr>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.codigo_producto_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.nombre_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.gama_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.dimensiones_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.proveedor_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.descripcion_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.cantidad_en_stock_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.precio_venta_tex);%>
                </td>
                <td>
                    <%producto.presentar(innui.modelo.jardineria.Producto.precio_proveedor_tex);%>
                </td>
                <td>
                    <button type="submit" formaction="${pageContext.request.contextPath}/index/producto/${producto.getModificacion()}/<%producto.presentar(innui.modelo.jardineria.Producto.codigo_producto_tex);%>">Editar</button> 
                    <button type="submit" formaction="${pageContext.request.contextPath}/index/producto/${producto.getBorrado()}/<%producto.presentar(innui.modelo.jardineria.Producto.codigo_producto_tex);%>">Borrar</a>
                </td>
            </tr>
            <% } %>       
        </tbody>
    </table>   
</form>
