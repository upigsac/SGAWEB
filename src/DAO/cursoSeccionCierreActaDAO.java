
package DAO;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cursoSeccionCierreActaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class cursoSeccionCierreActaDAO {
    
    
     public String insertar(cursoSeccionCierreActaC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_CURSO_SECCION_CIERRE_ACTA](?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());            
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getGrupo());
            cs.setString(8, item.getPersonal());
            cs.setInt(9, item.getEstadoRegistro());
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
    
    public cursoSeccionCierreActaC mostrarCursoSeccionCierreActa(int institucion, String periodo, String carrera, String curso, String seccion) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionCierreActaC item = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.HOR_CURSO_SECCION_CIERRE_ACTA WHERE INSTITUCION=? AND PERIODO=? AND  CARRERA=? AND CURSO=? AND  SECCION = ? AND ESTADO_REGISTRO IN (1)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cursoSeccionCierreActaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setFecha(rs.getDate("FECHA_CIERRE"));
                item.setPersonal(rs.getString("PERSONAL_CIERRE"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
           
        }
        return item;
    }
    
    
}
