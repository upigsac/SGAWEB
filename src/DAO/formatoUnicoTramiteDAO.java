package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexiones.Conexion;

import ENTIDAD.formatoUnicoTramiteC;

public class formatoUnicoTramiteDAO extends Conexion {
	 public String insertar(formatoUnicoTramiteC item) {
	     
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_FORMATO_UNICO_TRAMITE(1,?,?,?,?,?,?)}");
	            cs.setString(1, item.getFut());
	            cs.setString(2, item.getNumeroComprobante());
	            cs.setString(3, item.getConcepto());
	            cs.setString(4, item.getPersona());
	            cs.setString(5, item.getObservacion());
	            cs.setInt(6, item.getEstadoRegistro());

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
	 public String eliminar(formatoUnicoTramiteC item) {
	     
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_FORMATO_UNICO_TRAMITE(2,?,?,?,?,?,?)}");
	            cs.setString(1, item.getFut());
	            cs.setString(2, item.getNumeroComprobante());
	            cs.setString(3, item.getConcepto());
	            cs.setString(4, item.getPersona());
	            cs.setString(5, item.getObservacion());
	            cs.setInt(6, item.getEstadoRegistro());

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
	 
	 public List<formatoUnicoTramiteC> mostrarFormatoUnicoTramite() {

	        List<formatoUnicoTramiteC> lista = new ArrayList<>();
	        Connection c;
	        CallableStatement cs;
	        ResultSet rs;
	        formatoUnicoTramiteC item;

	        try {

	            c = ConexionWeb();
	            cs = c.prepareCall("SELECT *FROM DI.SYS_FORMATO_UNICO_TRAMITE  ");
	                        
	            rs = cs.executeQuery();

	            while (rs.next()) {

	                item = new formatoUnicoTramiteC();
	                item.setFut(rs.getString("FUT"));
	                item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));
	                item.setConcepto(rs.getString("CONCEPTO"));
	                item.setPersona(rs.getString("PERSONA"));
	                item.setObservacion(rs.getString("OBSERVACION"));
	                item.setEstadoRegistro(rs.getInt("estado_registro"));
	                lista.add(item);

	            }
	            cerrarCall(cs);
	            cerrarConexion(c);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }
	 
	 public formatoUnicoTramiteC mostrarFormatoUnicoTramite(String fut) {

	       
	        Connection c;
	        CallableStatement cs;
	        ResultSet rs;
	        formatoUnicoTramiteC item=null;

	        try {

	            c = ConexionWeb();
	            cs = c.prepareCall("SELECT *FROM DI.SYS_FORMATO_UNICO_TRAMITE  WHERE FUT=? AND ESTADO_REGISTRO=1");
	            cs.setString(1, fut);            
	            rs = cs.executeQuery();

	            while (rs.next()) {

	                item = new formatoUnicoTramiteC();
	                item.setFut(rs.getString("FUT"));
	                item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));
	                item.setConcepto(rs.getString("CONCEPTO"));
	                item.setPersona(rs.getString("PERSONA"));
	                item.setObservacion(rs.getString("OBSERVACION"));
	                item.setEstadoRegistro(rs.getInt("estado_registro"));
	              

	            }
	            cerrarCall(cs);
	            cerrarConexion(c);

	        } catch (SQLException e) {
	           System.out.print(e.getMessage());
	        }
	        return item;
	    }
}
