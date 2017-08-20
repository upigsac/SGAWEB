
package DAO;


import ENTIDAD.registroExamenesC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;




public class registroExamenesDAO implements Serializable {

  
	private static final long serialVersionUID = 1L;

	public List<registroExamenesC> mostrarRegistroExamenes(int institucion, String periodo, String malla, String carrera, String curso, String seccion, String cpersonal) {
        List<registroExamenesC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        registroExamenesC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.REGISTRO_DOCENTE_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND MALLA=? AND CARRERA=? AND CURSO=? AND SECCION=? AND CPERSONAL=? ORDER BY CREACION_FECHA");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, cpersonal);

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new registroExamenesC();

                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setTurno(rs.getInt("TURNO"));
                item.setNivelModular(rs.getInt("CICLO"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setFecha_examen(rs.getDate("FECHA_EXAMEN"));
                item.setHora_ingreso(rs.getTimestamp("HOR_ING"));
                item.setHora_salida(rs.getTimestamp("HOR_SAL"));
                item.setTipoExamen(rs.getString("TIPO"));
                item.setAula(rs.getString("AULA"));
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
    
    public String insertar(registroExamenesC item) { 
    	
    	
    	 System.out.println( util.convertDateString( item.getHora_ingreso(),"HH:mm"));
    	 System.out.println( util.convertTimes(item.getHora_ingreso()));
    	 System.out.println( "-----------------------");
    	 System.out.println( util.convertDateString( item.getHora_salida(),"HH:mm"));
    	 System.out.println( util.convertTimes(item.getHora_salida()));
         
         
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
       String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_REGISTRO_DOCENTE_EXAMEN(1,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getSeccion());
            cs.setString(6, item.getCurso());
            cs.setInt(7, item.getTurno());
            cs.setInt(8, item.getNivelModular());
            cs.setInt(9, item.getGrupo());
            cs.setString(10, item.getPersonal());
            cs.setString(11, item.getCpersonal());
                     
            cs.setString(12, util.convertDateString(item.getFecha_examen(), "dd-MM-yyyy"));
            cs.setString(13, util.convertDateString( item.getHora_ingreso(),"HH:mm"));
            cs.setString(14, util.convertDateString( item.getHora_salida(),"HH:mm"));
            cs.setString(15, item.getAula());
            cs.setString(16, item.getTipoExamen());
            cs.setInt(17, 4); // Tipo clase
            cs.setInt(18, item.getEstadoRegistro());

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
    
    public String eliminar(registroExamenesC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_REGISTRO_DOCENTE_EXAMEN(2,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getSeccion());
            cs.setString(6, item.getCurso());
            cs.setInt(7, item.getTurno());
            cs.setInt(8, item.getNivelModular());
            cs.setInt(9, item.getGrupo());
            cs.setString(10, item.getPersonal());
             cs.setString(11, item.getCpersonal());
                     
            cs.setString(12, util.convertDate(item.getFecha_examen(), "dd-MM-yyyy"));
            cs.setString(13, util.convertDate( item.getHora_ingreso(),"HH:mm"));
            cs.setString(14, util.convertDate( item.getHora_salida(),"HH:mm"));
            cs.setString(15, item.getAula());
            cs.setString(16, item.getTipoExamen());
            cs.setInt(17, 4); // Tipo clase
            cs.setInt(18, item.getEstadoRegistro());

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
