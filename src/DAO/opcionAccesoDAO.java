/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.opcionAccesoC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class opcionAccesoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public opcionAccesoC opcionAccesoCodigo(int opcion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionAccesoC Acceso = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM [SIGU].[SEG_OPCION] WHERE OPCION=?");
            cs.setInt(1, opcion);
            rs = cs.executeQuery();
            while (rs.next()) {

                Acceso = new opcionAccesoC();
                Acceso.setOpcion(rs.getString("opcion"));
                Acceso.setSeleccion(rs.getString("seleccion"));
                Acceso.setDescripcion(rs.getString("descripcion"));
                Acceso.setSubDescripcion(rs.getString("subdescripcion"));
                Acceso.setUrl(rs.getString("url"));
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Acceso;
    }

    public List<opcionAccesoC> menuPadre(int programa, int modulo, int tipo, String usuario) {

        List<opcionAccesoC> m = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionAccesoC Acceso ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  O.PROGRAMA,O.MODULO, M.DESCRIPCION,SO.SELECCION, SO.OPCION ,\n"
                    + "(select DESCRIPCION from [SIGU].[SEG_OPCION] WHERE OPCION=SO.OPCION) DESCRIPCION_PADRE,\n"
                    + "O.TIPO_OPCION,O.ESTADO_REGISTRO \n"
                    + "FROM [SIGU].[SEG_ACCESO_OPCION] O,[SIGU].[SEG_OPCION] SO ,[SIGU].[SEG_MODULO] M\n"
                    + "WHERE O.PROGRAMA=? AND O.MODULO=? AND  O.TIPO_OPCION=? AND O.USUARIO=? \n"
                    + "AND M.PROGRAMA=O.PROGRAMA AND M.MODULO=O.MODULO AND\n"
                    + " O.OPCION=SO.OPCION AND SO.SELECCION='P'\n"
                    + "UNION\n"
                    + "SELECT  O.PROGRAMA,O.MODULO,  M.DESCRIPCION ,SO.SELECCION,SO.OPCION,\n"
                    + "(select DESCRIPCION from [SIGU].[SEG_OPCION] WHERE OPCION=SO.OPCION) DESCRIPCION_PADRE,\n"
                    + "O.TIPO_OPCION,O.ESTADO_REGISTRO \n"
                    + "FROM [SIGU].[SEG_ACCESO_OPCION] O,[SIGU].[SEG_OPCION] SO ,[SIGU].[SEG_MODULO] M\n"
                    + "WHERE  O.PROGRAMA=? AND O.MODULO=?   AND O.TIPO_OPCION=? AND O.USUARIO=? \n"
                    + "AND M.PROGRAMA=O.PROGRAMA AND M.MODULO=O.MODULO AND\n"
                    + "O.OPCION=SO.OPCION AND SO.SELECCION='O'\n"
                    + "ORDER BY SO.SELECCION desc , DESCRIPCION_PADRE ");
            cs.setInt(1, programa);
            cs.setInt(2, modulo);
            cs.setInt(3, tipo);
            cs.setString(4, usuario);
            cs.setInt(5, programa);
            cs.setInt(6, modulo);
            cs.setInt(7, tipo);
            cs.setString(8, usuario);

            rs = cs.executeQuery();

            while (rs.next()) {

                Acceso = new opcionAccesoC();
                Acceso.setOpcion(rs.getString("opcion"));
                Acceso.setDescripcion(rs.getString("descripcion_padre"));
                Acceso.setSeleccion(rs.getString("seleccion").equals("P") ? "+" : "-");

                m.add(Acceso);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public List<opcionAccesoC> menuHijo(int programa, int modulo, int tipo, int padre, String usuario) {

        List<opcionAccesoC> m = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionAccesoC Acceso ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT  O.PROGRAMA,O.MODULO,M.DESCRIPCION ,O.OPCION,(select DESCRIPCION from [SIGU].[SEG_OPCION] WHERE OPCION=O.OPCION) DESCRIPCION_HIJO,\n"
                    + "  OPCION_PADRE,(select DESCRIPCION from [SIGU].[SEG_OPCION] WHERE OPCION=O.OPCION_PADRE) DESCRIPCION_PADRE,\n"
                    + "  O.ORDEN,O.TIPO_OPCION,O.OPCION_WHERE,O.ESTADO_REGISTRO  \n"
                    + "  FROM [SIGU].[SEG_ACCESO_OPCION] O , [SIGU].[SEG_MODULO] M\n"
                    + "  WHERE   M.PROGRAMA=O.PROGRAMA AND M.MODULO=O.MODULO  \n"
                    + "  AND O.PROGRAMA=? AND  O.MODULO=? AND O.TIPO_OPCION=? AND O.OPCION_PADRE=? AND USUARIO=?\n"
                    + "  ORDER BY O.PROGRAMA ,O.MODULO ,O.OPCION_PADRE, O.ORDEN");
            cs.setInt(1, programa);
            cs.setInt(2, modulo);
            cs.setInt(3, tipo);
            cs.setInt(4, padre);
            cs.setString(5, usuario);

            rs = cs.executeQuery();
            while (rs.next()) {

                Acceso = new opcionAccesoC();
                Acceso.setOpcion(rs.getString("opcion"));
                Acceso.setDescripcion(rs.getString("descripcion_hijo"));

                m.add(Acceso);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public List<opcionAccesoC> todosPadres(int programa, int modulo, int tipo, String usuario) {

        List<opcionAccesoC> m = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionAccesoC Acceso ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("  SELECT SO.* FROM [SIGU].[SEG_OPCION] AS SO WHERE SELECCION in('P','O')  AND NOT  EXISTS(\n"
                    + "  SELECT  OPCION_PADRE  FROM [SIGU].[SEG_ACCESO_OPCION] O , [SIGU].[SEG_MODULO] M\n"
                    + "  WHERE M.PROGRAMA=O.PROGRAMA AND M.MODULO=O.MODULO  \n"
                    + "  AND O.PROGRAMA=? AND  O.MODULO=? AND O.TIPO_OPCION=? \n"
                    + "  AND USUARIO=? AND O.OPCION=SO.OPCION)ORDER BY SELECCION desc ,DESCRIPCION");
            cs.setInt(1, programa);
            cs.setInt(2, modulo);
            cs.setInt(3, tipo);
            cs.setString(4, usuario);

            rs = cs.executeQuery();

            while (rs.next()) {

                Acceso = new opcionAccesoC();
                Acceso.setOpcion(rs.getString("opcion"));
                Acceso.setDescripcion(rs.getString("descripcion"));
                Acceso.setSeleccion(rs.getString("seleccion").equals("P") ? "+" : "-");

                m.add(Acceso);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public List<opcionAccesoC> todosHijos(int programa, int modulo, int tipo, String usuario) {

        List<opcionAccesoC> m = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        opcionAccesoC Acceso ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("  SELECT SO.*FROM [SIGU].[SEG_OPCION] AS SO WHERE SELECCION='H' AND NOT EXISTS(\n"
                    + "  SELECT  O.PROGRAMA,O.MODULO,M.DESCRIPCION ,\n"
                    + "  O.ORDEN,O.TIPO_OPCION,O.OPCION_WHERE,O.ESTADO_REGISTRO  \n"
                    + "  FROM [SIGU].[SEG_ACCESO_OPCION] O , [SIGU].[SEG_MODULO] M\n"
                    + "  WHERE M.PROGRAMA=O.PROGRAMA AND M.MODULO=O.MODULO  \n"
                    + "  AND O.PROGRAMA=? AND  O.MODULO=? AND O.TIPO_OPCION=? \n"
                    + "  AND USUARIO=? and SO.OPCION=O.OPCION )ORDER BY SO.DESCRIPCION");
            cs.setInt(1, programa);
            cs.setInt(2, modulo);
            cs.setInt(3, tipo);
            cs.setString(4, usuario);
            rs = cs.executeQuery();
            while (rs.next()) {

                Acceso = new opcionAccesoC();
                Acceso.setOpcion(rs.getString("opcion"));
                Acceso.setDescripcion(rs.getString("descripcion"));

                m.add(Acceso);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public String insertarMenuHijo(String usuario, int programa, int modulo, int opcionHijo, int opcionPadre, int orden, int tipoOpcion, int tipoSeguridad) {

        String inserto = "";
        Connection c = null;

        c = ConexionWeb();
        PreparedStatement pr = null;
        String sql = "INSERT INTO [SIGU].[SEG_ACCESO_OPCION](USUARIO, PROGRAMA, MODULO, OPCION, OPCION_PADRE, ORDEN, TIPO_OPCION, TIPO_SEGURIDAD,ESTADO_REGISTRO)\n"
                + "VALUES(?,?,?,?,?,?,?,?,1) ";
        try {
            pr = c.prepareStatement(sql);
            pr.setString(1, usuario);
            pr.setInt(2, programa);
            pr.setInt(3, modulo);
            pr.setInt(4, opcionHijo);
            pr.setInt(5, opcionPadre);
            pr.setInt(6, orden);
            pr.setInt(7, tipoOpcion);
            pr.setInt(8, tipoSeguridad);

            if (pr.executeUpdate() == 1) {
                inserto = "true";
            } else {
                inserto = "false";
            }
        } catch (Exception ex) {
            inserto = ex.getMessage();
        } finally {
            try {
                pr.close();
                c.close();
            } catch (SQLException ex) {

            }
        }
        return inserto;
    }

    public String insertarMenuPadre(String usuario, int programa, int modulo, int opcionHijo, int opcionPadre, int orden, int tipoOpcion, int tipoSeguridad) {

        String inserto = "";
        Connection c = null;

        c = ConexionWeb();
        PreparedStatement pr = null;
        String sql = "INSERT INTO [SIGU].[SEG_ACCESO_OPCION](USUARIO, PROGRAMA, MODULO, OPCION, OPCION_PADRE, ORDEN, TIPO_OPCION, TIPO_SEGURIDAD,ESTADO_REGISTRO)\n"
                + "VALUES(?,?,?,?,?,?,?,?,1) ";
        try {

            pr = c.prepareStatement(sql);
            pr.setString(1, usuario);
            pr.setInt(2, programa);
            pr.setInt(3, modulo);
            pr.setInt(4, opcionHijo);
            pr.setInt(5, opcionPadre);
            pr.setInt(6, orden);
            pr.setInt(7, tipoOpcion);
            pr.setInt(8, tipoSeguridad);

            if (pr.executeUpdate() == 1) {
                inserto = "true";
            } else {
                inserto = "false";
            }
        } catch (Exception ex) {
            inserto = ex.getMessage();
        } finally {
            try {
                pr.close();
                c.close();
            } catch (Exception ex) {

            }
        }
        return inserto;
    }

    public String eliminarMenuPadre(int programa, int modulo, int tipo, String usuario, int opcionPadre) {

        String inserto = "";
        Connection c = null;

        c = ConexionWeb();
        PreparedStatement pr = null;
        String sql = "DELETE  FROM [SIGU].[SEG_ACCESO_OPCION] \n"
                + "where PROGRAMA=? AND MODULO=? AND TIPO_OPCION=? AND   OPCION_PADRE=0 and USUARIO=? AND OPCION=?";
        try {

            pr = c.prepareStatement(sql);

            pr.setInt(1, programa);
            pr.setInt(2, modulo);
            pr.setInt(3, tipo);
            pr.setString(4, usuario);
            pr.setInt(5, opcionPadre);

            if (pr.executeUpdate() == 1) {
                inserto = "true";
            } else {
                inserto = "false";
            }
        } catch (Exception ex) {
            inserto = ex.getMessage();
        } finally {
            try {
                pr.close();
                c.close();
            } catch (Exception ex) {

            }
        }
        return inserto;
    }

    public String eliminarMenuHijo(int programa, int modulo, int tipo, String usuario, int opcionPadre, int opcionHijo) {

        String inserto = "";
        Connection c = null;

        c = ConexionWeb();
        PreparedStatement pr = null;       //quito esquema
        String sql = "DELETE  FROM [SIGU].[SEG_ACCESO_OPCION] \n"
                + "where PROGRAMA=? AND MODULO=? AND TIPO_OPCION=? AND   USUARIO=? and OPCION_PADRE=?  AND OPCION=?";
        try {

            pr = c.prepareStatement(sql);

            pr.setInt(1, programa);
            pr.setInt(2, modulo);
            pr.setInt(3, tipo);
            pr.setString(4, usuario);
            pr.setInt(5, opcionPadre);
            pr.setInt(6, opcionHijo);

            if (pr.executeUpdate() == 1) {
                inserto = "true";
            } else {
                inserto = "false";
            }
        } catch (Exception ex) {
            inserto = ex.getMessage();
        } finally {
            try {
                pr.close();
                c.close();
            } catch (Exception ex) {

            }
        }
        return inserto;
    }

}
