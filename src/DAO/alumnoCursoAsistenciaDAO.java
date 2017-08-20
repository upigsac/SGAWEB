/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.alumnoCursoAsistenciaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class alumnoCursoAsistenciaDAO {
     public List<alumnoCursoAsistenciaC> mostrarAlumnoCursoAsistencia(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String alumno,String fecha) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoCursoAsistenciaC> lista = new ArrayList<>();
        alumnoCursoAsistenciaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MOSTRAR_ACA_ALUMNO_CURSO_ASISTENCIA(1,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
            cs.setString(8, fecha);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCursoAsistenciaC();
                
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setFecha(rs.getDate("FECHA"));
                item.setAsistencia(rs.getBoolean("ASISTENCIA"));
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
     public List<alumnoCursoAsistenciaC> mostrarAlumnoCursoAsistenciaPrincipal(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String alumno,String fecha) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoCursoAsistenciaC> lista = new ArrayList<>();
        alumnoCursoAsistenciaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MOSTRAR_ACA_ALUMNO_CURSO_ASISTENCIA(2,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
            cs.setString(8, fecha);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCursoAsistenciaC();
                
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setFecha(rs.getDate("FECHA"));
                item.setAsistencia(rs.getBoolean("ASISTENCIA"));
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
}
