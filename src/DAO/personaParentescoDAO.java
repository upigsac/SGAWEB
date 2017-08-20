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
import ENTIDAD.personaParentescoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Beans.util;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class personaParentescoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public boolean insertar(personaParentescoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_DAT_PERSONA_PARENTESCO(1,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getParentesco());
            cs.setString(3, item.getPersonaParentesco());
            cs.setBoolean(4, item.isDependedeud());
            cs.setBoolean(5, item.isPagaestudios());
            cs.setBoolean(6, item.isViveconud());
            cs.setInt(7, item.getTipoEnfermedad());
            cs.setInt(8, item.getHospital());
            cs.setDouble(9, item.getGastos());
            cs.setString(10, item.getObservacion());
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
        return rpta;
    }
    
    public boolean eliminar(personaParentescoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_DAT_PERSONA_PARENTESCO(2,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getParentesco());
            cs.setString(3, item.getPersonaParentesco());
            cs.setBoolean(4, item.isDependedeud());
            cs.setBoolean(5, item.isPagaestudios());
            cs.setBoolean(6, item.isViveconud());
            cs.setInt(7, item.getTipoEnfermedad());
            cs.setInt(8, item.getHospital());
            cs.setDouble(9, item.getGastos());
            cs.setString(10, item.getObservacion());
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
            System.out.println(ex.getMessage());
        }
        return rpta;
    }
    
    
     public List<personaParentescoC> mostrarPersonaParentesco(String persona) {

        List<personaParentescoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaParentescoC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.DAT_PERSONA_PARENTESCO  WHERE PERSONA=?");
            cs.setString(1, persona);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personaParentescoC();
                item.setPersona(rs.getString("PERSONA"));
                item.setParentesco(rs.getInt("PARENTESCO"));
                item.setPersonaParentesco(rs.getString("PERSONA_PARENTESCO"));
                item.setDependedeud(rs.getBoolean("DEPENDEDEUD"));
                item.setViveconud(rs.getBoolean("VIVECONUND"));
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            // e.printStackTrace();
        }
        return lista;
    }
}
