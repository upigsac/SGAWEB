package DAO;



import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.constanciaMatricula;
import Beans.util;
import Conexiones.Conexion;
import ENTIDAD.constanciaMatriculaC;



public class constanciaMatriculaDAO extends Conexion implements Serializable {

	private static final long serialVersionUID = 1L;

	public String insertar(constanciaMatriculaC item) {
	     
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CONSTANCIA_MATRICULA(1,?,?,?,?,?,?)}");
	            cs.setString(1, item.getConstanciaMatricula());
	            cs.setInt(2, item.getInstitucion());
	            cs.setString(3, item.getCarrera());
	            cs.setString(4, item.getMalla());
	            cs.setString(5, item.getAlumno());
	            cs.setInt(6, item.getEstadoRegistro());

	            rpta = cs.executeUpdate() == 1 ;
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
	
	public String eliminar(constanciaMatriculaC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CONSTANCIA_MATRICULA(2,?,?,?,?,?,?)}");
            cs.setString(1, item.getConstanciaMatricula());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getAlumno());
            cs.setInt(6, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
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

	public List<constanciaMatricula.matriculaC> mostrarConstanciaMatricula(int institucion,String carrera,String despersona,String alumno) {

        List<constanciaMatricula.matriculaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        constanciaMatricula.matriculaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CONSTANCIA_MATRICULA(1,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, carrera);
            cs.setString(3, despersona);
            cs.setString(4, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new constanciaMatricula.matriculaC();
                item.getAlumno().setAlumno(rs.getString("ALUMNO"));
                item.getAlumnoCarrera().setMalla(rs.getString("MALLA"));
                item.getAlumnoCarrera().setPeriodoIngreso(rs.getString("PERIODO_INGRESO"));
                item.getPersona().setPersona(rs.getString("PERSONA"));
                item.getPersona().setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.getPersona().setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.getPersona().setNombres(rs.getString("NOMBRES"));
                item.getCarrera().setCarrera(rs.getString("CARRERA"));                
                item.getCarrera().setDescripcion(rs.getString("DESCARRERA"));
                item.getCarrera().setAbreviatura(rs.getString("ABREVIATURA"));
                item.getConstanciaMatricula().setConstanciaMatricula(rs.getString("CONSTANCIA"));
                item.getConstanciaMatricula().setInstitucion(rs.getInt("INSTITUCION"));
                item.getConstanciaMatricula().setCarrera(rs.getString("CARRERA"));
                item.getConstanciaMatricula().setMalla(rs.getString("MALLA"));
                item.getConstanciaMatricula().setAlumno(rs.getString("ALUMNO"));
                item.getConstanciaMatricula().setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
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
