
package DAO;


import Beans.horarioExamen;
import Beans.horarioSemanal;

import Beans.util;
import ENTIDAD.horarioCuerpoC;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class horarioSemanalDAO {
    
    
	
	public List<horarioCuerpoC> mostrarHorarioPersonalDocente(String institucion,String periodo,int dia,Date fechaInicio,String personal ,String seccion,String aula) {
		 System.out.println( aula);
        List<horarioCuerpoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horarioCuerpoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HORARIO_SEMANAL (1,?,?,?,?,?,?,?)}");
            cs.setString(1, institucion);
            cs.setString(2, periodo);            
            cs.setInt(3, dia);
            cs.setString(4, util.convertDate(fechaInicio));
            cs.setString(5, personal);
            cs.setString(6, seccion);
            cs.setString(7, aula);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new horarioCuerpoC();
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAula(rs.getString("AULA"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setDesTipoClase(rs.getString("DESTIPOCLASE"));
                item.setHoraInicio(rs.getTimestamp("HOR_ING"));
                item.setHoraFinal(rs.getTimestamp("HOR_SAL"));
                item.setAlto(rs.getInt("ALTO"));
                item.setTop(rs.getInt("TOP"));                
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	  System.out.println(e.getMessage());
        }
        return lista;
    }
	public List<horarioCuerpoC> mostrarHorarioAlumno(String institucion,String periodo,int dia,Date fechaInicio,String alumno) {
        List<horarioCuerpoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horarioCuerpoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HORARIO_SEMANAL (2,?,?,?,?,'%','%','%',?)}");
            cs.setString(1, institucion);
            cs.setString(2, periodo);            
            cs.setInt(3, dia);
            cs.setString(4, util.convertDate(fechaInicio));
            cs.setString(5, alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
            	System.out.println(rs.getString("CURSO"));
                item = new horarioCuerpoC();
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAula(rs.getString("AULA"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setDesTipoClase(rs.getString("DESTIPOCLASE"));
                item.setHoraInicio(rs.getTimestamp("HOR_ING"));
                item.setHoraFinal(rs.getTimestamp("HOR_SAL"));
                item.setAlto(rs.getInt("ALTO"));
                item.setTop(rs.getInt("TOP"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	  System.out.println(e.getMessage());
        }
        return lista;
    }
	
	
    
    
    public List<horarioSemanal.detalleHorario> mostrarHorarioDocente(String institucion,String periodo,String personal,int dia,Date fechaInicio) {

        List<horarioSemanal.detalleHorario> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horarioSemanal.detalleHorario item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HORARIO_SEMANAL_DOCENTE (?,?,?,?,?)}");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            cs.setInt(4, dia);
            cs.setString(5, util.convertDate(fechaInicio));            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new horarioSemanal.detalleHorario();
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAula(rs.getString("AULA"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setDesTipoClase(rs.getString("DESTIPOCLASE"));
                item.setHoraInicio(rs.getTimestamp("HOR_ING"));
                item.setHoraFinal(rs.getTimestamp("HOR_SAL"));
                item.setAlto(rs.getInt("ALTO"));
                item.setTop(rs.getInt("TOP"));                
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	  System.out.println(e.getMessage());
        }
        return lista;
    }
    
    
    
    public List<horarioCuerpoC> mostrarHorarioAlumno(String institucion, String periodo,String alumno,int dia,Date fechaInicio) {

        List<horarioCuerpoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horarioCuerpoC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HORARIO_SEMANAL_ALUMNO (?,?,?,?,?)}");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            cs.setInt(4, dia);
            cs.setString(5, util.convertDate(fechaInicio));            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new horarioCuerpoC();
                item.setDesPersona(rs.getString("DESPERSONAL"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAula(rs.getString("AULA"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setDesTipoClase(rs.getString("DESTIPOCLASE"));
                item.setHoraInicio(rs.getTimestamp("HOR_ING"));
                item.setHoraFinal(rs.getTimestamp("HOR_SAL"));
                item.setAlto(rs.getInt("ALTO"));
                item.setTop(rs.getInt("TOP"));                
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    
    public List<horarioExamen.detalleHorario> mostrarHorarioExamen(int institucion,String periodo,String carrera,String seccion,String tipoExamen,int dia,Date fecha) {

        List<horarioExamen.detalleHorario> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horarioExamen.detalleHorario item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HORARIO_EXAMEN (?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            cs.setString(5, tipoExamen);
            
            cs.setInt(6, dia);
            cs.setString(7, util.convertDate(fecha, "dd-MM-yyyy"));
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new horarioExamen.detalleHorario();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setPersona(rs.getString("PERSONA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setDesPersonal(rs.getString("DESPERSONAL"));
                item.setDesPersonal(rs.getString("DESPERSONAL"));
                item.setMalla(rs.getString("MALLA"));              
                item.setCarrera(rs.getString("CARRERA"));              
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAula(rs.getString("AULA"));
                item.setTurno(rs.getInt("TURNO"));
                item.setTipoExamen(rs.getString("TIPO"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setFecha(rs.getDate("FECHA_EXAMEN"));
                item.setHoraInicio(rs.getTimestamp("HOR_ING"));
                item.setHoraFinal(rs.getTimestamp("HOR_SAL"));
                item.setAlto(rs.getInt("ALTO"));
                item.setTop(rs.getInt("TOP"));
                item.setDia(rs.getInt("DIA"));
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
