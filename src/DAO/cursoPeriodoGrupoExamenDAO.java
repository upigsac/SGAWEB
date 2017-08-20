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

import ENTIDAD.cursoPeriodoGrupoExamenC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class cursoPeriodoGrupoExamenDAO {
    public String insertar(cursoPeriodoGrupoExamenC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CURSO_PERIODO_GRUPO_EXAMEN(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setInt(6, item.getGrupoExamen());            
            cs.setInt(7, item.getMaximoVacantes());            
            cs.setInt(8, item.getEstadoRegistro());
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
        }
        return codigo;
    }
    
    public List<cursoPeriodoGrupoExamenC> mostrarListaCursoPeriodoGrupoExamen(int institucion, String periodo, String carrera,String malla,String curso) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoPeriodoGrupoExamenC item ;
        List<cursoPeriodoGrupoExamenC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.HOR_CURSO_PERIODO_GRUPO_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            
            
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoPeriodoGrupoExamenC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));
                item.setMaximoVacantes(rs.getInt("MAXIMO_VACANTES"));
                item.setVacantesActuales(rs.getInt("ACTUAL_VACANTES"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
           
        }
        return lista;
    }
    public cursoPeriodoGrupoExamenC mostrarCursoPeriodoGrupoExamen(int institucion,String periodo,String carrera,String malla,String curso,int grupoExamen) {
                    
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        
        cursoPeriodoGrupoExamenC item=null;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM di.HOR_CURSO_PERIODO_GRUPO_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND GRUPO_EXAMEN=? AND ESTADO_REGISTRO=1");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);                        
            cs.setInt(6, grupoExamen);            
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new cursoPeriodoGrupoExamenC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setMaximoVacantes(rs.getInt("MAXIMO_VACANTES"));
                item.setVacantesActuales(rs.getInt("ACTUAL_VACANTES"));                
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error " + e.getMessage());
            //e.printStackTrace();
        }

        return item;
    }  
    
    public int mostrarTotalVacantes(int institucion, String periodo, String carrera, String curso ,String grupoExamen) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        int totalMatriculados=0 ;
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT SUM(MAXIMO_VACANTES)MATRICULADOS FROM DI.HOR_CURSO_PERIODO_GRUPO_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND CURSO=?  AND GRUPO_EXAMEN LIKE ?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, grupoExamen);

            rs = cs.executeQuery();

            while (rs.next()) {                
                totalMatriculados=(rs.getInt("MATRICULADOS"));              
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return totalMatriculados;
    }
}
