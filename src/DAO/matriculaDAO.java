/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.fichaMatricula;
import Beans.matricula;
import Conexiones.Conexion;

import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.cursosC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import org.primefaces.context.RequestContext;


public class matriculaDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ArrayList<String>> mostrarMatriculaAlumno(int institucion, String periodo, String alumno) {

        List<ArrayList<String>> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" select A.CURSO,C.DESCRIPCION,A.SECCION,B.CREDITOS ,(CASE WHEN A.CONVALIDADO = 1 THEN 'SI' ELSE 'NO' END ) CONVALIDADO,D.VACANTES_MAXIMAS -\n" +
                                "SIGU.SF_VACANTES_CURSO(1, A.INSTITUCION, A.PERIODO, A.CARRERA, A.MALLA, A.CURSO, A.SECCION, A.ALUMNO, A.GRUPO) AS VACANTES \n" +
                                ",SIGU.SF_CURSO_REPRO(2, A.INSTITUCION, A.PERIODO, A.ALUMNO, A.CARRERA, A.CURSO) AS VECES,A.GRUPO,E.DESCRIPCION DESSECCION\n" +
                                "from [SIGU].[ACA_ALUMNO_CURSO_SECCION] A ,[SIGU].[HOR_MALLA_CURRICULAR_CURSO_ACT] B,[UPA].[ACA_CURSOS] C,SIGU.HOR_CURSO_SECCION D,SIGU.HOR_SECCION E\n" +
                                "where A.INSTITUCION=B.INSTITUCION AND A.MALLA=B.MALLA AND A.CARRERA=B.CARRERA AND A.CURSO=B.CURSO\n" +
                                "AND A.CURSO=C.CURSO\n" +
                                "AND A.INSTITUCION=D.INSTITUCION AND A.PERIODO=D.PERIODO AND A.MALLA=D.MALLA AND A.CARRERA=D.CARRERA AND  A.SECCION=D.SECCION AND A.CURSO=D.CURSO AND A.GRUPO=D.GRUPO\n" +
                                "AND E.INSTITUCION=A.INSTITUCION AND E.SECCION=A.SECCION\n" +
                                "AND A.INSTITUCION=? AND A.PERIODO=? and A.ALUMNO=? ORDER BY A.CONVALIDADO ,A.CURSO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("CURSO"));
                array.add(rs.getString("DESCRIPCION"));
                array.add(rs.getString("SECCION")); //seccion
                array.add(rs.getString("CREDITOS"));
                array.add(rs.getString("CONVALIDADO"));
                array.add(rs.getString("VACANTES"));
                array.add(rs.getString("VECES"));
                array.add(rs.getString("GRUPO"));
                array.add(rs.getString("DESSECCION"));
                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error " + e.getMessage());
        }
        return lista;
    }

    public List<matricula.tablaCursos> mostrarCursosAlumno(int institucion, String periodo, String malla, String carrera, String alumno) {

        List<matricula.tablaCursos> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        matricula.tablaCursos item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.MATRICULA_CURSOS_ALUMNO (?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new matricula.tablaCursos();
                item.setCurso(rs.getString("CURSO"));
                item.setDescurso(rs.getString("DESCURSO"));
                item.setSeccion("");
                item.setCreditos(rs.getInt("CREDITOS"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error " + e.getMessage());
        }

        return lista;
    }

    public List<fichaMatricula.detalle> mostrarMatriculaAlumnos(int institucion, String periodo, String alumno) {

        List<fichaMatricula.detalle> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        fichaMatricula.detalle item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_FICHA_MATRICULA_ALUMNO(1,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new fichaMatricula.detalle();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setCreditos(rs.getInt("CREDITOS"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));
                item.setTurno(rs.getInt("TURNO"));
                item.setDesTurno(rs.getString("DESTURNO"));
                item.setCiclo(rs.getInt("NIVEL_MODULAR"));
                item.setFechaInicio(rs.getString("FEC_CLASES_INI"));
                item.setFechaFin(rs.getString("FEC_CLASES_FIN"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setHoraPractica(rs.getInt("HORAS_PRACTICA"));
                item.setHoraTeoria(rs.getInt("HORAS_TEORIA"));
                item.setRuta(rs.getString("RUTA"));
                item.setSilabu(rs.getString("SILABUS"));
                item.setFormula(rs.getInt("FORMULA"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error " + e.getMessage());
        }

        return lista;
    }

    public List<cursosC> mostrarCursosAlumnoExtra(int institucion, String periodo, String malla, String carrera, String alumno) {

        List<cursosC> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursosC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT c.* FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_MALLA_CURRICULAR_CURSO_ACT B,UPA.ACA_CURSOS C\n"
                    + "	WHERE A.INSTITUCION=A.INSTITUCION \n"
                    + "	AND A.MALLA=B.MALLA	\n"
                    + "	AND A.CARRERA=B.CARRERA\n"
                    + "	AND A.CURSO=B.CURSO\n"
                    + "	AND A.CURSO=C.CURSO\n"
                    + "	AND A.INSTITUCION=?\n"
                    + "	AND A.PERIODO=?\n"
                    + "	AND A.MALLA=?\n"
                    + "	AND A.CARRERA=?\n"
                    + "	AND NOT EXISTS(\n"
                    + "		SELECT D.* FROM SIGU.ACA_ALUMNO_CURSO_SECCION D \n"
                    + "		WHERE D.ALUMNO=? AND D.CARRERA=A.CARRERA \n"
                    + "		AND D.MALLA=A.MALLA	AND D.CURSO=A.CURSO AND (D.PROMEDIO>10 OR D.PROMEDIO IS NULL)	\n"
                    + "	)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cursosC();
                item.setCurso(rs.getString("CURSO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura("ABREVIATURA");
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            //e.printStackTrace();
            util.consolaCliente( "error " + e.getMessage());
        }

        return lista;
    }

    public String eliminarCursoMatricula(int institucion, String periodo, String alumno, String curso) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        String msg="";
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  di.sp_eliminar_matricula(?,?, ?, ?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            cs.setString(4, curso);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	msg=e.getMessage();
        	System.out.println(e.getMessage());
        	
        }
        return msg;
    }

    public boolean insertarPensiones(int institucion, String periodo, String alumno) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_CREAR_PENSIONES  (?, ? ,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);

            rpta = cs.executeUpdate() >= 1 ;

            if (rpta) {
                c.commit();

            } else {

                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

    public alumnoPeriodoC ultimaMatricula(int institucion, String usuario) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoPeriodoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT*from sigu.ACA_ALUMNO_PERIODO where ALUMNO=? and INSTITUCION=?\n"
                    + "and PERIODO=(select MAX(periodo) from sigu.ACA_ALUMNO_PERIODO where ALUMNO=? and INSTITUCION=? )");
            cs.setString(1, usuario);
            cs.setInt(2, institucion);
            cs.setString(3, usuario);
            cs.setInt(4, institucion);

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new alumnoPeriodoC();
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setSeccion_referencial(rs.getString("SECCION_REFERENCIAL"));
                item.setNivel_referencial(rs.getInt("NIVEL_REFERENCIAL"));
                item.setCursos_matriculados(rs.getInt("CURSOS_MATRICULADOS"));
                item.setCursos_aprobados(rs.getInt("CURSOS_APROBADOS"));
                item.setMalla(rs.getString("MALLA"));
                item.setCategoria(rs.getString("CATEGORIA"));
                item.setAut_doc_matricula(rs.getString("AUT_DOC_MATRICULA"));
                item.setAut_obs_matricula(rs.getString("AUT_OBS_MATRICULA"));
                item.setPromedio_semestral(rs.getDouble("PROMEDIO_SEMESTRAL"));
                item.setPromedio_ponderado(rs.getDouble("PROMEDIO_PONDERADO"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
           
        }
        return item;
    }

    public alumnoPeriodoC alumnoMatricula(int institucion, String periodo, String usuario) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoPeriodoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * from sigu.ACA_ALUMNO_PERIODO where institucion=? and  periodo=? AND ALUMNO=?  ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, usuario);

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new alumnoPeriodoC();
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setSeccion_referencial(rs.getString("SECCION_REFERENCIAL"));
                item.setCursos_matriculados(rs.getInt("CURSOS_MATRICULADOS"));
                item.setCursos_aprobados(rs.getInt("CURSOS_APROBADOS"));
                item.setCreditos_matriculados(rs.getInt("CREDITOS_MATRICULADOS"));
                item.setMalla(rs.getString("MALLA"));
                item.setCategoria(rs.getString("CATEGORIA"));
                item.setPromedio_semestral(rs.getDouble("PROMEDIO_SEMESTRAL"));
                item.setPromedio_ponderado(rs.getDouble("PROMEDIO_PONDERADO"));

                item.setAut_doc_matricula(rs.getString("AUT_DOC_MATRICULA"));
                item.setAut_obs_matricula(rs.getString("AUT_OBS_MATRICULA"));
                item.setPromedio_ponderado(rs.getDouble("PROMEDIO_PONDERADO"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.print(e.getMessage());
            //e.printStackTrace();
        }
        return item;
    }

    public boolean insertarMatriculaFinal(int institucion, String periodo, String alumno, int estado) {
        Connection c ;
        CallableStatement cs;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_INSERTAR_MATRICULA_FINAL (?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            cs.setInt(4, estado);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

    public boolean insertarAlumnoInstitucion(int institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_INSERTA_ALUMNO_INSTITUCION (?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);

            rpta = cs.executeUpdate() >= 1 ;
            if (rpta) {

                c.commit();
            } else {

                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

    public boolean InsertaMatricula(int institucion, String periodo, String malla, String carrera, String alumno, int tcurso, int tcredito, List<matricula.tablaCursos> cursos) {
        Connection c = null;
        CallableStatement insertcab ;
        CallableStatement insertdet ;
        boolean rp = true;
        try {
            c = ConexionWeb();
            c.setAutoCommit(false);
            insertcab = c.prepareCall("{CALL DI.SP_INSERTAR_MATRICULA (?,?,?,?,?,?,?) }");
            insertcab.setInt(1, institucion);
            insertcab.setString(2, periodo);
            insertcab.setString(3, alumno);
            insertcab.setString(4, carrera);
            insertcab.setString(5, malla);
            insertcab.setInt(6, tcurso);
            insertcab.setInt(7, tcredito);
            insertcab.executeUpdate();

            insertdet = c.prepareCall("insert into SIGU.ACA_ALUMNO_CURSO_SECCION\n"
                    + "(INSTITUCION,PERIODO,CARRERA,MALLA ,CURSO,SECCION, ALUMNO,GRUPO)\n"
                    + "values(?,?,?,?,?,?,?,?)");

            for (matricula.tablaCursos item : cursos) {

                if (!item.getSeccion().isEmpty()) {

                    insertdet.setInt(1, institucion);
                    insertdet.setString(2, periodo);
                    insertdet.setString(3, carrera);
                    insertdet.setString(4, malla);
                    insertdet.setString(5, item.getCurso());
                    insertdet.setString(6, item.getSeccion().substring(0, item.getSeccion().length() - 3));
                    insertdet.setString(7, alumno);
                    insertdet.setString(8, item.getGrupo());
                    insertdet.executeUpdate();
                }

            }
            c.commit();

            cerrarConexion(c);
        } catch (SQLException e) {
            if (c != null) {
                try {
                    deshacerCambios(c);
                    rp = false;
                } catch (Exception ex) {
                    //util.consolaCliente( ex.getMessage());
                    RequestContext.getCurrentInstance().execute("alert('1 SELECCIONAR LA SECCION  \\n \\n " + ex.getMessage() + "')");
                }

            }
            // util.consolaCliente( e.getMessage());
            RequestContext.getCurrentInstance().execute("alert('2 SELECCIONAR LA SECCION \\n \\n" + e.getMessage() + "')");

        }
        return rp;
    }

}
