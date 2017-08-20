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



public class ContenidoDAOC extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ArrayList<String>> mostrarArchivos(int periodo, String carrera) {

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

            cs.setInt(1, periodo);
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

    public List<ArrayList<String>> mostrarArchivosDocente(String docente, int periodo) {

        List<ArrayList<String>> lRetorna2 = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT SA.PERIODO,SA.NOMBREARCHIVO,SA.TIPO_EXAMEN,AC.DESCRIPCION,SA.RUTAARCHIVO ,SA.CREACION_FECHA FROM SIGU.SUBIDA_ARCHIVOS SA \n"
                    + " JOIN SIGU.PER_PERSONAL PP ON PP.PERSONAL=SA.DOCENTE\n"
                    + " JOIN UPA.DAT_PERSONAS DP ON DP.PERSONA=PP.PERSONA \n"
                    + " JOIN UPA.ACA_CARRERAS AC ON AC.CARRERA=SA.CARRERA WHERE DOCENTE= ? AND SA.PERIODO=? \n"
                    + " ORDER BY PERIODO,DESCRIPCION");

            cs.setString(1, docente);
            cs.setInt(2, periodo);
            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array1 = new ArrayList<>();
                array1.add(rs.getString("PERIODO"));
                array1.add(rs.getString("NOMBREARCHIVO"));
                array1.add(rs.getString("TIPO_EXAMEN"));
                array1.add(rs.getString("DESCRIPCION"));
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

    public void insertarSubidaArchivo(int institucion, String periodo, String docente, String nombreArchivo, String rutaArchivo, String tipoExamen, String carrera, int estado) {
        try {

            Connection c ;

            c = ConexionWeb();
            PreparedStatement pr ;
            pr = c.prepareStatement("{CALL SP_INSERTAR_SUBIDA_ARCHIVOS(?,?,?,?,?,?,?,?)}");

            pr.setInt(1, institucion);
            pr.setString(2, periodo);
            pr.setString(3, docente);
            pr.setString(4, nombreArchivo);
            pr.setString(5, rutaArchivo);
            pr.setString(6, tipoExamen);
            pr.setString(7, carrera);
            pr.setInt(8, estado);
            pr.executeUpdate();
            cerrarConexion(c);
        } catch (SQLException e) {
         
            util.consolaCliente( e.getMessage());

        }

    }

    public void eliminarArchivoDocente(String ruta) {
        try {
            Connection c ;

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

    public List<ArrayList<String>> gestorContenidoCursos(String codigoAlumno, String periodo, String carrera) {

        List<ArrayList<String>> contenidoCursos = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT AACS.PERIODO+'/'+AACS.CARRERA+'/'+ACU.CURSO+'/'+AACS.SECCION+'/'+PP.PERSONAL AS RUTA,ACU.DESCRIPCION,'CURSO' AS CURSO,AACS.PERIODO,'none' AS BLOQUE\n"
                    + "                 FROM SIGU.ACA_ALUMNO_CURSO_SECCION AACS JOIN UPA.ACA_CURSOS ACU ON ACU.CURSO=AACS.CURSO\n"
                    + "                 JOIN SIGU.HOR_CURSO_SECCION_DOCENTE HCSD ON HCSD.CARRERA=AACS.CARRERA JOIN SIGU.PER_PERSONAL PP\n"
                    + "                 ON PP.PERSONAL=HCSD.PERSONAL\n"
                    + "                 WHERE	AACS.SECCION=HCSD.SECCION AND AACS.CURSO=HCSD.CURSO AND AACS.PERIODO=HCSD.PERIODO AND AACS.CARRERA=HCSD.CARRERA AND\n"
                    + "                 AACS.ALUMNO=? AND AACS.PERIODO=? AND AACS.CARRERA=? ORDER BY DESCRIPCION");

            cs.setString(1, codigoAlumno);
            cs.setString(2, periodo);
            cs.setString(3, carrera);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> lista = new ArrayList<>();
                lista.add(rs.getString("RUTA"));
                lista.add(rs.getString("DESCRIPCION"));
                lista.add(rs.getString("CURSO"));
                lista.add(rs.getString("PERIODO"));
                lista.add(rs.getString("BLOQUE"));

                contenidoCursos.add(lista);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
        
        }

        return contenidoCursos;
    }

}
