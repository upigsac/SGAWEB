/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.institucionC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class institucionDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<institucionC> mostrarInstitucion() {

        List<institucionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC inst ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from [UPA].[SYS_INSTITUCION]");
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new institucionC();
                inst.setInstitucion(rs.getInt("INSTITUCION"));
                inst.setDescripcion(rs.getString("DESCRIPCION"));
                inst.setAbreviatura(rs.getString("ABREVIATURA"));
                inst.setRuc(rs.getString("RUC"));
                inst.setTitulo(rs.getString("TITULO"));
                inst.setSubTitulo(rs.getString("SUB_TITULO"));
                lista.add(inst);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
	public List<institucionC> mostrarConceptoInstitucion(String concepto,int tipoConcepto) {

        List<institucionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC inst ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  A.*FROM UPA.SYS_INSTITUCION A, SIGU.TES_CONCEPTOS_INSTITUCION B WHERE A.INSTITUCION=B.INSTITUCION AND B.CONCEPTO=? AND B.TIPO_CONCEPTO=?");
            cs.setString(1, concepto);
            cs.setInt(2, tipoConcepto);
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new institucionC();
                inst.setInstitucion(rs.getInt("INSTITUCION"));
                inst.setDescripcion(rs.getString("DESCRIPCION"));
                inst.setAbreviatura(rs.getString("ABREVIATURA"));
                inst.setRuc(rs.getString("RUC"));
                inst.setTitulo(rs.getString("TITULO"));
                inst.setSubTitulo(rs.getString("SUB_TITULO"));
                lista.add(inst);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public List<institucionC> mostrarInstitucionAlumno(String alumno) {

        List<institucionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC inst ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select B.* from SIGU.ACA_ALUMNO A, UPA.SYS_INSTITUCION B WHERE A.INSTITUCION=B.INSTITUCION AND  A.ALUMNO=?");
            cs.setString(1, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new institucionC();
                inst.setInstitucion(rs.getInt("INSTITUCION"));
                inst.setDescripcion(rs.getString("DESCRIPCION"));
                inst.setAbreviatura(rs.getString("ABREVIATURA"));
                inst.setRuc(rs.getString("RUC"));
                inst.setTitulo(rs.getString("TITULO"));
                inst.setSubTitulo(rs.getString("SUB_TITULO"));
                lista.add(inst);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }
    

    public List<institucionC> mostrarInstitucionUsuario(String usuario) {

        List<institucionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC inst ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM DI.SEG_USUARIO_INSTITUCION_WEB A,UPA.SYS_INSTITUCION B WHERE A.INSTITUCION=B.INSTITUCION AND  A.USUARIO=? AND A.ESTADO_REGISTRO=1");
            cs.setString(1, usuario);
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new institucionC();
                inst.setInstitucion(rs.getInt("INSTITUCION"));
                inst.setDescripcion(rs.getString("DESCRIPCION"));
                inst.setAbreviatura(rs.getString("ABREVIATURA"));
                inst.setRuc(rs.getString("RUC"));
                inst.setTitulo(rs.getString("TITULO"));
                inst.setSubTitulo(rs.getString("SUB_TITULO"));
                lista.add(inst);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<institucionC> mostrarInstitucionFaltantesUsuario(String usuario) {

        List<institucionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC inst ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.*from SIGU.WEB_INSTITUCION_ACCESO A,UPA.SYS_INSTITUCION C WHERE C.INSTITUCION=A.INSTITUCION\n" +
                               "AND NOT EXISTS(SELECT *FROM DI.SEG_USUARIO_INSTITUCION_WEB B WHERE B.INSTITUCION=A.INSTITUCION AND B.USUARIO=?)");
            cs.setString(1, usuario);
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new institucionC();
                inst.setInstitucion(rs.getInt("INSTITUCION"));
                inst.setDescripcion(rs.getString("DESCRIPCION"));
                inst.setAbreviatura(rs.getString("ABREVIATURA"));
                inst.setRuc(rs.getString("RUC"));
                inst.setTitulo(rs.getString("TITULO"));
                inst.setSubTitulo(rs.getString("SUB_TITULO"));
                lista.add(inst);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public institucionC mostrarInstitucionCodigo(int codigo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC inst = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from [UPA].[SYS_INSTITUCION] where INSTITUCION=?");
            cs.setInt(1, codigo);
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new institucionC();
                inst.setInstitucion(rs.getInt("INSTITUCION"));
                inst.setDescripcion(rs.getString("DESCRIPCION"));
                inst.setAbreviatura(rs.getString("ABREVIATURA"));
                inst.setRuc(rs.getString("RUC"));
                inst.setTitulo(rs.getString("TITULO"));
                inst.setSubTitulo(rs.getString("SUB_TITULO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inst;
    }

    public List<institucionC> mostrarInstitucionPersona(String persona) {

        List<institucionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC inst ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT I.INSTITUCION,I.DESCRIPCION,I.ABREVIATURA,I.RUC,I.TITULO,I.SUB_TITULO\n"
                    + "   FROM [UPA].[SYS_INSTITUCION] I ,[SIGU].[TES_CUENTA_PERSONA] CU ,UPA.DAT_PERSONAS P\n"
                    + "   WHERE CU.INSTITUCION=I.INSTITUCION AND P.PERSONA=CU.PERSONA \n"
                    + "   AND P.PERSONA=? \n"
                    + "   ORDER BY INSTITUCION");
            cs.setString(1, persona);
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new institucionC();
                inst.setInstitucion(rs.getInt("INSTITUCION"));
                inst.setDescripcion(rs.getString("DESCRIPCION"));
                inst.setAbreviatura(rs.getString("ABREVIATURA"));
                inst.setRuc(rs.getString("RUC"));
                inst.setTitulo(rs.getString("TITULO"));
                inst.setSubTitulo(rs.getString("SUB_TITULO"));
                lista.add(inst);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<List<String>> mostrarInstitucionLista(String persona) {

        List<List<String>> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<String> inst ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT I.INSTITUCION,I.DESCRIPCION,I.ABREVIATURA,I.RUC,I.TITULO,I.SUB_TITULO,DI.imagen,DI.ORDEN\n"
                    + "FROM [UPA].[SYS_INSTITUCION] I ,[SIGU].[TES_CUENTA_PERSONA] CU ,UPA.DAT_PERSONAS P, DATOS_INSTITUCION DI\n"
                    + "WHERE CU.INSTITUCION=I.INSTITUCION AND P.PERSONA=CU.PERSONA AND DI.institucion=I.INSTITUCION AND P.PERSONA=? ORDER BY DI.orden");
            cs.setString(1, persona);
            rs = cs.executeQuery();

            while (rs.next()) {
                inst = new ArrayList<>();
                inst.add(rs.getString(1));
                inst.add(rs.getString(2));
                inst.add(rs.getString(3));
                inst.add(rs.getString(4));
                inst.add(rs.getString(5));
                inst.add(rs.getString(6));
                inst.add(rs.getString(7));
                inst.add(rs.getString(8));
                lista.add(inst);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<institucionC> mostrarInstitucionDocente(String periodo, String personal) {

        List<institucionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT distinct B.INSTITUCION,B.DESCRIPCION,B.ABREVIATURA ,B.RUC,TITULO,SUB_TITULO\n"
                    + "FROM SIGU.HOR_CURSO_SECCION_DOCENTE A ,upa.SYS_INSTITUCION B \n"
                    + "WHERE A.INSTITUCION=B.INSTITUCION AND A.PERIODO=? AND A.PERSONAL=?");
            cs.setString(1, periodo);
            cs.setString(2, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new institucionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setRuc(rs.getString("RUC"));
                item.setTitulo(rs.getString("TITULO"));
                item.setSubTitulo(rs.getString("SUB_TITULO"));
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
