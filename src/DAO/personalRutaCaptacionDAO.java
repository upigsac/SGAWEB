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


import ENTIDAD.personalRutaCaptacionC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class personalRutaCaptacionDAO {
    public List<personalRutaCaptacionC> mostrarPersonalRutaCaptacion(Date fecha) {
     

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personalRutaCaptacionC> lista=new ArrayList<>();
        personalRutaCaptacionC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_RUTA_CAPTACION(?)}");
            
            cs.setString(1, util.convertDate(fecha));
            
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personalRutaCaptacionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setFecha(rs.getDate("FECHA"));
                item.setLatitud(rs.getDouble("LATITUD"));
                item.setLongitud(rs.getDouble("LONGITUD"));
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    public List<personalRutaCaptacionC> mostrarListaPersonalRutaCaptacion(Date fecha,String personal) {
     

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personalRutaCaptacionC> lista=new ArrayList<>();
        personalRutaCaptacionC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_RUTA_CAPTACION_PERSONAL(?,?)}");
            
            cs.setString(1, util.convertDate(fecha));
            cs.setString(2, personal);
            
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personalRutaCaptacionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setFecha(rs.getDate("FECHA"));
                item.setItem(rs.getInt("ITEM"));
                item.setLatitud(rs.getDouble("LATITUD"));
                item.setLongitud(rs.getDouble("LONGITUD"));
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
    
    public personalRutaCaptacionC mostrarPersonalRutaCaptacion(Date fecha,String personal,String indice) {
     

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;        
        personalRutaCaptacionC item =null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_PERSONAL_RUTA_CAPTACION WHERE FECHA=? AND PERSONAL=? AND ITEM=?");
            
            cs.setString(1, util.convertDate(fecha));
            cs.setString(2, personal);
            cs.setString(3, indice);
            
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personalRutaCaptacionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setItem(rs.getInt("ITEM"));
                item.setFecha(rs.getDate("FECHA"));
                item.setLatitud(rs.getDouble("LATITUD"));
                item.setLongitud(rs.getDouble("LONGITUD"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
    
    
    
     public String insertar(personalRutaCaptacionC item) {
        util.consolaCliente("Latitud" + item.getLatitud());
        util.consolaCliente("Longitud" + item.getLongitud());
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_RUTA_CAPTACION(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPersonal());
            cs.setInt(3, item.getItem());
            cs.setString(4, util.convertDate(item.getFecha()));
            cs.setDouble(5, item.getLatitud());
            cs.setDouble(6, item.getLongitud());
            cs.setString(7, item.getDireccion());
            cs.setString(8, item.getObservacion());
            cs.setInt(9, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
     
     
}
