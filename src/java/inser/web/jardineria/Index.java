/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.web.jardineria;

import inser.web.utilidades.Controladores_vistas;
import inser.web.utilidades.Idiomas;
import inser.web.utilidades.Redirecciones;
import java.io.Serializable;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspWriter;

/**
 *
 * @author informatica
 */
public class Index extends Controladores_vistas implements Serializable {
    public int serverPort_https = 8443;
    public HttpServletRequest httpServletRequest;
    public HttpServletResponse httpServletResponse;
    public JspWriter jspWriter;
    public HttpSession httpSession;
    public Boolean error_ret = true;
    public String error_texto = ""; //NOI18N
    public String lenguaje = "es";
    public int fila = -1;

    public String getError_texto() {
        return error_texto;
    }

    public Boolean getError_ret() {
        return error_ret;
    }
    
    public Index() {
    }
    /**
     * Iniciar el JavaBean
     * @param request Objeto con la información de la solicitud
     * @param response Objeto con generar la respuesta
     * @param out Objeto para escribir en la página Jsp
     * @param session Objeto con la información de una sesión web
     */
    public void iniciar_javabean(HttpServletRequest request, HttpServletResponse response, JspWriter out, HttpSession session) {
        String [] error = { "" }; //NOI18N
        httpServletRequest = request;
        httpServletResponse = response;
        jspWriter = out;
        httpSession = session;
        if (error_ret) {
            obtener_parametros(httpServletRequest, error);
            error_ret = configurar_log(error);
        }
        error_texto = error[0];
    }
    
    /**
     * Configura el bean para trabajar con la página
     */
    public boolean configurar_pagina() {
        String [] error = { "" }; //NOI18N
        if (error_ret) {
            error_ret = Redirecciones.redirecionar_http_a_https(serverPort_https, httpServletRequest, httpServletResponse, error);
            if (error_ret) {
                error_ret = Idiomas.poner_lenguaje(lenguaje); //NOI18N
            }
        }
        error_texto = error[0];
        return error_ret;
    }
    
    public String controlador() {
        String [] error = { "" }; //NOI18N
        String retorno = null;
        if (error_ret) {
            retorno = controlador(error);
            error_ret = (retorno != null);
        }
        if (error_ret == false){
             escribir_error(error[0]);
        }
        error_texto = error[0];
        return retorno;
    }

    public String vista() {
        String [] error = { "" }; //NOI18N
        String retorno = null;
        if (error_ret) {
            retorno = vista(error);
            error_ret = (retorno != null);
        }
        if (error_ret == false){
             escribir_error(error[0]);
        }
        error_texto = error[0];
        return retorno;
    }
    /**
     * Devuelve uno de los datos de la url (parte * del pattern), el separador es el caracter "/" (comienzan desde 0)
     * @param pos Posición del dato
     * @return 
     */
    public String leer_dato_url(int pos) {
        String [] error = { "" }; //NOI18N
        String retorno = null;
        if (pathinfo_lista != null) {
            retorno = pathinfo_lista.get(pos);
        } 
        if (error_ret == false){
             escribir_error(error[0]);
        }
        error_texto = error[0];
        return retorno;
    }    
    /**
     * Escribe un mensaje de error, si lo hay.
     * @return true si todo va bien, false si hay error
     */
    public boolean tratar_error_texto() {
        String [] error = { "" }; //NOI18N
        if (error_ret == false) {
            escribir_error(error_texto);
        }
        error_texto = error[0];
        return error_ret;
    }
    
    /**
     * Escribe un mensaj.
     * @param mensaje Mensaje qeu escribir
     * @return true si todo va bien, false si hay error
     */
    public boolean escribir(String mensaje) {
        String [] error = { "" }; //NOI18N
        try {
            error_ret = true;
            if (mensaje.isBlank() == false) {
                jspWriter.print(mensaje);
            }
        } catch (Exception e) {
            error_ret = false;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("in/inser/web/jardineria/in").getString("EXCEPCIÓN AL ESCRIBIR EN EL JSP. {0}"), new Object[] {error[0]});
            Logger.getLogger("inser").log(Level.SEVERE, error[0], e);
        }
        error_texto = error[0];
        return error_ret;
    }
    
    /**
     * Escribe un mensaje de error, si lo hay.
     * @param mensaje Mensaje qeu escribir
     * @return true si todo va bien, false si hay error
     */
    public boolean escribir_error(String mensaje) {
        return escribir(mensaje);
    }
    
    public void presentar(String nombre) {
        String [] error = { "" }; //NOI18N
        String texto = "";
        if (error_ret) {
            texto = leer_columna_de_fila(nombre, fila, error);
            error_ret = (texto != null);
            error_texto = error[0];
        }
        if (error_ret) {
            escribir(texto);
        } else {
            escribir_error(error_texto);
        }
        error_texto = error[0];        
    }
    
    public String leer_columna_de_fila(String nombre, int fila, String [] error) {
        return null;
    }
    
    public boolean presentar_siguiente() {
        boolean ret = true;
        String [] error = { "" }; //NOI18N
        List lista;
        if (error_ret == false) {
            ret = false;
        } else {
            lista = leer_lista();
            if (lista == null) {
                error_ret = false;
            } else {
                fila = fila + 1;
                if (fila >= lista.size()) {
                    error_ret = false;
                    fila = -1;
                }
            }
        }
        if (error_ret == false) {
            ret = false;
            escribir_error(error_texto);
        }
        error_texto = error[0];        
        return ret;
    }
    
    public List leer_lista() {
        return null;
    }
    /**
     * Configura los logger de Java
     * Por defecto stderr y stdoout debe ir al log de Tomcat (carpeta logs, del servidor):
     * Asegurarse de que en el archivo context.xml, en la etiqueta Context, está configurado el atributo swallowOutput="true"
     * o lo esta en el archivo server.xml
     * @param error
     * @return 
     */
    public boolean configurar_log(String [] error) {
        boolean ret = true;
        Logger innui_logger;
        Logger inclui_logger;
        Logger inser_logger;
        Handler [] handlers_array;
        ConsoleHandler consoleHandler;
        Formatter formatter;
        consoleHandler = new ConsoleHandler();
        formatter = new SimpleFormatter();
        // Logger de las clases de modelo (sin interfaz de usuario)
        innui_logger = Logger.getLogger("innui");
        handlers_array = innui_logger.getHandlers();
        if (handlers_array.length == 0) {
            consoleHandler.setFormatter(formatter);
            innui_logger.addHandler(consoleHandler);
        } else {
            for (Handler handle: handlers_array) {
                handle.setFormatter(formatter);
            }
        }
        // Logger de las clases básicas de consola (out y err)
        inclui_logger = Logger.getLogger("inclui");
        handlers_array = inclui_logger.getHandlers();
        if (handlers_array.length == 0) {
            consoleHandler.setFormatter(formatter);
            inclui_logger.addHandler(consoleHandler);
        } else {
            for (Handler handle: handlers_array) {
                handle.setFormatter(formatter);
            }
        }
        // Logger de las clases de servidor
        inser_logger = Logger.getLogger("inser");
        handlers_array = inser_logger.getHandlers();
        if (handlers_array.length == 0) {
            consoleHandler.setFormatter(formatter);
            inser_logger.addHandler(consoleHandler);
        } else {
            for (Handler handle: handlers_array) {
                handle.setFormatter(formatter);
            }
        }
        return ret;
    }
}
