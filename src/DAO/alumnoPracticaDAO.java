
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.alumnoPracticaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class alumnoPracticaDAO {
   public String insertar(alumnoPracticaC item) {
	   
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("ALUMNO", item.getAlumno());
            cs.setString("CURSO", item.getCurso());
            cs.setInt("OFICINA", item.getOficina());
            cs.setString("TUTOR", item.getTutor());    
            cs.setString("OBSERVACION", item.getObservacion());
            cs.setString("FECHAPLAN", util.convertDate(item.getFechaPlan(), "dd-MM-yyyy"));
            cs.setString("FECHAINICIO", util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
            cs.setString("FECHAINFORME", util.convertDate(item.getFechaInforme(), "dd-MM-yyyy"));
            cs.setString("INFORME", item.getInforme());
            cs.setString("FECHACONSTANCIA", util.convertDate(item.getFechaConstancia(), "dd-MM-yyyy"));           
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
        return codigo;
    }
   public String eliminar(alumnoPracticaC item) {
	     
       Connection c ;
       CallableStatement cs ;
       boolean rpta ;
       String codigo = "";
       try {
           c = ConexionWeb();
           cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA(?,?,?,?,?,?,?,?,?,?,?,?)}");
           cs.setInt("ITEMWEB", 1);
           cs.setString("ALUMNO", item.getAlumno());
           cs.setString("CURSO", item.getCurso());
           cs.setInt("OFICINA", item.getOficina());
           cs.setString("TUTOR", item.getTutor());    
           cs.setString("OBSERVACION", item.getObservacion());
           cs.setString("FECHAPLAN", util.convertDate(item.getFechaPlan(), "dd-MM-yyyy"));
           cs.setString("FECHAINICIO", util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
           cs.setString("FECHAINFORME", util.convertDate(item.getFechaInforme(), "dd-MM-yyyy"));
           cs.setString("INFORME", item.getInforme());
           cs.setString("FECHACONSTANCIA", util.convertDate(item.getFechaConstancia(), "dd-MM-yyyy"));           
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
       return codigo;
   }
  
   
   
   public alumnoPracticaC mostrarAlumnoPractica(String alumno,String curso) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
    
        alumnoPracticaC item =null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.ACA_ALUMNO_CURSO_PRACTICA WHERE ALUMNO=? AND CURSO=?");
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoPracticaC();
              
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setTutor(rs.getString("TUTOR"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setFechaInforme(rs.getDate("FECHAINFORME"));
                item.setFechaPlan(rs.getDate("FECHAPLAN"));
                item.setFechaInicio(rs.getDate("FECHAINCIO"));                
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setInforme(rs.getString("INFORME"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        }
        return item;
    }
   
   
}
