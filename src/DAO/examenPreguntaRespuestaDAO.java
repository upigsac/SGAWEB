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
import ENTIDAD.examenPreguntaRespuestaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class examenPreguntaRespuestaDAO {
    public boolean insertar(examenPreguntaRespuestaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_EXAMEN_PREGUNTA_RESPUESTA(1,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getExamen());
            cs.setInt(2, item.getGrupo());
            cs.setInt(3, item.getPregunta());
            cs.setInt(4, item.getRespuesta());
            cs.setInt(5, item.getOrden());
            cs.setDouble(6, item.getValor());            
            cs.setInt(7, item.getEstadoRegistro());
            

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
