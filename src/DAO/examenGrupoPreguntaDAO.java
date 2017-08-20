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
import ENTIDAD.examenGrupoPreguntaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class examenGrupoPreguntaDAO {
    
    
    
    public boolean insertar(examenGrupoPreguntaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_EXAMEN_GRUPO_PREGUNTA(1,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getExamen());
            cs.setInt(2, item.getGrupo());
            cs.setInt(3, item.getPregunta());
            cs.setInt(4, item.getOrden());
            cs.setInt(5, item.getMinuto());
            cs.setInt(6, item.getRespuestaCorrecta());            
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
