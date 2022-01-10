/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.web.utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author informatica
 */
public class Paginacion_busquedas_ordenaciones extends Paginacion_busquedas_ordenaciones_modelo {
    public static String pagina_fila_inicio_tex = "pagina_fila_inicio";
    public static String pagina_filas_num_tex = "pagina_filas_num";
    public static String pagina_avance_tex = "pagina_avance";
    public static String pagina_retroceso_tex = "pagina_retroceso";
    public static String pagina_fila_ir_tex = "pagina_fila_ir";
    public static String filtro_tex = "filtro";
    public static String filtro_procesar_tex = "filtro_procesar";
    public static String orden_ascendente_tex = "orden_ascendente";
    public static String orden_descendente_tex = "orden_descendente";
    public static String orden_indefinido_tex = "orden_indefinido";
    public static String orden_futuro_tex = "orden_futuro";
    public static String orden_ascendente_marca_tex = "/\\";
    public static String orden_descendente_marca_tex = "\\/";
    public String pagina_de_operacion = pagina_fila_ir_tex;
    public String pagina_fila_inicio = "";
    public String pagina_fila_fin = "";
    public String pagina_filas_num = "";
    public String pagina_total_filas_num = "";
    public String orden_futuro = orden_ascendente_tex;

    public String getPagina_fila_inicio() {
        return pagina_fila_inicio;
    }

    public String getPagina_fila_fin() {
        return pagina_fila_fin;
    }

    public String getPagina_total_filas_num() {
        return pagina_total_filas_num;
    }

    public String getPagina_filas_num() {
        return pagina_filas_num;
    }

    public String getFiltro() {
        return filtro;
    }

    public String getOrden() {
        if (orden_ascendente != null && orden_ascendente.isBlank() == false) {
            return orden_ascendente_tex;
        } else if (orden_descendente != null && orden_descendente.isBlank() == false) {
            return orden_descendente_tex;
        } else {
            return orden_indefinido_tex;
        }
    }
    
    public String getOrden_futuro() {
        if (orden_ascendente != null && orden_ascendente.isBlank() == false) {
            return orden_descendente_tex;
        } else if (orden_descendente != null && orden_descendente.isBlank() == false) {
            return orden_indefinido_tex;
        } else {
            return orden_ascendente_tex;
        }
    }
    
    public boolean procesar(HttpServletRequest httpServletRequest, String [] error) {
        boolean ret = true;
        String operacion = "";
        try {
            error[0] = "";
            pagina_filas_num = httpServletRequest.getParameter(pagina_filas_num_tex);
            if (pagina_filas_num != null) {
                pagina_tam = Integer.valueOf(pagina_filas_num);
                pagina_fila_inicio = httpServletRequest.getParameter(pagina_fila_inicio_tex);
                pagina_fila_inicio_num = Integer.valueOf(pagina_fila_inicio);
                pagina_de_operacion = httpServletRequest.getParameter(pagina_avance_tex);
                if (pagina_de_operacion != null) {
                    operacion = pagina_avance_tex;
                } else {
                    pagina_de_operacion = httpServletRequest.getParameter(pagina_retroceso_tex);
                    if (pagina_de_operacion != null) {
                        operacion = pagina_retroceso_tex;
                    } else {
                        pagina_de_operacion = httpServletRequest.getParameter(pagina_fila_ir_tex);
                        if (pagina_de_operacion != null) {
                            operacion = pagina_fila_ir_tex;
                        }
                    }
                }
                if (pagina_de_operacion != null) {
                    if (operacion.equals(pagina_retroceso_tex)) {
                        pagina_fila_inicio_num = Integer.valueOf(pagina_de_operacion);
                        pagina_fila_inicio_num = pagina_fila_inicio_num - pagina_tam;
                        if (pagina_fila_inicio_num < 1) {
                            pagina_fila_inicio_num = 1;
                        }
                    } else if (operacion.equals(pagina_fila_ir_tex)) {
                        pagina_fila_inicio_num = Integer.valueOf(pagina_fila_inicio);
                        if (pagina_fila_inicio_num <= 0) {
                            pagina_fila_inicio_num = 1;
                        }
                    } else {
                        pagina_fila_inicio_num = Integer.valueOf(pagina_de_operacion) + 1;
                    }
                }
            }
            pagina_fila_inicio = String.valueOf(pagina_fila_inicio_num);
            pagina_fila_fin = String.valueOf(pagina_fila_inicio_num + pagina_tam - 1);
            pagina_filas_num = String.valueOf(pagina_tam);
            filtro = httpServletRequest.getParameter(filtro_tex);
            orden_ascendente = httpServletRequest.getParameter(orden_ascendente_tex);
            orden_descendente = httpServletRequest.getParameter(orden_descendente_tex);
            String orden_futuro_recibido = httpServletRequest.getParameter(orden_futuro_tex);
            if (orden_futuro_recibido != null) {
                String [] parametros_orden_futuro = orden_futuro_recibido.split("->");
                if (parametros_orden_futuro[0].equals(orden_ascendente_tex))  {
                    orden_ascendente = parametros_orden_futuro[1];
                    orden_descendente = null;
                } else if (parametros_orden_futuro[0].equals(orden_descendente_tex))  {
                    orden_descendente = parametros_orden_futuro[1];
                    orden_ascendente = null;
                } else {
                    orden_ascendente = null;
                    orden_descendente = null;
                }
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Los datos recibidos para la paginación, la búsqueda y la ordenación no se pueden procesar. "
                    + error[0];
            Logger.getLogger("inser").log(Level.SEVERE, error[0], e);
        }
        return ret;
    }
    /**
     * Recibe el número total de filas
     * @param total_filas_num total de filas.
     * @param error En la posición 0, mensaje de error; si lo hay
     * @return -1 si hay error, el número de filas de la consulta; si tiene éxito.
     */
    public boolean poner_total_filas_num(long total_filas_num, String [] error) {
        boolean ret = true;
        pagina_total_filas_num = String.valueOf(total_filas_num);
        long fin = Long.valueOf(pagina_fila_fin);
        if (fin > total_filas_num) {
            pagina_fila_fin = String.valueOf(total_filas_num);
        }
        return ret;
    }

    public String leer_marca(String campo) {
        String retorno = "";
        if (orden_ascendente != null && orden_ascendente.equals(campo)) {
            retorno = orden_ascendente_marca_tex;
        } else if (orden_descendente != null && orden_descendente.equals(campo)) {
            retorno = orden_descendente_marca_tex;
        }
        return retorno;
    }

    public String leer_campo_ordenacion() {
        String retorno = "";
        if (orden_ascendente != null) {
            retorno = orden_ascendente;
        } else if (orden_descendente != null) {
            retorno = orden_descendente;
        }
        return retorno;
    }
}
