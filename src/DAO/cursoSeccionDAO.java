
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.cursoSeccionC;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class cursoSeccionDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public String insertar(cursoSeccionC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CURSO_SECCION(1,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getGrupo());
            cs.setInt(8, item.getVacantesMaximas());
            cs.setInt(9, item.getVacantesMinimas());
            cs.setInt(10, item.getVacantesActuales());
            cs.setInt(11, item.getTipoCertificado());
            cs.setInt(12, item.getEstadoRegistro());
            
           
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
	
	
public String eliminar(cursoSeccionC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CURSO_SECCION(2,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getGrupo());
            cs.setInt(8, item.getVacantesMaximas());
            cs.setInt(9, item.getVacantesMinimas());
            cs.setInt(10, item.getVacantesActuales());
            cs.setInt(11, item.getTipoCertificado());
            cs.setInt(12, item.getEstadoRegistro());
            
           
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
	
	
	
	
	

	public cursoSeccionC mostrarCursoSeccion(String institucion, String periodo, String carrera, String curso, String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionC item = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION WHERE INSTITUCION like ? AND PERIODO=? AND CARRERA=? AND CURSO = ? AND  SECCION=? AND ESTADO_REGISTRO=1");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setVacantesActuales(rs.getInt("VACANTES_ACTUALES"));
                item.setVacantesMaximas(rs.getInt("VACANTES_MAXIMAS"));
                item.setVacantesMinimas(rs.getInt("VACANTES_MINIMAS"));
                item.setTipoCertificado(rs.getInt("TIPO_CERTIFICADO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            e.printStackTrace();
        }
        return item;
    }

    public List<cursoSeccionC> mostrarCursoSeccion(int institucion, String periodo, String carrera, String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionC item ;
        List<cursoSeccionC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND   SECCION=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCurso(rs.getString("CURSO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setVacantesActuales(rs.getInt("VACANTES_ACTUALES"));
                item.setVacantesMaximas(rs.getInt("VACANTES_MAXIMAS"));
                item.setVacantesMinimas(rs.getInt("VACANTES_MINIMAS"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public List<cursoSeccionC> mostrarCursoSeccionTipo(String institucion, String periodo, String carrera, String tipo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionC item ;
        List<cursoSeccionC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT D.* FROM SIGU.HOR_MALLA_CURRICULAR_CURSO_ACT A ,SIGU.HOR_CURSO_PERIODO B ,SIGU.HOR_PERIODO_SECCION C,SIGU.HOR_CURSO_SECCION D\n"
                    + "WHERE A.INSTITUCION=B.INSTITUCION AND A.CARRERA=B.CARRERA AND A.MALLA=B.MALLA AND A.CURSO=B.CURSO \n"
                    + "AND B.INSTITUCION=C.INSTITUCION AND B.PERIODO=C.PERIODO AND B.CARRERA=C.CARRERA \n"
                    + "AND A.INSTITUCION=D.INSTITUCION AND B.PERIODO=D.PERIODO AND B.CARRERA=D.CARRERA AND B.CURSO=D.CURSO AND C.SECCION=D.SECCION\n"
                    + "AND B.INSTITUCION like ? AND B.PERIODO=? AND A.CARRERA LIKE ? AND A.TIPO_CURSO=?");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, tipo);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setVacantesActuales(rs.getInt("VACANTES_ACTUALES"));
                item.setVacantesMaximas(rs.getInt("VACANTES_MAXIMAS"));
                item.setVacantesMinimas(rs.getInt("VACANTES_MINIMAS"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
    public cursoSeccionC mostrarCursoSeccion(int institucion, String periodo, String carrera, String malla,String curso,String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionC item =null;
       
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setVacantesActuales(rs.getInt("VACANTES_ACTUALES"));
                item.setVacantesMaximas(rs.getInt("VACANTES_MAXIMAS"));
                item.setVacantesMinimas(rs.getInt("VACANTES_MINIMAS"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }

    public List<cursoSeccionC> mostrarCarreraSeccionTipo(String institucion, String periodo, String carrera, String tipo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionC item ;
        List<cursoSeccionC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT D.* FROM SIGU.HOR_MALLA_CURRICULAR_CURSO_ACT A ,SIGU.HOR_CURSO_PERIODO B ,SIGU.HOR_PERIODO_SECCION C,SIGU.HOR_CURSO_SECCION D\n"
                    + "WHERE A.INSTITUCION=B.INSTITUCION AND A.CARRERA=B.CARRERA AND A.MALLA=B.MALLA AND A.CURSO=B.CURSO \n"
                    + "AND B.INSTITUCION=C.INSTITUCION AND B.PERIODO=C.PERIODO AND B.CARRERA=C.CARRERA \n"
                    + "AND A.INSTITUCION=D.INSTITUCION AND B.PERIODO=D.PERIODO AND B.CARRERA=D.CARRERA AND B.CURSO=D.CURSO AND C.SECCION=D.SECCION\n"
                    + "AND B.INSTITUCION like ? AND B.PERIODO=? AND A.CARRERA LIKE ? AND A.TIPO_CURSO=?");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, tipo);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setVacantesActuales(rs.getInt("VACANTES_ACTUALES"));
                item.setVacantesMaximas(rs.getInt("VACANTES_MAXIMAS"));
                item.setVacantesMinimas(rs.getInt("VACANTES_MINIMAS"));
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
    
     public int mostrarTotalMatriculados(int institucion, String periodo, String carrera, String curso,String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        int totalMatriculados=0 ;
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT SUM(VACANTES_ACTUALES)MATRICULADOS FROM SIGU.HOR_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND CURSO=? AND SECCION LIKE ?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {                
                totalMatriculados=(rs.getInt("MATRICULADOS"));              
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return totalMatriculados;
    }
}
