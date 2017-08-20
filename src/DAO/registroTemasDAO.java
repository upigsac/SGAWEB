

package DAO;



import ENTIDAD.registroTemasC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;
import Conexiones.Conexion;


public class registroTemasDAO extends Conexion implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 public String insertar(registroTemasC item) {
	     
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_REGISTRO_DOCENTE_TEMAS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            cs.setInt("ITEMWEB", 1);
	            cs.setInt("INSTITUCION", item.getInstitucion());
	            cs.setString("PERIODO", item.getPeriodo());
	            cs.setString("CARRERA", item.getCarrera());
	            cs.setString("MALLA", item.getMalla());
	            cs.setString("CURSO", item.getCurso());
	            cs.setString("SECCION", item.getSeccion());
	            cs.setString("PERSONAL", item.getPersonal());
	            cs.setString("FECHA", util.convertDate(item.getFecha(), "dd-MM-yyyy") );
	            cs.setInt("SEMANA", item.getSemana());
	            cs.setInt("SESION", item.getSesion());
	            cs.setInt("DIA", item.getDia());
	            cs.setString("TEMA", item.getTema());
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
	 public String eliminar(registroTemasC item) {
	     
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_REGISTRO_DOCENTE_TEMAS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            cs.setInt("ITEMWEB", 2);
	            cs.setInt("INSTITUCION", item.getInstitucion());
	            cs.setString("PERIODO", item.getPeriodo());
	            cs.setString("CARRERA", item.getCarrera());
	            cs.setString("MALLA", item.getMalla());
	            cs.setString("CURSO", item.getCurso());
	            cs.setString("SECCION", item.getSeccion());
	            cs.setString("PERSONAL", item.getPersonal());
	            cs.setString("FECHA", util.convertDate(item.getFecha(), "dd-MM-yyyy") );
	            cs.setInt("SEMANA", item.getSemana());
	            cs.setInt("SESION", item.getSesion());
	            cs.setInt("DIA", item.getDia());
	            cs.setString("TEMA", item.getTema());
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
	

	public List<registroTemasC> mostrarRegistroTemas() {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        registroTemasC item ;
        List<registroTemasC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT INSTITUCION,PERIODO,CARRERA,'200702'  AS MALLA,CURSO,SECCION,PERSONAL,FECHA_INGRESO FECHA,SEMANA,SESION_CLASE SESION ,DIA,TEMA_DESARROLLADO TEMA ,1 ESTADO_REGISTRO FROM DI.REGISTRO_DOCENTE_TD WHERE INSTITUCION=1 AND PERIODO='20162' AND CARRERA='000001' AND CURSO='IS501' ORDER BY FECHA");
        

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new registroTemasC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setFecha(rs.getDate("FECHA"));
                item.setSemana(rs.getInt("SEMANA"));
                item.setSesion(rs.getInt("SESION"));
                item.setDia(rs.getInt("DIA"));
                item.setTema(rs.getString("TEMA"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));
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
