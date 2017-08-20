/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.fichaPsicologia;
import Beans.resultadoEncuestaDocente;
import Conexiones.Conexion;
import ENTIDAD.encuestaC;
import ENTIDAD.encuestaCursoSeccionDocenteC;
import ENTIDAD.encuestaGrupoC;
import ENTIDAD.encuestaGrupoPreguntaC;
import ENTIDAD.encuestaInstitucionC;
import ENTIDAD.encuestaPreguntaC;
import ENTIDAD.encuestaRespuestaC;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class encuestaDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//-------------RESULTADO DE ENCUESTA ------------------------
     public List<resultadoEncuestaDocente.detallePrenguta> mostrarResultadoPregunta(int institucion,String periodo,String carrera,String malla,String curso,String seccion, int encuesta,int grupo,int pregunta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<resultadoEncuestaDocente.detallePrenguta> lista =new ArrayList<>();
        resultadoEncuestaDocente.detallePrenguta item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_RESULTADO_ENCUESTA(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setInt(7, encuesta);
            cs.setInt(8, grupo);
            cs.setInt(9, pregunta);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new resultadoEncuestaDocente.detallePrenguta();
                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setRespuesta(rs.getInt("RESPUESTA"));
                item.setDesRespuesta(rs.getString("DESCRIPCION"));
                item.setAlumno(rs.getInt("ALUMNO"));
                item.setPorcetaje(rs.getInt("PORCENTAJE"));
                
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
    
     
     
      public List<resultadoEncuestaDocente.resumenEncuesta> mostrarResumenResultado(int institucion,String periodo,String personal) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<resultadoEncuestaDocente.resumenEncuesta> lista =new ArrayList<>();
        resultadoEncuestaDocente.resumenEncuesta item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_RESULADO_RESUMEN_ENCUESTA(?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new resultadoEncuestaDocente.resumenEncuesta();
                item.setCarrera(rs.getString("CARRERA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCurso(rs.getString("CURSO"));
                item.setNumeroAula(rs.getInt("NUMEROAULA"));
                item.setNumeroAlumno(rs.getInt("NUMEROALUMNO"));
                item.setTotalMaximo(rs.getInt("TOTALMAXIMO"));
                item.setTotalObtenido(rs.getInt("TOTALOBTENIDO"));
                
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
      
      public encuestaInstitucionC mostrarEncuestaInstitucion(int institucion,String periodo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;    
        
        encuestaInstitucionC item=null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ENCUESTA_INSTITUCION WHERE INSTITUCION=? AND PERIODO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            
            
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaInstitucionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setEncuesta(rs.getInt("ENCUESTA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                
                
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return item;
    }
    
    //------------------------------------------------------------
    
    
    
    public boolean registroEncuestaDocente(encuestaCursoSeccionDocenteC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_ENCUESTA_CURSO_SECCION_DOCENTE  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getGrupo());
            cs.setString(8, item.getPersonal());
            cs.setString(9, item.getAlumno());
            cs.setInt(10, item.getEncuesta());
            cs.setInt(11, item.getGrupoEncuesta());
            cs.setInt(12, item.getPregunta());
            cs.setInt(13, item.getRespuesta());
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
            util.consolaCliente( e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
    
    
    public int validaEncuesta(int institucion,String periodo,String carrera,String seccion,String curso,String alumno,int encuesta,int opcion) {
 
        
        Connection c ;
        CallableStatement cs ;
       
        int valida=0;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_VALIDA_ENCUESTA (?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            cs.setString(5, curso);
            cs.setString(6, alumno);
            cs.setInt(7, encuesta);
            cs.setInt(8, opcion);
            cs.registerOutParameter(9, Types.INTEGER);
            
            cs.executeUpdate();   
                valida=cs.getInt(9);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente("error en la validacion"+ e.getMessage());

            //e.printStackTrace();
        }
        return valida;
    }
    
    
    
    public List<encuestaPreguntaC> mostrarPreguntas(int encuesta,int grupo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<encuestaPreguntaC> lista =new ArrayList<>();
        encuestaPreguntaC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM DI.ENCUESTA_PREGUNTAS A, DI.ENCUESTA_GRUPO_PREGUNTA B WHERE A.PREGUNTA=B.PREGUNTA AND B.ENCUESTA=? AND B.GRUPO=? ");
            cs.setInt(1, encuesta);
            cs.setInt(2, grupo);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaPreguntaC();
                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
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
    
    
    
    
    public encuestaPreguntaC mostrarPreguntas(int pregunta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      

        encuestaPreguntaC item=null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ENCUESTA_PREGUNTAS WHERE PREGUNTA=?");
            cs.setInt(1, pregunta);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaPreguntaC();
                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
            
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return item;
    }

    public List<encuestaPreguntaC> mostrarSubPreguntas(int encuesta, int grupo, String pregunta, String tipo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<encuestaPreguntaC> lista = new ArrayList<>();

        encuestaPreguntaC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from di.ENCUESTA_EGRESADO_PREGUNTAS where encuesta=? and GRUPO=? AND  PADRE=? AND  TIPO_PREGUNTA=? ORDER BY ORDEN");
            cs.setInt(1, encuesta);
            cs.setInt(2, grupo);
            cs.setString(3, pregunta);
            cs.setString(4, tipo);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaPreguntaC();

                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
              
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

    public List<fichaPsicologia.detalle> mostrarResultadoEncuesta(int institucion, int periodo, String carrera, String ciclo, int encuesta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<fichaPsicologia.detalle> lista = new ArrayList<>();
        fichaPsicologia.detalle item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.INSTITUCION,A.PERIODO,A.CARRERA,A.USUARIO,D.APELLIDO_PATERNO +' '+ D.APELLIDO_MATERNO +' '+ D.NOMBRES PERSONA,B.NIVEL_REFERENCIAL,A.CREACION_FECHA \n"
                    + "FROM DI.ENCUESTA_PREGUNTA_RESPUESTA_EGRESADO_CABECERA A,SIGU.ACA_ALUMNO_PERIODO B,SIGU.ACA_ALUMNO C,UPA.DAT_PERSONAS D\n"
                    + "WHERE A.INSTITUCION=B.INSTITUCION AND A.PERIODO=B.PERIODO AND A.USUARIO=B.ALUMNO AND  A.INSTITUCION=? AND A.PERIODO=? AND A.ENCUESTA=?  AND A.CARRERA LIKE ? AND B.NIVEL_REFERENCIAL LIKE ?\n"
                    + "AND A.INSTITUCION=C.INSTITUCION AND  C.ALUMNO=A.USUARIO AND C.PERSONA=D.PERSONA");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setInt(3, encuesta);

            cs.setString(4, carrera);
            cs.setString(5, ciclo);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new fichaPsicologia.detalle();

                item.setPeriodo(rs.getInt("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setUsuario(rs.getString("USUARIO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setCiclo(rs.getString("NIVEL_REFERENCIAL"));
                item.setFecha(rs.getDate("CREACION_FECHA"));
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

    public List<encuestaGrupoC> mostrarGrupo(int encuesta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<encuestaGrupoC> lista = new ArrayList<>();
        encuestaGrupoC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.ENCUESTA_GRUPO WHERE ENCUESTA=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, encuesta);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaGrupoC();
                item.setEncuesta(rs.getInt("ENCUESTA"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
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
    
    public List<encuestaGrupoPreguntaC> mostrarGrupoPregunta(int encuesta,int grupo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs  ;

        List<encuestaGrupoPreguntaC> lista = new ArrayList<>();
        encuestaGrupoPreguntaC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ENCUESTA_GRUPO_PREGUNTA WHERE ENCUESTA=? AND GRUPO=?");
            cs.setInt(1, encuesta);
            cs.setInt(2, grupo);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaGrupoPreguntaC();
                item.setEncuesta(rs.getInt("ENCUESTA"));
                item.setGrupo(rs.getInt("GRUPO"));                
                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setTipo(rs.getString("TIPO"));
                item.setOrden(rs.getInt("ORDEN"));
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

    public boolean controlEncuesta(int institucion, int periodo, String usuario, int encuesta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        Boolean rpt = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from DI.ENCUESTA_PREGUNTA_RESPUESTA_EGRESADO_CABECERA  WHERE INSTITUCION=? AND PERIODO=? AND  USUARIO=? AND  ENCUESTA=?");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, usuario);
            cs.setInt(4, encuesta);

            rs = cs.executeQuery();
            while (rs.next()) {
                rpt = true;

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return rpt;
    }

    public List<ArrayList<String>> getVerificar(String usuario) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<ArrayList<String>> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from di.ENCUESTA_PREGUNTA_RESPUESTA_EGRESADO_CABECERA WHERE USUARIO=?");
            cs.setString(1, usuario);

            rs = cs.executeQuery();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();

                array.add(rs.getString("USUARIO"));
                array.add(rs.getString("CARRERA"));
                array.add(rs.getString("AÑO_EGRESO"));
                array.add(rs.getString("ESTADO"));
                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }

    public encuestaC mostrarEncuesta(int encuesta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        encuestaC item = null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from DI.ENCUESTA WHERE ENCUESTA=?");
            cs.setInt(1, encuesta);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaC();
                item.setEncuesta(rs.getInt("ENCUESTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setFecha_inicio(rs.getString("FECHA_INICIO"));
                item.setFecha_fin(rs.getString("FECHA_FIN"));
                item.setEstado_Registro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return item;
    }

    public String insertarEncuesta(String usuario, int encuesta, int pregunta, int respuesta, String observacion) {
        Connection c ;
        CallableStatement cs ;
        String rpta = "";

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  SP_REGISTRO_ENCUESTA_EGRESADO  (?,?,?,?,?,?)}");
            cs.setInt(1, pregunta);
            cs.setInt(2, respuesta);
            cs.setString(3, observacion);
            cs.setString(4, usuario);
            cs.setInt(5, encuesta);
            cs.registerOutParameter(6, java.sql.Types.VARCHAR);

            if (cs.executeUpdate() == 1) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            rpta = cs.getString(6);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

    public String resgitrarEncuesta(int encuesta, int pregunta, int respuesta, String observacion, String usuario) {
        Connection c ;
        CallableStatement cs ;
        String rpta = "";

        try {

            c = ConexionWeb();
            cs = c.prepareCall("INSERT INTO DI.ENCUESTA_PREGUNTA_RESPUESTA_EGRESADO(ENCUESTA,PREGUNTA,RESPUESTA,DESCRIPCION,USUARIO)VALUES(?,?,?,?,?)");
            cs.setInt(1, encuesta);
            cs.setInt(2, pregunta);
            cs.setInt(3, respuesta);
            cs.setString(4, observacion);
            cs.setString(5, usuario);
            if (cs.executeUpdate() == 1) {
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

    public String resgitrarCabecera(int institucion, int periodo, String carrera, String usuario, String encuesta, int estado) {
        Connection c ;
        CallableStatement cs ;
        String rpta = "";

        try {

            c = ConexionWeb();
            cs = c.prepareCall("INSERT INTO DI.ENCUESTA_PREGUNTA_RESPUESTA_EGRESADO_CABECERA(INSTITUCION,PERIODO,CARRERA,USUARIO,ENCUESTA,ESTADO)VALUES(?,?,?,?,?,?)");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, usuario);
            cs.setString(5, encuesta);
            cs.setInt(6, estado);
            if (cs.executeUpdate() == 1) {
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

    public boolean registro(String usuario, String ano) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  SP_REGISTRO_ENCUESTA_CABECERA  (?,?)}");
            cs.setString(1, usuario);

            cs.setString(2, ano);

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

    public List<encuestaRespuestaC> mostrarRespuesta(int encuesta,int grupo, int pregunta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<encuestaRespuestaC> lista = new ArrayList<>();
        encuestaRespuestaC item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM DI.ENCUESTA_PREGUNTA_RESPUESTA A ,DI.ENCUESTA_RESPUESTAS B WHERE B.RESPUESTA=A.RESPUESTA AND A.ENCUESTA=? AND A.GRUPO=? AND A.PREGUNTA=? ORDER BY A.ORDEN ASC");
            cs.setInt(1, encuesta);
            cs.setInt(2, grupo);
            cs.setInt(3, pregunta);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaRespuestaC();

                item.setRepuesta(rs.getInt("RESPUESTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));

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

    public int getRespuestasMarcada(String usuario, int pregunta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        int rpt = 0;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select RESPUESTA from DI.ENCUESTA_PREGUNTA_RESPUESTA_EGRESADO where USUARIO=? and PREGUNTA=?");
            cs.setString(1, usuario);
            cs.setInt(2, pregunta);

            rs = cs.executeQuery();
            while (rs.next()) {
                rpt = rs.getInt("RESPUESTA");
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return rpt;
    }
}
