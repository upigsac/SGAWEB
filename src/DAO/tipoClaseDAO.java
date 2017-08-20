/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoClaseC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.util;


public class tipoClaseDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public tipoClaseC mostrarTipoClase(int tipoClase) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoClaseC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_TIPO_CLASE WHERE TIPO_CLASE=?");
            cs.setInt(1, tipoClase);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoClaseC();
                item.setTipoclase(rs.getInt("TIPO_CLASE"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
