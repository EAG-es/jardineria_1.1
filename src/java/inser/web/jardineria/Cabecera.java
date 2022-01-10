/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.web.jardineria;

import inser.web.utilidades.Idiomas;
import java.io.Serializable;

/**
 *
 * @author informatica
 */
public class Cabecera extends Index implements Serializable {
    public static String lenguaje_tex = "lenguaje";
    public Cabecera() {
        
    }
    
    public void procesar() {
        String [] error = { "" }; //NOI18N
        String lenguaje = httpServletRequest.getParameter(lenguaje_tex);
        if (lenguaje != null) {
            this.lenguaje = lenguaje;
            error_ret = Idiomas.poner_lenguaje(lenguaje);
//            throw new RuntimeException("Error forzado");
        }
        if (error_ret == false){
             escribir_error(error[0]);
        }
        error_texto = error[0];
    }
}
