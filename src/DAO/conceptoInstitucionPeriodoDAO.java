/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;




import ENTIDAD.conceptoInstitucionPeriodoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;
import Conexiones.Conexion;

public class conceptoInstitucionPeriodoDAO extends Conexion implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String insertar(conceptoInstitucionPeriodoC item) {
		
		  
		
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TES_CONCEPTO_INSTITUCION_PERIODO(1,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getConcepto());
            cs.setInt(4, item.getTipoConcepto());
            cs.setDouble(5, item.getMontoPago());
            cs.setInt(6, item.getTipoMoneda());
            cs.setInt(7, item.getTipoInteres());
            cs.setDouble(8, item.getMontoInteres());
            cs.setInt(9, item.getEstadoRegistro());

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
    
    public List<conceptoInstitucionPeriodoC> mostrarConceptoInstitucionPeriodo(int tipoConcepto,String concepto) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        conceptoInstitucionPeriodoC item =null;
        List<conceptoInstitucionPeriodoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.TES_CONCEPTO_INSTITUCION_PERIODO WHERE  TIPO_CONCEPTO=? AND CONCEPTO=?");
            cs.setInt(1, tipoConcepto);
           cs.setString(2, concepto);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new conceptoInstitucionPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));                
                item.setMontoPago(rs.getDouble("MON_PAGO"));
                item.setTipoMoneda(rs.getInt("TI_MONEDA"));
                item.setTipoInteres(rs.getInt("TIPO_INTERES"));
                item.setMontoInteres(rs.getDouble("MON_INTERES"));
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
    public conceptoInstitucionPeriodoC mostrarConceptoInstitucionPeriodo(String concepto) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        conceptoInstitucionPeriodoC item =null;
     
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.TES_CONCEPTO_INSTITUCION_PERIODO WHERE  CONCEPTO=?");
           cs.setString(1, concepto);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new conceptoInstitucionPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));                
                item.setMontoPago(rs.getDouble("MON_PAGO"));
                item.setTipoMoneda(rs.getInt("TI_MONEDA"));
                item.setTipoInteres(rs.getInt("TIPO_INTERES"));
                item.setMontoInteres(rs.getDouble("MON_INTERES"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                     

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
   
    
    public conceptoInstitucionPeriodoC mostrarConceptoInstitucionPeriodo(int institucion,String periodo,String seccion,boolean tipo) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        conceptoInstitucionPeriodoC item =null;    
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT top 1 A.*FROM SIGU.TES_CONCEPTO_INSTITUCION_PERIODO A,SIGU.HOR_PERIODO_SECCION B,SIGU.VTS_PRODUCTO_CONCEPTO C,SIGU.TES_CONCEPTOS D WHERE B.INSTITUCION=? AND B.PERIODO=? AND B.SECCION=? AND A.INSTITUCION=B.INSTITUCION  AND B.PERIODO=A.PERIODO AND C.INSTITUCION=A.INSTITUCION AND C.PAQUETE=B.PAQUETE AND C.PRODUCTO=B.PRODUCTO AND A.CONCEPTO=C.CONCEPTO AND C.DESCUENTO='0000' AND D.CONCEPTO=A.CONCEPTO AND D.CERTIFICACION=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, seccion);
            cs.setBoolean(4, tipo);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new conceptoInstitucionPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));                
                item.setMontoPago(rs.getDouble("MON_PAGO"));
                item.setTipoMoneda(rs.getInt("TI_MONEDA"));
                item.setTipoInteres(rs.getInt("TIPO_INTERES"));
                item.setMontoInteres(rs.getDouble("MON_INTERES"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));                         

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
   
}
