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

import ENTIDAD.alumnoPracticaCronogramaCorteC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class alumnoPracticaCronogramaCorteDAO {
    public String insertar(alumnoPracticaCronogramaCorteC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_CORTE(1,?,?,?,?,?,?,?,?,?)}");            
            
            cs.setString(1, item.getAlumno());
            cs.setString(2, item.getCurso());
            cs.setInt(3, item.getCronograma());
            cs.setString(4, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString(5, item.getFut());
            cs.setString(6, item.getPractica());
            cs.setString(7, item.getAutorizado());
            cs.setString(8, item.getObservacion());
            cs.setInt(9, item.getEstadoRegistro());

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
        return codigo;
    }
    
    public String eliminar(alumnoPracticaCronogramaCorteC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_CORTE(2,?,?,?,?,?,?,?,?,?)}");            
            
            cs.setString(1, item.getAlumno());
            cs.setString(2, item.getCurso());
            cs.setInt(3, item.getCronograma());
            cs.setString(4, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString(5, item.getFut());
            cs.setString(6, item.getPractica());
            cs.setString(7, item.getAutorizado());
            cs.setString(8, item.getObservacion());
            cs.setInt(9, item.getEstadoRegistro());

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
        return codigo;
    }
}
