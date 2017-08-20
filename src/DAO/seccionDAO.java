/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;



import ENTIDAD.seccionC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class seccionDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public List<seccionC> mostrarSeccionPrincipal(int institucion, String periodo ,String carrera,String persona) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HOR_CURSO_SECCION_DOCENTE (7,?,?,?,'%','%','%','%',?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, persona);
            

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
	

	public List<seccionC> mostrarSeccion(int institucion, String periodo, String carrera, String nivel, String turno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC item ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM  SIGU.HOR_PERIODO_SECCION A ,SIGU.HOR_SECCION B WHERE A.INSTITUCION=? and  A.PERIODO=? and A.CARRERA=? AND A.NIVEL_MODULAR like ? AND A.TURNO LIKE ? AND A.INSTITUCION=B.INSTITUCION AND A.SECCION=B.SECCION AND A.ESTADO_REGISTRO IN(1,31,33,40,42)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, nivel);
            cs.setString(5, turno);

            rs = cs.executeQuery();

            while (rs.next()) {

            	item = new seccionC();
            	item.setInstitucion(rs.getInt("institucion"));
            	item.setSeccion(rs.getString("seccion"));
            	item.setDescripcion(rs.getString("descripcion"));
            	item.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
     public List<seccionC> mostrarSeccion(int institucion, String periodo, String carrera) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM SIGU.HOR_PERIODO_SECCION A,SIGU.HOR_SECCION B WHERE B.INSTITUCION=A.INSTITUCION AND B.SECCION=A.SECCION AND A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
          

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
     
     public List<seccionC> filtroSeccion(int institucion, String periodo, String carrera,int turno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM SIGU.HOR_PERIODO_SECCION A,SIGU.HOR_SECCION B WHERE B.INSTITUCION=A.INSTITUCION AND B.SECCION=A.SECCION AND A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.TURNO=? ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setInt(4, turno);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
     
     
     
     
     
    
    
    public List<seccionC> filtroSeccion(int institucion,String cadena) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select top 50 *from SIGU.HOR_SECCION where INSTITUCION=? AND SECCION + DESCRIPCION like ?");
            cs.setInt(1, institucion);
            cs.setString(2, "%"+cadena+"%");
            

            rs = cs.executeQuery();

            while (rs.next()) {
                
                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
    
    
    public List<seccionC> mostrarSeccion(int institucion, String periodo, String carrera, int nivel) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM SIGU.HOR_PERIODO_SECCION A,SIGU.HOR_SECCION B WHERE A.INSTITUCION=B.INSTITUCION AND A.SECCION=B.SECCION \n" +
                               "AND  A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.NIVEL_MODULAR=? AND A.ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setInt(4, nivel);
            

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
     public List<seccionC> mostrarListaSeccion(int institucion, String periodo, String carrera, String curso) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM SIGU.HOR_SECCION A, SIGU.HOR_CURSO_SECCION B  WHERE B.INSTITUCION=? AND B.PERIODO=? AND B.CARRERA=? AND  B.CURSO=? AND A.INSTITUCION=B.INSTITUCION AND A.SECCION=B.SECCION AND B.ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.alert(e.getMessage());
        }
        return lista;
    }

    public seccionC mostrarSeccion(int institucion, String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_SECCION WHERE INSTITUCION=? AND SECCION=? ");
            cs.setInt(1, institucion);
            cs.setString(2, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new seccionC();
                item.setInstitucion(rs.getInt("institucion"));
                item.setSeccion(rs.getString("seccion"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return item;
    }

    public List<seccionC> mostrarSeccion(int institucion, String periodo, String curso, String personal) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM SIGU.HOR_CURSO_SECCION_DOCENTE A ,SIGU.HOR_SECCION B\n"
                    + "WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CURSO=? AND A.PERSONAL=?\n"
                    + "AND A.INSTITUCION=B.INSTITUCION AND A.SECCION=B.SECCION AND A.ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, curso);
            cs.setString(4, personal);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
    
    public List<seccionC> mostrarSeccion(int institucion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT top 50 *FROM SIGU.HOR_SECCION WHERE INSTITUCION=?");
            cs.setInt(1, institucion);
     

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<seccionC> seccionCursoCarreraAutorizacionNota(String carrera, String curso) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select DISTINCT hc.INSTITUCION,HC.SECCION,hc.DESCRIPCION,hc.ESTADO_REGISTRO \n"
                    + "                                FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,\n"
                    + "                                [SIGU].[PER_PERSONAL] B ,UPA.DAT_PERSONAS C, [UPA].[ACA_CURSOS] D, SIGU.HOR_SECCION HC \n"
                    + "                                WHERE A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA AND D.CURSO=A.CURSO AND \n"
                    + "                                A.CARRERA LIKE ? and A.CURSO LIKE ? and hc.SECCION=a.SECCION \n"
                    + "                                UNION SELECT 1,'%','TODOS',1");

            cs.setString(1, carrera);
            cs.setString(2, curso);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<seccionC> seccionCursoCarrera(String carrera, String curso) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT hc.INSTITUCION,HC.SECCION,hc.DESCRIPCION,hc.ESTADO_REGISTRO \n"
                    + " FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,\n"
                    + " [UPA].[ACA_CURSOS] D, SIGU.HOR_SECCION HC \n"
                    + " WHERE D.CURSO=A.CURSO AND \n"
                    + " A.CARRERA = ? and A.CURSO =? and hc.SECCION=a.SECCION ");

            cs.setString(1, carrera);
            cs.setString(2, curso);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<seccionC> seccionPeriodoCurso(int institucion, String periodo, String curso) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.INSTITUCION ,A.SECCION+' - '+A.GRUPO SECCION,B.DESCRIPCION +' - '+A.GRUPO DESCRIPCION ,C.ESTADO_REGISTRO \n" +
                            "FROM sigu.HOR_CURSO_SECCION A ,SIGU.HOR_SECCION B ,SIGU.HOR_PERIODO_SECCION C\n" +
                            "WHERE A.INSTITUCION=B.INSTITUCION AND A.SECCION=B.SECCION AND \n" +
                            "C.INSTITUCION=A.INSTITUCION AND C.PERIODO=A.PERIODO AND C.SECCION=A.SECCION AND\n" +
                            "A.INSTITUCION=? AND A.PERIODO=? AND A.CURSO=? AND C.ESTADO_REGISTRO IN(31,33,1) ORDER BY B.DESCRIPCION");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, curso);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<seccionC> getBuscaPersonaCodigo(int periodo, String personal, String curso) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("  select  hc.INSTITUCION,HC.SECCION,hc.DESCRIPCION,hc.ESTADO_REGISTRO\n"
                    + "                               FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A\n"
                    + "                                  ,[SIGU].[PER_PERSONAL] B ,UPA.DAT_PERSONAS C, [UPA].[ACA_CURSOS] D, SIGU.HOR_SECCION HC\n"
                    + "                                  WHERE A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA AND D.CURSO=A.CURSO AND\n"
                    + "                                   A.PERIODO=? AND B.PERSONAL=? AND A.CURSO=? and hc.SECCION=a.SECCION\n"
                    + "                                  ORDER BY hc.SECCION");
            cs.setInt(1, periodo);
            cs.setString(2, personal);
            cs.setString(3, curso);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
        }
        return lista;
    }

    public List<seccionC> mostrarSeccionPeriodoCarreraDocente(int institucion, String periodo, String carrera, String docente) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc;
        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT distinct hc.INSTITUCION,HC.SECCION,hc.DESCRIPCION,hc.ESTADO_REGISTRO FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,[SIGU].[PER_PERSONAL] B ,UPA.DAT_PERSONAS C, SIGU.HOR_SECCION HC                    WHERE A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA  AND A.INSTITUCION=HC.INSTITUCION  AND A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND  B.PERSONA=? AND  hc.SECCION=a.SECCION AND A.ESTADO_REGISTRO IN(1,59) ORDER BY hc.SECCION  ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, docente);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
    
     public List<seccionC> mostrarSeccionPersonalSecundario(int institucion, String periodo, String personal, String carrera) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc;
        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT B.*FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN A,SIGU.HOR_SECCION B,UPA.ACA_CARRERAS C,DI.ACA_ALUMNO_CURSO_SECCION_GRUPO_EXAMEN D\n" +
                               " WHERE A.INSTITUCION=B.INSTITUCION AND C.CARRERA=A.CARRERA AND A.INSTITUCION=? AND B.SECCION=D.SECCION AND A.PERIODO=? AND A.PERSONAL=? AND A.CARRERA=?\n" +
                               " AND D.INSTITUCION=A.INSTITUCION AND D.PERIODO=A.PERIODO AND D.MALLA=A.MALLA AND A.CARRERA=D.CARRERA AND A.CURSO=D.CURSO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            cs.setString(4, carrera);
            rs = cs.executeQuery();
            while (rs.next()) {
                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<seccionC> mostrarSeccionCursoDocente(String persona, String periodo, String carrera, String curso) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select hc.INSTITUCION,HC.SECCION,hc.DESCRIPCION,hc.ESTADO_REGISTRO\n"
                    + "FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,\n"
                    + "[SIGU].[PER_PERSONAL] B ,UPA.DAT_PERSONAS C, [UPA].[ACA_CURSOS] D, SIGU.HOR_SECCION HC\n"
                    + "WHERE A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA AND D.CURSO=A.CURSO AND\n"
                    + "B.PERSONA=? AND A.PERIODO=? AND A.CARRERA=? and A.CURSO=? and hc.SECCION=a.SECCION AND A.ESTADO_REGISTRO=1\n"
                    + "ORDER BY hc.SECCION");

            cs.setString(1, persona);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
        }
        return lista;
    }

    public List<seccionC> mostrarListaSeccion(String institucion, String periodo,String personal, String carrera, String curso) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select hc.INSTITUCION,HC.SECCION,hc.DESCRIPCION,hc.ESTADO_REGISTRO\n"
                    + "FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,\n"
                    + "[UPA].[ACA_CURSOS] D, SIGU.HOR_SECCION HC\n"
                    + "WHERE D.CURSO=A.CURSO AND  hc.SECCION=a.SECCION AND A.ESTADO_REGISTRO=1 AND \n"
                    + "A.INSTITUCION LIKE ? AND A.PERIODO=? AND A.PERSONAL=? AND  A.CARRERA=? and A.CURSO=? \n"
                    + "ORDER BY hc.SECCION");
            
            
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);            
            cs.setString(4, carrera);
            cs.setString(5, curso);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
        }
        return lista;
    }

    public List<seccionC> mostrarSeccionPeriodoCarrera(int periodo, String carrera) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" select  distinct hc.INSTITUCION,HC.SECCION,hc.DESCRIPCION,hc.ESTADO_REGISTRO\n"
                    + " FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A, SIGU.HOR_SECCION HC\n"
                    + " WHERE A.PERIODO=? AND A.CARRERA=? AND  hc.SECCION=a.SECCION\n"
                    + " ORDER BY hc.SECCION ");

            cs.setInt(1, periodo);
            cs.setString(2, carrera);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
        }
        return lista;
    }

    public List<seccionC> mostrarSeccionPeriodoCarreraTurno(int periodo, String carrera, String turno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionC secc ;

        List<seccionC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select distinct B.INSTITUCION,B.SECCION,B.DESCRIPCION,B.ESTADO_REGISTRO from sigu.HOR_CURSO_SECCION A,SIGU.HOR_SECCION B\n"
                    + " where  \n"
                    + " A.INSTITUCION=B.INSTITUCION\n"
                    + " AND A.INSTITUCION=1\n"
                    + " AND A.SECCION=B.SECCION\n"
                    + " AND A.PERIODO=? \n"
                    + " AND A.CARRERA=? \n"
                    + " AND SUBSTRING(A.SECCION,LEN(A.SECCION)-1,1)=?");

            cs.setInt(1, periodo);
            cs.setString(2, carrera);
            cs.setString(3, turno);

            rs = cs.executeQuery();

            while (rs.next()) {

                secc = new seccionC();
                secc.setInstitucion(rs.getInt("institucion"));
                secc.setSeccion(rs.getString("seccion"));
                secc.setDescripcion(rs.getString("descripcion"));
                secc.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(secc);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<seccionC> mostrarSeccionInstitucionPeriodoDocente(String institucion, String periodo, String carrera, String personal) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<seccionC> lista = new ArrayList<>();
        seccionC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  B.* FROM SIGU.HOR_CURSO_SECCION_DOCENTE A , SIGU.HOR_SECCION B WHERE A.INSTITUCION=? AND  A.PERIODO=? AND A.CARRERA like ? AND A.PERSONAL like ? AND A.INSTITUCION=B.INSTITUCION AND A.SECCION=B.SECCION");

            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, personal);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new seccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<ArrayList<String>> listaSeccionAlumno(String codigoUsuario, String periodo) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT [INSTITUCION],[PERIODO],[CARRERA],[SECCION],[ALUMNO]\n"
                    + "FROM [SIGU].[ACA_ALUMNO_CURSO_SECCION] where ALUMNO=? and PERIODO=? and INSTITUCION=1\n"
                    + "GROUP BY [INSTITUCION],[PERIODO],[CARRERA],[SECCION],[ALUMNO]");

            cs.setString(1, codigoUsuario);
            cs.setString(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("INSTITUCION"));
                array.add(rs.getString("PERIODO"));
                array.add(rs.getString("CARRERA"));
                array.add(rs.getString("SECCION"));
                array.add(rs.getString("ALUMNO"));
                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        
        }
        return lista;
    }

    public List<ArrayList<String>> horarioSeccion(int institucion, int periodo, String alumno, String turno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_HORARIO_ALUMNO (?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, alumno);
            cs.setString(4, turno);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("HORA_INI"));
                array.add(rs.getString("HORA_FIN"));
                array.add(rs.getString("LUNES"));
                array.add(rs.getString("MARTES"));
                array.add(rs.getString("MIERCOLES"));
                array.add(rs.getString("JUEVES"));
                array.add(rs.getString("VIERNES"));
                array.add(rs.getString("SABADO"));
                array.add(rs.getString("DOMINGO"));

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
        }
        return lista;
    }

    public List<ArrayList<String>> horarioSeccionExamen(int institucion, int periodo, String alumno, String tipoExamen) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_HORARIO_ALUMNO_EXAMENES] (?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, alumno);
            cs.setString(4, tipoExamen);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("HORA_INI"));
                array.add(rs.getString("HORA_FIN"));
                array.add(rs.getString("LUNES"));
                array.add(rs.getString("MARTES"));
                array.add(rs.getString("MIERCOLES"));
                array.add(rs.getString("JUEVES"));
                array.add(rs.getString("VIERNES"));
                array.add(rs.getString("SABADO"));
                array.add(rs.getString("DOMINGO"));

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<ArrayList<String>> horarioAula(int institucion, int periodo, String aula, String turno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_HORARIO_AULA](?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, aula);
            cs.setString(4, turno);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("HORA_INI"));
                array.add(rs.getString("HORA_FIN"));
                array.add(rs.getString("LUNES"));
                array.add(rs.getString("MARTES"));
                array.add(rs.getString("MIERCOLES"));
                array.add(rs.getString("JUEVES"));
                array.add(rs.getString("VIERNES"));
                array.add(rs.getString("SABADO"));
                array.add(rs.getString("DOMINGO"));

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
       
        }
        return lista;
    }

    public List<ArrayList<String>> horarioDocente(int institucion, String periodo, String carrera, String personal, String turno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_HORARIO_DOCENTE] (?,?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, personal);
            cs.setString(5, turno);

            rs = cs.executeQuery();

            while (rs.next()) {
                
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("HORA_INI"));
                array.add(rs.getString("HORA_FIN"));
                array.add(rs.getString("LUNES"));
                array.add(rs.getString("MARTES"));
                array.add(rs.getString("MIERCOLES"));
                array.add(rs.getString("JUEVES"));
                array.add(rs.getString("VIERNES"));
                array.add(rs.getString("SABADO"));
                array.add(rs.getString("DOMINGO"));
                
                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<ArrayList<String>> horarioDocenteExamen(int institucion, String periodo, String carrera, String personal, String turno, String examen, String grupo) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_HORARIO_DOCENTE_EXAMEN] (?,?,?,?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, personal);
            cs.setString(5, turno);
            cs.setString(6, examen);
            cs.setString(7, grupo);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("HORA_INI"));
                array.add(rs.getString("HORA_FIN"));
                array.add(rs.getString("LUNES"));
                array.add(rs.getString("MARTES"));
                array.add(rs.getString("MIERCOLES"));
                array.add(rs.getString("JUEVES"));
                array.add(rs.getString("VIERNES"));
                array.add(rs.getString("SABADO"));
                array.add(rs.getString("DOMINGO"));

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<ArrayList<String>> horarioCiclo(int institucion, int periodo, String carrera, String ciclo, String turno,String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_HORARIO_CICLO] (?,?,?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, ciclo);
            cs.setString(5, turno);
            cs.setString(6, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {
                
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("HORA_INI"));
                array.add(rs.getString("HORA_FIN"));
                array.add(rs.getString("LUNES"));
                array.add(rs.getString("MARTES"));
                array.add(rs.getString("MIERCOLES"));
                array.add(rs.getString("JUEVES"));
                array.add(rs.getString("VIERNES"));
                array.add(rs.getString("SABADO"));
                array.add(rs.getString("DOMINGO"));

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
     public String insertarSeccion(seccionC item) {
     
        Connection c ;
        CallableStatement cs;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_SECCION(?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getSeccion());
            cs.setString(3, item.getDescripcion());
            cs.setInt(4, item.getEstadoRegistro());

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
