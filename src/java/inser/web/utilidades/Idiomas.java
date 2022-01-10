/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.web.utilidades;

import java.util.Locale;

/**
 *
 * @author informatica
 */
public class Idiomas {
    
    public static boolean poner_lenguaje(String lenguaje) {
        boolean ret = true;
        Locale locale = new Locale(lenguaje);
        Locale.setDefault(locale);
        return ret;
    }
    
    public static boolean poner_lenguaje(String lenguaje, String pais) {
        boolean ret = true;
        Locale locale = new Locale(lenguaje, pais);
        Locale.setDefault(locale);
        return ret;
    }
}
