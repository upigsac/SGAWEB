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
import ENTIDAD.alumnoCursoSeccionGrupoC;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;
import javax.swing.JOptionPane;


public class alumnoCursoSeccionGrupoDAO {
     public String insertar(alumnoCursoSeccionGrupoC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CURSO_SECCION_GRUPO(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setInt(8, item.getGrupo());  
            cs.setInt(9, item.getEstadoRegistro());
            rpta = cs.executeUpdate() >= 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return codigo;
    }
     
     public String eliminar(alumnoCursoSeccionGrupoC item) {          
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_ELIMINAR_ALUMNO_CURSO_SECCION_GRUPO](?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setInt(8, item.getGrupo());  
            cs.setInt(9, item.getEstadoRegistro());
            rpta = cs.executeUpdate() >= 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return codigo;
    }
     
}
