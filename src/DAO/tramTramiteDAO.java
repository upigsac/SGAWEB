/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.tramTramiteC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tramTramiteDAO {
    public String insertar(tramTramiteC item) {        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_DOCUMENTARIO](1,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setInt(2, item.getTramite());
            cs.setString(3, item.getDescripcion());
            cs.setString(4, item.getAbreviatura());
            cs.setInt(5, item.getTiempoEntrega());
            cs.setInt(6, item.getTipoTramite());            
            cs.setInt(7, item.getEstadoRegistro());            
            cs.registerOutParameter(2, Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                codigo = cs.getString(2);
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            
          //  JOptionPane.showMessageDialog(null, ex.getMessage());
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
    
    
    
    public String eliminar(tramTramiteC item) {        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_DOCUMENTARIO](2,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setInt(2, item.getTramite());
            cs.setString(3, item.getDescripcion());
            cs.setString(4, item.getAbreviatura());
            cs.setInt(5, item.getTiempoEntrega());
            cs.setInt(6, item.getTipoTramite());            
            cs.setInt(7, item.getEstadoRegistro());            
            cs.registerOutParameter(2, Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                codigo = cs.getString(2);
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            
          //  JOptionPane.showMessageDialog(null, ex.getMessage());
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
    
    
    public List<tramTramiteC> mostrarTramiteDocumentario(int tipoTramite) {

        List<tramTramiteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTramiteC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM  DI.TRAM_TRAMITE_DOCUMENTARIO  WHERE TIPO_TRAMITE=? AND ESTADO_REGISTRO=1 ORDER BY DESCRIPCION ");
            cs.setInt(1, tipoTramite);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));                
                item.setTramite(rs.getInt("TRAMITE"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setTiempoEntrega(rs.getInt("TIEMPO_ENTREGA"));                
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo ENCONTRE " + e.getMessage());
        }
        return lista;
    }
    
    public List<tramTramiteC> mostrarTramiteDocumentario() {

        List<tramTramiteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTramiteC item ;

        try {

            c = ConexionWeb(); 
            cs = c.prepareCall("SELECT *FROM DI.TRAM_TRAMITE_DOCUMENTARIO WHERE ESTADO_REGISTRO=1 ORDER BY TIPO_TRAMITE,DESCRIPCION");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));                
                item.setTramite(rs.getInt("TRAMITE"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setTiempoEntrega(rs.getInt("TIEMPO_ENTREGA"));   
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
    
    public tramTramiteC mostrarUltimoTramiteDocumentario() {

       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTramiteC item = null ;

        try {

            c = ConexionWeb(); 
            cs = c.prepareCall("SELECT TOP 1 * FROM DI.TRAM_TRAMITE_DOCUMENTARIO ORDER BY TRAMITE DESC");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));                
                item.setTramite(rs.getInt("TRAMITE"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setTiempoEntrega(rs.getInt("TIEMPO_ENTREGA"));   
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

               

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return item;
    }
    
    public List<tramTramiteC> filtroTramiteDocumentario(String tipoTramite,String descripcion) {

        List<tramTramiteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTramiteC item ;

        try {

            c = ConexionWeb(); 
            cs = c.prepareCall("SELECT  *FROM DI.TRAM_TRAMITE_DOCUMENTARIO WHERE TIPO_TRAMITE LIKE ? AND DESCRIPCION LIKE '%'+ ? +'%'");
            cs.setString(1, tipoTramite);
            cs.setString(2, descripcion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));                
                item.setTramite(rs.getInt("TRAMITE"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setTiempoEntrega(rs.getInt("TIEMPO_ENTREGA"));   
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error filtroTramiteDocumentario " + e.getMessage());
        }
        return lista;
    }
    
    
    public tramTramiteC mostrarTramiteDocumentario(int institucion,int tramite) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTramiteC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_TRAMITE_DOCUMENTARIO WHERE INSTITUCION=? AND TRAMITE=? ");
            cs.setInt(1, institucion);   
            cs.setInt(2, tramite);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));                
                item.setTramite(rs.getInt("TRAMITE"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setTiempoEntrega(rs.getInt("TIEMPO_ENTREGA"));  
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error  " + e.getMessage());
        }
        return item;
    }
    
    
     
    
    
    
}
