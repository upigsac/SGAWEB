/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;


import ENTIDAD.eventoExpositorC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class eventoExpositorDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<eventoExpositorC> mostrarEventosExpositor(String evento) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<eventoExpositorC> lista = new ArrayList<>();
        eventoExpositorC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.EVEN_EVENTO_EXPOSITOR WHERE EVENTO=?");
            cs.setString(1, evento);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new eventoExpositorC();

                item.setEvento(rs.getString("EVENTO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setTema(rs.getString("TEMA"));
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

}
