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
import ENTIDAD.bscObjetivoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class bscObjetivoDAO {
    
     public String insertar(bscObjetivoC item) {     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_OBJETIVO(1,?,?,?,?)}");
            cs.setString(1, item.getObjetivo());
            cs.setString(2, item.getDescripcion());
            cs.setString(3, item.getAbreviatura());
            cs.setInt(4, item.getEstadoRegistro());
            cs.registerOutParameter(1, Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                codigo=cs.getString(1);
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
     
     public String eliminar(bscObjetivoC item) {     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_OBJETIVO(2,?,?,?,?)}");
            cs.setString(1, item.getObjetivo());
            cs.setString(2, item.getDescripcion());
            cs.setString(3, item.getAbreviatura());
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
    
    
    public List<bscObjetivoC> mostrarObjetivo(int perpestiva) {
      
        List<bscObjetivoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscObjetivoC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM DI.BSC_OBJETIVO A,DI.BSC_PERPESTIVA_OBJETIVO B WHERE B.OBJETIVO=A.OBJETIVO AND B.PERPESTIVA=? AND A.ESTADO_REGISTRO=1 order by a.CREACION_FECHA");
            cs.setInt(1, perpestiva);
       
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscObjetivoC();                
                item.setObjetivo(rs.getString("OBJETIVO"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));                
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException e) {
           System.out.println("bscObjetivoDAO -> 124 " + e.getMessage());
            
        }
        return lista;
    }
    
    
    public List<bscObjetivoC> mostrarFiltroObjetivo(int perpestiva,String desObjetivo) {
      
        List<bscObjetivoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscObjetivoC item;
       
        try {
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT TOP 50 *FROM DI.BSC_OBJETIVO A,DI.BSC_PERPESTIVA_OBJETIVO B WHERE B.OBJETIVO=A.OBJETIVO AND B.PERPESTIVA=? AND A.DESCRIPCION LIKE ? order by a.CREACION_FECHA");
            cs.setInt(1, perpestiva);
            cs.setString(2,"%"+ desObjetivo + "%");       
            rs = cs.executeQuery();           
            while (rs.next()) {
                item = new bscObjetivoC();                
                item.setObjetivo(rs.getString("OBJETIVO"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));                
                lista.add(item);                
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           System.out.println("bscObjetivoDAO -> 124 " + e.getMessage());            
        }
        return lista;
    }
    
    public List<bscObjetivoC> mostrarObjetivo(int perpestiva,int anio ,int mes) {
      
        List<bscObjetivoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscObjetivoC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("{CALL DI.SP_BSC_BALANCE_SCORE_CARD (2,?,'%','%',?,?)}");
            cs.setInt(1, perpestiva);
            cs.setInt(2, anio);
            cs.setInt(3, mes);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscObjetivoC();
                
                item.setObjetivo(rs.getString("OBJETIVO"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                item.setAnio(rs.getInt("ANO"));
                item.setMes(rs.getInt("MES"));
                item.setActual(rs.getDouble("ACTUAL"));
                item.setMeta(rs.getDouble("META"));
                item.setPorcentaje(rs.getDouble("PORCENTAJE"));
                item.setColor(rs.getString("COLOR"));  
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException e) {
           System.out.println("eroor mostrarObjetivo" +e.getMessage());
            
        }
        return lista;
    }
    public List<bscObjetivoC> mostrarObjetivo(int perpestiva,int anio ) {
      
        List<bscObjetivoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscObjetivoC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("{CALL DI.SP_BSC_BALANCE_SCORE_CARD (5,?,'%','%',?,'%')}");
            cs.setInt(1, perpestiva);
            cs.setInt(2, anio);
           
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscObjetivoC();
                
                item.setObjetivo(rs.getString("OBJETIVO"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                item.setAnio(rs.getInt("ANO"));               
                item.setActual(rs.getDouble("ACTUAL"));
                item.setMeta(rs.getDouble("META"));
                item.setPorcentaje(rs.getDouble("PORCENTAJE"));
                item.setColor(rs.getString("COLOR"));  
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException e) {
           System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
    public List<bscObjetivoC> filtroObjetivo(String descripcion ) {
      
        List<bscObjetivoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscObjetivoC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT TOP 50 *FROM DI.BSC_OBJETIVO  WHERE DESCRIPCION LIKE ? AND ESTADO_REGISTRO=1");
            cs.setString(1, "%"+descripcion+"%");
            
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscObjetivoC();
                
                item.setObjetivo(rs.getString("OBJETIVO"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                                
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
