/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;


import ENTIDAD.eventoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class eventoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<eventoC> mostrarEventos(String tipo) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<eventoC> lista = new ArrayList<>();
        eventoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.EVEN_EVENTO WHERE TIPO_EVENTO=?");

            cs.setString(1, tipo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new eventoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setEvento(rs.getString("EVENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setTipo_evento(rs.getString("TIPO_EVENTO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public boolean registrarEvento(int institucion, String desEvento, String tipo, String fecha_ini, String fecha_fin, String hora_practica, String hora_teoria, String lugar, String caratura) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        String evento = "";

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_CREAR_EVENTO(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, desEvento);
            cs.setString(3, tipo);
            cs.setString(4, fecha_ini);
            cs.setString(5, fecha_fin);
            cs.setString(6, hora_practica);
            cs.setString(7, hora_teoria);
            cs.setString(8, lugar);
            cs.setString(9, caratura);
            cs.registerOutParameter(10, java.sql.Types.VARCHAR);
            cs.executeUpdate();
            c.commit();
            evento = cs.getString(10);
            util.consolaCliente( evento);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR ELIMINAR " + e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
}
