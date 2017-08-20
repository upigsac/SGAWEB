/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.opcionAccesoUsuarioC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class opcionAccesoUsuarioDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<opcionAccesoUsuarioC> MostarMenuPadre(String usuario, int programa, int modulo, int tipo) {

        List<opcionAccesoUsuarioC> m = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionAccesoUsuarioC programaDAO ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM [SIGU].[SEG_ACCESO_OPCION] WHERE USUARIO=?"
                    + " AND PROGRAMA=? AND MODULO=? AND TIPO_OPCION=? and OPCION_PADRE=0"
                    + " order by OPCION ,OPCION_PADRE");
            cs.setString(1, usuario);
            cs.setInt(2, programa);
            cs.setInt(3, modulo);
            cs.setInt(4, tipo);

            rs = cs.executeQuery();
            while (rs.next()) {

                programaDAO = new opcionAccesoUsuarioC();
                programaDAO.setUsuario(rs.getString("usuario"));
                programaDAO.setPrograma(rs.getInt("programa"));
                programaDAO.setModulo(rs.getInt("modulo"));
                programaDAO.setOpcion(rs.getInt("opcion"));
                programaDAO.setOpcionPadre(rs.getInt("opcion_Padre"));
                programaDAO.setOrden(rs.getInt("orden"));
                programaDAO.setTipoOpcion(rs.getInt("tipo_opcion"));
                programaDAO.setTipoSeguridada(rs.getInt("tipo_seguridad"));
                programaDAO.setOpcionCondicion(rs.getString("opcion_where"));
                programaDAO.setEstadoRegistro(rs.getInt("estado_registro"));

                m.add(programaDAO);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public List<opcionAccesoUsuarioC> MostarMenuHijo(String usuario, int programa, int modulo, int tipo, int opcionPadre) {
        List<opcionAccesoUsuarioC> m = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionAccesoUsuarioC programaDAO ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM [SIGU].[SEG_ACCESO_OPCION] WHERE USUARIO=?"
                    + " AND PROGRAMA=? AND MODULO=? AND TIPO_OPCION=? AND OPCION_PADRE=?");
            cs.setString(1, usuario);
            cs.setInt(2, programa);
            cs.setInt(3, modulo);
            cs.setInt(4, tipo);
            cs.setInt(5, opcionPadre);
            rs = cs.executeQuery();
            while (rs.next()) {

                programaDAO = new opcionAccesoUsuarioC();
                programaDAO.setUsuario(rs.getString("usuario"));
                programaDAO.setPrograma(rs.getInt("programa"));
                programaDAO.setModulo(rs.getInt("modulo"));
                programaDAO.setOpcion(rs.getInt("opcion"));
                programaDAO.setOpcionPadre(rs.getInt("opcion_Padre"));

                programaDAO.setOrden(rs.getInt("orden"));
                programaDAO.setTipoOpcion(rs.getInt("tipo_opcion"));
                programaDAO.setTipoSeguridada(rs.getInt("tipo_seguridad"));
                programaDAO.setOpcionCondicion(rs.getString("opcion_where"));
                programaDAO.setEstadoRegistro(rs.getInt("estado_registro"));

                m.add(programaDAO);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

}
