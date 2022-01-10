/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelo.jardineria;

/**
 *
 * @author informatica
 */
public class Producto {
    public static String codigo_producto_tex = "codigo_producto";
    public static String nombre_tex = "nombre";
    public static String gama_tex = "gama";
    public static String dimensiones_tex = "dimensiones";
    public static String proveedor_tex = "proveedor";
    public static String descripcion_tex = "descripcion";
    public static String cantidad_en_stock_tex = "cantidad_en_stock";
    public static String precio_venta_tex = "precio_venta";
    public static String precio_proveedor_tex = "precio_proveedor";
    public String codigo_producto;
    public String nombre;
    public String gama;
    public String dimensiones;
    public String proveedor;
    public String descripcion;
    public int cantidad_en_stock;
    public double precio_venta;
    public double precio_proveedor;   

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGama() {
        return gama;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad_en_stock() {
        return cantidad_en_stock;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public double getPrecio_proveedor() {
        return precio_proveedor;
    }
}
