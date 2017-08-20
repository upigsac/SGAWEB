/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.grupoSangineoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class grupoSangineoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<grupoSangineoC> mostrarGrupoSangineo() {

        List<grupoSangineoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        grupoSangineoC tipoE ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_GRUPO_SANGINEO");
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new grupoSangineoC();
                tipoE.setGrupoSangineo(rs.getInt("GRUPO_SANGINEO"));
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
