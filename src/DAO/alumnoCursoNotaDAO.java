/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.misNotas;


import ENTIDAD.alumnoCursoNotaC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import java.sql.Types;

public class alumnoCursoNotaDAO implements Serializable {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String inserta(alumnoCursoNotaC item) {
        Connection c;
        CallableStatement cs;
        boolean rpta = false;
        String promedio = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_NOTA  (1, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setString(8, item.getTipoExamen());
            cs.setDouble(9, item.getNota());
            cs.setString(10, item.getAutPersonal());
            cs.setString(11, item.getAutObservacion());
            cs.setInt(12, item.getEstadoRegistro());
            cs.registerOutParameter(13, Types.VARCHAR);
            cs.executeUpdate();

            rpta = true;
            if (rpta) {

                c.commit();
               promedio = cs.getString(13);
                
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
                System.out.println("ERROR DE BD ->" + ex.getMessage());
        }
        return promedio;
    }
    
    
    public String eliminar(alumnoCursoNotaC item) {
        Connection c;
        CallableStatement cs;
        boolean rpta = false;
        String promedio = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_NOTA  (2, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setString(8, item.getTipoExamen());
            cs.setDouble(9, item.getNota());
            cs.setString(10, item.getAutPersonal());
            cs.setString(11, item.getAutObservacion());
            cs.setInt(12, item.getEstadoRegistro());
            cs.registerOutParameter(13, Types.VARCHAR);
            cs.executeUpdate();

            rpta = true;
            if (rpta) {

                c.commit();
               promedio = cs.getString(13);
                
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
                System.out.println("ERROR DE BD ->" + ex.getMessage());
        }
        return promedio;
    }
    
    public List<alumnoCursoNotaC> mostrarAlumnoCursoNotaP(int institucion, String periodo,String carrera,String malla,String curso,String seccion,String alumno,String tipoNota) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoCursoNotaC> lista = new ArrayList<>();
        alumnoCursoNotaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ALUMNO_CURSO_NOTA(1,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
            cs.setString(8, tipoNota);            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCursoNotaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setNota(rs.getDouble("NOTA"));
                item.setNotaT(rs.getString("NOTAT"));
                item.setAutPersonal(rs.getString("AUT_MOD_PERSONAL"));
                item.setAutObservacion(rs.getString("AUT_MOD_OBSERVACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    public List<alumnoCursoNotaC> mostrarAlumnoCursoNotaH(int institucion, String periodo,String carrera,String malla,String curso,String seccion,String alumno,String tipoNota) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoCursoNotaC> lista = new ArrayList<>();
        alumnoCursoNotaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ALUMNO_CURSO_NOTA(2,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
            cs.setString(8, tipoNota);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCursoNotaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setNota(rs.getDouble("NOTA"));
                item.setNotaT(rs.getString("NOTAT"));
                item.setAutPersonal(rs.getString("AUT_MOD_PERSONAL"));
                item.setAutObservacion(rs.getString("AUT_MOD_OBSERVACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    

    public List<alumnoCursoNotaC> mostrarAlumno(int institucion, String persona) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoCursoNotaC> lista = new ArrayList<>();
        alumnoCursoNotaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO WHERE INSTITUCION=? AND PERSONA=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCursoNotaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setNota(rs.getDouble("NOTA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    
    public List<alumnoCursoNotaC> mostrarAlumno(int institucion, String periodo,String carrera,String malla,String curso,String seccion,String alumno) {
    	
    	
    	
    	
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoCursoNotaC> lista = new ArrayList<>();
        alumnoCursoNotaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CURSO_NOTA WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND ALUMNO like ? ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCursoNotaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setNota(rs.getDouble("NOTA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    public List<misNotas.detalleNotas> mostrarAlumno(int institucion, String periodo,String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<misNotas.detalleNotas> lista = new ArrayList<>();
        misNotas.detalleNotas item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MIS_NOTAS(?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new misNotas.detalleNotas();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPractica1(rs.getDouble("PRACTICA_1"));
                item.setPractica2(rs.getDouble("PRACTICA_2"));
                item.setExamenParcial(rs.getDouble("PARCIAL"));
                item.setExamenFinal(rs.getDouble("FINAL"));
                item.setPromedio(rs.getDouble("PROMEDIO"));
                item.setPromedioPonderadoPeriodo(rs.getDouble("PROMEDIO_SEMESTRAL"));
                item.setPromedioPonderadoGeneral(rs.getDouble("PROMEDIO_GENERAL"));
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
        return lista;
    }
}
