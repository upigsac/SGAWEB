/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.admin_notas;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.autorizaNotaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class autorizaNotaDAO {
	
	
	
	 public List<autorizaNotaC> mostrarAutorizaNota(int institucion,String periodo,String carrera,String malla,String curso,String seccion) {

	        List<autorizaNotaC> lista = new ArrayList<>();

	        try {
	            Connection c ;
	            CallableStatement cs ;
	            ResultSet rs ;
	            autorizaNotaC item = null;
	            c = ConexionWeb();
	            cs = c.prepareCall("SELECT *FROM SIGU.ACA_AUTORIZACION_NOTA WHERE INSTITUCION=? AND PERIODO=? AND CARRERA LIKE ? AND MALLA LIKE ? AND CURSO LIKE ? AND SECCION LIKE ? AND ESTADO_REGISTRO=1");
	            cs.setInt(1, institucion);
	            cs.setString(2, periodo);
	            cs.setString(3, carrera);
	            cs.setString(4, malla);
	            cs.setString(5, curso);
	            cs.setString(6, seccion);
	            

	            rs = cs.executeQuery();
	            while (rs.next()) {
	                item = new autorizaNotaC();
	                item.setInstitucion(rs.getInt("INSTITUCION"));
	                item.setPeriodo(rs.getString("PERIODO"));
	                item.setCarrera(rs.getString("CARRERA"));
	                item.setMalla(rs.getString("MALLA"));
	                item.setCurso(rs.getString("CURSO"));
	                item.setSeccion(rs.getString("SECCION"));
	                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
	                item.setFechaInicio(rs.getDate("FECHA_DESDE"));
	                item.setFechaFin(rs.getDate("FECHA_HASTA"));
	                item.setObservacion(rs.getString("OBSERVACION"));
	                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
	                lista.add(item);
	            }
	            cerrarCall(cs);
	            cerrarConexion(c);
	        } catch (SQLException e) {
	            System.out.println("datosAutorizacion " + e.getMessage());
	        }
	        return lista;
	    }
	
	
	
	
	
	
	
	
    public List<admin_notas.listaTipoExamen> datosTipoAutorizacionPadre(int institucion,String periodo,String carrera,String malla,String curso) {

        List<admin_notas.listaTipoExamen> lista = new ArrayList<>();

        try {
            Connection c ;
            CallableStatement cs ;
            ResultSet rs ;
            admin_notas.listaTipoExamen item = null;
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.TIPO_EXAMEN,C.DESCRIPCION,B.DECENDENCIA,B.TIPO_EXAMEN_PADRE,B.LECTURA ,B.ORDEN,B.ORDEN_RE\n" +
                                "FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_FORMULA_EXAMEN B,SIGU.HOR_TIPO_EXAMEN C  WHERE \n" +
                                "A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.MALLA=? AND A.CURSO LIKE ?  AND DECENDENCIA='P'\n" +
                                "AND A.FORMULA=B.FORMULA AND C.TIPO_EXAMEN=B.TIPO_EXAMEN\n" +
                                "ORDER BY TIPO_EXAMEN_PADRE, ORDEN");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new admin_notas.listaTipoExamen();
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setLectura(rs.getBoolean("LECTURA"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println("datosAutorizacion " + e.getMessage());
        }
        return lista;
    }
    
    public List<admin_notas.listaTipoExamen> datosTipoAutorizacionHijo(int institucion,String periodo,String carrera,String malla,String curso,String tipoExamenPadre) {

        List<admin_notas.listaTipoExamen> lista = new ArrayList<>();

        try {
            Connection c ;
            CallableStatement cs ;
            ResultSet rs ;
            admin_notas.listaTipoExamen item = null;
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.TIPO_EXAMEN,C.DESCRIPCION,B.DECENDENCIA,B.TIPO_EXAMEN_PADRE,B.LECTURA ,B.ORDEN,B.ORDEN_RE\n" +
                                "FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_FORMULA_EXAMEN B,SIGU.HOR_TIPO_EXAMEN C  WHERE \n" +
                                "A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.MALLA=? AND A.CURSO LIKE ?  AND DECENDENCIA='H' AND TIPO_EXAMEN_PADRE=?\n" +
                                "AND A.FORMULA=B.FORMULA AND C.TIPO_EXAMEN=B.TIPO_EXAMEN\n" +
                                "ORDER BY TIPO_EXAMEN_PADRE, ORDEN");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, tipoExamenPadre);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new admin_notas.listaTipoExamen();
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setLectura(rs.getBoolean("LECTURA"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println("datosAutorizacion " + e.getMessage());
        }
        return lista;
    }
    
    
    public String insertar(autorizaNotaC item) {
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_AUTORIZA_NOTA](?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setInt(3, item.getAutorizacion());
            cs.setString(4, item.getDocumento());
            cs.setString(5, item.getCarrera());
            cs.setString(6, item.getMalla());
            cs.setString(7, item.getCurso());
            cs.setString(8, item.getSeccion());
            cs.setInt(9, item.getGrupo());
            cs.setString(10, item.getPersonal());
            cs.setString(11, item.getTipoExamen());
            cs.setString(12, util.convertDate(item.getFechaInicio()) );
            cs.setString(13, util.convertDate(item.getFechaFin()) );
            cs.setString(14, item.getObservacion() );
            cs.setInt(15, item.getEstadoRegistro());

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


}
