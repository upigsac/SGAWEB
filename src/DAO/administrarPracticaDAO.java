
package DAO;

import Beans.administrarPractica;
import Beans.practicaCalificacion;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class administrarPracticaDAO {
    
    
    public List<administrarPractica.alumnoPractica> mostrarAlumnoPractica(String paterno,String materno,String nombres) {
        administrarPractica.alumnoPractica item ;
        List<administrarPractica.alumnoPractica> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL SP_FILTRO_PERSONA (1,'','','',?,?,?)}");            
            cs.setString(1, paterno);
            cs.setString(2, materno);
            cs.setString(3, nombres);
            rs = cs.executeQuery();
            while (rs.next()) {
                item=new administrarPractica.alumnoPractica();
                item.getAlumnoCursoSeccion().setInstitucion(rs.getInt("INSTITUCION"));
                item.getAlumnoCursoSeccion().setPeriodo(rs.getString("PERIODO"));
                item.getAlumnoCursoSeccion().setCarrera(rs.getString("CARRERA"));
                item.getAlumnoCursoSeccion().setMalla(rs.getString("MALLA"));
                item.getAlumnoCursoSeccion().setCurso(rs.getString("CURSO"));
                item.getCurso().setCurso(rs.getString("CURSO"));
                item.getCurso().setDescripcion(rs.getString("DESCURSO"));
               
                item.getAlumno().setAlumno(rs.getString("ALUMNO"));
                item.getPersona().setPersona(rs.getString("PERSONA"));
                item.getPersona().setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.getPersona().setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.getPersona().setNombres(rs.getString("NOMBRES"));
                
                item.getAlumnoCursoSeccion().setPromedio(rs.getDouble("PROMEDIO"));
                item.getPersona().setCelular(rs.getString("TELEFONO_CELULAR"));
                item.getPersona().setTelefono(rs.getString("TELEFONO"));
                item.getPersona().setEmailP(rs.getString("EMAIL_PRINCIPAL"));
                item.getPeriodoSeccion().setSeccion(rs.getString("SECCION"));
                item.getPeriodoSeccion().setFechaInicio(rs.getDate("FEC_CLASES_INI"));
                item.getPeriodoSeccion().setFechaFin(rs.getDate("FEC_CLASES_FIN"));
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
    
    public List<practicaCalificacion.alumnoCalificacionC> mostrarAlumnoPracticaCalificacion(String paterno,String materno,String nombres) {
        practicaCalificacion.alumnoCalificacionC item ;
        List<practicaCalificacion.alumnoCalificacionC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL SP_FILTRO_PERSONA (2,'','','',?,?,?)}");
            
            cs.setString(1, paterno);
            cs.setString(2, materno);
            cs.setString(3, nombres);
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new practicaCalificacion.alumnoCalificacionC();
                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("TELEFONO_CELULAR"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setFechaInicio(rs.getDate("FECHAINICIO"));
                item.setFechaFinal(rs.getDate("FECHAFINAL"));
                lista.add(item);
                
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
            
        }
        return lista;
    }
    
    
    public List<administrarPractica.cabeceraCronograma> mostrarCabeceraCronograma(String alumno,String curso,int cronograma) {
        administrarPractica.cabeceraCronograma item ;
        List<administrarPractica.cabeceraCronograma> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CRONOGRAMA_PRACTICA_ALUMNO (1,?,?,?)}");
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            cs.setInt(3, cronograma);
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new administrarPractica.cabeceraCronograma();
                item.setFecha(rs.getDate("FECHA"));
                item.setDia(rs.getInt("DIA"));
                
                
                
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
    public List<administrarPractica.fichaActividad> mostrarFichaActividad(int cronograma,String alumno,String curso,Date fecha) {
        administrarPractica.fichaActividad item ;
        List<administrarPractica.fichaActividad> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CRONOGRAMA_PRACTICA_ALUMNO(2,?,?,?,?)}");            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            cs.setInt(3, cronograma);
            cs.setString(4, util.convertDate(fecha, "dd-MM-yyyy"));
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new administrarPractica.fichaActividad();
                item.setCronogroma(rs.getInt("CRONOGRAMA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setDia(rs.getInt("DIA"));
                item.setFecha(rs.getDate("FECHA"));
                item.setHoraInicio(rs.getTimestamp("HORAINICIO"));
                item.setHoraFinal(rs.getTimestamp("HORAFINAL"));
                item.setActividad(rs.getString("ACTIVIDAD"));
                item.setAlto(rs.getInt("ALTO"));
                item.setDistancia(rs.getInt("DISTANCIA"));
                item.setAltoFicha(rs.getInt("ALTO_FICHA"));
                item.setDistanciaFicha(rs.getInt("DISTANCIA_FICHA"));
                
                
                
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        }
        return lista;
    }
    
    
     public String insertar(administrarPractica.fichaActividad item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_ACTIVIDAD(1,?,?,?,?,?,?,?,?,?,?)}");            
            cs.setInt(1, item.getCronogroma());
            cs.setString(2, item.getAlumno());
            cs.setString(3, item.getCurso());
            cs.setInt(4, item.getDia());            
            cs.setString(5, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString(6, util.convertDate(item.getFechaFin(), "dd-MM-yyyy"));
            cs.setString(7, util.convertDate(item.getHoraInicio(), "HH:mm"));
            cs.setString(8, util.convertDate(item.getHoraFinal(), "HH:mm"));
            cs.setString(9, item.getActividad());
            cs.setInt(10, item.getEstadoRegistro());

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
      public String eliminar(administrarPractica.fichaActividad item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_ACTIVIDAD(2,?,?,?,?,?,?,?,?,?,?)}");            
            cs.setInt(1, item.getCronogroma());
            cs.setString(2, item.getAlumno());
            cs.setString(3, item.getCurso());
            cs.setInt(4, item.getDia());            
            cs.setString(5, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString(6, util.convertDate(item.getFechaFin(), "dd-MM-yyyy"));
            cs.setString(7, util.convertDate(item.getHoraInicio(), "HH:mm"));
            cs.setString(8, util.convertDate(item.getHoraFinal(), "HH:mm"));
            cs.setString(9, item.getActividad());
            cs.setInt(10, item.getEstadoRegistro());

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
      
      public String editar(administrarPractica.fichaActividad item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_ACTIVIDAD(3,?,?,?,?,?,?,?,?,?,?)}");            
             cs.setInt(1, item.getCronogroma());
            cs.setString(2, item.getAlumno());
            cs.setString(3, item.getCurso());
            cs.setInt(4, item.getDia());            
            cs.setString(5, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString(6, util.convertDate(item.getFechaFin(), "dd-MM-yyyy"));
            cs.setString(7, util.convertDate(item.getHoraInicio(), "HH:mm"));
            cs.setString(8, util.convertDate(item.getHoraFinal(), "HH:mm"));
            cs.setString(9, item.getActividad());
            cs.setInt(10, item.getEstadoRegistro());

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
      
      
      public List<administrarPractica.alumnoPracticaCronograma> mostrarAlumnoPracticaCronograma(String alumno,String curso) {
        administrarPractica.alumnoPracticaCronograma item ;
        List<administrarPractica.alumnoPracticaCronograma> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.CRONOGRAMA,A.ALUMNO,A.CURSO,A.FECHAINICIO,A.FECHAFINAL,SUM(DATEDIFF(MI,B.HORAINICIO,B.HORAFINAL))MINUTO_PROYECTADO,SUM(DATEDIFF(MI,CASE WHEN   B.HORAENTRADA<B.HORAINICIO THEN B.HORAINICIO ELSE B.HORAENTRADA END,CASE WHEN  B.HORASALIDA>B.HORAFINAL THEN B.HORAFINAL ELSE B.HORASALIDA END))MINUTO_EJECUTADO,A.ESTADO_REGISTRO,C.ESTADO_REGISTRO CORTE,C.FUT,C.FECHA FECHACORTE,C.OBSERVACION FROM DI.ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA A LEFT JOIN DI.ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_ACTIVIDAD B ON B.CRONOGRAMA=A.CRONOGRAMA AND B.ALUMNO=A.ALUMNO AND B.CURSO=A.CURSO LEFT JOIN DI.ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_CORTE C ON C.ALUMNO=A.ALUMNO AND C.CURSO=A.CURSO AND C.CRONOGRAMA=A.CRONOGRAMA WHERE  A.ALUMNO=? AND A.CURSO=? GROUP BY A.CRONOGRAMA,A.ALUMNO,A.CURSO,A.FECHAINICIO,A.FECHAFINAL,A.ESTADO_REGISTRO,C.ESTADO_REGISTRO,C.FECHA,C.FUT,C.OBSERVACION ORDER BY 1");            
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new administrarPractica.alumnoPracticaCronograma();
                item.setCronograma(rs.getInt("CRONOGRAMA"));                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setFechaInicio(rs.getDate("FECHAINICIO"));
                item.setFechaFinal(rs.getDate("FECHAFINAL"));
                item.setMinutoProyectado(rs.getInt("MINUTO_PROYECTADO"));
                item.setMinutoEjecutado(rs.getInt("MINUTO_EJECUTADO"));
                item.setCorte(rs.getInt("CORTE")); 
                item.setFut(rs.getString("FUT"));
                item.setObervacion(rs.getString("OBSERVACION"));
                item.setFechaCorte(rs.getDate("FECHACORTE"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                lista.add(item);
                
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
            
        }
        return lista;
    }
}
