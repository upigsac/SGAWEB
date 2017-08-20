/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoCursoC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tipoCursoDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<tipoCursoC> mostrarTipoCurso() {

        List<tipoCursoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoCursoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_TIPO_FORMACION");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoCursoC();
                item.setTipoCurso(rs.getInt("TIPO_FOMACION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setCondicion(rs.getInt("CONDICION"));                
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
