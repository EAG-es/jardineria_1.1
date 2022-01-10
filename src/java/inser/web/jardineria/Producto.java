/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.web.jardineria;

import innui.jdbc.jardineria.MySql;
import static innui.jdbc.jardineria.MySql.jdbc_clave_tex;
import static innui.jdbc.jardineria.MySql.jdbc_uri_tex;
import static innui.jdbc.jardineria.MySql.jdbc_usuario_tex;
import innui.modelo.jardineria.Producto_operaciones;
import static inser.web.utilidades.Controladores_vistas.borrado;
import static inser.web.utilidades.Controladores_vistas.consulta;
import inser.web.utilidades.Paginacion_busquedas_ordenaciones;
import java.util.List;

/**
 *
 * @author informatica
 */
public class Producto extends Index {
    public int pagina_tam = 15;   
    public Producto_operaciones producto_operaciones;
    public List <innui.modelo.jardineria.Producto> consulta_lista;
    public MySql mySql;
    public String jdbc_uri = null;
    public String jdbc_usuario = null;
    public String jdbc_clave = null;
    public String mensaje="";
    public innui.modelo.jardineria.Producto producto;
    public Paginacion_busquedas_ordenaciones paginacion_busquedas_ordenaciones;

    public innui.modelo.jardineria.Producto getProducto() {
        return producto;
    }

    public Paginacion_busquedas_ordenaciones getPaginacion_busquedas_ordenaciones() {
        return paginacion_busquedas_ordenaciones;
    }

    public String getMensaje() {
        String texto = mensaje;
        mensaje = "";
        return texto;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public Producto() {
        mySql = new MySql();
        producto_operaciones = new Producto_operaciones();
        paginacion_busquedas_ordenaciones = new Paginacion_busquedas_ordenaciones();
        paginacion_busquedas_ordenaciones.pagina_tam = pagina_tam;
    }

    /**
     * Obtiene los valores de contexto del web.xml
     */
    public boolean configurar_contexto() {
        String [] error = { "" }; //NOI18N
        if (error_ret) {
           jdbc_uri = httpServletRequest.getServletContext().getInitParameter(jdbc_uri_tex);
           jdbc_usuario = httpServletRequest.getServletContext().getInitParameter(jdbc_usuario_tex);
           jdbc_clave = httpServletRequest.getServletContext().getInitParameter(jdbc_clave_tex);
        }
        error_texto = error[0];
        return error_ret;
    }

    
    public void procesar() {
        String [] error = { "" }; //NOI18N
        String vista = null;
        String codigo_producto = null;
        long filas_num = 0;
        if (error_ret) {
            configurar_contexto();
        }
        if (error_ret) {
            error_ret = mySql.conectar(jdbc_uri, jdbc_usuario, jdbc_clave, error);
        }
        if (error_ret) {
            vista = vista(error);
            error_ret = (vista != null);
        }
        if (error_ret) {
            producto_operaciones.connection = mySql.connection;
            if (vista.equals(consulta)) {
                error_ret = paginacion_busquedas_ordenaciones.procesar(httpServletRequest, error);
                if (error_ret) {
                    filas_num = producto_operaciones.contar_filas(error);
                    error_ret = (filas_num != -1);
                }
                if (error_ret) {
                    error_ret = paginacion_busquedas_ordenaciones.poner_total_filas_num(filas_num, error);
                }
                if (error_ret) {
                    consulta_lista = producto_operaciones.listar(paginacion_busquedas_ordenaciones, error);
                    error_ret = (null != consulta_lista);
                }
            } else if (vista.equals(borrado)) {
                codigo_producto = leer_dato_url(2, error);
                error_ret = (codigo_producto != null);
                if (error_ret) {
                    error_ret = producto_operaciones.borrar(codigo_producto, error);
                }
                if (error_ret) {
                    escribir_mensaje("Registro borrado con éxito. ");
                } else {
                    error_ret = true;
                    mensaje = error[0];
                }
                // Establezco que se repita el procesamiento de "consulta"
                poner_dato_url(1, consulta, error);
                procesar();
            } else if (vista.equals(creacion)) {
                if (error_ret) {
                    if (httpServletRequest.getParameter(creacion) != null) {
                        crear();
                    }
                }
            } else if (vista.equals(modificacion)) {
                if (error_ret) {
                    codigo_producto = leer_dato_url(2, error);
                    error_ret = (codigo_producto != null);
                }
                if (error_ret) {
                    producto = producto_operaciones.encontrar(codigo_producto, error);
                }
                if (error_ret) {
                    if (httpServletRequest.getParameter(modificacion) != null) {
                        modificar();
                    }
                }
            }
        }
        if (error_ret == false){
            escribir_error(error[0]);
        }
        error_texto = error[0];
    }

    public void crear() {
        String [] error = { "" }; //NOI18N
        producto = leer_formulario_creacion_modificacion(error);
        error_ret = (producto != null && error[0].isBlank());
        if (error_ret == false) {
            if (producto.codigo_producto.isBlank() == false
                    && producto.nombre.isBlank() == false
                    && producto.gama.isBlank() == false)
                error_ret = true;
        }
        if (error_ret) {
            error_ret = producto_operaciones.crear(producto, error);
        }        
        if (error_ret == false){
            escribir_error(error[0]);
        } else {
            escribir_mensaje("Registro creado con éxito. ");
        }
        error_texto = error[0];
    }
    
    public void modificar() {
        String [] error = { "" }; //NOI18N
        producto = leer_formulario_creacion_modificacion(error);
        error_ret = (producto != null && error[0].isBlank());
        if (error_ret == false) {
            if (producto.codigo_producto.isBlank() == false
                    && producto.nombre.isBlank() == false)
                error_ret = true;
        }
        if (error_ret) {
            error_ret = producto_operaciones.modificar(producto, error);
        }        
        if (error_ret == false){
            escribir_error(error[0]);
        } else {
            escribir_mensaje("Registro modificado con éxito. ");
        }
        error_texto = error[0];
    }
    
    public innui.modelo.jardineria.Producto leer_formulario_creacion_modificacion(String [] error) {
        innui.modelo.jardineria.Producto producto;
        producto = new innui.modelo.jardineria.Producto();
        String parametro;
        error[0] = "";
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.codigo_producto_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "";
            error[0] = error[0] + "Falta codigo_producto. ";
        }
        producto.codigo_producto = parametro;
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.nombre_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "";
            error[0] = error[0] + "Falta nombre. ";
        }
        producto.nombre = parametro;
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.gama_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "";
            error[0] = error[0] + "Falta gama. ";
        }
        producto.gama = parametro;
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.dimensiones_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "";
            error[0] = error[0] + "Falta dimensiones. ";
        }
        producto.dimensiones = parametro;
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.proveedor_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "";
            error[0] = error[0] + "Falta proveedor. ";
        }
        producto.proveedor = parametro;
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.descripcion_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "";
            error[0] = error[0] + "Falta descripcion. ";
        }
        producto.descripcion = parametro;
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.cantidad_en_stock_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "0";
            error[0] = error[0] + "Falta cantidad_en_stock. ";
        }
        producto.cantidad_en_stock = Integer.valueOf(parametro);
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.precio_venta_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "0";
            error[0] = error[0] + "Falta precio_venta. ";
        }
        producto.precio_venta = Double.valueOf(parametro);
        parametro = httpServletRequest.getParameter(innui.modelo.jardineria.Producto.precio_proveedor_tex);
        if (parametro == null || parametro.isBlank()) {
            parametro = "0";
            error[0] = error[0] + "Falta precio_proveedor. ";
        }
        producto.precio_proveedor = Double.valueOf(parametro);
        return producto;
    }
    
    /**
     * Escribe un mensaje de error, si lo hay.
     * @param mensaje Mensaje qeu escribir
     * @return true si todo va bien, false si hay error
     */
    @Override    
    public boolean escribir_error(String mensaje) {
        this.mensaje = mensaje;
        error_ret = true;
        return error_ret;
    }   

    /**
     * Escribe un mensaje.
     * @param mensaje Mensaje qeu escribir
     * @return true si todo va bien, false si hay error
     */ 
    public boolean escribir_mensaje(String mensaje) {
        this.mensaje = mensaje;
        error_ret = true;
        return error_ret;
    }
    
    @Override
    public List leer_lista() {
        return consulta_lista;
    }
    
    @Override
    public String leer_columna_de_fila(String nombre, int fila, String [] error) {
        boolean ret = true;
        String retorno = null;
        innui.modelo.jardineria.Producto producto;
        if (consulta_lista == null) {
            ret = false;
            error[0] = "La lista no ha sido creada. ";
        } else {
            producto = consulta_lista.get(fila);
            if (nombre.equals("codigo_producto")) {
                retorno = producto.codigo_producto;
            } else if (nombre.equals("nombre")) {
                retorno = producto.nombre;
            } else if (nombre.equals("gama")) {
                retorno = producto.gama;
            } else if (nombre.equals("dimensiones")) {
                retorno = producto.dimensiones;
            } else if (nombre.equals("proveedor")) {
                retorno = producto.proveedor;
            } else if (nombre.equals("descripcion")) {
                retorno = producto.descripcion;
            } else if (nombre.equals("cantidad_en_stock")) {
                retorno = String.valueOf(producto.cantidad_en_stock);
            } else if (nombre.equals("precio_venta")) {
                retorno = String.valueOf(producto.precio_venta);
            } else if (nombre.equals("precio_proveedor")) {
                retorno = String.valueOf(producto.precio_proveedor);
            }
        }
        if (ret) {
            if (retorno == null) {
                retorno = "<0>";
            }
        }
        return retorno;
    }

}
