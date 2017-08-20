/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;
import ENTIDAD.firmaCertificacionCursoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class firmaCertificacionCursoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public boolean insertar(firmaCertificacionCursoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_FIRMA_CERTIFICACION_CURSO(1,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getPersona());
            cs.setString(8, item.getCargo());
            cs.setString(9, item.getAbreviatura());
            cs.setInt(10, item.getEstadoRegistro());

            

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR INSERTAR " + e.getMessage());
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        return rpta;
    }
	
	public boolean eliminar(firmaCertificacionCursoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_FIRMA_CERTIFICACION_CURSO(2,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getPersona());
            cs.setString(8, item.getCargo());
            cs.setString(9, item.getAbreviatura());
            cs.setInt(10, item.getEstadoRegistro());

            

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

   

    public List<firmaCertificacionCursoC> mostrarFirmaCurso(int institucion, String periodo, String carrera, String seccion, String curso) {

        List<firmaCertificacionCursoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        firmaCertificacionCursoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT  *FROM DI.HOR_FIRMA_CERTIFICACION_CURSO  WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND SECCION=? AND CURSO=? AND ESTADO_REGISTRO=1");

            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            cs.setString(5, curso);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new firmaCertificacionCursoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCargo(rs.getString("CARGO"));
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
