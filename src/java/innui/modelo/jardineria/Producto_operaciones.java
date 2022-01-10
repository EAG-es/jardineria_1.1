/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelo.jardineria;

import inser.web.utilidades.Paginacion_busquedas_ordenaciones_modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author informatica
 */
public class Producto_operaciones {
    public Connection connection = null;
    
    public long contar_filas(String [] error) {
        boolean ret = true;
        long retorno = -1;
        String sql_comando;
        Statement statement;
        ResultSet resultSet;
        try {
            sql_comando = "select count("
                + Producto.codigo_producto_tex + ") "
                + "from producto ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql_comando);
            resultSet.next();
            retorno = resultSet.getInt(1);
        } catch (Exception e) {
            retorno = -1;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al listar productos. "
                    + error[0];
            Logger.getLogger("innui").log(Level.SEVERE, error[0], e);
        }
        return retorno;
    }
    /**
     * Devuelve una lista con las filas de la tabla productos 
     * @param paginacion_busquedas_ordenaciones_modelo Datos para la paginacion.
     * @param error En la posición 0, mensaje de error, si lo hay. 
     * @return null si hay error, una lista de productos si no hay error
     */
    public List<Producto> listar(Paginacion_busquedas_ordenaciones_modelo paginacion_busquedas_ordenaciones_modelo, String [] error) {
        boolean ret = true;
        List lista = null;
        String sql_comando;
        Producto producto;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            sql_comando = "select "
                + Producto.codigo_producto_tex + ", "
                + Producto.nombre_tex + ", "
                + Producto.gama_tex + ", "
                + Producto.dimensiones_tex + ", "
                + Producto.proveedor_tex + ", "
                + Producto.descripcion_tex + ", "
                + Producto.cantidad_en_stock_tex + ", "
                + Producto.precio_venta_tex + ", "
                + Producto.precio_proveedor_tex + " "
                + "from producto ";
            if (paginacion_busquedas_ordenaciones_modelo.filtro != null
                    && paginacion_busquedas_ordenaciones_modelo.filtro.isBlank() == false) {
                sql_comando = sql_comando 
                    + "where "
                    + Producto.descripcion_tex + " like ? ";
            }
            if (paginacion_busquedas_ordenaciones_modelo.orden_ascendente != null) {
                sql_comando = sql_comando 
                        + "order by " + paginacion_busquedas_ordenaciones_modelo.orden_ascendente + " asc ";
            } else if (paginacion_busquedas_ordenaciones_modelo.orden_descendente != null) {
                sql_comando = sql_comando 
                        + "order by " + paginacion_busquedas_ordenaciones_modelo.orden_descendente + " desc ";
            }
            sql_comando = sql_comando 
                + "limit ?, ? ";
            preparedStatement = connection.prepareStatement(sql_comando);
            int i = 1;
            if (paginacion_busquedas_ordenaciones_modelo.filtro != null
                    && paginacion_busquedas_ordenaciones_modelo.filtro.isBlank() == false) {
                String filtro_like = "%" + paginacion_busquedas_ordenaciones_modelo.filtro + "%";
                preparedStatement.setString(i, filtro_like);
                i = i + 1;
            }
            preparedStatement.setInt(i, paginacion_busquedas_ordenaciones_modelo.pagina_fila_inicio_num - 1);
            i = i + 1;
            preparedStatement.setInt(i, paginacion_busquedas_ordenaciones_modelo.pagina_tam);
            resultSet = preparedStatement.executeQuery();
            lista = new LinkedList();
            while (resultSet.next() ) {
                producto = new Producto();
                producto.codigo_producto = resultSet.getString(Producto.codigo_producto_tex);
                producto.nombre = resultSet.getString(Producto.nombre_tex);
                producto.gama = resultSet.getString(Producto.gama_tex);                
                producto.dimensiones = resultSet.getString(Producto.dimensiones_tex);                
                producto.proveedor  = resultSet.getString(Producto.proveedor_tex);
                producto.descripcion = resultSet.getString(Producto.descripcion_tex);
                producto.cantidad_en_stock = resultSet.getInt(Producto.cantidad_en_stock_tex);
                producto.precio_venta = resultSet.getDouble(Producto.precio_venta_tex);
                producto.precio_proveedor = resultSet.getDouble(Producto.precio_proveedor_tex);
                lista.add(producto);
            }
        } catch (Exception e) {
            lista = null;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al listar productos. "
                    + error[0];
            Logger.getLogger("innui").log(Level.SEVERE, error[0], e);
        }
        return lista;
    }
    
    /**
     * Devuelve una fila de la tabla productos 
     * @param error En la posición 0, mensaje de error, si lo hay. 
     * @return null si hay error, una lista de productos si no hay error
     */
    public Producto encontrar(String codigo_producto, String [] error) {
        boolean ret = true;
        String sql_comando;
        Producto producto = null;
        PreparedStatement preparedStatement;
        try {
            sql_comando = "select "
                + Producto.codigo_producto_tex + ", "
                + Producto.nombre_tex + ", "
                + Producto.gama_tex + ", "
                + Producto.dimensiones_tex + ", "
                + Producto.proveedor_tex + ", "
                + Producto.descripcion_tex + ", "
                + Producto.cantidad_en_stock_tex + ", "
                + Producto.precio_venta_tex + ", "
                + Producto.precio_proveedor_tex + " "
                + "from producto "
                + "where "
                + Producto.codigo_producto_tex + " = ? ";
            preparedStatement = connection.prepareStatement(sql_comando);
            preparedStatement.setString(1, codigo_producto);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            producto = new Producto();
            producto.codigo_producto = resultSet.getString(Producto.codigo_producto_tex);
            producto.nombre = resultSet.getString(Producto.nombre_tex);
            producto.gama = resultSet.getString(Producto.gama_tex);                
            producto.dimensiones = resultSet.getString(Producto.dimensiones_tex);                
            producto.proveedor  = resultSet.getString(Producto.proveedor_tex);
            producto.descripcion = resultSet.getString(Producto.descripcion_tex);
            producto.cantidad_en_stock = resultSet.getInt(Producto.cantidad_en_stock_tex);
            producto.precio_venta = resultSet.getDouble(Producto.precio_venta_tex);
            producto.precio_proveedor = resultSet.getDouble(Producto.precio_proveedor_tex);

        } catch (Exception e) {
            producto = null;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al encontrar producto. "
                    + error[0];
            Logger.getLogger("innui").log(Level.SEVERE, error[0], e);
        }
        return producto;
    }
    
    public boolean borrar(String codigo_producto, String [] error) {
        boolean ret = true;
        String sql_comando;
        PreparedStatement preparedStatement;
        try {
            sql_comando = "delete from producto where codigo_producto = ?";
            preparedStatement = connection.prepareStatement(sql_comando);
            preparedStatement.setString(1, codigo_producto);
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                ret = false;
                error[0] = "Error al borrar producto: " + codigo_producto;
            }
        } catch (Exception e) {
            ret = false;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al borrar producto. "
                    + error[0];
            Logger.getLogger("innui").log(Level.SEVERE, error[0], e);
        }
        return ret;
    }

    public boolean crear(Producto producto, String [] error) {
        boolean ret = true;
        String sql_comando;
        PreparedStatement preparedStatement;
        try {
            sql_comando = "insert into producto ("
                + Producto.codigo_producto_tex + ", "
                + Producto.nombre_tex + ", "
                + Producto.gama_tex + ", "
                + Producto.dimensiones_tex + ", "
                + Producto.proveedor_tex + ", "
                + Producto.descripcion_tex + ", "
                + Producto.cantidad_en_stock_tex + ", "
                + Producto.precio_venta_tex + ", "
                + Producto.precio_proveedor_tex + " "
                + ") values ("
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?)";
            preparedStatement = connection.prepareStatement(sql_comando);
            preparedStatement.setString(1, producto.codigo_producto);
            preparedStatement.setString(2, producto.nombre);
            preparedStatement.setString(3, producto.gama);
            if (producto.dimensiones== null || producto.dimensiones.isBlank()) {
                preparedStatement.setNull(4, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(4, producto.dimensiones);
            }
            if (producto.proveedor== null || producto.proveedor.isBlank()) {
                preparedStatement.setNull(5, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(5, producto.proveedor);
            }
            if (producto.descripcion== null || producto.descripcion.isBlank()) {
                preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(6, producto.descripcion);
            }
            preparedStatement.setInt(7, producto.cantidad_en_stock);
            preparedStatement.setDouble(8, producto.precio_venta);
            if ((int)(producto.precio_proveedor*1000) == 0) {
                preparedStatement.setNull(9, java.sql.Types.DECIMAL);
            } else {
                preparedStatement.setDouble(9, producto.precio_proveedor);
            }
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                ret = false;
                error[0] = "Error al crear el producto: " + producto.codigo_producto;
            }
        } catch (Exception e) {
            ret = false;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al crear producto. "
                    + error[0];
            Logger.getLogger("innui").log(Level.SEVERE, error[0], e);
        }
        return ret;
    }

    public boolean modificar(Producto producto, String [] error) {
        boolean ret = true;
        String sql_comando;
        PreparedStatement preparedStatement;
        try {
            sql_comando = "update producto set "
                + Producto.codigo_producto_tex + " = ?, "
                + Producto.nombre_tex + " = ?, "
                + Producto.gama_tex + " = ?, "
                + Producto.dimensiones_tex + " = ?, "
                + Producto.proveedor_tex + " = ?, "
                + Producto.descripcion_tex + " = ?, "
                + Producto.cantidad_en_stock_tex + " = ?, "
                + Producto.precio_venta_tex + " = ?, "
                + Producto.precio_proveedor_tex + " = ? "
                + "where "
                + Producto.codigo_producto_tex + " = ? ";
            preparedStatement = connection.prepareStatement(sql_comando);
            preparedStatement.setString(1, producto.codigo_producto);
            preparedStatement.setString(2, producto.nombre);
            preparedStatement.setString(3, producto.gama);
            if (producto.dimensiones== null || producto.dimensiones.isBlank()) {
                preparedStatement.setNull(4, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(4, producto.dimensiones);
            }
            if (producto.proveedor== null || producto.proveedor.isBlank()) {
                preparedStatement.setNull(5, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(5, producto.proveedor);
            }
            if (producto.descripcion== null || producto.descripcion.isBlank()) {
                preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(6, producto.descripcion);
            }
            preparedStatement.setInt(7, producto.cantidad_en_stock);
            preparedStatement.setDouble(8, producto.precio_venta);
            if ((int)(producto.precio_proveedor*1000) == 0) {
                preparedStatement.setNull(9, java.sql.Types.DECIMAL);
            } else {
                preparedStatement.setDouble(9, producto.precio_proveedor);
            }
            preparedStatement.setString(10, producto.codigo_producto);
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                ret = false;
                error[0] = "Error al actualizar el producto: " + producto.codigo_producto;
            }
        } catch (Exception e) {
            ret = false;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al actualizar producto. "
                    + error[0];
            Logger.getLogger("innui").log(Level.SEVERE, error[0], e);
        }
        return ret;
    }
}
