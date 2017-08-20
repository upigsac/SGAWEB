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

import ENTIDAD.personalAmonestacionC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class personalAmonestacionDAO {
    public List<personalAmonestacionC> mostrarPersonalAmonestacion(String personal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalAmonestacionC item;
        List<personalAmonestacionC> lista = new ArrayList<>();

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_AMONESTACION WHERE PERSONAl=?  and estado_registro=1");
            cs.setString(1, personal);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personalAmonestacionC();
                item.setPersonal(rs.getString("PERSONAL"));
                item.setItem(rs.getInt("ITEM"));
                item.setTipo(rs.getInt("TIPO"));
                item.setMotivo(rs.getString("MOTIVO"));
                item.setFecha(rs.getDate("FECHA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

            util.consolaCliente( "ERROR" + e.getMessage());

        }
        return lista;
    }
    
    
     public boolean insertarPersonalAmonestacion(personalAmonestacionC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_AMONESTACION (?,?,?,?,?,?)}");
            cs.setString(1, item.getPersonal());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getTipo());
            cs.setString(4, item.getMotivo());
            cs.setString(5, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
               
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
            util.consolaCliente( "ERROR GUARDAR CONTRATO " + e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

}
