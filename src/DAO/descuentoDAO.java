/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.descuentoC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class descuentoDAO implements Serializable  {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public descuentoC mostrarUnicoDescuento(int institucion,String periodo,String concepto) {

        
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        descuentoC item=null ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 1 B.* FROM SIGU.TES_CONCEPTO_INSTITUCION_DESCUENTO A,SIGU.TES_DESCUENTO B WHERE  A.INSTITUCION=? AND A.PERIODO=? AND A.CONCEPTO=? AND B.DESCUENTO=A.DESCUENTO ORDER BY CANTIDAD DESC");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, concepto);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new descuentoC();
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setTipoDescuento(rs.getInt("TIPO_DESC"));
                item.setCantidad(rs.getDouble("CANTIDAD"));
                item.setTipoMoneda(rs.getInt("TIPO_MONEDA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
              

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            System.out.println( e.getMessage());
        }
        return item;
    }
    
    
    
    
    
    
    
    
    
    public List<descuentoC> mostrarDescuento(int institucion,String periodo,String concepto) {

        List<descuentoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        descuentoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_DESCUENTO_CONCEPTO (?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, concepto);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new descuentoC();
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setTipoDescuento(rs.getInt("TIPO_DESC"));
                item.setCantidad(rs.getDouble("CANTIDAD"));
                item.setTipoMoneda(rs.getInt("TIPO_MONEDA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    public List<descuentoC> mostrarDescuento() {

        List<descuentoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        descuentoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from SIGU.TES_DESCUENTO where ESTADO_REGISTRO=1");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new descuentoC();
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setTipoDescuento(rs.getInt("TIPO_DESC"));
                item.setCantidad(rs.getDouble("CANTIDAD"));
                item.setTipoMoneda(rs.getInt("TIPO_MONEDA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            System.out.println( e.getMessage());
        }
        return lista;
    }
    public descuentoC mostrarDescuento(String descuento) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        descuentoC item=null;
        
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  SIGU.TES_DESCUENTO WHERE  DESCUENTO=?");
            
            cs.setString(1, descuento);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new descuentoC();
                item.setDescuento(rs.getString("DESCUENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setTipoDescuento(rs.getInt("TIPO_DESC"));
                item.setCantidad(rs.getDouble("CANTIDAD"));
                item.setTipoMoneda(rs.getInt("TIPO_MONEDA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error DESCUENTO" + e.getMessage());
            
            //e.printStackTrace();
        }

        return item;
    }
}
