/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cpersonalC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.util;


public class cpersonalDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public cpersonalC mostrarCPersonal(String personal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cpersonalC item=null ;
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from DI.PER_CPERSONAL where PERSONAL=?");
            cs.setString(1, personal);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cpersonalC();
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setModalidad(rs.getInt("MODALIDAD"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setSemestre(rs.getString("SEMESTRE"));
                item.setFechaInicio(rs.getDate("FECHAINCIO"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente(e.getMessage());
            
        }
        return item;
    }

    public boolean insertar(cpersonalC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MATENIMIENTO_CPERSONAL (1,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getCpersonal());
            cs.setString(2, item.getPersonal());
            cs.setInt(3, item.getModalidad());
            cs.setString(4, item.getPeriodo());
            cs.setString(5, item.getSemestre());
            cs.setString(6, util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
            cs.setInt(7, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR guardar " + e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
    public boolean eliminar(cpersonalC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MATENIMIENTO_CPERSONAL (2,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getCpersonal());
            cs.setString(2, item.getPersonal());
            cs.setInt(3, item.getModalidad());
            cs.setString(4, item.getPeriodo());
            cs.setString(5, item.getSemestre());
            cs.setString(6, util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
            cs.setInt(7, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR ELIMINAR " + e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
}
