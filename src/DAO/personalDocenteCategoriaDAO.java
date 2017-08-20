package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ENTIDAD.personalDocenteCategoriaC;


public class personalDocenteCategoriaDAO {

	
	

	 public List<personalDocenteCategoriaC> mostrarPersonalDocenteCategoria() {

	        Connection c ;
	        CallableStatement cs;
	        ResultSet rs;
	        personalDocenteCategoriaC item = null;
	        List<personalDocenteCategoriaC> lista=new ArrayList<>();

	        try {

	            c = ConexionWeb();
	            cs = c.prepareCall("SELECT *FROM  DI.PER_PERSONAL_DOCENTE_CATEGORIA WHERE ESTADO_REGISTRO=1");
	          

	            rs = cs.executeQuery();

	            while (rs.next()) {
	                item = new personalDocenteCategoriaC();
	                item.setCategoria(rs.getInt("CATEGORIA"));
	                item.setDescripcion(rs.getString("DESCRIPCION"));
	                item.setAbreviatura(rs.getString("ABREVIATURA"));
	                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
	                lista.add(item);
	            }
	            cerrarCall(cs);
	            cerrarConexion(c);

	        } catch (SQLException e) {
	            
	            System.out.println(e.getMessage());
	        }
	        return lista;
	    }
}
