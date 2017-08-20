/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.hospitalC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class hospitalDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<hospitalC> mostrarHospital() {

        List<hospitalC> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        hospitalC tipoE ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.PER_HOSPITAL");
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new hospitalC();
                tipoE.setHospital(rs.getInt("HOSPITAL"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(tipoE);

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
