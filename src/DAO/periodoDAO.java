/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.periodoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;



public class periodoDAO extends Conexion implements Serializable {

	private static final long serialVersionUID = 1L;
	public List<periodoC> mostrarPeriodoConcepto(int institucion, String concepto) {
        List<periodoC> lista = new ArrayList<>();

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM SIGU.TES_CONCEPTO_INSTITUCION_PERIODO A,UPA.ACA_PERIODOS B WHERE A.INSTITUCION=? AND A.CONCEPTO=? AND B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO");
            cs.setInt(1, institucion);
            cs.setString(2, concepto);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new periodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDesPeriodo(rs.getString("DESCRIPCION"));
                item.setPeriodoAnt(rs.getString("periodo_anterior"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }

	
	public List<periodoC> mostrarPeriodoAlumno(int institucion, String alumno) {
        List<periodoC> m = new ArrayList<>();

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC periodo ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* from sigu.ACA_ALUMNO_PERIODO A ,UPA.ACA_PERIODOS B where B.INSTITUCION=? AND A.ALUMNO=? AND A.INSTITUCION=B.INSTITUCION  AND A.PERIODO=B.PERIODO  AND A.ESTADO_REGISTRO=5 ORDER BY PERIODO DESC");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {

                periodo = new periodoC();
                periodo.setInstitucion(rs.getInt("INSTITUCION"));
                periodo.setPeriodo(rs.getString("PERIODO"));
                periodo.setDesPeriodo(rs.getString("DESCRIPCION"));
                periodo.setPeriodoAnt(rs.getString("periodo_anterior"));
                m.add(periodo);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return m;
    }

    public List<periodoC> getPeriodoAlumno(String Usuario) {

        List<periodoC> m = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs;
        periodoC periodo ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select B.*from sigu.ACA_ALUMNO_PERIODO A ,UPA.ACA_PERIODOS B where A.ALUMNO=? AND A.INSTITUCION=B.INSTITUCION  AND A.PERIODO=B.PERIODO AND B.INSTITUCION=1 AND A.ESTADO_REGISTRO=5 ORDER BY PERIODO DESC");
            cs.setString(1, Usuario);
            rs = cs.executeQuery();
            while (rs.next()) {

                periodo = new periodoC();
                periodo.setInstitucion(rs.getInt("institucion"));
                periodo.setPeriodo(rs.getString("periodo"));
                periodo.setDesPeriodo(rs.getString("DESCRIPCION"));
                periodo.setPeriodoAnt(rs.getString("periodo_anterior"));
                m.add(periodo);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return m;
    }

    public List<periodoC> mostrarPeriodoInstitucion(int institucion) {

        List<periodoC> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from UPA.ACA_PERIODOS where INSTITUCION=? order by PERIODO desc");
            cs.setInt(1, institucion);
            rs = cs.executeQuery();
            while (rs.next()) {

            	item = new periodoC();
            	item.setInstitucion(rs.getInt("institucion"));
                item.setPeriodo(rs.getString("periodo"));
                item.setDesPeriodo(rs.getString("DESCRIPCION"));
                item.setPeriodoAnt(rs.getString("periodo_anterior"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
        }
        return lista;
    }

    public periodoC periodoMatricula(int institucion, int periodo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC item = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from UPA.ACA_PERIODOS where INSTITUCION=? and PERIODO=?");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new periodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDesPeriodo(rs.getString("DESCRIPCION"));
                item.setPeriodoAnt(rs.getString("PERIODO_ANTERIOR"));
                item.setOrdinario(rs.getInt("ORDINARIO"));
                item.setFechaDesde(rs.getDate("FECHA_DESDE"));
                item.setFechaHasta(rs.getDate("FECHA_HASTA"));
                item.setFechaMaDesde(rs.getDate("FECHA_DESDE_MAT"));
                item.setFechaMaHasta(rs.getDate("FECHA_HASTA_MAT"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
        }
        return item;
    }

   

    public List<ArrayList<String>> cantSemanasPerido(int institucion, int periodo) {

        List<ArrayList<String>> semanas = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  DATEDIFF(WEEK,FECHA_DESDE,FECHA_HASTA) SEMANAS FROM UPA.ACA_PERIODOS WHERE  INSTITUCION=? AND PERIODO=? ");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {

                for (int i = 1; i <= rs.getInt("SEMANAS"); i++) {
                    ArrayList<String> array = new ArrayList<>();
                    array.add("SEMANA " + i);
                    array.add("SEMANA_" + i);
                    semanas.add(array);
                }

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
           
        }
        return semanas;
    }

    public int ultimoPeriodoAlumno(String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        int periodo = 0;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select max(periodo)periodo from SIGU.ACA_ALUMNO_PERIODO where  ALUMNO=?");
            cs.setString(1, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {

                periodo = rs.getInt("periodo");

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
        }
        return periodo;
    }

    public List<periodoC> getPeriodoPersonal(String persona) {

        List<periodoC> m = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC periodo ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select distinct B.INSTITUCION , B.PERIODO,B.DESCRIPCION,B.PERIODO_ANTERIOR,B.PERIODO_SIGUIENTE\n"
                    + "from SIGU.HOR_CURSO_SECCION_DOCENTE A  ,UPA.ACA_PERIODOS B, [SIGU].[PER_PERSONAL] C,DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN D\n"
                    + "where  C.PERSONA=?  AND B.INSTITUCION=1 AND(( A.PERIODO=B.PERIODO AND C.PERSONAL=A.PERSONAL )OR(B.PERIODO=D.PERIODO AND D.PERSONAL=C.PERSONAL)) order by B.PERIODO desc");
            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {

                periodo = new periodoC();
                periodo.setInstitucion(rs.getInt("INSTITUCION"));
                periodo.setPeriodo(rs.getString("PERIODO"));
                periodo.setDesPeriodo(rs.getString("DESCRIPCION"));
                periodo.setPeriodoAnt(rs.getString("periodo_anterior"));
                m.add(periodo);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente("error periodo" + e.getMessage());

            
        }
        return m;
    }
    
    
    
    public List<periodoC> mostrarPeriodoPersonal(String personal) {

        List<periodoC> m = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC periodo ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT B.*FROM SIGU.HOR_CURSO_SECCION_DOCENTE A,UPA.ACA_PERIODOS B WHERE A.INSTITUCION LIKE '%' AND A.PERSONAL=? AND B.PERIODO=A.PERIODO AND B.INSTITUCION=A.INSTITUCION\n" +
                                "UNION\n" +
                                "SELECT DISTINCT B.*FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN  A,UPA.ACA_PERIODOS B WHERE A.INSTITUCION=1 AND A.PERSONAL=? AND B.PERIODO=A.PERIODO AND B.INSTITUCION=A.INSTITUCION\n" +
                                "order by PERIODO desc");
            cs.setString(1, personal);
            cs.setString(2, personal);
            rs = cs.executeQuery();
            while (rs.next()) {

                periodo = new periodoC();
                periodo.setInstitucion(rs.getInt("INSTITUCION"));
                periodo.setPeriodo(rs.getString("PERIODO"));
                periodo.setDesPeriodo(rs.getString("DESCRIPCION"));
                periodo.setPeriodoAnt(rs.getString("periodo_anterior"));
                m.add(periodo);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente("error periodo" + e.getMessage());

            
        }
        return m;
    }

    public periodoC mostrarPeriodo(int institucion, String periodo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.ACA_PERIODOS where INSTITUCION=? AND PERIODO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new periodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDesPeriodo(rs.getString("DESCRIPCION"));
                item.setPeriodoAnt(rs.getString("PERIODO_ANTERIOR"));
                item.setOrdinario(rs.getInt("ORDINARIO"));
                item.setFechaDesde(rs.getDate("FECHA_DESDE"));
                item.setFechaHasta(rs.getDate("FECHA_HASTA"));
                item.setFechaMaDesde(rs.getDate("FECHA_DESDE_MAT"));
                item.setFechaMaHasta(rs.getDate("FECHA_HASTA_MAT"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return item;
    }

    public List<periodoC> getPeriodoAdminitrativo(int institucion) {
        List<periodoC> m = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs;
        periodoC periodo ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.ACA_PERIODOS where INSTITUCION=? AND FECHA_DESDE<GETDATE() and PERIODO not in ('0000000000') ORDER BY PERIODO DESC");
            cs.setInt(1, institucion);
            rs = cs.executeQuery();
            while (rs.next()) {

                periodo = new periodoC();
                periodo.setInstitucion(rs.getInt("INSTITUCION"));
                periodo.setPeriodo(rs.getString("PERIODO"));
                periodo.setDesPeriodo(rs.getString("DESCRIPCION"));
                periodo.setPeriodoAnt(rs.getString("PERIODO_ANTERIOR"));
                periodo.setOrdinario(rs.getInt("ORDINARIO"));
                periodo.setFechaDesde(rs.getDate("FECHA_DESDE"));
                periodo.setFechaHasta(rs.getDate("FECHA_HASTA"));
                periodo.setFechaMaDesde(rs.getDate("FECHA_DESDE_MAT"));
                periodo.setFechaMaHasta(rs.getDate("FECHA_HASTA_MAT"));
                m.add(periodo);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return m;
    }
public List<periodoC> cuentaPersonaPeriodo(String institucion, String persona) {
        List<periodoC> m = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        periodoC periodo ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT TCP.INSTITUCION,TCP.PERIODO,AP.DESCRIPCION,AP.PERIODO_ANTERIOR FROM SIGU.TES_CUENTA_PERSONA TCP JOIN UPA.ACA_PERIODOS AP ON TCP.PERIODO=AP.PERIODO AND TCP.INSTITUCION=AP.INSTITUCION WHERE TCP.INSTITUCION=? AND TCP.PERSONA = ? ORDER BY TCP.PERIODO DESC");

            cs.setString(1, institucion);
            cs.setString(2, persona);

            rs = cs.executeQuery();
            while (rs.next()) {

                periodo = new periodoC();
                periodo.setInstitucion(rs.getInt("institucion"));
                periodo.setPeriodo(rs.getString("periodo"));
                periodo.setDesPeriodo(rs.getString("DESCRIPCION"));
                periodo.setPeriodoAnt(rs.getString("periodo_anterior"));
                m.add(periodo);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println("cuentaPersonaPeriodo " + e.getMessage());
        }
        return m;
    }
}
