/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.carrerasC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class facultadCarreraDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<carrerasC> mostrarFacultadCarrera(int institucion, int facultad) {

        List<carrerasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        carrerasC carre ;

        try {

            c = ConexionWeb();

            cs = c.prepareCall("select B.* from SIGU.ACA_FACULTAD_CARRERA A ,UPA.ACA_CARRERAS B WHERE A.INSTITUCION=? and A.FACULTAD=? AND  A.CARRERA=B.CARRERA");

            cs.setInt(1, institucion);
            cs.setInt(2, facultad);

            rs = cs.executeQuery();
            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

        }
        return lista;
    }
}
