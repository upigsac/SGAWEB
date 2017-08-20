/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.bscPerpestivaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bscPerpestivaDAO {
    public List<bscPerpestivaC> mostrarPerpestiva() {      
        List<bscPerpestivaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscPerpestivaC item;       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM  DI.BSC_PERPESTIVA");
            
       
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscPerpestivaC();                
                item.setPerpestiva(rs.getInt("PERPESTIVA"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                                
                item.setPrefijo(rs.getString("PREFIJO"));                                
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
    public List<bscPerpestivaC> mostrarPerpestiva(int anio,int mes) {      
        List<bscPerpestivaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscPerpestivaC item;       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("{CALL DI.SP_BSC_BALANCE_SCORE_CARD (1,'%','%','%',?,?) }");
            cs.setInt(1, anio);
            cs.setInt(2, mes);
       
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscPerpestivaC();                
                item.setPerpestiva(rs.getInt("PERPESTIVA"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                                
                item.setClassIcon(rs.getString("CLASSICON"));                                
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
}
