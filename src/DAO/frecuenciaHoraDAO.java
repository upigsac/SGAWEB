/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.frecuenciaHoraC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class frecuenciaHoraDAO {
     public List<frecuenciaHoraC> mostrarFrecuenciaHora() {
      
        List<frecuenciaHoraC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        frecuenciaHoraC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT * FROM DI.VEN_FRECUENCIA_HORA");
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new frecuenciaHoraC();
                item.setFrecuenciaHora(rs.getInt("FRECUENCIA_HORA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));                
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
     
     
     public List<frecuenciaHoraC> mostrarFrecuenciaHora(int tipoFrecuencia) {
      
        List<frecuenciaHoraC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        frecuenciaHoraC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT * FROM DI.VEN_FRECUENCIA_HORA where TIPO_FRECUENCIA=?");
            cs.setInt(1, tipoFrecuencia);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                item = new frecuenciaHoraC();
                item.setFrecuenciaHora(rs.getInt("FRECUENCIA_HORA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setTurno(rs.getInt("TURNO"));                
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
     
     public List<frecuenciaHoraC> mostrarFrecuenciaHora(int tipoFrecuencia,int frecuenciaDia) {
      
        List<frecuenciaHoraC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        frecuenciaHoraC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT * FROM DI.VEN_FRECUENCIA_HORA where TIPO_FRECUENCIA=? AND FRECUENCIA_DIA=? AND ESTADO_REGISTRO=1 ORDER BY ORDEN");
            cs.setInt(1, tipoFrecuencia);
            cs.setInt(2, frecuenciaDia);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                item = new frecuenciaHoraC();
                item.setFrecuenciaHora(rs.getInt("FRECUENCIA_HORA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setTurno(rs.getInt("TURNO"));                
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
}
