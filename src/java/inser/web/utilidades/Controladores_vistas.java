/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.web.utilidades;

import java.util.Arrays;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author informatica
 */
public class Controladores_vistas {
    public static String index = "index";
    public static String consulta = "consulta";
    public static String modificacion = "modificacion";
    public static String visual = "visual";
    public static String creacion = "creacion";
    public static String alta = creacion;
    public static String borrado = "borrado";
    public static String baja = borrado;
    public static String listado = consulta;
    public static String login = "login";
    public static String logout = "logout";
    public static String registro = "registro";
    public List <String> pathinfo_lista;

    public static String getIndex() {
        return index;
    }

    public static String getConsulta() {
        return consulta;
    }

    public static String getAlta() {
        return alta;
    }

    public static String getBaja() {
        return baja;
    }

    public static String getModificacion() {
        return modificacion;
    }

    public static String getVisual() {
        return visual;
    }

    public static String getCreacion() {
        return creacion;
    }

    public static String getBorrado() {
        return borrado;
    }

    public static String getListado() {
        return listado;
    }

    public static String getLogin() {
        return login;
    }

    public static String getLogout() {
        return logout;
    }

    public static String getRegistro() {
        return registro;
    }
    
    public List <String> obtener_parametros(HttpServletRequest httpServletRequest, String [] error) {
        boolean ret = true;
        String pathinfo;
        List <String> lista = null;
        // https://localhost:8443/hola_mundo_web/index/*
        // https://localhost:8443/hola_mundo_web/index/producto/list
        pathinfo = httpServletRequest.getPathInfo();
        if (pathinfo != null) {
            pathinfo = pathinfo.trim();
            if (pathinfo.startsWith("/")) {
                pathinfo = pathinfo.substring("/".length());
            }
            String [] trozos_array = pathinfo.split("/");
            lista = Arrays.asList(trozos_array);
            pathinfo_lista = lista;
        }
        return lista;
    }

    public String controlador(String [] error) {
        boolean ret = true;
        String retorno = null;
        if (pathinfo_lista != null) {
            retorno = pathinfo_lista.get(0);
        } 
        if (retorno == null) {
            retorno = index; //NOI18N
        }
        return retorno;
    }
    
    public String vista(String [] error) {
        boolean ret = true;
        String retorno = null;
        if (pathinfo_lista != null) {
            retorno = pathinfo_lista.get(1);
        } 
        if (retorno == null) {
            retorno = consulta; //NOI18N
        }
        return retorno;
    }
    /**
     * Devuelve uno de los datos de la url (parte * del pattern), el separador es el caracter "/" (comienzan desde 0)
     * @param pos Posición del dato
     * @param error
     * @return 
     */
    public String leer_dato_url(int pos, String [] error) {
        boolean ret = true;
        String retorno = null;
        if (pathinfo_lista != null) {
            retorno = pathinfo_lista.get(pos);
        } 
        return retorno;
    }
    
    /**
     * Establece el valor de uno de los datos de la url (parte * del pattern), el separador es el caracter "/" (comienzan desde 0)
     * @param pos Posición del dato
     * @param error
     * @return 
     */
    public String poner_dato_url(int pos, String valor, String [] error) {
        boolean ret = true;
        String retorno = null;
        if (pathinfo_lista != null) {
            pathinfo_lista.set(pos, valor);
        } 
        return retorno;
    }
    
}
