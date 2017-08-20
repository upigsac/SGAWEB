/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.opcionModuloC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class opcionModuloDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<opcionModuloC> MostarOpcion(int programa) {
        List<opcionModuloC> m = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionModuloC modulo ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM [SIGU].[SEG_MODULO] where PROGRAMA=?");
            cs.setInt(1, programa);

            rs = cs.executeQuery();

            while (rs.next()) {

                modulo = new opcionModuloC();
                modulo.setPrograma(rs.getInt("programa"));
                modulo.setModulo(rs.getInt("modulo"));
                modulo.setDescripcion(rs.getString("descripcion"));
                modulo.setAbreviatura(rs.getString("descripcion"));
                m.add(modulo);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

}
