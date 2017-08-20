package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import ENTIDAD.personaNoteC;


public class personaNoteDAO {

	
	
	
	public boolean insertar(personaNoteC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_PERSONA_NOTE(?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);            
            cs.setString("PERSONA", item.getPersona());
            cs.setInt("NOTE", item.getNote());
            cs.setString("DESCRIPCION", item.getDescripcion());
            cs.setInt("TOP", item.getTop());
            cs.setInt("LEFT", item.getLeft());            
            cs.setString("COLOR", item.getColor());            
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
        	   System.out.println(ex.getMessage());
        }
        return rpta;
    }
	
	public boolean eliminar(personaNoteC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_PERSONA_NOTE(?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);            
            cs.setString("PERSONA", item.getPersona());
            cs.setInt("NOTE", item.getNote());
            cs.setString("DESCRIPCION", item.getDescripcion());
            cs.setInt("TOP", item.getTop());
            cs.setInt("LEFT", item.getLeft());            
            cs.setString("COLOR", item.getColor());            
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
        	   System.out.println(ex.getMessage());
        }
        return rpta;
    }
	
	
	public List<personaNoteC> mostrarNotePersona(String persona) {

        List<personaNoteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaNoteC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_PERSONA_NOTE WHERE PERSONA=?");
            cs.setString(1, persona);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personaNoteC();
                item.setPersona(rs.getString("PERSONA"));
                item.setNote(rs.getInt("NOTE"));
                item.setLeft(rs.getInt("LEFT"));
                item.setTop(rs.getInt("TOP"));
                item.setColor(rs.getString("COLOR"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
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
