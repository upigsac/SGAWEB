/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.estadoC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class estadoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public estadoC mostrarEstadoCodigo(int estado) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        estadoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from [UPA].[SYS_ESTADO_REGISTROS] where ESTADO_REGISTRO=?");
            cs.setInt(1, estado);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new estadoC();
                item.setEstado(rs.getInt("ESTADO_REGISTRO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTipo(rs.getInt("TIPO_ESTADO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

}
