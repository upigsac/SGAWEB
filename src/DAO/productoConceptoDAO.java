/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.productoConceptoC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class productoConceptoDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<productoConceptoC> mostrarProductoConcepto(int institucion,String producto) {

        List<productoConceptoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        productoConceptoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.VTS_PRODUCTO_CONCEPTO where INSTITUCION=? AND  PRODUCTO=? ");
            cs.setInt(1, institucion);
            cs.setString(2, producto);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new productoConceptoC();

                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setPaquete(rs.getString("PAQUETE"));
                item.setProducto(rs.getString("PRODUCTO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_DESCUENTO"));
                item.setDescucento(rs.getString("DESCUENTO"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setTipoNivelModular(rs.getInt("TIPO_NIVEL_MODULAR"));
                item.setNumCuotas(rs.getInt("NUM_CUOTAS"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
