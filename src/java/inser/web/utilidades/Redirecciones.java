/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.web.utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author informatica
 */
public class Redirecciones {
    /**
     * Redirecciona a protocolo seguro.
     * @param serverPort_https Puerto seguro
     * @param httpServletRequest objeto con la información de la petición
     * @param httpServletResponse objeto para dar respuesta
     * @param error En la posición 0. Mensaje de error
     * @return Devuelve true si ya está en protocolo seguro, es decir: no hay que redireccionar. false si hay que eredireccionar o hubo error (ver error[0]
     */
    public static boolean redirecionar_http_a_https(int serverPort_https, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String [] error){
        boolean ret = true;
        String scheme;
        String requestedURL;
        StringBuffer requestedURL_stringbuilder;
        String serverName;
        int serverPort;
        try {
            scheme = httpServletRequest.getScheme();
            scheme = scheme.toLowerCase();
            if (scheme.startsWith("https") == false) {
//                requestedURI = httpServletRequest.getRequestURI();
                serverName = httpServletRequest.getServerName();
                requestedURL_stringbuilder = httpServletRequest.getRequestURL();
                requestedURL = requestedURL_stringbuilder.toString();
                requestedURL = requestedURL.replaceFirst("http://", "https://");
                serverPort = httpServletRequest.getServerPort();
                if (requestedURL.indexOf(":" + serverPort) >= 0) {
                    requestedURL = requestedURL.replaceFirst(":" + serverPort, "");
                }
                serverPort = serverPort_https;
                requestedURL = requestedURL.replaceFirst(serverName, serverName + ":" + serverPort);
                httpServletResponse.sendRedirect(requestedURL);
                ret = false;
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error la redirecionar de http a https. " + error[0];
            ret = false;
            Logger.getLogger("inser").log(Level.SEVERE, error[0], e);
        }
        return ret;
    }
    
}
