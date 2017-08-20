/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.idiomaC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class idiomaDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<idiomaC> mostrarIdioma() {

        List<idiomaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        idiomaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_IDIOMA WHERE ESTADO_REGISTRO=1 ORDER BY 2  ");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new idiomaC();
                item.setIdioma(rs.getInt("IDIOMA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
     public idiomaC mostrarIdioma(int idioma) {

        
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        idiomaC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_IDIOMA WHERE IDIOMA=? AND  ESTADO_REGISTRO=1 ORDER BY 2  ");
            cs.setInt(1, idioma);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new idiomaC();
                item.setIdioma(rs.getInt("IDIOMA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
