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

import ENTIDAD.invCursoSeccionGrupoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class invCursoSeccionGrupoDAO {
    
    
    public String insertar(invCursoSeccionGrupoC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_INV_CURSO_SECCION_GRUPO(?,?,?,?,?,?,?,?,?,?, ?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setInt(7, item.getGrupoExamen());
            cs.setString(8, item.getTema());
            cs.setInt(9, item.getVacanteMaximo());
            cs.setInt(10, item.getVacanteActual());
            cs.setInt(11, item.getEstadoRegistro());

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
    
    
    public String eliminar(invCursoSeccionGrupoC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM DI.INV_CURSO_SECCION_GRUPO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND GRUPO_EXAMEN=?");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setInt(7, item.getGrupoExamen());
          

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
    
    public List<invCursoSeccionGrupoC> mostrarGrupos(int institucion,String periodo,String carrera,String curso,String seccion) {

        List<invCursoSeccionGrupoC> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        invCursoSeccionGrupoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.INV_CURSO_SECCION_GRUPO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA like ? AND CURSO like ? AND SECCION like ? ORDER BY GRUPO_EXAMEN");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new invCursoSeccionGrupoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));
                item.setTema(rs.getString("TEMA"));
                item.setVacanteMaximo(rs.getInt("VACANTE_MAXIMO"));
                item.setVacanteActual(rs.getInt("VACANTE_ACTUAL"));
                item.setFechaAsistente(rs.getDate("FECHA_ASISTENTE"));
                item.setFechaPonente(rs.getDate("FECHA_PONENTE"));
                item.setModalidad(rs.getString("MODALIDAD"));
                item.setHoraInicio(rs.getTimestamp("HORA_INICIO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
           
            System.out.println(ex.getSQLState());
        }
        return lista;
    }
    
    
    
    public invCursoSeccionGrupoC mostrarCursoSeccionGrupo(int institucion,String periodo,String carrera,String malla,String curso,String seccion,int grupo) {
                    
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        
        invCursoSeccionGrupoC item=null;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM di.INV_CURSO_SECCION_GRUPO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND GRUPO_EXAMEN=? AND ESTADO_REGISTRO=1");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);                        
            cs.setString(6, seccion);            
            cs.setInt(7, grupo);            
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new invCursoSeccionGrupoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));
                item.setTema(rs.getString("TEMA"));
                item.setVacanteMaximo(rs.getInt("VACANTE_MAXIMO"));
                item.setVacanteActual(rs.getInt("VACANTE_ACTUAL"));
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
    
    
    public List<invCursoSeccionGrupoC> mostrarCursoSeccionGrupo(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String grupo,String alumno) {
                    
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<invCursoSeccionGrupoC> lista=new ArrayList<>();
        invCursoSeccionGrupoC item=null;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.INV_CURSO_SECCION_GRUPO A WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA LIKE ? AND A.MALLA LIKE ? AND A.CURSO LIKE ? AND A.SECCION LIKE ? AND A.GRUPO_EXAMEN LIKE ?  AND EXISTS(SELECT * FROM DI.INV_ALUMNO_CURSO_SECCION_GRUPO B WHERE  B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO AND B.CARRERA=A.CARRERA AND B.MALLA=A.MALLA AND B.CURSO=A.CURSO AND B.SECCION=A.SECCION AND B.GRUPO_EXAMEN=A.GRUPO_EXAMEN AND  B.ALUMNO LIKE ?)");            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);                        
            cs.setString(6, seccion);            
            cs.setString(7, grupo);            
            cs.setString(8, alumno);            
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new invCursoSeccionGrupoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));
                item.setTema(rs.getString("TEMA"));
                item.setVacanteMaximo(rs.getInt("VACANTE_MAXIMO"));
                item.setVacanteActual(rs.getInt("VACANTE_ACTUAL"));
                item.setFechaAsistente(rs.getDate("FECHA_ASISTENTE"));
                item.setFechaPonente(rs.getDate("FECHA_PONENTE"));
                item.setModalidad(rs.getString("MODALIDAD"));
                item.setHoraInicio(rs.getTimestamp("HORA_INICIO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error " + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
    
           
}
