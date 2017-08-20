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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class validacionDAO {
    public void valida() {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;        
        
        
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT APP_NAME()APLICACION");
            
            rs = cs.executeQuery();
            
            while (rs.next()) {     
                
                
                
                 util.consolaCliente( rs.getString("APLICACION"));

            }
            
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           util.consolaCliente( e.getMessage());
        }
       
    }
}
