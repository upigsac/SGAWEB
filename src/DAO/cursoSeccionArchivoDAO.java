/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.exploradorArchivo;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cursoSeccionArchivoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class cursoSeccionArchivoDAO {
    public List<cursoSeccionArchivoC> mostrarCursoSeccionArchivo(int institucion, String periodo, String carrera,String malla,String curso,String seccion,String archivoPadre) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionArchivoC item;
        List<cursoSeccionArchivoC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HOR_CURSO_SECCION_ARCHIVO(1,?,?,?,?,?,?,?)}");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, archivoPadre);
            
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new cursoSeccionArchivoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setArchivo(rs.getString("ARCHIVO"));
                item.setRuta(rs.getString("RUTA"));
                item.setArchivoPadre(rs.getString("ARCHIVO_PADRE"));
                item.setFormato(rs.getString("FORMATO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.alert(e.getMessage());
            

        }
        return lista;
    }
    
    public String insertar(cursoSeccionArchivoC item) {

        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_HOR_CURSO_SECCION_ARCHIVO(1,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getArchivo());
            cs.setString(8, item.getRuta());
            cs.setString(9, item.getFormato());
            cs.setInt(10, item.getPeso());
            cs.setString(11, item.getArchivoPadre());
            cs.setInt(12, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1;
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
    public String insertarCarpetaRaiz(cursoSeccionArchivoC item) {

        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_HOR_CURSO_SECCION_ARCHIVO(3,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getArchivo());
            cs.setString(8, item.getRuta());
            cs.setString(9, item.getFormato());
            cs.setInt(10, item.getPeso());
            cs.setString(11, item.getArchivoPadre());
            cs.setInt(12, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1;
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
    
    
    
    public String eliminar(cursoSeccionArchivoC item) {

        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_HOR_CURSO_SECCION_ARCHIVO(2,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            
            System.out.println(item.getInstitucion());
            System.out.println( item.getPeriodo());
            System.out.println( item.getCarrera());
            System.out.println( item.getMalla());
            System.out.println(item.getCurso());
            System.out.println( item.getSeccion());
            System.out.println( item.getArchivo());
            System.out.println( item.getRuta());
            System.out.println( item.getFormato());
            System.out.println( item.getPeso());
            System.out.println( item.getArchivoPadre());
            System.out.println( item.getEstadoRegistro());
            
            
            
            
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getArchivo());
            cs.setString(8, item.getRuta());
            cs.setString(9, item.getFormato());
            cs.setInt(10, item.getPeso());
            cs.setString(11, item.getArchivoPadre());
            cs.setInt(12, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1;
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
    
    public List<exploradorArchivo.cursoSeccionPersonal> mostrarCursoSeccionPersonal(int institucion, String periodo, String personal) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        exploradorArchivo.cursoSeccionPersonal item;
        List<exploradorArchivo.cursoSeccionPersonal> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HOR_CURSO_SECCION_ARCHIVO(2,?,?,'','','','','','',?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new exploradorArchivo.cursoSeccionPersonal();
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
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.alert(e.getMessage());

        }
        return lista;
    }
    public List<exploradorArchivo.cursoSeccionPersonal> mostrarCursoSeccionAlumno(int institucion, String periodo, String alumno) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        exploradorArchivo.cursoSeccionPersonal item;
        List<exploradorArchivo.cursoSeccionPersonal> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HOR_CURSO_SECCION_ARCHIVO(4,?,?,'','','','','','','',?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {

               item = new exploradorArchivo.cursoSeccionPersonal();
               item.setInstitucion(rs.getInt("INSTITUCION"));
               item.setPeriodo(rs.getString("PERIODO"));                
               item.setCarrera(rs.getString("CARRERA"));                
               item.setMalla(rs.getString("MALLA"));
               item.setCurso(rs.getString("CURSO"));
               item.setDesCurso(rs.getString("DESCURSO"));
               item.setSeccion(rs.getString("SECCION"));
               item.setDesSeccion(rs.getString("DESSECCION"));
               item.setDesturno(rs.getString("DESTURNO"));
               item.setTurno(rs.getInt("TURNO"));
               item.setPersonal(rs.getString("PERSONAL"));
               item.setPersona(rs.getString("PERSONA"));
               item.setDesPersona(rs.getString("DESPERSONA"));
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
