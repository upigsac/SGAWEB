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

import ENTIDAD.marcacionPuertaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class marcacionPuertaDAO {
     public String insertar(marcacionPuertaC item) {
     
        Connection c =null;
        CallableStatement cs=null ;
        
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACC_MARCACION(1,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getLocal());
            cs.setInt(2, item.getPuerta());
            cs.setString(3, util.convertDate(item.getFecha(), "dd-MM-yyyy HH:mm:ss"));
            cs.setInt(4,item.getItem());
            cs.setString(5,item.getPersona());
            cs.setInt(6, item.getEstadoRegistro());
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.executeUpdate() ;
            c.commit();
            codigo = cs.getString(7);
           
            
        } catch (SQLException ex) {
        	   deshacerCambios(c);
            System.out.println(ex.getMessage());
         
        }finally{
        	cerrarCall(cs);
            cerrarConexion(c);
        }
        return codigo;
    }
}
