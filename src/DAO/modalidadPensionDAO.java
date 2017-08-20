/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.modalidadPensionC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class modalidadPensionDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<modalidadPensionC> mostrarModalidadIngreso() {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        modalidadPensionC item ;
        List<modalidadPensionC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_MODALIDAD_PENSION WHERE ESTADO_REGISTRO=1");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new modalidadPensionC();
                item.setModalida(rs.getInt("MODALIDAD"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
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
