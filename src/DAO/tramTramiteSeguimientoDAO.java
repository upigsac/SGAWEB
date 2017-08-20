
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;


import ENTIDAD.tramTramiteSeguimientoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class tramTramiteSeguimientoDAO {
    public String insertar(tramTramiteSeguimientoC item) {
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_SEGUIMIENTO](1,?,?,?,?,?,?,?,?,?,?, ?)}");
          
            cs.setString(1, item.getExpediente());
            cs.setInt(2, item.getOficina());            
            cs.setInt(3, item.getItem());
            cs.setString(4, util.convertDate(item.getFechaEntrega(), "dd-MM-yyyy")  ); 
            cs.setString(5, util.convertDate(item.getHoraEntrega(),"HH:mm:ss")); 
            cs.setString(6, item.getUsuarioEmisor());
            cs.setString(7, item.getAsunto());
            cs.setString(8, item.getUsuarioReceptor());
            cs.setString(9, util.convertDate(item.getFechaResivido(), "dd-MM-yyyy")); 
            cs.setString(10, util.convertDate(item.getHoraResivido(),"HH:mm:ss"));          
            cs.setInt(11, item.getEstadoRegistro());
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
    
    
    public String eliminar(tramTramiteSeguimientoC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_SEGUIMIENTO](2,?,?,?,?,?,?,?,?,?,?, ?)}");
          
            cs.setString(1, item.getExpediente());
            cs.setInt(2, item.getOficina());            
            cs.setInt(3, item.getItem());
            cs.setString(4, util.convertDate(util.fechaHoy()) ); // fecha ingreso
            cs.setString(5, "08:00:15"); //hora ingreso
            cs.setString(6, item.getUsuarioEmisor());
            cs.setString(7, item.getAsunto());
            cs.setString(8, item.getUsuarioReceptor());
            cs.setString(9, null); // fecha salida
            cs.setString(10, null); //hora salida           
            cs.setInt(11, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
    
    
     public List<tramTramiteSeguimientoC> mostrarTramiteSeguimiento(String expediente,String oficina,String usuario) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramiteSeguimientoC> lista = new ArrayList<>();
        tramTramiteSeguimientoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.TRAM_TRAMITE_SEGUIMIENTO WHERE EXPEDIENTE LIKE ? AND OFICINA like ?   ");
            cs.setString(1, expediente);
            cs.setString(2, oficina);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteSeguimientoC();
              
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setItem(rs.getInt("ITEM"));                
                item.setOficina(rs.getInt("OFICINA"));
                item.setUsuarioEmisor(rs.getString("USUARIO_EMISOR"));
                item.setFechaEntrega(rs.getDate("FECHA_INGRESO"));
                item.setHoraEntrega(rs.getTime("HORA_INGRESO"));
                item.setAsunto(rs.getString("ASUNTO"));
                item.setUsuarioReceptor(rs.getString("USUARIO_RECEPTOR"));
                item.setFechaResivido(rs.getDate("FECHA_SALIDA"));
                item.setHoraResivido(rs.getTime("HORA_SALIDA"));
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
     
     public tramTramiteSeguimientoC mostrarTramiteSeguimiento(String expediente,int oficina,int item) {
    
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        tramTramiteSeguimientoC registro =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.TRAM_TRAMITE_SEGUIMIENTO WHERE EXPEDIENTE = ? AND OFICINA = ? AND ITEM=?  ");
            cs.setString(1, expediente);
            cs.setInt(2, oficina);
            cs.setInt(3, item);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                registro = new tramTramiteSeguimientoC();
               
                registro.setExpediente(rs.getString("EXPEDIENTE"));
                registro.setItem(rs.getInt("ITEM"));                
                registro.setOficina(rs.getInt("OFICINA"));
                registro.setUsuarioEmisor(rs.getString("USUARIO_EMISOR"));
                registro.setFechaEntrega(rs.getDate("FECHA_INGRESO"));
                registro.setHoraEntrega(rs.getTime("HORA_INGRESO"));
                registro.setAsunto(rs.getString("ASUNTO"));
                registro.setUsuarioReceptor(rs.getString("USUARIO_RECEPTOR"));
                registro.setFechaResivido(rs.getDate("FECHA_SALIDA"));
                registro.setHoraResivido(rs.getTime("HORA_SALIDA"));
                registro.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            System.out.println(e.getMessage());
        }
        return registro;
    }
     
     public tramTramiteSeguimientoC mostrarTramiteSeguimientoExpedienteOficina(String expediente,int oficina) {
    	    
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         
         tramTramiteSeguimientoC registro =null;

         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT * FROM DI.TRAM_TRAMITE_SEGUIMIENTO WHERE EXPEDIENTE = ? AND OFICINA = ?  ");
             cs.setString(1, expediente);
             cs.setInt(2, oficina);
            
             
             rs = cs.executeQuery();

             while (rs.next()) {
                 registro = new tramTramiteSeguimientoC();
                
                 registro.setExpediente(rs.getString("EXPEDIENTE"));
                 registro.setItem(rs.getInt("ITEM"));                
                 registro.setOficina(rs.getInt("OFICINA"));
                 registro.setUsuarioEmisor(rs.getString("USUARIO_EMISOR"));
                 registro.setFechaEntrega(rs.getDate("FECHA_INGRESO"));
                 registro.setHoraEntrega(rs.getTime("HORA_INGRESO"));
                 registro.setAsunto(rs.getString("ASUNTO"));
                 registro.setUsuarioReceptor(rs.getString("USUARIO_RECEPTOR"));
                 registro.setFechaResivido(rs.getDate("FECHA_SALIDA"));
                 registro.setHoraResivido(rs.getTime("HORA_SALIDA"));
                 registro.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                 
             }
             cerrarCall(cs);
             cerrarConexion(c);

         } catch (SQLException e) {
            
             System.out.println(e.getMessage());
         }
         return registro;
     }
     
      public tramTramiteSeguimientoC mostrarTramiteSeguimiento(String expediente,int item) {
    
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        tramTramiteSeguimientoC registro =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.TRAM_TRAMITE_SEGUIMIENTO WHERE EXPEDIENTE = ?  AND ITEM=?  ");
            cs.setString(1, expediente);           
            cs.setInt(2, item);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                registro = new tramTramiteSeguimientoC();
               
                registro.setExpediente(rs.getString("EXPEDIENTE"));
                registro.setItem(rs.getInt("ITEM"));                
                registro.setOficina(rs.getInt("OFICINA"));
                registro.setUsuarioEmisor(rs.getString("USUARIO_EMISOR"));
                registro.setFechaEntrega(rs.getDate("FECHA_INGRESO"));
                registro.setHoraEntrega(rs.getTime("HORA_INGRESO"));
                registro.setAsunto(rs.getString("ASUNTO"));
                registro.setUsuarioReceptor(rs.getString("USUARIO_RECEPTOR"));
                registro.setFechaResivido(rs.getDate("FECHA_SALIDA"));
                registro.setHoraResivido(rs.getTime("HORA_SALIDA"));
                registro.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return registro;
    }
     
     public List<tramTramiteSeguimientoC> mostrarTramiteSeguimiento(String expediente,String oficina,String usuario,Date fechaInicio,Date fechaFin) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramiteSeguimientoC> lista = new ArrayList<>();
        tramTramiteSeguimientoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.TRAM_TRAMITE_SEGUIMIENTO WHERE EXPEDIENTE LIKE ? AND OFICINA like ? AND  FECHA_INGRESO BETWEEN ? AND ? ORDER BY EXPEDIENTE DESC");
            cs.setString(1, expediente);
            cs.setString(2, oficina);
            cs.setString(3, util.convertDate(fechaInicio));
            cs.setString(4, util.convertDate(fechaFin));
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteSeguimientoC();
                
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setItem(rs.getInt("ITEM"));                
                item.setOficina(rs.getInt("OFICINA"));
                item.setUsuarioEmisor(rs.getString("USUARIO_EMISOR"));
                item.setFechaEntrega(rs.getDate("FECHA_INGRESO"));
                item.setHoraEntrega(rs.getTime("HORA_INGRESO"));
                item.setAsunto(rs.getString("ASUNTO"));
                item.setUsuarioReceptor(rs.getString("USUARIO_RECEPTOR"));
                item.setFechaResivido(rs.getDate("FECHA_SALIDA"));
                item.setHoraResivido(rs.getTime("HORA_SALIDA"));
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
     
     
     public List<tramTramiteSeguimientoC> mostrarTramiteSeguimiento(String expediente) {

         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         List<tramTramiteSeguimientoC> lista = new ArrayList<>();
         tramTramiteSeguimientoC item ;

         try {

             c = ConexionWeb();
             cs = c.prepareCall("select *from DI.TRAM_TRAMITE_SEGUIMIENTO where EXPEDIENTE=? AND ESTADO_REGISTRO<>0 ORDER BY ITEM");
             cs.setString(1, expediente);
             
             
             rs = cs.executeQuery();

             while (rs.next()) {
                 item = new tramTramiteSeguimientoC();
                 
                 item.setExpediente(rs.getString("EXPEDIENTE"));
                 item.setItem(rs.getInt("ITEM"));                
                 item.setOficina(rs.getInt("OFICINA"));
                 item.setUsuarioEmisor(rs.getString("USUARIO_EMISOR"));
                 item.setFechaEntrega(rs.getDate("FECHA_INGRESO"));
                 item.setHoraEntrega(rs.getTime("HORA_INGRESO"));
                 item.setAsunto(rs.getString("ASUNTO"));
                 item.setUsuarioReceptor(rs.getString("USUARIO_RECEPTOR"));
                 item.setFechaResivido(rs.getDate("FECHA_SALIDA"));
                 item.setHoraResivido(rs.getTime("HORA_SALIDA"));
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
