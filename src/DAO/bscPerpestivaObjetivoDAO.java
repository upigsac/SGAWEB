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


import ENTIDAD.bscPerpestivaObjetivoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class bscPerpestivaObjetivoDAO {
    
    
    public String insertar(bscPerpestivaObjetivoC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_PERPESTIVA_OBJETIVO(1,?,?,?,?,?)}");
         
            cs.setInt(1, item.getPerpestiva());
            cs.setString(2, item.getObjetivo());
            cs.setString(3, item.getAnalisis());
            cs.setString(4, item.getRecomendacion());
            cs.setInt(5, item.getEstadoRegistro());

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
    
    
    
    public String eliminar(bscPerpestivaObjetivoC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_PERPESTIVA_OBJETIVO(2,?,?,?,?,?)}");
            cs.setInt(1, item.getPerpestiva());
            cs.setString(2, item.getObjetivo());
            cs.setString(3, item.getAnalisis());
            cs.setString(4, item.getRecomendacion());
            cs.setInt(5, item.getEstadoRegistro());

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
    
    
    public bscPerpestivaObjetivoC mostrarPerpestivaObjetivo(int perpestiva,String objectivo) {
      
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscPerpestivaObjetivoC item=null;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM DI.BSC_PERPESTIVA_OBJETIVO WHERE PERPESTIVA=? AND OBJETIVO=?");
            cs.setInt(1, perpestiva);
            cs.setString(2, objectivo);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscPerpestivaObjetivoC();
                
                item.setPerpestiva(rs.getInt("PERPESTIVA"));              
                item.setObjetivo(rs.getString("OBJETIVO"));                
                item.setAnalisis(rs.getString("ANALISIS"));                                
                item.setRecomendacion(rs.getString("RECOMENDACION"));                                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
        
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException e) {
           System.out.println(e.getMessage());
            
        }
        return item;
    }
     
}
