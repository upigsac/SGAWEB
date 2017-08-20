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

import ENTIDAD.bscPerpestivaObjetivoIndicadorDetalleC;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class bscPerpestivaObjetivoIndicadorDetalleDAO {
    
    public String insertar(bscPerpestivaObjetivoIndicadorDetalleC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_PERPESTIVA_OBJETIVO_INDICADOR_DETALLE (1,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPerpestiva());
            cs.setString(2, item.getObjetivo());
            cs.setString(3, item.getIndicador());            
            cs.setInt(4, item.getAnio());            
            cs.setInt(5, item.getMes());            
            cs.setDouble(6, item.getMeta());            
            cs.setDouble(7, item.getActual());                        
            cs.setInt(8, item.getEstadoRegistro());

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
    
    
    
     public String eliminar(bscPerpestivaObjetivoIndicadorDetalleC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_PERPESTIVA_OBJETIVO_INDICADOR_DETALLE (2,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPerpestiva());
            cs.setString(2, item.getObjetivo());
            cs.setString(3, item.getIndicador());            
            cs.setInt(4, item.getAnio());            
            cs.setInt(5, item.getMes());            
            cs.setDouble(6, item.getMeta());            
            cs.setDouble(7, item.getActual());                        
            cs.setInt(8, item.getEstadoRegistro());

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
    
    
    public List<bscPerpestivaObjetivoIndicadorDetalleC> mostrarPerpestivaObjetivo(int perpestiva,int objetivo,int indicador) {
      
        List<bscPerpestivaObjetivoIndicadorDetalleC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscPerpestivaObjetivoIndicadorDetalleC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM DI.BSC_PERPESTIVA_OBJETIVO_INDICADOR_DETALLE WHERE PERPESTIVA=? AND OBJETIVO=? AND INDICADOR=? ORDER BY MES");
            cs.setInt(1, perpestiva);
            cs.setInt(2, objetivo);
            cs.setInt(3, indicador);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscPerpestivaObjetivoIndicadorDetalleC();
                item.setPerpestiva(rs.getInt("PERPESTIVA"));
                item.setObjetivo(rs.getString("OBJETIVO"));
                item.setIndicador(rs.getString("INDICADOR"));
                item.setAnio(rs.getInt("ANO"));                
                item.setMes(rs.getInt("MES"));                
                item.setActual(rs.getDouble("ACTUAL"));                
                item.setMeta(rs.getDouble("META"));                
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
    public List<bscPerpestivaObjetivoIndicadorDetalleC> mostrarPerpestivaObjetivo(int perpestiva,String objetivo,String indicador,int anio) {
      
        List<bscPerpestivaObjetivoIndicadorDetalleC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscPerpestivaObjetivoIndicadorDetalleC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("{CALL DI.SP_BSC_BALANCE_SCORE_CARD (4,?,?,?,?,'%')}");
            cs.setInt(1, perpestiva);
            cs.setString(2, objetivo);
            cs.setString(3, indicador);
            cs.setInt(4, anio);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscPerpestivaObjetivoIndicadorDetalleC();
                item.setPerpestiva(rs.getInt("PERPESTIVA"));
                item.setObjetivo(rs.getString("OBJETIVO"));
                item.setIndicador(rs.getString("INDICADOR"));
                item.setAnio(rs.getInt("ANO"));                
                item.setMes(rs.getInt("MES"));                
                item.setActual(rs.getDouble("ACTUAL"));                
                item.setMeta(rs.getDouble("META"));                
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
    
    public List<bscPerpestivaObjetivoIndicadorDetalleC> mostrarPerpestivaObjetivoDetalle(int perpestiva,String objetivo,String indicador,int anio) {
      
        List<bscPerpestivaObjetivoIndicadorDetalleC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscPerpestivaObjetivoIndicadorDetalleC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("{CALL DI.SP_BSC_BALANCE_SCORE_CARD (7,?,?,?,?,'%')}");
            cs.setInt(1, perpestiva);
            cs.setString(2, objetivo);
            cs.setString(3, indicador);
            cs.setInt(4, anio);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscPerpestivaObjetivoIndicadorDetalleC();
                item.setPerpestiva(rs.getInt("PERPESTIVA"));
                item.setObjetivo(rs.getString("OBJETIVO"));
                item.setIndicador(rs.getString("INDICADOR"));
                item.setAnio(rs.getInt("ANO"));                
                item.setMes(rs.getInt("MES"));                
                item.setActual(rs.getDouble("ACTUAL"));                
                item.setMeta(rs.getDouble("META"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                  item.setColor(rs.getString("COLOR"));
                item.setPorcentaje(rs.getDouble("PORCENTAJE"));
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

