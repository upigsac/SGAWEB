/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.entidadPrestadoraSaludC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class entidadPrestadoraSaludDAO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<entidadPrestadoraSaludC> mostrarEntidadPrestadoraSalud() {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<entidadPrestadoraSaludC> lista = new ArrayList<>();
        entidadPrestadoraSaludC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_ENTIDADES_PRESTADORAS_SALUD ");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new entidadPrestadoraSaludC();
                item.setEps(rs.getInt("EPS"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
