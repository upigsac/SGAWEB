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
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.personalEntregaC;
import ENTIDAD.personalEntregaMonedaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;


public class personalEntregaDAO {
     public String insertar(personalEntregaC item,List<personalEntregaMonedaC> lista) {
        
         String codigo="";
         
         
         
        Connection c ;
        c = ConexionWeb();
        CallableStatement cs ;
        CallableStatement css ;
        
        
        try {
            c.setAutoCommit(false);   
           
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_ENTREGA (?,?,?,?,?,?,?,?,?)}");            
            cs.setString(1, item.getPersonal());
            cs.setInt(2, item.getAno());
            cs.setInt(3, item.getMes());
            cs.setInt(4, item.getDia());
            cs.setDouble(5, item.getMontoTotal());
            cs.setDouble(6, item.getCajaChica());
            cs.setDouble(7, item.getCajaSidem());
            cs.setString(8, item.getObservacion());
            cs.setInt(9, item.getEstadoRegistro());          
            cs.executeUpdate();
            
            
            for (personalEntregaMonedaC itemList : lista) {
            css = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_ENTREGA_MONEDA (?,?,?,?,?,?,?)}");  
            /*
             util.consolaCliente( "------------------------------");
          util.consolaCliente( itemList.getPersonal());
          util.consolaCliente(""+ itemList.getAno());
          util.consolaCliente(""+ itemList.getMes());
          util.consolaCliente(""+ itemList.getDia());
          util.consolaCliente(""+ itemList.getDinero());
          util.consolaCliente(""+itemList.getCantidad());
          
          util.consolaCliente(""+ item.getEstadoRegistro()); 
            util.consolaCliente( "------------------------------");
            */
            
            css.setString(1, itemList.getPersonal());
            css.setInt(2, itemList.getAno());
            css.setInt(3, itemList.getMes());
            css.setInt(4, itemList.getDia());
            css.setString(5, itemList.getDinero());
            css.setInt(6, itemList.getCantidad());
            css.setInt(7, itemList.getEstadoRegistro());          
            css.executeUpdate();
              
            }
            
            
            
            
             c.commit();   
            } catch (SQLException e) {           
              util.consolaCliente("erro" + e.getMessage());
             if (c != null) {
                try {
                    deshacerCambios(c);                    
                } catch (Exception ex) {
                    util.consolaCliente( ex.getMessage());                    
                }
            }
        }finally{
          
            cerrarConexion(c);
        }

        return codigo;
    }
     
     
     public personalEntregaC mostrarPersonalEntrega(String personal, int anio, int mes, int dia) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        personalEntregaC item=null;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.MO_PERSONAL_ENTREGA WHERE PERSONAL=? AND AÑO=? AND MES=? AND DIA=?");
            cs.setString(1, personal);
            cs.setInt(2, anio);
            cs.setInt(3, mes);
            cs.setInt(4, dia);
            
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personalEntregaC();
                item.setPersonal(rs.getString("PERSONAL"));
                item.setAno(rs.getInt("AÑO"));
                item.setMes(rs.getInt("MES"));
                item.setDia(rs.getInt("DIA"));
                item.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                item.setCajaChica(rs.getDouble("CAJA_CHICA"));
                item.setCajaSidem(rs.getDouble("CAJA_SIDEM"));
                item.setObservacion(rs.getString("OBSERVACION"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error" + e.getMessage());
        }
        return item;
    }
}
