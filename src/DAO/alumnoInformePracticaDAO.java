
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.alumnoInformePracticaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class alumnoInformePracticaDAO {
    
     public String insertar(alumnoInformePracticaC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_INFORME(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("ALUMNO", item.getAlumno());
            cs.setString("CURSO", item.getCurso());
            cs.setInt("ITEM", item.getItem());
            cs.setString("FECHA", util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString("INFORME", item.getInforme());
            cs.setString("RUTA", item.getRuta());
            cs.setInt("TIPO", item.getTipo());
            cs.setBoolean("APROBADO", item.isAprobado());
            cs.setString("FECHA_REVISADO", util.convertDate(item.getFechaRevisado(), "dd-MM-yyyy"));
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
     public String eliminar(alumnoInformePracticaC item) {
         
         Connection c ;
         CallableStatement cs ;
         boolean rpta ;
         String codigo = "";
         try {
             c = ConexionWeb();
             cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_INFORME(?,?,?,?,?,?,?,?,?,?,?)}");
             cs.setInt("ITEMWEB", 1);
             cs.setString("ALUMNO", item.getAlumno());
             cs.setString("CURSO", item.getCurso());
             cs.setInt("ITEM", item.getItem());
             cs.setString("FECHA", util.convertDate(item.getFecha(), "dd-MM-yyyy"));
             cs.setString("INFORME", item.getInforme());
             cs.setString("RUTA", item.getRuta());
             cs.setInt("TIPO", item.getTipo());
             cs.setBoolean("APROBADO", item.isAprobado());
             cs.setString("FECHA_REVISADO", util.convertDate(item.getFechaRevisado(), "dd-MM-yyyy"));
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
     
     
     
    public List<alumnoInformePracticaC> mostrarAlumnoInforme(String alumno,String curso,String tipo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoInformePracticaC> lista=new ArrayList<>();
        alumnoInformePracticaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ACA_ALUMNO_CURSO_PRACTICA_INFORME WHERE ALUMNO=? AND CURSO=? AND TIPO LIKE ? ORDER BY ITEM");
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            cs.setString(3, tipo);
            
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoInformePracticaC();
                item.setItem(rs.getInt("ITEM"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setFecha(rs.getDate("FECHA"));
                item.setFechaRevisado(rs.getDate("FECHA_REVISADO"));
                item.setInforme(rs.getString("INFORME"));
                item.setRuta(rs.getString("RUTA"));
                item.setAprobado(rs.getBoolean("APROBADO"));
                item.setTipo(rs.getInt("TIPO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.print(e.getMessage());
            
        }
        return lista;
    }
}
