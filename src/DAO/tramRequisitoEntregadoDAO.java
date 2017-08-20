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

import ENTIDAD.tramTramiteRequisitoEntregadoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class tramRequisitoEntregadoDAO {
    
    public String insertar(tramTramiteRequisitoEntregadoC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_REQUISITO_ENTREGADO](?,?,?,?,?,?)}");
            cs.setString("ITEMWEB", "1");
            cs.setString("EXPEDIENTE", item.getExpediente());
            cs.setInt("REQUISITO", item.getRequisito());          
            cs.setBoolean("ENTREGADO", item.isEntregado() );
            cs.setString("FECHAENTREGA", util.convertDate(item.getFechaEntrega()) );
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
            
            
            
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
    
    public String eliminar(tramTramiteRequisitoEntregadoC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_REQUISITO_ENTREGADO](2,?,?,?,?)}");
           
            cs.setString(1, item.getExpediente());
            cs.setInt(2, item.getRequisito());            
            cs.setString(3, util.convertDate(item.getFechaEntrega()) );
            cs.setInt(4, item.getEstadoRegistro());
            
            
            
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
    
    
     public List<tramTramiteRequisitoEntregadoC> mostrarRequisitoEntregados(String expediente) {

        List<tramTramiteRequisitoEntregadoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTramiteRequisitoEntregadoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_REQUISITOS_ENTREGADOS WHERE EXPEDIENTE AND ESTADO_REGISTRO=1");
            cs.setString(1, expediente);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteRequisitoEntregadoC();
                item.setExpediente(rs.getString("EXPEDIENTE"));                                
                item.setRequisito(rs.getInt("REQUISITO"));                
                item.setFechaEntrega(rs.getDate("FECHAENTREGA"));
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
     
     public List<String> mostrarRequisitoEntregado(String expediente) {

        List<String> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_REQUISITOS_ENTREGADOS WHERE EXPEDIENTE=? AND ESTADO_REGISTRO=1");
            cs.setString(1, expediente);
            rs = cs.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("REQUISITO"));                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
}
