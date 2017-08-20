/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.ubigeoDepartamentoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class ubigeoDepartamentoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ubigeoDepartamentoC> mostrarDepartamento() {

        List<ubigeoDepartamentoC> lista = new ArrayList<ubigeoDepartamentoC>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ubigeoDepartamentoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.SYS_UBIGEO_DEPARTAMENTO");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ubigeoDepartamentoC();
                item.setDepartamento(rs.getString("DEPARTAMENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

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
