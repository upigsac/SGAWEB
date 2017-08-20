/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Beans.util;


public class reprogramacionDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean insertarNotaAlumnoReprogramacion(String periodo, String malla, String carrera, String curso, String seccion, String alumno, String tipo, double nota, String personal, String observacion) {
        Connection c ;
        CallableStatement cs ;

        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  SIGU.WEB_SQL_ACA_ALUMNO_CURSO_NOTA  (1, 1, ?, ?, ?, ?, ?, ?, ?,?,?,?,'1','')}");
            cs.setString(1, periodo);
            cs.setString(2, carrera);
            cs.setString(3, malla);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, alumno);
            cs.setString(7, tipo);
            cs.setDouble(8, nota);
            cs.setString(9, personal);
            cs.setString(10, observacion);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            e.printStackTrace();
        }
        return rpta;
    }
}
