/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.cursoPeriodoC;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class cursoPeriodoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public cursoPeriodoC mostrarCursoPerido(int institucion, String periodo,String malla, String carrera, String curso) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoPeriodoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_PERIODO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cursoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setMalla(rs.getString("MALLA"));
                item.setItem(rs.getInt("ITEM_ACT"));
                item.setFormula(rs.getInt("FORMULA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return item;
    }
	


	public cursoPeriodoC mostrarCursoPerido(int institucion, String periodo, String carrera, String curso) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoPeriodoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_PERIODO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND CURSO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cursoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setMalla(rs.getString("MALLA"));
                item.setItem(rs.getInt("ITEM_ACT"));
                item.setFormula(rs.getInt("FORMULA"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
	
	public cursoPeriodoC mostrarCursoPerido(int institucion, String periodo, String carrera,String malla,String curso, String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoPeriodoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_CURSO_SECCION B WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.MALLA LIKE '%' AND A.CURSO=? AND B.SECCION LIKE ? AND B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO AND B.CARRERA=A.CARRERA AND B.MALLA=A.MALLA AND B.CURSO=A.CURSO ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cursoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setMalla(rs.getString("MALLA"));
                item.setItem(rs.getInt("ITEM_ACT"));
                item.setFormula(rs.getInt("FORMULA"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public List<cursoPeriodoC> mostrarCursoPerido(int institucion, String periodo, String carrera) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoPeriodoC item ;
        List<cursoPeriodoC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_PERIODO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new cursoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setItem(rs.getInt("ITEM_ACT"));
                item.setFormula(rs.getInt("FORMULA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
    

    public String insertar(cursoPeriodoC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CURSOPERIODO(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setInt(6, item.getItem());
            cs.setInt(7, item.getFormula());
            cs.setInt(8, item.getEstadoRegistro());

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
    
    
    public String eliminar(cursoPeriodoC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM SIGU.HOR_CURSO_PERIODO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=?");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
           

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
}
