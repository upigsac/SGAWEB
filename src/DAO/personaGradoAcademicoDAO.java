

package DAO;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.personaGradoAcademicoC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;


public class personaGradoAcademicoDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean insertar(personaGradoAcademicoC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONA_GRADO_ACADEMICO(1,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getGrado());
            cs.setString(4, item.getDenominacion());            
            cs.setInt(5, item.getInstitucion());            
            cs.setString(6,util.convertDate(item.getFecha(),"dd-MM-yyyy"));            
            cs.setInt(7, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
     public boolean eliminar(personaGradoAcademicoC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONA_GRADO_ACADEMICO(2,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getGrado());
            cs.setString(4, item.getDenominacion());            
            cs.setInt(5, item.getInstitucion());            
            cs.setString(6,util.convertDate(item.getFecha(),"dd-MM-yyyy"));              
            cs.setInt(7, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
     public List<personaGradoAcademicoC> mostrarPersonalGradoAcademico(String personal) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaGradoAcademicoC> lista = new ArrayList<>();
        personaGradoAcademicoC item;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PERSONA_GRADO_ACADEMICO WHERE PERSONA=? AND ESTADO_REGISTRO=1 ORDER BY FECHA DESC");
            cs.setString(1, personal);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                
                item = new personaGradoAcademicoC();
                item.setPersona(rs.getString("PERSONA"));
                item.setItem(rs.getInt("ITEM"));
                item.setGrado(rs.getInt("GRADO"));
                item.setDenominacion(rs.getString("DENOMINACION"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setFecha(rs.getDate("FECHA"));
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
