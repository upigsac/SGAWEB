/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Beans.matriculaInstitutos;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.alumnoCursoSeccionC;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
;


public class alumnoCursoSeccionDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String insertar(alumnoCursoSeccionC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CURSO_SECCION (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("CARRERA", item.getCarrera());
            cs.setString("MALLA", item.getMalla());
            cs.setString("CURSO", item.getCurso());
            cs.setString("SECCION", item.getSeccion());
            cs.setString("ALUMNO", item.getAlumno());
            cs.setString("GRUPO", item.getGrupo());
            cs.setDouble("PROMEDIO", item.getPromedio());
            cs.setString("RESOLUCION", item.getResolucion());
            cs.setString("OBSERVACION", item.getObservacion());
            cs.setBoolean("CONVALIDADO", item.isConvalidado());
            cs.setString("PERSONA_CONVALIDA", "");
            cs.setString("AUT_PER_REGISTRO", "");
            cs.setString("AUT_DOC_REGISTRO", "");
            cs.setString("AUT_OBS_REGISTRO", "");
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro()); 
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

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
	
	
	public String eliminar(alumnoCursoSeccionC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CURSO_SECCION (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("CARRERA", item.getCarrera());
            cs.setString("MALLA", item.getMalla());
            cs.setString("CURSO", item.getCurso());
            cs.setString("SECCION", item.getSeccion());
            cs.setString("ALUMNO", item.getAlumno());
            cs.setString("GRUPO", item.getGrupo());
            cs.setDouble("PROMEDIO", item.getPromedio());
            cs.setString("RESOLUCION", item.getResolucion());
            cs.setString("OBSERVACION", item.getObservacion());
            cs.setBoolean("CONVALIDADO", item.isConvalidado());
            cs.setString("PERSONA_CONVALIDA", "");
            cs.setString("AUT_PER_REGISTRO", "");
            cs.setString("AUT_DOC_REGISTRO", "");
            cs.setString("AUT_OBS_REGISTRO", "");
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro()); 
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

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
	
	public alumnoCursoSeccionC mostrarAlumnoCursoSeccionAprobado(int institucion,  String carrera, String malla,  String curso,String alumno) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoCursoSeccionC item =null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CURSO_SECCION WHERE INSTITUCION=? AND CARRERA=? AND MALLA=? AND CURSO LIKE ? AND  ALUMNO=? AND PROMEDIO >=11");
            cs.setInt(1, institucion);          
            cs.setString(2, carrera);
            cs.setString(3, malla);
            cs.setString(4, curso);
            cs.setString(5, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPromedio(rs.getDouble("PROMEDIO"));

                item.setGrupo(rs.getString("GRUPO"));

                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
             
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           System.out.println(e.getMessage());
            
        }
        return item;
    }



	public List<alumnoCursoSeccionC> mostrarAlumnoCursoSeccion(int institucion, String periodo, String carrera, String malla, String seccion, String curso) {
        alumnoCursoSeccionC item ;

        List<alumnoCursoSeccionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CURSO_SECCION where INSTITUCION=? AND PERIODO=? AND  CARRERA like ? AND MALLA like ? AND  SECCION like ? AND CURSO like ? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, seccion);
            cs.setString(6, curso);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPromedio(rs.getDouble("PROMEDIO"));

                item.setGrupo(rs.getString("GRUPO"));

                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	   System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
    public alumnoCursoSeccionC mostrarItemAlumnoCursoSeccion(int institucion, String periodo, String carrera, String malla,  String curso,String seccion,String alumno) {
        

     
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoCursoSeccionC item =null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CURSO_SECCION where INSTITUCION=? AND PERIODO=? AND  CARRERA=? AND MALLA LIKE ?  AND CURSO LIKE ? AND SECCION LIKE ? AND  ALUMNO=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPromedio(rs.getDouble("PROMEDIO"));

                item.setGrupo(rs.getString("GRUPO"));

                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	   System.out.println(e.getMessage());
            
        }
        return item;
    }
    
    public List<alumnoCursoSeccionC> mostrarAlumnoCursoSeccionFitro(int institucion, String periodo, String paterno, String materno, String nombres) {
        alumnoCursoSeccionC item ;

        List<alumnoCursoSeccionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 50 A.* FROM SIGU.ACA_ALUMNO_CURSO_SECCION A,SIGU.ACA_ALUMNO B,UPA.DAT_PERSONAS C,UPA.ACA_CURSOS D,SIGU.HOR_CURSO_SECCION_DOCENTE E,SIGU.HOR_SECCION F\n" +
                                "WHERE A.ALUMNO=B.ALUMNO\n" +
                                "AND C.PERSONA=B.PERSONA\n" +
                                "AND D.CURSO=A.CURSO\n" +
                                "AND E.INSTITUCION=A.INSTITUCION AND E.PERIODO=A.PERIODO AND E.CARRERA=A.CARRERA AND E.MALLA=A.MALLA AND E.SECCION=A.SECCION AND E.CURSO=A.CURSO\n" +
                                "AND F.INSTITUCION=A.INSTITUCION AND F.SECCION=A.SECCION\n" +
                                "AND  A.INSTITUCION=3 AND APELLIDO_PATERNO  LIKE ? AND APELLIDO_MATERNO LIKE ? AND NOMBRES LIKE ?\n" +
                                "ORDER BY 1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, paterno);
            cs.setString(4, materno);
            cs.setString(5, nombres);
            

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPromedio(rs.getDouble("PROMEDIO"));

                item.setGrupo(rs.getString("GRUPO"));

                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	   System.out.println(e.getMessage());
         
        }
        return lista;
    }
    
    
    

    public List<alumnoCursoSeccionC> mostrarAlumnoCursoSeccion(int institucion, String periodo, String alumno) {
        alumnoCursoSeccionC item ;

        List<alumnoCursoSeccionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT INSTITUCION,PERIODO,CARRERA,MALLA,SECCION,ALUMNO,GRUPO,ESTADO_REGISTRO FROM SIGU.ACA_ALUMNO_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=?  AND ALUMNO=?    GROUP BY INSTITUCION,PERIODO,CARRERA,MALLA,SECCION,ALUMNO,GRUPO ,ESTADO_REGISTRO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setGrupo(rs.getString("GRUPO"));

                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	   System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
    
    public List<alumnoCursoSeccionC> mostrarAlumnoCursoSeccion(int institucion, String periodo,String carrera, String alumno) {
        alumnoCursoSeccionC item ;

        List<alumnoCursoSeccionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.ACA_ALUMNO_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA LIKE ? AND ALUMNO=?    ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCurso(rs.getString("CURSO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	   System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
    public List<alumnoCursoSeccionC> mostrarAlumnoCursosSeccionMatriculados(int institucion, String periodo, String alumno) {
        alumnoCursoSeccionC item ;

        List<alumnoCursoSeccionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND ALUMNO=?  AND ESTADO_REGISTRO IN(1,10)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCurso(rs.getString("CURSO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setGrupo(rs.getString("GRUPO"));

                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return lista;
    }

    public String insertarMatriculaInstituto(int institucion, String periodo, String seccion, String alumno, int estado) {

        Connection c ;
        CallableStatement cs ;
        String rpta = "";

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MATRICULAR_INSTITUTO  (?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, seccion);
            cs.setString(4, alumno);
            cs.setInt(5, estado);

            if (cs.executeUpdate() == 1) {

                c.commit();
            } else {
                deshacerCambios(c);
            }

            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {            
            System.out.println(e.getMessage());
        }
        return rpta;
    }

    public String eliminarMatriculaInstituto(int institucion, String periodo, String seccion, String alumno) {

        Connection c ;
        CallableStatement cs;
        String rpta = "";

        try {

            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM SIGU.ACA_ALUMNO_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND SECCION=? AND ALUMNO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, seccion);
            cs.setString(4, alumno);

            if (cs.executeUpdate() == 1) {

                c.commit();
            } else {
                deshacerCambios(c);
            }

            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	   System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
    
    public List<matriculaInstitutos.listaAlumnosMatriculados> listadoMatriculadoInstituto(int institucion, String periodo, String carrera,String seccion) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        matriculaInstitutos.listaAlumnosMatriculados item;              
        List<matriculaInstitutos.listaAlumnosMatriculados> lista=new ArrayList<>();
        try {
   
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_LISTA_MATRICULADOS_INSTITUTO(?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            

           
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new matriculaInstitutos.listaAlumnosMatriculados();
                item.setPersona(rs.getString("PERSONA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setDatos(rs.getString("DATOS"));
                item.setFecha(rs.getString("FECHA"));
                item.setHora(rs.getString("HORA"));
                

                
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	   System.out.println(e.getMessage());
           
        }
        return lista;
    }
    
    
    
    public List<alumnoCursoSeccionC> mostrarAlumnoCursoSeccion(int institucion, String periodo, String carrera, String malla, String curso, String seccion,String alumno) {
        alumnoCursoSeccionC item ;

        List<alumnoCursoSeccionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ALUMNO_CURSO_SECCION(1,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoCursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPromedio(rs.getDouble("PROMEDIO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	   System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
}
