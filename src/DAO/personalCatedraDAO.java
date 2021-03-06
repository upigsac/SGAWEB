/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.personalCatedraC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class personalCatedraDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<personalCatedraC> mostrarPersonalCatedra(String personal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalCatedraC item;
        List<personalCatedraC> lista = new ArrayList<>();

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_PERSONAL_CATEDRA WHERE PERSONAL=? AND ESTADO_REGISTRO=1");
            cs.setString(1, personal);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personalCatedraC();
                item.setPersonal(rs.getString("PERSONAL"));
                item.setCentroEducativo(rs.getString("INSTITUCION_CATEDRA"));
                item.setCatedra(rs.getInt("CATEDRA"));
                item.setMeses(rs.getInt("MESES"));
                item.setDictar(rs.getBoolean("DICTAR_CATEDRA"));
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
}
