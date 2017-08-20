/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.tipoViviendaC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tipoViviendaDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<tipoViviendaC> mostrarTipoVivienda() {

        List<tipoViviendaC> lista = new ArrayList<tipoViviendaC>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        tipoViviendaC tipoE = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.SYS_TIPOS_VIVIENDA");
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new tipoViviendaC();
                tipoE.setTipoVivienda(rs.getInt("TIPO_VIVIENDA"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
