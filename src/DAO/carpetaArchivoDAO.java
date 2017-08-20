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
import ENTIDAD.carpetaArchivoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class carpetaArchivoDAO {
        public carpetaArchivoC mostrarCarpetaArchivo(int carpeta, String extension) {
        util.consolaCliente("extension: " +extension);
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carpetaArchivoC item=null;
       

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.GES_CARPETA_ARCHIVO WHERE CARPETA=? AND EXTENSION=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, carpeta);
            cs.setString(2, extension);
           

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new carpetaArchivoC();
                
                item.setCarpeta(rs.getInt("CARPETA"));
                item.setArchivo(rs.getString("ARCHIVO"));
                item.setExtension(rs.getString("EXTENSION"));
                item.setPesoMaximo(rs.getInt("PESO_MAXIMO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
        }
        return item;
    }
}
