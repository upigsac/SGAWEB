
package DAO;

import Conexiones.Conexion;

import ENTIDAD.tipoExamenC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tipoExamenDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public tipoExamenC mostrarTipoExamen(String tipoExamen) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.HOR_TIPO_EXAMEN WHERE TIPO_EXAMEN=?");
            cs.setString(1, tipoExamen);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoExamenC();
                item.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setDeslinea1(rs.getString("DESLINEA1"));
                item.setDeslinea2(rs.getString("DESLINEA2"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return item;
    }

    public List<tipoExamenC> mostrarTipoExamen() {

        List<tipoExamenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC tipoE ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from SIGU.HOR_TIPO_EXAMEN");
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new tipoExamenC();
                tipoE.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setDeslinea1(rs.getString("DESLINEA1"));
                tipoE.setDeslinea2(rs.getString("DESLINEA2"));
                tipoE.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
     public List<tipoExamenC> mostrarTipoExamen(int institucion,String periodo,String malla,String carrera,String curso,String dependencia ,Boolean subDocente) {

        List<tipoExamenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC tipoE ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM SIGU.HOR_TIPO_EXAMEN A,SIGU.HOR_FORMULA_EXAMEN B, SIGU.HOR_CURSO_PERIODO C WHERE C.INSTITUCION=? AND C.PERIODO=? AND C.MALLA=? AND C.CARRERA=? AND C.CURSO=?  AND B.DECENDENCIA=? AND B.SUB_DOCENTE = ? AND B.FORMULA=C.FORMULA AND B.TIPO_EXAMEN=A.TIPO_EXAMEN ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, dependencia);
            cs.setBoolean(7, subDocente);
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new tipoExamenC();
                tipoE.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setDeslinea1(rs.getString("DESLINEA1"));
                tipoE.setDeslinea2(rs.getString("DESLINEA2"));
                tipoE.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<tipoExamenC> mostrarTipoExamenFormula(int formula) {
        List<tipoExamenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC tipoE ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.* FROM SIGU.HOR_FORMULA A,SIGU.HOR_FORMULA_EXAMEN B,SIGU.HOR_TIPO_EXAMEN C\n"
                    + "WHERE A.FORMULA=B.FORMULA AND A.FORMULA=? AND C.TIPO_EXAMEN=B.TIPO_EXAMEN");
            cs.setInt(1, formula);
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new tipoExamenC();
                tipoE.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setDeslinea1(rs.getString("DESLINEA1"));
                tipoE.setDeslinea2(rs.getString("DESLINEA2"));
                tipoE.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<tipoExamenC> mostrarTipoExamenFormulaRegular() {
        List<tipoExamenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC tipoE ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT distinct C.*FROM SIGU.HOR_FORMULA A,SIGU.HOR_FORMULA_EXAMEN B ,SIGU.HOR_TIPO_EXAMEN C WHERE A.FORMULA=B.FORMULA AND B.TIPO_EXAMEN=C.TIPO_EXAMEN AND A.FORMULA IN(8,10) AND B.LECTURA=0 ORDER BY 2");

            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new tipoExamenC();
                tipoE.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setDeslinea1(rs.getString("DESLINEA1"));
                tipoE.setDeslinea2(rs.getString("DESLINEA2"));
                tipoE.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<tipoExamenC> mostrarTipoExamenAutorizacion() {

        List<tipoExamenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC tipoE ;

        try {
           //deshabilitados u0 u5 y0

            c = ConexionWeb();
            cs = c.prepareCall("select TIPO_EXAMEN,DESCRIPCION,ABREVIATURA,DESLINEA1,DESLINEA2,ESTADO_REGISTRO from sigu.HOR_TIPO_EXAMEN where TIPO_EXAMEN\n"
                    + "in ('u1','u2','u3','u4','u6','u7','u8','u9','v1','v2','v5',\n"
                    + "'v6','y1','y2','y3','y4','y5','ep','ef','ea','es')\n"
                    + "UNION\n"
                    + "SELECT '%','TODOS','','','',1 ORDER BY TIPO_EXAMEN");
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new tipoExamenC();
                tipoE.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setDeslinea1(rs.getString("DESLINEA1"));
                tipoE.setDeslinea2(rs.getString("DESLINEA2"));
                tipoE.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
     public List<tipoExamenC> mostrarTipoExamenPersonalSecundario(int institucion,String periodo,String personal,String carrera,String seccion,String curso) {

        List<tipoExamenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC tipoE ;

        try {
           

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT E.*FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN A,SIGU.HOR_SECCION B,UPA.ACA_CARRERAS C,UPA.ACA_CURSOS D,SIGU.HOR_TIPO_EXAMEN E\n" +
                               "WHERE A.INSTITUCION=B.INSTITUCION  AND  C.CARRERA=A.CARRERA AND D.CURSO=A.CURSO AND E.TIPO_EXAMEN=A.TIPO_EXAMEN AND\n" +
                               " A.INSTITUCION=? AND A.PERIODO=? AND A.PERSONAL=? AND A.CARRERA=?  AND B.SECCION like ? AND D.CURSO=?");
              cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            cs.setString(4, carrera);
            cs.setString(5, seccion);
            cs.setString(6, curso);
            rs = cs.executeQuery();
          
            

            while (rs.next()) {
                tipoE = new tipoExamenC();
                tipoE.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setDeslinea1(rs.getString("DESLINEA1"));
                tipoE.setDeslinea2(rs.getString("DESLINEA2"));
                tipoE.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return lista;
    }
     
     
     
     
     public List<tipoExamenC> mostrarTipoExamen(int institucion,String periodo,int vencimiento) {

        List<tipoExamenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoExamenC tipoE ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT DISTINCT E.* FROM SIGU.HOR_PERIODO_SECCION A,SIGU.HOR_CURSO_PERIODO B,SIGU.HOR_CURSO_SECCION C ,SIGU.HOR_FORMULA_EXAMEN D,SIGU.HOR_TIPO_EXAMEN E  WHERE A.INSTITUCION=? AND A.PERIODO=?  AND A.VENCIMIENTO=?  AND B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO AND B.CARRERA=A.CARRERA AND DECENDENCIA NOT IN('N')  AND C.INSTITUCION=A.INSTITUCION AND C.PERIODO=A.PERIODO AND C.CARRERA=A.CARRERA AND C.MALLA=B.MALLA AND C.CURSO=B.CURSO AND C.SECCION=A.SECCION  AND D.FORMULA=B.FORMULA AND D.SUB_FORMULA IS NULL  AND E.TIPO_EXAMEN=D.TIPO_EXAMEN  AND NOT EXISTS(SELECT *FROM DI.SYS_SEMANA_EXAMEN WHERE INSTITUCION=A.INSTITUCION AND PERIODO=A.PERIODO AND GRUPO=A.VENCIMIENTO AND TIPO_EXAMEN=E.TIPO_EXAMEN)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, vencimiento);
            rs = cs.executeQuery();

            while (rs.next()) {
                tipoE = new tipoExamenC();
                tipoE.setTipo_examen(rs.getString("TIPO_EXAMEN"));
                tipoE.setDescripcion(rs.getString("DESCRIPCION"));
                tipoE.setAbreviatura(rs.getString("ABREVIATURA"));
                tipoE.setDeslinea1(rs.getString("DESLINEA1"));
                tipoE.setDeslinea2(rs.getString("DESLINEA2"));
                tipoE.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(tipoE);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
     public List<tipoExamenC> mostrarTipoExamen(int formula) {

         List<tipoExamenC> lista = new ArrayList<>();
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         tipoExamenC item ;

         try {

             c = ConexionWeb();
             cs = c.prepareCall(" SELECT B.* FROM  SIGU.HOR_FORMULA_EXAMEN A,SIGU.HOR_TIPO_EXAMEN B WHERE FORMULA=? AND ACTA=1 AND B.TIPO_EXAMEN=A.TIPO_EXAMEN");
             cs.setInt(1, formula);
            
             rs = cs.executeQuery();

             while (rs.next()) {
            	 item = new tipoExamenC();
            	 item.setTipo_examen(rs.getString("TIPO_EXAMEN"));
            	 item.setDescripcion(rs.getString("DESCRIPCION"));
                 item.setAbreviatura(rs.getString("ABREVIATURA"));
                 item.setDeslinea1(rs.getString("DESLINEA1"));
                 item.setDeslinea2(rs.getString("DESLINEA2"));
                 item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                 lista.add(item);

             }
             cerrarCall(cs);
             cerrarConexion(c);

         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         }
         return lista;
     }

     
     
     
     public List<tipoExamenC> mostrarTipoExamen(int institucion,String periodo,String carrera,String malla,String curso,String dependecia) {

         List<tipoExamenC> lista = new ArrayList<>();
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         tipoExamenC item ;

         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT C.*FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_FORMULA_EXAMEN B,SIGU.HOR_TIPO_EXAMEN C WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.MALLA=? AND A.CURSO=? AND B.DECENDENCIA=? AND B.FORMULA=A.FORMULA AND C.TIPO_EXAMEN=B.TIPO_EXAMEN");
             cs.setInt(1, institucion);
             cs.setString(2, periodo);
             cs.setString(3, carrera);
             cs.setString(4, malla);
             cs.setString(5, curso);
             cs.setString(6, dependecia);
             
             
            
             rs = cs.executeQuery();

             while (rs.next()) {
            	 item = new tipoExamenC();
            	 item.setTipo_examen(rs.getString("TIPO_EXAMEN"));
            	 item.setDescripcion(rs.getString("DESCRIPCION"));
                 item.setAbreviatura(rs.getString("ABREVIATURA"));
                 item.setDeslinea1(rs.getString("DESLINEA1"));
                 item.setDeslinea2(rs.getString("DESLINEA2"));
                 item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                 lista.add(item);

             }
             cerrarCall(cs);
             cerrarConexion(c);

         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         }
         return lista;
     }
     
     public List<tipoExamenC> mostrarTipoExamen(int institucion,String periodo,String carrera,String malla,String curso,String dependecia,String tipoExamenPadre) {

         List<tipoExamenC> lista = new ArrayList<>();
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         tipoExamenC item ;

         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT C.*FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_FORMULA_EXAMEN B,SIGU.HOR_TIPO_EXAMEN C WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.MALLA=? AND A.CURSO=? AND B.DECENDENCIA=? AND B.TIPO_EXAMEN_PADRE=? AND B.FORMULA=A.FORMULA AND C.TIPO_EXAMEN=B.TIPO_EXAMEN");
             cs.setInt(1, institucion);
             cs.setString(2, periodo);
             cs.setString(3, carrera);
             cs.setString(4, malla);
             cs.setString(5, curso);
             cs.setString(6, dependecia);
             cs.setString(7, tipoExamenPadre);
             
            
             rs = cs.executeQuery();

             while (rs.next()) {
            	 item = new tipoExamenC();
            	 item.setTipo_examen(rs.getString("TIPO_EXAMEN"));
            	 item.setDescripcion(rs.getString("DESCRIPCION"));
                 item.setAbreviatura(rs.getString("ABREVIATURA"));
                 item.setDeslinea1(rs.getString("DESLINEA1"));
                 item.setDeslinea2(rs.getString("DESLINEA2"));
                 item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                 lista.add(item);

             }
             cerrarCall(cs);
             cerrarConexion(c);

         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         }
         return lista;
     }

}
