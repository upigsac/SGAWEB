
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.personalOficinaC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.util;

public class personalOficinaDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public personalOficinaC mostrarPersonalOficina(int institucion,String personal) {
     

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalOficinaC item=null ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.PER_PERSONAL_OFICINA WHERE INSTITUCION=? AND PERSONAL=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, personal);
            
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personalOficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setCargo(rs.getInt("CARGO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
    
     public personalOficinaC mostrarPersonalOficina(String personal) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalOficinaC item=null ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.PER_PERSONAL_OFICINA WHERE  PERSONAL=? AND ESTADO_REGISTRO=1");
            cs.setString(1, personal);            
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personalOficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setCargo(rs.getInt("CARGO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
