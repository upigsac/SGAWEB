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


import ENTIDAD.cursoSeccionSecundarioC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class cursoSeccionSecundarioDAO {
    
    
    public String insertar(cursoSeccionSecundarioC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_CURSO_SECCION_SECUNDARIO(1,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPrincipal());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setString(4, item.getCarrera());
            cs.setString(5, item.getMalla());
            cs.setString(6, item.getCurso());
            cs.setString(7, item.getSeccion());         
            cs.setInt(8, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                util.consolaCliente( "guardo..");
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
    
    public String eliminar(cursoSeccionSecundarioC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_CURSO_SECCION_SECUNDARIO(2,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPrincipal());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setString(4, item.getCarrera());
            cs.setString(5, item.getMalla());
            cs.setString(6, item.getCurso());
            cs.setString(7, item.getSeccion());         
            cs.setInt(8, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                util.consolaCliente( "eliminado");
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
    
    
    public cursoSeccionSecundarioC mostrarcursoSeccionSecundario(int institucion, String periodo, String malla, String carrera, String curso, String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionSecundarioC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.HOR_CURSO_SECCION_SECUNDARIO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionSecundarioC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));                
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));              
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());

        }
        return item;
    }
   
    
    
}
