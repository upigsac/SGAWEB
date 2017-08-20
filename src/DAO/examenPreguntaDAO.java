/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.examenPreguntaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class examenPreguntaDAO {
    public boolean insertar(examenPreguntaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_EXAMEN_PREGUNTA(1,?,?,?)}");
            cs.setInt(1, item.getPregunta());
            cs.setString(2, item.getDescripcion());
            cs.setInt(3, item.getEstadoRegistro());
            

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
            
        }
        return rpta;
    }
}
