/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.localPuertaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class localPuertaDAO {
    public localPuertaC mostrarLocalPuerta(String estacion) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        localPuertaC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from SIGU.LOG_LOCAL_PUERTA WHERE ESTACION=? AND ESTADO_REGISTRO=1");
            cs.setString(1, estacion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new localPuertaC();
                item.setLocal(rs.getInt("LOCAL"));
                item.setPuerta(rs.getInt("PUERTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setEstacion(rs.getString("ESTACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return item;
    }
}
