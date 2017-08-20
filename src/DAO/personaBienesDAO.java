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

import ENTIDAD.personaBienesC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Beans.util;


public class personaBienesDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean insertarPersonaBienes(personaBienesC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_PER_BIENES(?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getBienes());
            cs.setInt(3, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return rpta;
    }
}
