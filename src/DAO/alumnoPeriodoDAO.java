
package DAO;

import Beans.misNotas;
import Beans.rankingAlumno;
import Beans.util;
import Conexiones.Conexion;




import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.personaC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class alumnoPeriodoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	   public String insertar(alumnoPeriodoC item) {
	        
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO(?,?,?,?,?)}");
	            cs.setInt("ITEMWEB", 1);
	            cs.setInt("INSTITUCION", item.getInstitucion());
	            cs.setString("ALUMNO", item.getPeriodo());
	            cs.setString("PERSONA", item.getAlumno());
	            cs.setString("CARRERA", item.getCarrera());
	            cs.setString("MALLA", item.getMalla());
	            cs.setInt("CURSOS_MATRICULADOS", item.getCursos_matriculados());
	            
	            cs.setInt("CURSOS_APROBADOS", item.getCursos_aprobados());
	            cs.setInt("CURSOS_CONVALIDADOS", item.getCursos_convalidados());
	            cs.setInt("CURSOS_RETIRADOS", item.getCursos_retirados());
	            cs.setInt("CREDITOS_MATRICULADOS", item.getCreditos_matriculados());
	            cs.setInt("CREDITOS_APROBADOS", item.getCreditos_aprobados());
	            cs.setInt("CREDITOS_CONVALIDADOS", item.getCreditos_convalidados());
	            cs.setInt("CREDITOS_RETIRADOS", item.getCreditos_retirados());
	            cs.setString("SECCION_REFERENCIAL", item.getSeccion_referencial());
	            cs.setInt("NIVEL_REFERENCIAL", item.getNivel_referencial());
	            cs.setDouble("PROMEDIO_SEMESTRAL", item.getPromedio_semestral());
	            
	            cs.setInt("ORDEN_SEMESTRAL", item.getOrden_semestral());
	            cs.setInt("ORDEN_NIVEL", item.getOrden_nivel());
	            cs.setDouble("PROMEDIO_PONDERADO", item.getPromedio_ponderado());
	            cs.setInt("ORDEN_PONDERADO", item.getOrden_ponderado());
	            cs.setString("DOCUMENTO_AUTORIZA", item.getDocumento_autoriza());
	            cs.setString("RESOLUCION_NUMERO", item.getResolucion_numero());
	            cs.setString("RESOLUCION_FECHA", util.convertDate(item.getResolucion_fecha(), "dd-MM-yyyy"));
	            cs.setString("CODIGO_RESERVA", item.getCodigo_reserva());
	            cs.setString("OBSERVACION", item.getObservacion());
	            cs.setString("FECHA_MATRICULA", util.convertDate(item.getFecha_matricula(), "dd-MM-yyyy") );
	            cs.setString("CATEGORIA", item.getCategoria());
	            cs.setString("DESCUENTO", item.getDescuento());
	            cs.setDouble("MONTO_BASE", item.getMonto_base());
	            cs.setDouble("MONTO_PAGO", item.getMonto_pago());
	            cs.setDouble("MONTO_PAGO_ADC", item.getMonto_pago_adc());
	            cs.setDouble("MONTO_ADICIONAL", item.getMonto_adicinal());
	            cs.setString("AUT_PER_MATRICULA", item.getAut_per_matricula());
	            cs.setString("AUT_DOC_MATRICULA", item.getAut_doc_matricula());
	            cs.setString("AUT_OBS_MATRICULA", item.getAut_obs_matricula());
	            
	            cs.setString("AUT_PER_MALLA", item.getAut_per_malla());
	            cs.setString("AUT_PER_MALLA", item.getAut_doc_malla());
	            cs.setString("AUT_PER_MALLA", item.getAut_obs_malla());
	            
	            cs.setString("AUT_PER_CARRERA", item.getAut_per_carrera());
	            cs.setString("AUT_PER_CARRERA", item.getAut_doc_carrera());
	            cs.setString("AUT_PER_CARRERA", item.getAut_obs_carrera());
	            
	            cs.setString("AUT_PER_CARRERA", item.getAut_per_categoria());
	            cs.setString("AUT_PER_CARRERA", item.getAut_doc_categoria());
	            cs.setString("AUT_PER_CARRERA", item.getAut_obs_categoria());
	         
	            
	            cs.setInt("ESTADO_REGISTRO", item.getEstado_registro());

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
	
	
	   public String eliminar(alumnoPeriodoC item) {
	        
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta ;
	        String codigo = "";
	        try {
	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO(?,?,?,?,?)}");
	            cs.setInt("ITEMWEB", 2);
	            cs.setInt("INSTITUCION", item.getInstitucion());
	            cs.setString("ALUMNO", item.getPeriodo());
	            cs.setString("PERSONA", item.getAlumno());
	            cs.setString("CARRERA", item.getCarrera());
	            cs.setString("MALLA", item.getMalla());
	            cs.setInt("CURSOS_MATRICULADOS", item.getCursos_matriculados());	            
	            cs.setInt("CURSOS_APROBADOS", item.getCursos_aprobados());
	            cs.setInt("CURSOS_CONVALIDADOS", item.getCursos_convalidados());
	            cs.setInt("CURSOS_RETIRADOS", item.getCursos_retirados());
	            cs.setInt("CREDITOS_MATRICULADOS", item.getCreditos_matriculados());
	            cs.setInt("CREDITOS_APROBADOS", item.getCreditos_aprobados());
	            cs.setInt("CREDITOS_CONVALIDADOS", item.getCreditos_convalidados());
	            cs.setInt("CREDITOS_RETIRADOS", item.getCreditos_retirados());
	            cs.setString("SECCION_REFERENCIAL", item.getSeccion_referencial());
	            cs.setInt("NIVEL_REFERENCIAL", item.getNivel_referencial());
	            cs.setDouble("PROMEDIO_SEMESTRAL", item.getPromedio_semestral());	            
	            cs.setInt("ORDEN_SEMESTRAL", item.getOrden_semestral());
	            cs.setInt("ORDEN_NIVEL", item.getOrden_nivel());
	            cs.setDouble("PROMEDIO_PONDERADO", item.getPromedio_ponderado());
	            cs.setInt("ORDEN_PONDERADO", item.getOrden_ponderado());
	            cs.setString("DOCUMENTO_AUTORIZA", item.getDocumento_autoriza());
	            cs.setString("RESOLUCION_NUMERO", item.getResolucion_numero());
	            cs.setString("RESOLUCION_FECHA", util.convertDate(item.getResolucion_fecha(), "dd-MM-yyyy"));
	            cs.setString("CODIGO_RESERVA", item.getCodigo_reserva());
	            cs.setString("OBSERVACION", item.getObservacion());
	            cs.setString("FECHA_MATRICULA", util.convertDate(item.getFecha_matricula(), "dd-MM-yyyy") );
	            cs.setString("CATEGORIA", item.getCategoria());
	            cs.setString("DESCUENTO", item.getDescuento());
	            cs.setDouble("MONTO_BASE", item.getMonto_base());
	            cs.setDouble("MONTO_PAGO", item.getMonto_pago());
	            cs.setDouble("MONTO_PAGO_ADC", item.getMonto_pago_adc());
	            cs.setDouble("MONTO_ADICIONAL", item.getMonto_adicinal());
	            cs.setString("AUT_PER_MATRICULA", item.getAut_per_matricula());
	            cs.setString("AUT_DOC_MATRICULA", item.getAut_doc_matricula());
	            cs.setString("AUT_OBS_MATRICULA", item.getAut_obs_matricula());	            
	            cs.setString("AUT_PER_MALLA", item.getAut_per_malla());
	            cs.setString("AUT_PER_MALLA", item.getAut_doc_malla());
	            cs.setString("AUT_PER_MALLA", item.getAut_obs_malla());	            
	            cs.setString("AUT_PER_CARRERA", item.getAut_per_carrera());
	            cs.setString("AUT_PER_CARRERA", item.getAut_doc_carrera());
	            cs.setString("AUT_PER_CARRERA", item.getAut_obs_carrera());	            
	            cs.setString("AUT_PER_CARRERA", item.getAut_per_categoria());
	            cs.setString("AUT_PER_CARRERA", item.getAut_doc_categoria());
	            cs.setString("AUT_PER_CARRERA", item.getAut_obs_categoria());  
	            cs.setInt("ESTADO_REGISTRO", item.getEstado_registro());

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
	
	   public alumnoPeriodoC mostrarAlumnoPeriodo(int institucion, String periodo, String carrera, String malla ,String alumno) {
	        Connection c ;
	        CallableStatement cs ;
	        ResultSet rs ;	      
	        alumnoPeriodoC item=null ;
	        try {

	            c = ConexionWeb();
	            cs = c.prepareCall("SELECT *FROM  SIGU.ACA_ALUMNO_PERIODO where INSTITUCION=? and PERIODO=? AND CARRERA=? AND MALLA=? AND ALUMNO=?");
	            cs.setInt(1, institucion);
	            cs.setString(2, periodo);
	            cs.setString(3, carrera);
	            cs.setString(4, malla);
	            cs.setString(5, alumno);
	            rs = cs.executeQuery();

	            while (rs.next()) {
	                item = new alumnoPeriodoC();
	   	       
	                
	                item.setInstitucion(rs.getInt("INSTITUCION"));
		             item.setPeriodo(rs.getString("PERIODO"));
		             item.setAlumno(rs.getString("ALUMNO"));
		             item.setCarrera(rs.getString("CARRERA"));
		             item.setMalla(rs.getString("MALLA"));
		             item.setCursos_matriculados(rs.getInt("CURSOS_MATRICULADOS"));	            
		             item.setCursos_aprobados(rs.getInt("CURSOS_APROBADOS"));
		            
		             item.setCursos_convalidados(rs.getInt("CURSOS_CONVALIDADOS"));
		             item.setCursos_retirados(rs.getInt("CURSOS_RETIRADOS"));
		             item.setCreditos_matriculados(rs.getInt("CREDITOS_MATRICULADOS"));
		             item.setCreditos_aprobados(rs.getInt("CREDITOS_APROBADOS"));
		             item.setCreditos_convalidados(rs.getInt("CREDITOS_CONVALIDADOS"));
		             item.setCreditos_retirados(rs.getInt("CREDITOS_RETIRADOS"));
		             item.setSeccion_referencial(rs.getString("SECCION_REFERENCIAL"));
		             item.setNivel_referencial(rs.getInt("NIVEL_REFERENCIAL"));
		             item.setPromedio_semestral(rs.getDouble("PROMEDIO_SEMESTRAL"));	            
		             item.setOrden_semestral(rs.getInt("ORDEN_SEMESTRAL"));
		             item.setOrden_nivel(rs.getInt("ORDEN_NIVEL"));
		             item.setPromedio_ponderado(rs.getDouble("PROMEDIO_PONDERADO"));
		             item.setOrden_ponderado(rs.getInt("ORDEN_PONDERADO"));
		             item.setDocumento_autoriza(rs.getString("DOCUMENTO_AUTORIZA"));
		             item.setResolucion_numero(rs.getString("RESOLUCION_NUMERO"));
		             item.setResolucion_fecha(rs.getDate("RESOLUCION_FECHA")) ; 
		             item.setCodigo_reserva(rs.getString("CODIGO_RESERVA"));
		             item.setObservacion(rs.getString("OBSERVACION"));
		             item.setFecha_matricula(rs.getDate("FECHA_MATRICULA"));
		             item.setCategoria(rs.getString("CATEGORIA"));
		             item.setDescuento(rs.getString("DESCUENTO"));
		             item.setMonto_base(rs.getDouble("MONTO_BASE"));
		             item.setMonto_pago(rs.getDouble("MONTO_PAGO"));
		             item.setMonto_pago_adc(rs.getDouble("MONTO_PAGO_ADC"));
		             item.setMonto_adicinal(rs.getDouble("MONTO_ADICIONAL"));
		             item.setAut_per_matricula(rs.getString("AUT_PER_MATRICULA"));
		             item.setAut_doc_matricula(rs.getString("AUT_DOC_MATRICULA"));
		             item.setAut_obs_matricula(rs.getString("AUT_OBS_MATRICULA"));	            
		             item.setAut_per_malla(rs.getString("AUT_PER_MALLA"));
		             item.setAut_doc_malla(rs.getString("AUT_DOC_MALLA"));
		             item.setAut_obs_malla(rs.getString("AUT_OBS_MALLA"));	            
		             item.setAut_per_carrera(rs.getString("AUT_PER_CARRERA"));
		             item.setAut_doc_carrera(rs.getString("AUT_DOC_CARRERA"));
		             item.setAut_obs_carrera(rs.getString("AUT_OBS_CARRERA"));	            
		             item.setAut_per_categoria(rs.getString("AUT_PER_CATEGORIA"));
		             item.setAut_doc_categoria(rs.getString("AUT_DOC_CATEGORIA"));
		             item.setAut_obs_categoria(rs.getString("AUT_OBS_CATEGORIA"));  
		             item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

	            }
	            cerrarCall(cs);
	            cerrarConexion(c);

	        } catch (SQLException e) {
	            
	        	System.out.println(e.getMessage());
	        }
	        return item;
	    }
	
	public alumnoPeriodoC mostrarUltimaMatricula(int institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoPeriodoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select top 1*from SIGU.ACA_ALUMNO_PERIODO where INSTITUCION=? and ALUMNO=? and ESTADO_REGISTRO=5 order by PERIODO desc");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCursos_matriculados(rs.getInt("CURSOS_MATRICULADOS"));
                item.setCursos_aprobados(rs.getInt("CURSOS_APROBADOS"));
                item.setSeccion_referencial(rs.getString("SECCION_REFERENCIAL"));
                item.setNivel_referencial(rs.getInt("NIVEL_REFERENCIAL"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return item;
    }

    public List<alumnoPeriodoC> mostrarAlumnoPeriodo(int institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoPeriodoC> lista = new ArrayList<>();
        alumnoPeriodoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from SIGU.ACA_ALUMNO_PERIODO where INSTITUCION=? and ALUMNO=? and ESTADO_REGISTRO=5 order by PERIODO desc");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCursos_matriculados(rs.getInt("CURSOS_MATRICULADOS"));
                item.setCursos_aprobados(rs.getInt("CURSOS_APROBADOS"));
                item.setSeccion_referencial(rs.getString("SECCION_REFERENCIAL"));
                item.setNivel_referencial(rs.getInt("NIVEL_REFERENCIAL"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<alumnoPeriodoC> mostrarAlumnoPeriodo(int institucion, String periodo, String carrera, String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoPeriodoC> lista = new ArrayList<>();
        alumnoPeriodoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from SIGU.ACA_ALUMNO_PERIODO where INSTITUCION=? and PERIODO=? AND CARRERA=? AND SECCION_REFERENCIAL=? AND ESTADO_REGISTRO=5 order by PERIODO desc");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new alumnoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCursos_matriculados(rs.getInt("CURSOS_MATRICULADOS"));
                item.setCursos_aprobados(rs.getInt("CURSOS_APROBADOS"));
                item.setSeccion_referencial(rs.getString("SECCION_REFERENCIAL"));
                item.setNivel_referencial(rs.getInt("NIVEL_REFERENCIAL"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public List<misNotas.detallePromedioPonderado> mostrarAlumnoPeriodoPromedioPonderado(int institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<misNotas.detallePromedioPonderado> lista = new ArrayList<>();
        misNotas.detallePromedioPonderado item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_PROMEDIOS_PONDERADOS(?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new misNotas.detallePromedioPonderado();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDesPeriodo(rs.getString("DESPERIODO"));
                item.setPromedioGeneral(rs.getString("PROMEDIO_GENERAL"));
                item.setAlumno(rs.getString("ALUMNO"));
               
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    public alumnoPeriodoC mostrarPeriodoAlumno(int institucion,String periodo, String alumno) {
        

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoPeriodoC item=null ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO_PERIODO WHERE INSTITUCION=? AND PERIODO=? AND ALUMNO=? AND ESTADO_REGISTRO=5");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new alumnoPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCursos_matriculados(rs.getInt("CURSOS_MATRICULADOS"));
                item.setCursos_aprobados(rs.getInt("CURSOS_APROBADOS"));
                item.setSeccion_referencial(rs.getString("SECCION_REFERENCIAL"));
                item.setNivel_referencial(rs.getInt("NIVEL_REFERENCIAL"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
              

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return item;
    }
    
    
    public List<rankingAlumno.detalleRankingC> mostrarAlumnoPeriodoRanking(int institucion, String periodo,String carrera,String malla,String alumno ) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<rankingAlumno.detalleRankingC> lista = new ArrayList<>();
        rankingAlumno.detalleRankingC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ALUMNO_PERIODO(?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("INSTITUCION", institucion);
            cs.setString("PERIODO", periodo);
            cs.setString("CARRERA", carrera);
            cs.setString("MALLA", malla);
            cs.setString("ALUMNO", alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new rankingAlumno.detalleRankingC();
                item.setAlumnoPeriodo(new alumnoPeriodoC(rs.getInt("INSTITUCION"), rs.getString("PERIODO"), rs.getString("ALUMNO"), rs.getString("CARRERA"), rs.getString("MALLA"), rs.getInt("CURSOS_MATRICULADOS"), rs.getInt("CURSOS_APROBADOS"), rs.getInt("CREDITOS_MATRICULADOS"), rs.getInt("NIVEL_REFERENCIAL"), rs.getString("SECCION_REFERENCIAL"), null, rs.getDouble("PROMEDIO_SEMESTRAL"), rs.getDouble("PROMEDIO_PONDERADO"), null, null, null, 1));
                item.setPersona(new personaC());
                item.getPersona().setPersona(rs.getString("PERSONA"));
                item.getPersona().setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.getPersona().setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.getPersona().setNombres(rs.getString("NOMBRES"));
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
