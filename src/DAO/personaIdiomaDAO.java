/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import ENTIDAD.personaIdiomaC;


public class personaIdiomaDAO implements Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean insertar(personaIdiomaC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONA_IDIOMAS(1,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getIdioma());
            cs.setString(4, item.getNivel());            
            cs.setInt(5, item.getEstadoRegistro());
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
     
     
     
     public boolean eliminar(personaIdiomaC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONA_IDIOMAS(2,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getIdioma());
            cs.setString(4, item.getNivel());            
            cs.setInt(5, item.getEstadoRegistro());
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
     
     public List<personaIdiomaC> mostrarPersonaIdioma(String persona) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaIdiomaC> lista = new ArrayList<>();
        personaIdiomaC item;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PERSONA_IDIOMA WHERE PERSONA=? AND ESTADO_REGISTRO=1");
            cs.setString(1, persona);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personaIdiomaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setItem(rs.getInt("ITEM"));
                item.setIdioma(rs.getInt("IDIOMA"));
                item.setNivel(rs.getString("NIVEL"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error" + e.getMessage());
        }
        return lista;
    }
}
