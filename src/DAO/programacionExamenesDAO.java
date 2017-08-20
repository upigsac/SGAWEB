/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;
import ENTIDAD.programacionExamenesC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class programacionExamenesDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<programacionExamenesC> mostrarCarreras(String tipo, String carrera, String periodo) {

        List<programacionExamenesC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        programacionExamenesC archivo ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM [SIGU].[SUBIDA_ARCHIVOS] WHERE PERIODO=? AND CARRERA=? and TIPO_EXAMEN=?");
            cs.setString(3, tipo);
            cs.setString(2, carrera);
            cs.setString(1, periodo);
            rs = cs.executeQuery();

            while (rs.next()) {
                archivo = new programacionExamenesC();

                archivo.setInstitucion(rs.getInt("INSTITUCION"));
                archivo.setPeriodo(rs.getString("PERIODO"));
                archivo.setDocente(rs.getString("DOCENTE"));
                archivo.setNombre(rs.getString("NOMBREARCHIVO"));
                archivo.setRuta(rs.getString("RUTAARCHIVO"));
                archivo.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                archivo.setCarrera(rs.getString("CARRERA"));
                archivo.setEstado(rs.getInt("ESTADO_REGISTRO"));
                lista.add(archivo);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
