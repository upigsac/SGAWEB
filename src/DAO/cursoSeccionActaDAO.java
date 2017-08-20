/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cursoSeccionActaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class cursoSeccionActaDAO {
    
       public String insertarCurso(cursoSeccionActaC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CURSO_SECCION_ACTA(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setInt(7, item.getGrupo());
            cs.setString(8, item.getTipoExamen());
            cs.setString(9, item.getPersonalEmision());
            cs.setString(10, item.getPersonalRecepcion());
            cs.setInt(11, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
       
        public cursoSeccionActaC mostrarCursoSeccionActa(int institucion, String periodo, String carrera, String curso, String seccion,String tipoExamen) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionActaC item = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.HOR_CURSO_SECCION_ACTA WHERE INSTITUCION=? AND PERIODO=? AND  CARRERA=? AND CURSO=? AND  SECCION = ? AND TIPO_EXAMEN=? AND ESTADO_REGISTRO IN (1)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, tipoExamen);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionActaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setFechaEmision(rs.getDate("FECHA_EMISION"));
                item.setFechaRecepcion(rs.getDate("FECHA_RECEPCION"));                
                item.setPersonalRecepcion(rs.getString("PERSONAL_RECEPCION"));
                item.setPersonalEmision(rs.getString("PERSONAL_EMISION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
           
        }
        return item;
    }
    
    
    
}
