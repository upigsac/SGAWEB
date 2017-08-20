/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.opcionProgramaC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class opcionProgramaDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<opcionProgramaC> MostarOpcion() {
        List<opcionProgramaC> m = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionProgramaC programa ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM [SIGU].[SEG_PROGRAMA] ");

            rs = cs.executeQuery();

            while (rs.next()) {

                programa = new opcionProgramaC();
                programa.setPrograma(rs.getInt("programa"));
                programa.setDescripcion(rs.getString("descripcion"));
                programa.setAbreviatura(rs.getString("abreviatura"));
                programa.setEstado(rs.getInt("estado_registro"));
                m.add(programa);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

}
