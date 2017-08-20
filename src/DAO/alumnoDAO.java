
package DAO;

import Beans.certificaciones;
import Beans.registroEvaluacion;

import Conexiones.Conexion;

import ENTIDAD.AlumnoCons;
import ENTIDAD.alumnoC;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class alumnoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public String insertar(alumnoC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO(?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("ALUMNO", item.getAlumno());
            cs.setString("PERSONA", item.getPersona());
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());

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
    
 public String eliminar(alumnoC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO(?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("ALUMNO", item.getAlumno());
            cs.setString("PERSONA", item.getPersona());
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());

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




	

  

    public alumnoC mostrarAlumnoCodigo(String institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO WHERE INSTITUCION like  ? AND ALUMNO=? AND ESTADO_REGISTRO=1");
            cs.setString(1, institucion);
            cs.setString(2, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCodigoExterno(rs.getString("CODIGO_EXTERNO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return item;
    }
    
    
    public alumnoC mostrarAlumnoPersona(String persona) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 1*FROM SIGU.ACA_ALUMNO WHERE PERSONA=? AND ESTADO_REGISTRO=1");
            cs.setString(1, persona);            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCodigoExterno(rs.getString("CODIGO_EXTERNO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return item;
    }
    
    
    
    public List<alumnoC> mostrarAlumno( String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<alumnoC> lista=new ArrayList<>();
        alumnoC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO WHERE  ALUMNO=? AND ESTADO_REGISTRO=1");
            
            cs.setString(1, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCodigoExterno(rs.getString("CODIGO_EXTERNO"));
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

    

    public alumnoC mostrarAlumno(int institucion, String persona) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_ALUMNO WHERE INSTITUCION=? AND PERSONA=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCodigoExterno(rs.getString("CODIGO_EXTERNO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());

        }
        return item;
    }

    public List<alumnoC> mostrarAlumnoSinNota(int institucion, String periodo, String malla, String carrera, String seccion, String curso, String tipoNota) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        alumnoC item ;
        List<alumnoC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.* FROM SIGU.ACA_ALUMNO_CURSO_SECCION A , SIGU.ACA_ALUMNO C WHERE C.INSTITUCION=A.INSTITUCION AND C.ALUMNO=A.ALUMNO AND A.INSTITUCION=? AND  A.PERIODO=? AND A.MALLA=? AND A.CARRERA=? AND A.SECCION=? and A.CURSO=?  \n"
                    + "AND  NOT EXISTS(SELECT *FROM SIGU.ACA_ALUMNO_CURSO_NOTA B WHERE B.INSTITUCION=A.INSTITUCION AND B.MALLA=A.MALLA AND B.PERIODO=A.PERIODO AND B.CARRERA=A.CARRERA AND B.SECCION=A.SECCION AND B.CURSO= A.CURSO AND B.ALUMNO=A.ALUMNO AND B.TIPO_EXAMEN=?)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, seccion);
            cs.setString(6, curso);
            cs.setString(7, tipoNota);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCodigoExterno(rs.getString("CODIGO_EXTERNO"));
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

    public List<registroEvaluacion.tablaDetalleC> registroEvaluacion(int institucion, String periodo, String carrera, String curso, String seccion) {

        List<registroEvaluacion.tablaDetalleC> listaDatos = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        registroEvaluacion.tablaDetalleC item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL SP_MOSTRAR_ALUMNO_REGISTRO_EVAL(?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new registroEvaluacion.tablaDetalleC();
                item.setGrupo(rs.getInt("GRUPO"));
                item.setCodAlumno(rs.getString("ALUMNO"));
                item.setAlumno(rs.getString("NOMBRES"));
                item.setRa(rs.getInt("RA"));
                item.setRb(rs.getInt("RB"));
                item.setMa(rs.getInt("MA"));
                item.setMb(rs.getInt("MB"));
                item.setAa(rs.getInt("AA"));
                item.setAb(rs.getInt("AB"));
                item.setPa(rs.getInt("PA"));
                item.setPb(rs.getInt("PB"));
                listaDatos.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            
        }
        return listaDatos;
    }

   

    public boolean insertarGrupoAlumno(int institucion, String periodo, String carrera, String curso, String seccion, String alumno, String grupo) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("UPDATE [DI].[REGISTRO_INVESTIGACION] SET GRUPO=? WHERE INSTITUCION=? AND  PERIODO=? AND CARRERA=? AND CURSO=? AND SECCION=? AND ALUMNO=?");
            cs.setString(1, grupo);
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
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
        return rpta;
    }

    public boolean insertarEvaluacionNota(int institucion, String periodo, String carrera, String curso, String seccion, String alumno, String colum, String nota) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("UPDATE [DI].[REGISTRO_INVESTIGACION] SET " + colum + "=? WHERE INSTITUCION=? AND  PERIODO=? AND CARRERA=? AND CURSO=? AND SECCION=? AND ALUMNO=?");
            cs.setString(1, nota);
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);

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
        return rpta;
    }

    public List<AlumnoCons> listAlumnosxCurso(String alumno, String periodo, int institucion) {
        Connection c = ConexionWeb();
        CallableStatement cs ;
        List<AlumnoCons> lstalumno = new ArrayList<>();
        ResultSet rs ;
        try {
            cs = c.prepareCall("{CALL DI.SEL_ALUMOSxCURSO_JAVA(?,?,?)}");
            cs.setString(1, alumno);
            cs.setString(2, periodo);
            cs.setInt(3, institucion);
            rs = cs.executeQuery();
            while (rs.next()) {
                AlumnoCons a = new AlumnoCons();
                a.setAlumnos(rs.getString("ALUMNO"));
                a.setNombres(rs.getString("NOMBRES"));
                a.setPeriodos(rs.getString("PERIODO"));
                a.setIdperiodo(rs.getString("IDPERIODO"));
                a.setCarrera(rs.getString("CARRERA"));
                a.setIdcarrera(rs.getString("IDCARRERA"));
                a.setIdcurso(rs.getString("CURSO"));
                a.setAsignatura(rs.getString("ASIGNATURA"));
                a.setSeccion(rs.getString("SECCION"));
                a.setGrupo(rs.getString("GRUPO"));
                a.setPromedio(rs.getInt("PROMEDIO"));
                a.setResolucion(rs.getString("RESOLUCION"));
                a.setPconvalidado(rs.getInt("PORCENTAJE_CONVALIDADO"));
                a.setConvalidado(rs.getInt("CONVALIDADO"));
                a.setRegistro(rs.getString("REGISTRO"));
                a.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lstalumno.add(a);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lstalumno;
    }

    public int updalumnocursoseccion(String alumno, List<AlumnoCons> detalle) {
        Connection c = ConexionWeb();
        CallableStatement cs ;
        int rp = 0;
        try {
            cs = c.prepareCall("{CALL DI.UDP_ALUMNO_CURSO_JAVA(?,?,?,?,?,?,?,?,?,?)}");
            for (int i = 0; i < detalle.size(); i++) {
                cs.setString(1, alumno);
                cs.setString(2, detalle.get(i).getIdcarrera());
                cs.setString(3, detalle.get(i).getIdperiodo());
                cs.setString(4, detalle.get(i).getSeccion());
                cs.setString(5, detalle.get(i).getIdcurso());
                cs.setString(6, detalle.get(i).getGrupo());
                cs.setDouble(7, detalle.get(i).getPromedio());
                cs.setInt(8, detalle.get(i).getPconvalidado());
                cs.setInt(9, detalle.get(i).getConvalidado());
                cs.setInt(10, detalle.get(i).getEstado_registro());
                rp = cs.executeUpdate();
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return rp;
    }

    public List<AlumnoCons> listAlumnosxPeriodo(String alumno, String periodo, int institucion) {
        Connection c = ConexionWeb();
        CallableStatement cs ;
        List<AlumnoCons> lstalumno = new ArrayList<>();
        ResultSet rs ;
        try {
            cs = c.prepareCall("{CALL DI.SEL_ALUMOSxPERINST_JAVA(?,?,?)}");
            cs.setString(1, alumno);
            cs.setString(2, periodo);
            cs.setInt(3, institucion);
            rs = cs.executeQuery();
            while (rs.next()) {
                AlumnoCons a = new AlumnoCons();
                a.setAlumnos(rs.getString("ALUMNO"));
                a.setNombres(rs.getString("NOMBRES"));
                a.setPeriodos(rs.getString("PERIODO"));
                a.setCarrera(rs.getString("CARRERA"));
                a.setCurso_mat(rs.getInt("CURSOS_MATRICULADOS"));
                a.setCurso_aprov(rs.getInt("CURSOS_APROBADOS"));
                a.setCurso_conv(rs.getInt("CURSOS_CONVALIDADOS"));
                a.setCurso_ret(rs.getInt("CURSOS_RETIRADOS"));
                a.setCreditos_mat(rs.getDouble("CREDITOS_MATRICULADOS"));
                a.setCreditos_aprov(rs.getDouble("CREDITOS_APROBADOS"));
                a.setCreditos_conv(rs.getDouble("CREDITOS_CONVALIDADOS"));
                a.setCreditos_ret(rs.getDouble("CREDITOS_RETIRADOS"));
                a.setSeccion(rs.getString("SECCION_REFERENCIAL"));
                a.setNivel_ref(rs.getInt("NIVEL_REFERENCIAL"));
                a.setPromedio(rs.getInt("PROMEDIO_SEMESTRAL"));
                a.setCategoria(rs.getString("CATEGORIA"));
                a.setDscto(rs.getString("DESCUENTO"));
                a.setMonto_base(rs.getDouble("MONTO_BASE"));
                a.setMonto_pago(rs.getDouble("MONTO_PAGO"));
                a.setRegistro(rs.getString("REGISTRO"));
                a.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lstalumno.add(a);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lstalumno;
    }

    public int udp_alumnosxperiodo(String alumno, String periodo, int institucion, List<AlumnoCons> alumnos) {
        Connection c = ConexionWeb();
        CallableStatement cs ;
        int rp = 0;
        try {
            cs = c.prepareCall("{CALL DI.UDP_ALUMNO_PERIODO_JAVA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            for (int i = 0; i < alumnos.size(); i++) {
                cs.setString(1, alumno);
                cs.setString(2, periodo);
                cs.setInt(3, institucion);
                cs.setInt(4, alumnos.get(i).getNivel_ref());
                cs.setInt(5, alumnos.get(i).getCurso_mat());
                cs.setInt(6, alumnos.get(i).getCurso_aprov());
                cs.setInt(7, alumnos.get(i).getCurso_conv());
                cs.setInt(8, alumnos.get(i).getCurso_ret());
                cs.setDouble(9, alumnos.get(i).getCreditos_mat());
                cs.setDouble(10, alumnos.get(i).getCreditos_aprov());
                cs.setDouble(11, alumnos.get(i).getCreditos_conv());
                cs.setDouble(12, alumnos.get(i).getCreditos_ret());
                cs.setDouble(13, alumnos.get(i).getPromedio());
                cs.setDouble(14, alumnos.get(i).getPromedio_pon());
                cs.setString(15, alumnos.get(i).getCategoria());
                cs.setString(16, alumnos.get(i).getDscto());
                cs.setDouble(17, alumnos.get(i).getMonto_base());
                cs.setDouble(18, alumnos.get(i).getMonto_pago());
                cs.setInt(19, alumnos.get(i).getEstado_registro());
                rp = cs.executeUpdate();
            }

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return rp;
    }

    public List<certificaciones.detalle> mostrarAlumnoCertificacion(int institucion, String periodo, String carrera, String curso, String seccion) {

        List<certificaciones.detalle> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        certificaciones.detalle item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_DETALLE_CERTIFICACION(?,?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new certificaciones.detalle();
                item.setCodigo(rs.getString("ALUMNO"));
                item.setEstudiante(rs.getString("ESTUDIANTE"));
                item.setPromedio(rs.getDouble("PROMEDIO"));
                item.setFechaEntrega(rs.getString("FECHA_ENTREGA"));
                item.setFechaImpresion(rs.getString("FECHA_IMPRESION"));
                item.setTotalCertificado(rs.getString("TOTAL_CERTIFICADO"));
                item.setTotalConcepto(rs.getString("TOTAL_CONCEPTO"));
                item.setPagoConcepto(rs.getDouble("PAGO_CONCEPTO"));
                item.setPagoCertificado(rs.getDouble("PAGO_CERTIFICADO"));
                item.setItem(rs.getInt("ITEM"));

                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
           
        }
        return lista;
    }
    
    public String  mostrarPagosCertificado(int institucion, int periodo, String carrera, String curso, String seccion, String alumno) {

       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        String comprobante="";
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT SUBSTRING( ISNULL( (SELECT MAX(TC.NUM_COMPROBANTE) FROM SIGU.TES_DET_PAGOS_PERSONA TC INNER JOIN SIGU.TES_CONCEPTOS C ON TC.CONCEPTO=C.CONCEPTO AND TC.TIPO_CONCEPTO=C.TIPO_CONCEPTO\n" +
                               " WHERE TC.PERSONA=AL.PERSONA AND C.DESCRIPCION=(SELECT CU.DESCRIPCION FROM UPA.ACA_CURSOS CU WHERE CU.CURSO=CS.CURSO) AND TC.MONTO_TOTAL>=30 AND TC.ESTADO_REGISTRO=28), ''),5,11)as COMPROBANTE\n" +
                               "FROM SIGU.ACA_ALUMNO_CARRERA AC LEFT OUTER JOIN SIGU.ACA_ALUMNO AL ON AC.ALUMNO=AL.ALUMNO AND AC.INSTITUCION=AL.INSTITUCION\n" +
                               "	,SIGU.ACA_ALUMNO_CURSO_SECCION CS\n" +
                               "WHERE AC.INSTITUCION=? AND CS.PERIODO=? AND AC.CARRERA=?  AND CS.CURSO=? AND CS.SECCION=? AND AC.ALUMNO=?  AND CS.ALUMNO=AC.ALUMNO AND CS.CARRERA=AC.CARRERA \n" +
                               "ORDER BY 1");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
         
                comprobante=rs.getString("COMPROBANTE");
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return comprobante;
    }
    
    
        public String  mostrarPagoTotalCertificado(int institucion,  String concepto, String alumno) {
        
        
 
            
            
            
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        String TotalPago="";
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT CONVERT(DECIMAL(5,2),SUM(B.MONTO_TOTAL))TOTAL_PAGO FROM SIGU.TES_DET_PAGOS_PERSONA B,SIGU.ACA_ALUMNO C\n" +
                                "WHERE C.INSTITUCION=B.INSTITUCION AND C.PERSONA=B.PERSONA\n" +
                                "AND B.INSTITUCION=? AND B.CONCEPTO=? AND C.ALUMNO=? AND B.ESTADO_REGISTRO=28");

            cs.setInt(1, institucion);
            cs.setString(2, concepto);
            cs.setString(3, alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
         
                TotalPago=rs.getString("TOTAL_PAGO");
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return TotalPago;
    }
    
    
    public List<alumnoC> mostrarAlumnoInstitucionPeriodo(int institucion,String periodo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
       List<alumnoC> lista = new ArrayList<>();
        alumnoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM SIGU.ACA_ALUMNO A,SIGU.ACA_ALUMNO_PERIODO B WHERE A.INSTITUCION=B.INSTITUCION AND A.ALUMNO=B.ALUMNO AND B.INSTITUCION=? AND B.PERIODO=? AND B.ESTADO_REGISTRO=5");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
           
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCodigoExterno(rs.getString("CODIGO_EXTERNO"));
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
 public List<alumnoC> mostrarAlumnoInstitucionPeriodoCarerra(int institucion,String periodo,String carrera) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
       List<alumnoC> lista = new ArrayList<>();
        alumnoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM SIGU.ACA_ALUMNO A,SIGU.ACA_ALUMNO_PERIODO B WHERE A.INSTITUCION=B.INSTITUCION AND A.ALUMNO=B.ALUMNO AND B.INSTITUCION=? AND B.PERIODO=? AND B.CARRERA=? AND B.ESTADO_REGISTRO=5");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
           
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new alumnoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCodigoExterno(rs.getString("CODIGO_EXTERNO"));
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
    
    
    
    
    
    
     public boolean insertarAlumnoInstitucion(int institucion, String persona) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_INSERTAR_ALUMNO(?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, persona);
            
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
        return rpta;
    }
    
    

}
