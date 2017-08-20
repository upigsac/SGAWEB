/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.gradoInstruccionC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class gradoInstruccionDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<gradoInstruccionC> mostrarGrado(int nivel) {

        List<gradoInstruccionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        gradoInstruccionC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM SIGU.SYS_GRADO_INSTRUCCION A, DI.SYS_NIVEL_GRADO B WHERE A.GRADO_INSTRUCCION=B.GRADO AND  B.NIVEL IN(?) AND A.ESTADO_REGISTRO=1");
            cs.setInt(1, nivel);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                item = new gradoInstruccionC();
                item.setGradoInstruccion(rs.getInt("GRADO_INSTRUCCION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoregistro(rs.getInt("ESTADO_REGISTRO"));

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
    
    public gradoInstruccionC mostrarGradoAcademico(int grado) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        gradoInstruccionC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.SYS_GRADO_INSTRUCCION A WHERE GRADO_INSTRUCCION=?");
            cs.setInt(1, grado);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                item = new gradoInstruccionC();
                item.setGradoInstruccion(rs.getInt("GRADO_INSTRUCCION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoregistro(rs.getInt("ESTADO_REGISTRO"));

                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {        
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
