
package DAO;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.alumnoCertificacionC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;

public class alumnoCertificacionDAO implements Serializable {

   
	private static final long serialVersionUID = 1L;

	public boolean insertar(alumnoCertificacionC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CERTIFICACION(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setInt(8, item.getGrupo());
            cs.setString(9, util.convertDate(item.getFechaImpresion(), "dd-MM-yyyy") );
            cs.setString(10, util.convertDate(item.getFechaEntrega(),"dd-MM-yyyy") );
            cs.setInt(11, item.getItem());
            cs.setString(12, item.getLibro());
            cs.setString(13, item.getFolio());
            cs.setInt(14, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
	
	
	public boolean eliminar(alumnoCertificacionC item) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CERTIFICACION(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setInt(8, item.getGrupo());
            cs.setDate(9, (Date) item.getFechaImpresion());
            cs.setDate(10, (Date) item.getFechaEntrega());
            cs.setInt(11, item.getItem());
            cs.setInt(12, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

    public alumnoCertificacionC mostrarUltimoAlumnoCertificacion(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        alumnoCertificacionC item = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 1 *FROM DI.ACA_ALUMNO_CERTIFICACION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND ALUMNO=? ORDER BY ITEM DESC");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCertificacionC();

                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setFechaEntrega(rs.getDate("FECHA_ENTREGA"));
                item.setFechaImpresion(rs.getDate("FECHA_IMPRESION"));
                item.setItem(rs.getInt("ITEM"));
                item.setLibro(rs.getString("LIBRO"));
                item.setFolio(rs.getString("FOLIO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           
           System.out.println(e.getMessage());
        }

        return item;
    }

    public List<alumnoCertificacionC> mostrarAlumnoCertificacion(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<alumnoCertificacionC> lista = new ArrayList<>();
        alumnoCertificacionC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ACA_ALUMNO_CERTIFICACION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND ALUMNO=? ORDER BY ITEM DESC");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoCertificacionC();

                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setFechaEntrega(rs.getDate("FECHA_ENTREGA"));
                item.setFechaImpresion(rs.getDate("FECHA_IMPRESION"));
                item.setItem(rs.getInt("ITEM"));
                item.setLibro(rs.getString("LIBRO"));
                item.setFolio(rs.getString("FOLIO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }

}
