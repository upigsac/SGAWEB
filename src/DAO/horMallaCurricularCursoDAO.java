/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.horMallaCurricularCursoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class horMallaCurricularCursoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<horMallaCurricularCursoC> mostrarMallaCurricularCurso(int institucion, String carrera, String malla) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horMallaCurricularCursoC item ;
        List<horMallaCurricularCursoC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_MALLA_CURRICULAR_CURSO WHERE INSTITUCION=? AND CARRERA=? AND MALLA=?");
            cs.setInt(1, institucion);
            cs.setString(2, carrera);
            cs.setString(3, malla);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new horMallaCurricularCursoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<horMallaCurricularCursoC> mostrarMallaCurricularCurso(int institucion, String periodo, String carrera, String malla) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horMallaCurricularCursoC item ;
        List<horMallaCurricularCursoC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_MALLA_CURRICULAR_CURSO A WHERE A.INSTITUCION=? AND A.CARRERA=? AND A.MALLA=?  \n"
                    + "AND NOT EXISTS(SELECT *FROM SIGU.HOR_CURSO_PERIODO B WHERE B.INSTITUCION=A.INSTITUCION AND B.PERIODO=? AND B.CARRERA=A.CARRERA AND B.MALLA=A.MALLA AND B.CURSO=A.CURSO  )");
            cs.setInt(1, institucion);
            cs.setString(2, carrera);
            cs.setString(3, malla);
            cs.setString(4, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new horMallaCurricularCursoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

}
