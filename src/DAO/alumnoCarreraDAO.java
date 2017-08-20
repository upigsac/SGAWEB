/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Conexiones.Conexion;

import ENTIDAD.alumnoCarreraC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class alumnoCarreraDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	 public String insertar(alumnoCarreraC item) {	        
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CARRERA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            cs.setInt("ITEMWEB", 1);
	            cs.setInt("INSTITUCION", item.getInstitucion());
	            cs.setString("CARRERA", item.getCarrera());
	            cs.setString("ALUMNO", item.getAlumno());
	            cs.setString("MALLA", item.getMalla());
	            cs.setString("CATEGORIA", item.getCategoria());
	            cs.setString("DESCUENTO", item.getDescuento());
	            cs.setDouble("CREDITOS_ACUMULADOS", item.getCreditosAcumulados());
	            cs.setDouble("PROMEDIO_PONDERADO", item.getPromedioPonderado());
	            cs.setInt("ORDEN_PONDERADO", item.getOrdenPonderado());
	            cs.setString("PERIODO_INGRESO", item.getPeriodoIngreso());
	            cs.setString("AUT_PER_MALLA", null);
	            cs.setString("AUT_DOC_MALLA", null);
	            cs.setString("AUT_OBS_MALLA", null);
	            cs.setString("AUT_PER_CARRERA", null);
	            cs.setString("AUT_DOC_CARRERA", null);
	            cs.setString("AUT_OBS_CARRERA", null);
	            cs.setString("AUT_PER_CATEGORIA", null);
	            cs.setString("AUT_DOC_CATEGORIA", null);
	            cs.setString("AUT_OBS_CATEGORIA", null);
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
	
	 public String eliminar(alumnoCarreraC item) {	        
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CARRERA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            cs.setInt("ITEMWEB", 2);
	            cs.setInt("INSTITUCION", item.getInstitucion());
	            cs.setString("CARRERA", item.getCarrera());
	            cs.setString("ALUMNO", item.getAlumno());
	            cs.setString("MALLA", item.getMalla());
	            cs.setString("CATEGORIA", item.getCategoria());
	            cs.setString("DESCUENTO", item.getDescuento());
	            cs.setDouble("CREDITOS_ACUMULADOS", item.getCreditosAcumulados());
	            cs.setDouble("PROMEDIO_PONDERADO", item.getPromedioPonderado());
	            cs.setInt("ORDEN_PONDERADO", item.getOrdenPonderado());
	            cs.setString("PERIODO_INGRESO", item.getPeriodoIngreso());
	            cs.setString("AUT_PER_MALLA", null);
	            cs.setString("AUT_DOC_MALLA", null);
	            cs.setString("AUT_OBS_MALLA", null);
	            cs.setString("AUT_PER_CARRERA", null);
	            cs.setString("AUT_DOC_CARRERA", null);
	            cs.setString("AUT_OBS_CARRERA", null);
	            cs.setString("AUT_PER_CATEGORIA", null);
	            cs.setString("AUT_DOC_CATEGORIA", null);
	            cs.setString("AUT_OBS_CATEGORIA", null);
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
	
	
	
	
	
	
	

	public alumnoCarreraC mostrarAlumnoCarrera(int institucion, String alumno) {
        alumnoCarreraC item = null;
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CARRERA WHERE INSTITUCION=? AND ALUMNO=? AND ESTADO_REGISTRO in(6,1) ");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
            	item=new alumnoCarreraC();
            	item.setInstitucion(rs.getInt("INSTITUCION"));
            	item.setCarrera(rs.getString("CARRERA"));
            	item.setAlumno(rs.getString("ALUMNO"));
            	item.setMalla(rs.getString("MALLA"));
            	item.setCategoria(rs.getString("CATEGORIA"));
            	item.setDescuento(rs.getString("DESCUENTO"));
            	item.setCreditosAcumulados(rs.getDouble("CREDITOS_ACUMULADOS"));
            	item.setPromedioPonderado(rs.getDouble("PROMEDIO_PONDERADO"));
            	item.setOrdenPonderado(rs.getInt("ORDEN_PONDERADO"));
            	item.setPeriodoIngreso(rs.getString("PERIODO_INGRESO"));
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return item;
    }
    
    
    
    public List<alumnoCarreraC> mostrarAlumnoCarreras(int institucion, String alumno) {
        alumnoCarreraC item ;
        List<alumnoCarreraC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CARRERA WHERE INSTITUCION=? AND ALUMNO=? AND ESTADO_REGISTRO in(6,1) ");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new alumnoCarreraC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCategoria(rs.getString("CATEGORIA"));
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setCreditosAcumulados(rs.getDouble("CREDITOS_ACUMULADOS"));
                item.setPromedioPonderado(rs.getDouble("PROMEDIO_PONDERADO"));
                item.setOrdenPonderado(rs.getInt("ORDEN_PONDERADO"));
                item.setPeriodoIngreso(rs.getString("PERIODO_INGRESO"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
     public List<alumnoCarreraC> mostrarAlumnoCarreras(int institucion) {
        alumnoCarreraC item ;
        List<alumnoCarreraC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_CARRERA WHERE INSTITUCION=?  ");
            cs.setInt(1, institucion);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new alumnoCarreraC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCategoria(rs.getString("CATEGORIA"));
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setCreditosAcumulados(rs.getDouble("CREDITOS_ACUMULADOS"));
                item.setPromedioPonderado(rs.getDouble("PROMEDIO_PONDERADO"));
                item.setOrdenPonderado(rs.getInt("ORDEN_PONDERADO"));
                item.setPeriodoIngreso(rs.getString("PERIODO_INGRESO"));
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
