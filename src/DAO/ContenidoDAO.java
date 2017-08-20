/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Beans.util;
import Conexiones.Conexion;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ContenidoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void prueba() {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select APP_NAME() proyecto");

            rs = cs.executeQuery();

            while (rs.next()) {

                util.consolaCliente( rs.getString("proyecto"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
            
        }

    }

    public List<ArrayList<String>> mostrarArchivos(String periodo, String carrera) {

        List<ArrayList<String>> lRetorna = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT A.PERSONAL,C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES FROM SIGU.HOR_CURSO_SECCION_DOCENTE A\n"
                    + " ,SIGU.PER_PERSONAL B,UPA.DAT_PERSONAS C\n"
                    + " WHERE \n"
                    + " A.PERSONAL=B.PERSONAL AND B.PERSONA=C.PERSONA AND\n"
                    + " A.PERIODO=? AND A.CARRERA=? ORDER BY   2,3,4");

            cs.setString(1, periodo);
            cs.setString(2, carrera);
            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("PERSONAL"));
                array.add(rs.getString("NOMBRES"));
                array.add(rs.getString("APELLIDO_PATERNO"));
                array.add(rs.getString("APELLIDO_MATERNO"));
                lRetorna.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
            
        }
        return lRetorna;
    }

    public List<ArrayList<String>> mostrarUltimosArchivos(int periodo, String carrera, String alumno) {

        List<ArrayList<String>> lRetorna2 = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" select A.PERSONAL , B.ALUMNO ,D.DESCRIPCION,  C.TIPO_EXAMEN ,  C.NOMBREARCHIVO ,C.RUTAARCHIVO ,   convert(CHAR(10),C.CREACION_FECHA,105) FECHA \n"
                    + " from SIGU.HOR_CURSO_SECCION_DOCENTE A ,SIGU.ACA_ALUMNO_CURSO_SECCION B,SIGU.SUBIDA_ARCHIVOS C,UPA.ACA_CURSOS D\n"
                    + " WHERE A.PERIODO=B.PERIODO AND A.CARRERA=B.CARRERA AND A.SECCION=B.SECCION AND A.CURSO=B.CURSO AND C.CARRERA =A.CARRERA\n"
                    + " AND C.PERIODO=A.PERIODO AND C.DOCENTE =A.PERSONAL AND A.CURSO=D.CURSO AND \n"
                    + " A.PERIODO =? AND A.CARRERA=? AND B.ALUMNO=? ORDER BY NOMBREARCHIVO ");

            cs.setInt(1, periodo);
            cs.setString(2, carrera);
            cs.setString(3, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array1 = new ArrayList<>();
                array1.add(rs.getString("PERSONAL"));
                array1.add(rs.getString("ALUMNO"));
                array1.add(rs.getString("DESCRIPCION"));
                array1.add(rs.getString("TIPO_EXAMEN"));
                array1.add(rs.getString("NOMBREARCHIVO"));
                array1.add(rs.getString("RUTAARCHIVO"));
                array1.add(rs.getString("FECHA"));
                lRetorna2.add(array1);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
           
        }
        return lRetorna2;
    }

    public List<ArrayList<String>> mostrarCarpetasDocente(int periodo, String docente) {

        List<ArrayList<String>> lRetorna2 = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT  TIPO_EXAMEN ,COUNT(*)AS CANTIDAD FROM SIGU.SUBIDA_ARCHIVOS WHERE PERIODO=? AND DOCENTE=? AND  ESTADO_REGISTRO=5 \n"
                    + "GROUP BY TIPO_EXAMEN ");

            cs.setInt(1, periodo);
            cs.setString(2, docente);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array1 = new ArrayList<>();
                array1.add(rs.getString("TIPO_EXAMEN"));
                array1.add(rs.getString("CANTIDAD"));
                array1.add("");
                array1.add("");
                array1.add("");
                lRetorna2.add(array1);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
         
        }
        return lRetorna2;
    }

    public List<ArrayList<String>> mostrarArchivoDocenteExamen(int periodo, String docente, String examen) {

        List<ArrayList<String>> lRetorna2 = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  PERIODO,NOMBREARCHIVO,RUTAARCHIVO,CREACION_FECHA FROM SIGU.SUBIDA_ARCHIVOS \n"
                    + " WHERE PERIODO=?  AND DOCENTE=? AND TIPO_EXAMEN=? AND ESTADO_REGISTRO=5 ");

            cs.setInt(1, periodo);
            cs.setString(2, docente);
            cs.setString(3, examen);
            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array1 = new ArrayList<>();
                array1.add(rs.getString("PERIODO"));
                array1.add(rs.getString("NOMBREARCHIVO"));
                array1.add(rs.getString("RUTAARCHIVO"));
                array1.add(rs.getString("CREACION_FECHA"));
                lRetorna2.add(array1);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return lRetorna2;
    }

    public List<ArrayList<String>> mostrarRolExamenesAdmin(String periodo, String carrera, String examen) {

        List<ArrayList<String>> lRetorna2 = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("select A.DOCENTE,C.APELLIDO_PATERNO +' '+ C.APELLIDO_MATERNO +' '+ C.NOMBRES DESPERSONA ,A.NOMBREARCHIVO,A.RUTAARCHIVO,convert(VARCHAR(10),A.CREACION_FECHA,103) FECHA ,convert(VARCHAR(8),A.CREACION_FECHA,108) HORA \n"
                    + "from sigu.SUBIDA_ARCHIVOS A ,SIGU.PER_PERSONAL B,UPA.DAT_PERSONAS C where A.DOCENTE=B.PERSONAL AND B.PERSONA=C.PERSONA AND A.ESTADO_REGISTRO in (1,2) and A.PERIODO=? AND A.CARRERA=? AND A.TIPO_EXAMEN=?");

            cs.setString(1, periodo);
            cs.setString(2, carrera);
            cs.setString(3, examen);
            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array1 = new ArrayList<>();
                array1.add(rs.getString("DOCENTE"));
                array1.add(rs.getString("DESPERSONA"));
                array1.add(rs.getString("NOMBREARCHIVO"));
                array1.add(rs.getString("RUTAARCHIVO"));
                array1.add(rs.getString("FECHA"));
                array1.add(rs.getString("HORA"));
                lRetorna2.add(array1);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return lRetorna2;
    }

    public List<ArrayList<String>> mostrarArchivosDocente(String docente, String periodo, String carrera) {

        List<ArrayList<String>> lRetorna2 = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT SA.PERIODO,SA.NOMBREARCHIVO,SA.TIPO_EXAMEN,AC.DESCRIPCION,SA.RUTAARCHIVO ,SA.SECCION,SA.CURSO,convert(VARCHAR(10),SA.CREACION_FECHA,103) CREACION_FECHA  FROM SIGU.SUBIDA_ARCHIVOS SA \n"
                    + " JOIN SIGU.PER_PERSONAL PP ON PP.PERSONAL=SA.DOCENTE\n"
                    + " JOIN UPA.DAT_PERSONAS DP ON DP.PERSONA=PP.PERSONA \n"
                    + " JOIN UPA.ACA_CARRERAS AC ON AC.CARRERA=SA.CARRERA WHERE DOCENTE= ? AND SA.PERIODO=? and SA.CARRERA=? \n"
                    + " ORDER BY PERIODO,DESCRIPCION");

            cs.setString(1, docente);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array1 = new ArrayList<>();
                array1.add(rs.getString("PERIODO"));
                array1.add(rs.getString("NOMBREARCHIVO"));
                array1.add(rs.getString("TIPO_EXAMEN"));
                array1.add(rs.getString("DESCRIPCION"));
                array1.add(rs.getString("RUTAARCHIVO"));
                array1.add(rs.getString("SECCION"));
                array1.add(rs.getString("CURSO"));
                array1.add(rs.getString("CREACION_FECHA"));
                lRetorna2.add(array1);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return lRetorna2;
    }

    public void insertarSubidaArchivo(int institucion, String periodo, int grupo, String docente, String nombreArchivo, String rutaArchivo, String tipoExamen, String carrera, String curso, String seccion, int estado) {
        try {

            Connection c ;

            c = ConexionWeb();
            PreparedStatement pr ;
            pr = c.prepareStatement("{CALL SP_INSERTAR_SUBIDA_ARCHIVOS(?,?,?,?,?,?,?,?,?,?,?)}");
            pr.setInt(1, institucion);
            pr.setString(2, periodo);
            pr.setInt(3, grupo);
            pr.setString(4, docente);
            pr.setString(5, nombreArchivo);
            pr.setString(6, rutaArchivo);
            pr.setString(7, tipoExamen);
            pr.setString(8, carrera);
            pr.setString(9, curso);
            pr.setString(10, seccion);
            pr.setInt(11, estado);
            pr.executeUpdate();
            cerrarConexion(c);
        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());

        }

    }

    public void eliminarArchivoDocente(String ruta) {
        try {
            Connection c  ;

            c = ConexionWeb();
            PreparedStatement pr ;

            pr = c.prepareStatement("DELETE FROM SIGU.SUBIDA_ARCHIVOS where RUTAARCHIVO=?");

            pr.setString(1, ruta);

            pr.executeUpdate();

            pr.close();
            c.close();

        } catch (SQLException ex) {
            ex.getMessage();
        } finally {

        }
    }

    public List<ArrayList<String>> gestorContenidoCursos(String alumno, int periodo, String carrera) {

        List<ArrayList<String>> contenidoCursos = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select A.PERIODO,A.CURSO,B.DESCRIPCION,D.PERSONAL,E.PERSONA,E.APELLIDO_PATERNO +' '+E.APELLIDO_MATERNO +' '+ E.NOMBRES AS DESPERSONA ,\n"
                    + " A.PERIODO + '/'+ A.CARRERA+'/'+ A.CURSO +'/'+ A.SECCION +'/'+C.PERSONAL AS RUTA \n "
                    + " from SIGU.ACA_ALUMNO_CURSO_SECCION A,UPA.ACA_CURSOS B,SIGU.HOR_CURSO_SECCION_DOCENTE C,SIGU.PER_PERSONAL D,UPA.DAT_PERSONAS E\n"
                    + " WHERE A.PERIODO=? AND A.CARRERA=? AND A.ALUMNO=? AND A.CURSO=B.CURSO AND A.PERIODO=C.PERIODO AND A.CURSO=C.CURSO \n"
                    + " AND A.SECCION=C.SECCION AND C.PERSONAL=D.PERSONAL AND D.PERSONA=E.PERSONA order by B.DESCRIPCION");

            cs.setInt(1, periodo);
            cs.setString(2, carrera);
            cs.setString(3, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> lista = new ArrayList<>();
                lista.add(rs.getString("PERIODO"));
                lista.add(rs.getString("CURSO"));
                lista.add(rs.getString("DESCRIPCION").toUpperCase());
                lista.add(rs.getString("PERSONAL"));
                lista.add(rs.getString("PERSONA"));
                lista.add(rs.getString("DESPERSONA"));
                lista.add(rs.getString("RUTA"));

                contenidoCursos.add(lista);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }

        return contenidoCursos;
    }

}
