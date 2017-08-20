package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import Conexiones.Conexion;
import ENTIDAD.mensajeC;


public class mensajeDAO extends Conexion {
	public String insertar(mensajeC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_MENSAJE(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("MENSAJE", item.getMensaje());
         
            cs.setString("CUERPO", item.getCuerpo());
            cs.setString("PIE", item.getPie());
            cs.setString("FECHAINICIO", util.convertDate(item.getFechaInicio(), "dd-MM-yyyy")  );
            cs.setString("FECHAFINAL", util.convertDate(item.getFechaFinal(),"dd-MM-yyyy"));
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
	
	public String eliminar(mensajeC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_MENSAJE(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("MENSAJE", item.getMensaje());
        
            cs.setString("CUERPO", item.getCuerpo());
            cs.setString("PIE", item.getPie());
            cs.setString("FECHAINICIO", util.convertDate(item.getFechaInicio(), "dd-MM-yyyy")  );
            cs.setString("FECHAFINAL", util.convertDate(item.getFechaFinal(),"dd-MM-yyyy"));
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
	
	
	public List<mensajeC> mostrarMensaje() {

        List<mensajeC> lista = new ArrayList<mensajeC>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        mensajeC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.MENSAJE_PERSONALIZADO");
            rs = cs.executeQuery();

            while (rs.next()) {

            	item = new mensajeC();
            	item.setMensaje(rs.getInt("MENSAJE"));
            	item.setCuerpo(rs.getString("CUERPO"));
            	item.setPie(rs.getString("PIE"));
            	item.setFechaInicio(rs.getDate("FECHA_INI"));
            	item.setFechaFinal(rs.getDate("FECHA_FIN"));
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
	
	
	public List<mensajeC> mostrarMensaje(int institucion,String periodo,String carrera,String alumno) {

        List<mensajeC> lista = new ArrayList<mensajeC>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        mensajeC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM DI.MENSAJE_PERSONALIZADO_ALUMNO A,DI.MENSAJE_PERSONALIZADO B  WHERE A.INSTITUCION=? AND A.PERIODO=? AND  A.CARRERA in('%',?) AND A.ALUMNO in('%',?) AND B.MENSAJE=A.MENSAJE AND GETDATE() BETWEEN B.FECHA_INI AND FECHA_FIN");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {

            	item = new mensajeC();
            	item.setMensaje(rs.getInt("MENSAJE"));
            	item.setCuerpo(rs.getString("CUERPO"));
            	item.setPie(rs.getString("PIE"));
            	item.setFechaInicio(rs.getDate("FECHA_INI"));
            	item.setFechaFinal(rs.getDate("FECHA_FIN"));
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
}
