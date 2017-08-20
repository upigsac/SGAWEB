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
import ENTIDAD.bscIndicadorC;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class bscIndicadorDAO {
    
    public String insertar(bscIndicadorC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_INDICADOR(1,?,?,?,?)}");
            cs.setString(1, item.getIndicador());
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
    
    
    public String eliminar(bscIndicadorC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_INDICADOR(2,?,?,?,?)}");
            cs.setString(1, item.getIndicador());
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
    
    
    public List<bscIndicadorC> mostrarIndicador(int perpestiva,String objectivo,String personal) {
      
        List<bscIndicadorC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscIndicadorC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT A.* FROM  DI.BSC_INDICADOR A,DI.BSC_PERPESTIVA_OBJETIVO_INDICADOR B, DI.BSC_REPONSABLE_INDICADOR C WHERE A.INDICADOR=B.INDICADOR AND C.INDICADOR=A.INDICADOR AND  B.PERPESTIVA=? AND B.OBJETIVO=? AND C.PERSONAL=? order by A.CREACION_FECHA");
            cs.setInt(1, perpestiva);
            cs.setString(2, objectivo);
            cs.setString(3, personal);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscIndicadorC();
                
                item.setIndicador(rs.getString("INDICADOR"));              
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
    public List<bscIndicadorC> mostrarFiltroIndicador(int perpestiva,String objectivo,String desIndicador) {
      
        List<bscIndicadorC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscIndicadorC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT TOP 50 A.* FROM  DI.BSC_INDICADOR A,DI.BSC_PERPESTIVA_OBJETIVO_INDICADOR B WHERE A.INDICADOR=B.INDICADOR AND B.PERPESTIVA=? AND B.OBJETIVO=? AND A.DESCRIPCION like ? order by A.CREACION_FECHA");
            cs.setInt(1, perpestiva);
            cs.setString(2, objectivo);
            cs.setString(3,"%"+ desIndicador +"%");
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscIndicadorC();
                
                item.setIndicador(rs.getString("INDICADOR"));              
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
    
    public List<bscIndicadorC> mostrarIndicador(int perpestiva,String objectivo,int anio ,int mes) {
      
        List<bscIndicadorC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscIndicadorC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("{CALL DI.SP_BSC_BALANCE_SCORE_CARD (3,?,?,'%',?,?)}");
            cs.setInt(1, perpestiva);
            cs.setString(2, objectivo);
            cs.setInt(3, anio);
            cs.setInt(4, mes);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscIndicadorC();
                
                item.setIndicador(rs.getString("INDICADOR"));              
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
           System.out.println(e.getMessage());
            
        }
        return lista;
    }
    public List<bscIndicadorC> mostrarIndicador(int perpestiva,String objectivo,int anio ) {
      
        List<bscIndicadorC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscIndicadorC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("{CALL DI.SP_BSC_BALANCE_SCORE_CARD (6,?,?,'%',?,'%')}");
            cs.setInt(1, perpestiva);
            cs.setString(2, objectivo);
            cs.setInt(3, anio);
            
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscIndicadorC();
                
                item.setIndicador(rs.getString("INDICADOR"));              
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
    public List<bscIndicadorC> mostrarIndicador() {
      
        List<bscIndicadorC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscIndicadorC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM  DI.BSC_INDICADOR ");
      
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscIndicadorC();
                
                item.setIndicador(rs.getString("INDICADOR"));              
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
