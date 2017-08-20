package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexiones.Conexion;
import ENTIDAD.mensajeAlumnoC;



public class mensajeAlumnoDAO extends Conexion  {

	public List<mensajeAlumnoC> mostrarMensajeAlumno(int mensaje) {

        List<mensajeAlumnoC> lista = new ArrayList<mensajeAlumnoC>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        mensajeAlumnoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.MENSAJE_PERSONALIZADO_ALUMNO WHERE MENSAJE=?");
            cs.setInt(1, mensaje);
            rs = cs.executeQuery();

            while (rs.next()) {

            	item = new mensajeAlumnoC();
            	item.setMensaje(rs.getInt("MENSAJE"));
            	item.setInstitucion(rs.getInt("INSTITUCION"));
            	item.setPeriodo(rs.getString("PERIODO"));
            	item.setCarrera(rs.getString("CARRERA"));
            	item.setAlumno(rs.getString("ALUMNO"));
            	item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
          
            System.out.println(e.getMessage());
        }
        return lista;
    }
	
	public String insertar(mensajeAlumnoC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_MENSAJE_ALUMNO(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("MENSAJE", item.getMensaje());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("CARRERA", item.getCarrera());
            cs.setString("ALUMNO", item.getAlumno());            
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());

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
        return codigo;
    }
	
	
	public String eliminar(mensajeAlumnoC item) {	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_MENSAJE_ALUMNO(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("MENSAJE", item.getMensaje());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("CARRERA", item.getCarrera());
            cs.setString("ALUMNO", item.getAlumno());            
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());

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
        return codigo;
    }
	
}
