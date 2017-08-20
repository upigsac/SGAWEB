/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.detallePagoPersonaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class detallePagoPersonaDAO {
	
	
	
     public List<detallePagoPersonaC> mostrarDetallePagoPersona(int institucion,String concepto,String alumno) {
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<detallePagoPersonaC> lista=new ArrayList<>();
        detallePagoPersonaC item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM SIGU.TES_DET_PAGOS_PERSONA A ,SIGU.ACA_ALUMNO B ,SIGU.TES_PAGOS_PERSONA C WHERE  A.INSTITUCION LIKE ? AND A.CONCEPTO LIKE ? AND B.ALUMNO=? AND A.PERSONA= B.PERSONA AND B.INSTITUCION=A.INSTITUCION  AND C.NUM_COMPROBANTE=A.NUM_COMPROBANTE AND C.TIPO_DOCUMENTO=A.TIPO_DOCUMENTO AND C.ESTADO_REGISTRO=28");
            
            cs.setString(1, "%");
            cs.setString(2, concepto);
            cs.setString(3, alumno);
            
            

            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new detallePagoPersonaC();
                item.setTipoDocumento(rs.getInt("TIPO_DOCUMENTO"));
                item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));
                item.setNumeroFut(rs.getString("NUM_FUT"));
                item.setPersona(rs.getString("PERSONA"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setMontoBase(rs.getDouble("MONTO_BASE"));
                item.setMontoParte(rs.getDouble("MONTO_PARTE"));
                item.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                item.setSaldoItem(rs.getDouble("SALDO_ITEM"));
                item.setMontoDescuento(rs.getDouble("MONTO_DESC"));
                item.setMontoDescuentoAdi(rs.getDouble("MONTO_DESCUENTO_ADI"));
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setDescuentoAdi(rs.getString("DESCUENTO_ADI"));     
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            
            
        }

        return lista;
    }
     public List<detallePagoPersonaC> mostrarDetallePagoPersona(String institucion,String concepto,String persona) {
         
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;      
         List<detallePagoPersonaC> lista=new ArrayList<>();
         detallePagoPersonaC item;
         try {             
             c = ConexionWeb();
             cs = c.prepareCall("SELECT *FROM  SIGU.TES_DET_PAGOS_PERSONA WHERE INSTITUCION LIKE ? AND  CONCEPTO=? AND PERSONA= ?  AND ESTADO_REGISTRO=28 AND TIPO_DOCUMENTO IN(5,26,6)");             
             cs.setString(1, institucion);
             cs.setString(2, concepto);
             cs.setString(3, persona);
             rs = cs.executeQuery();
             while (rs.next()) {
                 
                 item = new detallePagoPersonaC();
                 item.setTipoDocumento(rs.getInt("TIPO_DOCUMENTO"));
                 item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));
                 item.setNumeroFut(rs.getString("NUM_FUT"));
                 item.setPersona(rs.getString("PERSONA"));
                 item.setInstitucion(rs.getInt("INSTITUCION"));
                 item.setPeriodo(rs.getString("PERIODO"));
                 item.setConcepto(rs.getString("CONCEPTO"));
                 item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                 item.setMontoBase(rs.getDouble("MONTO_BASE"));
                 item.setMontoParte(rs.getDouble("MONTO_PARTE"));
                 item.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                 item.setSaldoItem(rs.getDouble("SALDO_ITEM"));
                 item.setMontoDescuento(rs.getDouble("MONTO_DESC"));
                 item.setMontoDescuentoAdi(rs.getDouble("MONTO_DESCUENTO_ADI"));
                 item.setDescuento(rs.getString("DESCUENTO"));
                 item.setDescuentoAdi(rs.getString("DESCUENTO_ADI"));
                 
                 
                 lista.add(item);
                 
                 
         
             }
             cerrarCall(cs);
             cerrarConexion(c);
         } catch (SQLException e) {
         	System.out.println(e.getMessage());
             
             
         }

         return lista;
     }
     
public String mostrarTotalConcepto(String institucion,String concepto,String alumno) {
         
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;      
     
         String total="";
         try {
             
             c = ConexionWeb();
             cs = c.prepareCall("SELECT [DI].[SF_PAGO_CONCEPTO_ALUMNO](?,?,?)AS TOTAL");
             
             cs.setString(1, institucion);
             cs.setString(2, concepto);
             cs.setString(3, alumno);
             
             

             rs = cs.executeQuery();
             while (rs.next()) {                 
              
            	 total=rs.getString("TOTAL");
              
                 
                 
         
             }
             cerrarCall(cs);
             cerrarConexion(c);
         } catch (SQLException e) {
         	System.out.println(e.getMessage());
             
             
         }

         return total;
     }
}
