/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.revistaDetalleC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class revistaDetalleDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<revistaDetalleC> detalleRevista(String revista) {

        List<revistaDetalleC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        revistaDetalleC item;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from DI.REVISTA_DETALLE WHERE REVISTA=? ORDER BY PAGINA");

            
            cs.setString(1, revista);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new revistaDetalleC();
                item.setRevista(rs.getString("REVISTA"));
                item.setPagina(rs.getInt("PAGINA"));
                item.setRutaImagen(rs.getString("RUTA_IMAGEN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
           // e.printStackTrace();
        }
        return lista;
    }
    public String detalleRevistaLineal(String revista) {

        String lista = "";
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from DI.REVISTA_DETALLE WHERE REVISTA=? ORDER BY PAGINA");

            
            cs.setString(1, revista);
            rs = cs.executeQuery();

            while (rs.next()) {
                
                lista=lista + ",";
                lista =lista+ rs.getString("RUTA_IMAGEN");
                
                
            }
          
            
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
           // e.printStackTrace();
        }
        return lista.substring(1, lista.length());
    }
}
