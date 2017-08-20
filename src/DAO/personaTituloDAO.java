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
import ENTIDAD.personaTituloC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class personaTituloDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean insertar(personaTituloC item) {       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONA_TITULO(1,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setString(3, item.getTitulo());
            cs.setInt(4, item.getInstitucion());            
            cs.setString(5,util.convertDate(item.getFecha(), "dd-MM-yyyy") );            
            cs.setInt(6, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR MANTENIMIENTO IDIOMA " + e.getMessage());
        }
        return rpta;
    }
     
     public List<personaTituloC> mostrarPersonalTitulo(String personal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaTituloC> lista = new ArrayList<>();
        personaTituloC item;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PERSONA_TITULO WHERE PERSONA=? AND ESTADO_REGISTRO=1");
            cs.setString(1, personal);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personaTituloC();
                item.setPersona(rs.getString("PERSONA"));
                item.setItem(rs.getInt("ITEM"));
                item.setTitulo(rs.getString("TITULO"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setFecha(rs.getDate("FECHA"));
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
     
     public boolean eliminar(personaTituloC item) {
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONA_TITULO(2,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setString(3, item.getTitulo());
            cs.setInt(4, item.getInstitucion());            
            cs.setString(5,util.convertDate(item.getFecha(), "dd-MM-yyyy") );            
            cs.setInt(6, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR MANTENIMIENTO IDIOMA " + e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
}
