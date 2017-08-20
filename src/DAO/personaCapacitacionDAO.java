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
import ENTIDAD.personaCapacitacionC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class personaCapacitacionDAO {
    public boolean insertar(personaCapacitacionC item) {
      
                
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_CAPACITACIONES(1,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getTipo());
            cs.setString(4, item.getDenominacion());            
            cs.setInt(5, item.getInstitucion());            
            cs.setString(6,util.convertDate(item.getFecha(), "dd-MM-yyyy") );            
            cs.setInt(7, item.getEstadoRegistro());
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
      public boolean eliminar(personaCapacitacionC item) {
      
                
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_CAPACITACIONES(2,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getTipo());
            cs.setString(4, item.getDenominacion());            
            cs.setInt(5, item.getInstitucion());            
            cs.setString(6,util.convertDate(item.getFecha(), "dd-MM-yyyy") );            
            cs.setInt(7, item.getEstadoRegistro());
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
     public List<personaCapacitacionC> mostrarPersonalCapacitacion(String personal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaCapacitacionC> lista = new ArrayList<>();
        personaCapacitacionC item;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PERSONA_CAPACITACIONES WHERE PERSONA=? AND ESTADO_REGISTRO=1");
            cs.setString(1, personal);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personaCapacitacionC();
                item.setPersona(rs.getString("PERSONA"));
                item.setItem(rs.getInt("ITEM"));
                item.setTipo(rs.getInt("TIPO"));
                item.setDenominacion(rs.getString("DENOMINACION"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setFecha(rs.getDate("FECHA"));
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
