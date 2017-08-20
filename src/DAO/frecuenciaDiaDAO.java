/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.frecuenciaDiaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class frecuenciaDiaDAO {
    public List<frecuenciaDiaC> mostrarFrecuenciaDia() {
      
        List<frecuenciaDiaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        frecuenciaDiaC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT * FROM DI.VEN_FRECUENCIA_DIA");
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new frecuenciaDiaC();
                item.setFrecuenciaDia(rs.getInt("FRECUENCIA_DIA"));
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
    public List<frecuenciaDiaC> mostrarFrecuenciaDia(int tipoFrecuencia) {
      
        List<frecuenciaDiaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        frecuenciaDiaC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT * FROM DI.VEN_FRECUENCIA_DIA WHERE TIPO_FRECUENCIA=?  AND ESTADO_REGISTRO=1 ORDER BY ORDEN");
            cs.setInt(1, tipoFrecuencia);
            rs = cs.executeQuery();
            
           
            while (rs.next()) {
                item = new frecuenciaDiaC();
                item.setFrecuenciaDia(rs.getInt("FRECUENCIA_DIA"));
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
    
    
    public List<frecuenciaDiaC> mostrarFrecuenciaDia(int institucion,String periodo,String carrera) {
        
        List<frecuenciaDiaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        frecuenciaDiaC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT DISTINCT B.*FROM SIGU.HOR_PERIODO_SECCION A, DI.VEN_FRECUENCIA_DIA B WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.FRECUENCIA_DIA=B.FRECUENCIA_DIA ORDER BY B.ORDEN");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();
            
           
            while (rs.next()) {
                item = new frecuenciaDiaC();
                item.setFrecuenciaDia(rs.getInt("FRECUENCIA_DIA"));
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
}
