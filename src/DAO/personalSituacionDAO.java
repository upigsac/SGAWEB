/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.personalSituacionC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class personalSituacionDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<personalSituacionC> mostrarPersonalSituacion() {
        List<personalSituacionC> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalSituacionC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_PERSONAL_SITUACION");
            
            
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personalSituacionC();
                item.setSituacion(rs.getInt("SITUACION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatua(rs.getString("ABREVIATURA"));
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
}
