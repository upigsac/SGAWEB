package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;
import Conexiones.Conexion;

import ENTIDAD.conceptoInstitucionDescuentoC;

public class conceptoInstitucionDescuentoDAO extends Conexion {
	public String insertar(conceptoInstitucionDescuentoC item) {
		
		  
		
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TES_CONCEPTO_INSTITUCION_DESCUENTO(1,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getConcepto());
            cs.setInt(4, item.getTipoConcepto());
            cs.setString(5, item.getDescuento());
            cs.setString(6, item.getCentroCosto());            
            cs.setInt(7, item.getEstadoRegistro());

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
	public String eliminar(conceptoInstitucionDescuentoC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TES_CONCEPTO_INSTITUCION_DESCUENTO(2,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getConcepto());
            cs.setInt(4, item.getTipoConcepto());
            cs.setString(5, item.getDescuento());
            cs.setString(6, item.getCentroCosto());            
            cs.setInt(7, item.getEstadoRegistro());

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
	
	
	public List<conceptoInstitucionDescuentoC> mostrarConceptoInstitucionDescuento(String concepto) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        conceptoInstitucionDescuentoC item ;
        List<conceptoInstitucionDescuentoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select  *from SIGU.TES_CONCEPTO_INSTITUCION_DESCUENTO WHERE CONCEPTO=? ORDER BY PERIODO");
           cs.setString(1, concepto);
            rs = cs.executeQuery();

            while (rs.next()) {
               
                item = new conceptoInstitucionDescuentoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setCentroCosto(rs.getString("CENTRO_COSTO"));
                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);               

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
