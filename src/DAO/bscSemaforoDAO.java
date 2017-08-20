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


import ENTIDAD.bscSemaforoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class bscSemaforoDAO {
    
    public String insertar(bscSemaforoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_SEMAFORO(1,?,?,?,?,?,?)}");
            cs.setInt(1, item.getSemaforo());
            cs.setString(2, item.getDescripcion());
            cs.setInt(3, item.getDesde());
            cs.setInt(4, item.getHasta());
            cs.setString(5, item.getColor());
            cs.setInt(6, item.getEstadoRegistro());

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
    
    
     public String eliminar(bscSemaforoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_SEMAFORO(2,?,?,?,?,?,?)}");
            cs.setInt(1, item.getSemaforo());
            cs.setString(2, item.getDescripcion());
            cs.setInt(3, item.getDesde());
            cs.setInt(4, item.getHasta());
            cs.setString(5, item.getColor());
            cs.setInt(6, item.getEstadoRegistro());

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
    
    
    
     public List<bscSemaforoC> mostrarSemaforo() {      
        List<bscSemaforoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        bscSemaforoC item;       
        try {
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM DI.BSC_SEMAFORO WHERE ESTADO_REGISTRO=1 ORDER BY HASTA ");
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new bscSemaforoC();                
                item.setSemaforo(rs.getInt("SEMAFORO"));              
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setColor(rs.getString("COLOR"));
                item.setDesde(rs.getInt("DESDE"));
                item.setHasta(rs.getInt("HASTA"));
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
