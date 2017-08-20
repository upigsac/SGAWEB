/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.registroAsistencia;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.alumnoCursoAsistenciaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class registroAsistenciaDAO {
    
    public String insertar(alumnoCursoAsistenciaC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_ASISTENCIA(1,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setString(8, util.convertDate(item.getFecha(), "dd-MM-yyyy") );
            cs.setBoolean(9, item.isAsistencia());
            cs.setInt(10, item.getEstadoRegistro());

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
    
    
    
    public List<registroAsistencia.fechaAsistencia> mostrarFechaAsistencia(int institucion,String periodo,String carrera,String malla,String curso,String seccion,Date fechaIni,Date fechaFin) {
      
        
        List<registroAsistencia.fechaAsistencia> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        registroAsistencia.fechaAsistencia item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_ACA_ALUMNO_CURSO_ASISTENCIA(1,?,?,?,?,?,?,'','',?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, util.convertDate(fechaIni, "dd-MM-yyyy"));
            cs.setString(8, util.convertDate(fechaFin, "dd-MM-yyyy"));

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new registroAsistencia.fechaAsistencia();
                item.setFecha(rs.getDate("FECHA"));
                item.setDia(rs.getInt("DIA"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));                          
                item.setTema(rs.getString("TEMA_DESARROLLADO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));                          
                item.setBandera(true);
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        
            System.out.println( "erroress" + e.getMessage());
        }
        return lista;
    }
    
    public List<registroAsistencia.fechaAsistencia> mostrarFechaAsistencia(int institucion,String periodo,String carrera,String malla,String curso,String seccion) {
      
        
        List<registroAsistencia.fechaAsistencia> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        registroAsistencia.fechaAsistencia item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_ACA_ALUMNO_CURSO_ASISTENCIA(5,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
          

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new registroAsistencia.fechaAsistencia();
                item.setFecha(rs.getDate("FECHA"));
                item.setDia(rs.getInt("DIA"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));                          
                item.setTema(rs.getString("TEMA_DESARROLLADO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));                          
                item.setBandera(true);
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        
            System.out.println( "erroress" + e.getMessage());
        }
        return lista;
    }
    
     public List<registroAsistencia.alumnoRegistroAsistencia> mostrarRegistroAsistencia(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String alumno) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<registroAsistencia.alumnoRegistroAsistencia> lista = new ArrayList<>();
        registroAsistencia.alumnoRegistroAsistencia item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_ACA_ALUMNO_CURSO_ASISTENCIA(2,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new registroAsistencia.alumnoRegistroAsistencia();
                
                item.setFecha(rs.getDate("FECHA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setAsitencia(rs.getBoolean("ASISTENCIA"));                          
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        
            System.out.println( "erroress" + e.getMessage());
        }
        return lista;
    }
     public List<registroAsistencia.alumnoCursoAsistencia> mostrarAlumnoCursoAsistencia(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String alumno) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<registroAsistencia.alumnoCursoAsistencia> lista = new ArrayList<>();
        registroAsistencia.alumnoCursoAsistencia item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_ACA_ALUMNO_CURSO_ASISTENCIA(3,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new registroAsistencia.alumnoCursoAsistencia();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));                          
                item.setDesPersona(rs.getString("DESPERSONA"));            
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));                          
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        
            System.out.println( "erroress" + e.getMessage());
        }
        return lista;
    }
     
     public List<registroAsistencia.personalCursoSeccion> mostrarPersonalCursoSeccion(int institucion, String periodo, String personal) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        registroAsistencia.personalCursoSeccion item;
        List<registroAsistencia.personalCursoSeccion> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_ACA_ALUMNO_CURSO_ASISTENCIA (4,?,?,'','','','','',?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new registroAsistencia.personalCursoSeccion();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
               item.setDesSeccion(rs.getString("DESSECCION"));
               item.setDesturno(rs.getString("DESTURNO"));
               item.setTurno(rs.getInt("TURNO"));
               item.setDesSede(rs.getString("DESSEDE"));
               item.setFechaInicio(rs.getDate("FEC_CLASES_INI"));
               item.setFechaFinal(rs.getDate("FEC_CLASES_FIN"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.alert(e.getMessage());

        }
        return lista;
    }
}
