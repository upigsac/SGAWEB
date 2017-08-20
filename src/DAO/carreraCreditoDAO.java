/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.carreraCreditoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class carreraCreditoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public carreraCreditoC mostrarCarreras(int institucion, String periodo, String carrera, int nivel) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carreraCreditoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from SIGU.HOR_CARRERA_CREDITO where INSTITUCION=? and PERIODO=? and CARRERA=? and NIVEL=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setInt(4, nivel);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new carreraCreditoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getInt("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setNivel(rs.getInt("NIVEL"));
                item.setCreditos(rs.getInt("CREDITOS"));
                item.setMaxCreditos(rs.getInt("CREDITOS_MAX"));
                item.setMinCreditos(rs.getInt("CREDITOS_MIN"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return item;
    }
}
