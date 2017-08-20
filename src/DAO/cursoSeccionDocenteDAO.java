
package DAO;

import Beans.cargaElectivaPersonal;

import ENTIDAD.cursoSeccionDocenteC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;




public class cursoSeccionDocenteDAO extends Conexiones.Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<cursoSeccionDocenteC> mostrarCursoSeccionDocente(int institucion, String periodo, String personal) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item;
        List<cursoSeccionDocenteC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION_DOCENTE WHERE INSTITUCION=? AND PERIODO=? AND  PERSONAL like ? AND ESTADO_REGISTRO IN(1,59)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return lista;
    }
	
	
	
    
    
    public List<cursoSeccionDocenteC> mostrarCursoSeccionDocentePrincipal(String institucion, String periodo,String carrera,String malla,String curso,String seccion, String personal) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item;
        List<cursoSeccionDocenteC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HOR_CURSO_SECCION_DOCENTE(1,?,?,?,?,?,?,?)}");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                System.out.println(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());

        }
        return lista;
    }
    public List<cursoSeccionDocenteC> mostrarCursoSeccionDocenteSecundario(int institucion, String periodo,String carrera,String malla,String curso,String seccion, String personal) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item;
        List<cursoSeccionDocenteC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HOR_CURSO_SECCION_DOCENTE(2,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());

        }
        return lista;
    }
    
    public String eliminar(cursoSeccionDocenteC item) {
        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM SIGU.HOR_CURSO_SECCION_DOCENTE WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=?  AND CURSO=? AND SECCION=? AND GRUPO=? AND PERSONAL=?");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getGrupo());
            cs.setString(8, item.getPersonal());
      
            rpta = cs.executeUpdate() >= 1;
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
        return codigo;
    }
    

    public List<cursoSeccionDocenteC> mostrarCursoSeccionDocente(String institucion, String periodo, String personal, String estado) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item;
        List<cursoSeccionDocenteC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION_DOCENTE WHERE INSTITUCION like ? AND PERIODO LIKE   ? AND  PERSONAL=? AND ESTADO_REGISTRO in(1,59)");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	  System.out.println(e.getMessage());

        }
        return lista;
    }

    public List<cursoSeccionDocenteC> mostrarCursoSeccionAlumno(int institucion, String periodo, String alumno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        List<cursoSeccionDocenteC> lista = new ArrayList<>();
        cursoSeccionDocenteC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select C.*from sigu.ACA_ALUMNO_PERIODO A,SIGU.ACA_ALUMNO_CURSO_SECCION B,SIGU.HOR_CURSO_SECCION_DOCENTE C WHERE A.INSTITUCION=B.INSTITUCION AND A.PERIODO=B.PERIODO AND A.ALUMNO=B.ALUMNO AND A.CARRERA=B.CARRERA AND A.MALLA=B.MALLA AND A.ESTADO_REGISTRO=5 AND A.INSTITUCION=C.INSTITUCION AND A.PERIODO=C.PERIODO AND A.MALLA=C.MALLA AND A.CARRERA=C.CARRERA AND B.SECCION=C.SECCION AND B.CURSO=C.CURSO AND C.ESTADO_REGISTRO=1 AND a.INSTITUCION=? AND A.PERIODO=? AND  A.ALUMNO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           System.out.println(e.getMessage());

        }
        return lista;
    }

    public cursoSeccionDocenteC mostrarPeriodoCarreraCursoSeccion(int institucion, String periodo, String carrera, String curso, String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION_DOCENTE WHERE INSTITUCION=? AND PERIODO=? AND  CARRERA=? AND CURSO=? AND  SECCION like ? AND ESTADO_REGISTRO in(1,59)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	  System.out.println(e.getMessage());

        }
        return item;
    }

    public List<cursoSeccionDocenteC> mostrarListaCursoSeccionDocente(int institucion, String periodo, String malla, String carrera, String curso, String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item = null;
        List<cursoSeccionDocenteC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION_DOCENTE WHERE INSTITUCION=? AND PERIODO=? AND  CARRERA=? AND CURSO=? AND  SECCION like ? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	  System.out.println(e.getMessage());

        }
        return lista;
    }

    public cursoSeccionDocenteC mostrarPeriodoCarreraCursoSeccion(int institucion, String periodo, String malla, String carrera, String curso, String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION_DOCENTE WHERE INSTITUCION=? AND PERIODO=? AND MALLA=? AND  CARRERA=? AND CURSO=? AND  SECCION=? AND ESTADO_REGISTRO IN(1,59)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	  System.out.println(e.getMessage());

        }
        return item;
    }

    public cursoSeccionDocenteC mostrarPeriodoCarreraCursoSeccion(int institucion, String periodo, String malla, String carrera, String curso, String seccion, String personal) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        cursoSeccionDocenteC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION_DOCENTE WHERE INSTITUCION=? AND PERIODO=? AND MALLA=? AND  CARRERA=? AND CURSO=? AND  SECCION=? AND PERSONAL=? AND ESTADO_REGISTRO IN(1,59)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.setPersonal(rs.getString("PERSONAL"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	  System.out.println(e.getMessage());

        }
        return item;
    }

    public String insertar(cursoSeccionDocenteC item) {

        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";

       

        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_CURSO_SECCION_DOCENTE(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("CARRERA", item.getCarrera());
            cs.setString("MALLA", item.getMalla());
            cs.setString("CURSO", item.getCurso());
            cs.setString("SECCION", item.getSeccion());
            cs.setString("GRUPO", item.getGrupo());
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("HORASTEORIA", item.getHora_teoria());
            cs.setInt("HORASPRACTICA", item.getHora_practica());
            cs.setBoolean("PRINCIAL", true);
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
            rpta = cs.executeUpdate() >= 1;
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
        return codigo;
    }

    //-------- CARGA ELECTIVA DOCENTE ----------------------------
    public List<cargaElectivaPersonal.detalleCargaElectiva> mostrarCargaElectiva(int institucion, String periodo, String carrera, String persona) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        List<cargaElectivaPersonal.detalleCargaElectiva> lista = new ArrayList<>();
        cargaElectivaPersonal.detalleCargaElectiva item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CARGA_ELECTIVA_PERSONAL (?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            cs.setString(3, carrera);
            cs.setString(4, persona);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cargaElectivaPersonal.detalleCargaElectiva();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));
                item.setTurno(rs.getInt("TURNO"));
                item.setDesTurno(rs.getString("DESTURNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setCredito(rs.getInt("CREDITOS"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setTipoCurso(rs.getInt("TIPO_CURSO"));
                item.setDesTipoCurso(rs.getString("DESTIPOCURSO"));

                item.setHorasPractica(rs.getInt("HORAS_PRACTICA"));
                item.setHorasTeoria(rs.getInt("HORAS_TEORIA"));
                item.setTotalHoras(rs.getInt("TOTAL"));

                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	  System.out.println(e.getMessage());

        }
        return lista;
    }

}
