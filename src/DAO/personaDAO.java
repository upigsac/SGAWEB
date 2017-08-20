
package DAO;

import Beans.asistenciaEvento;
import Beans.constanciaDeNoAdeudar;
import Beans.cuentaPersona;
import Conexiones.Conexion;



import ENTIDAD.personaC;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java.util.List;

import org.primefaces.model.UploadedFile;

import  Beans.util;
import ENTIDAD.PersonaNET;
import java.io.FileNotFoundException;


public class personaDAO extends Conexion implements Serializable {
	
	
	

	private static final long serialVersionUID = 1L;
	
public List<personaC> mostrarPersona() {
		
		
		
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
        List<personaC> lista=new ArrayList<>();
        personaC item =null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.DAT_PERSONAS WHERE ESTADO_REGISTRO=1 ");
            
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setNumero_documento(rs.getString("NRO_DOCUMENTO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return lista;
    }
	public personaC validaDNI(String persona,String numero) {
	
		
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
    
        personaC item =null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.DAT_PERSONAS WHERE NRO_DOCUMENTO=? AND PERSONA NOT IN (?) ");
            
            cs.setString(1, numero);
            cs.setString(2, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setNumero_documento(rs.getString("NRO_DOCUMENTO"));
               

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return item;
    }

	 public String insertar(personaC item) {
         
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta;
	        String codigo = "";
	        try {             
	            c = ConexionWeb();
	            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_PERSONA(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,? )}");
	            cs.setString("PERSONA", item.getPersona());
	            cs.setString("APATERNO", item.getPaterno());
	            cs.setString("AMATERNO", item.getMaterno());
	            cs.setString("NOMBRES", item.getNombres());	            
	            cs.setString("DIRECCION_UBIGEO", item.getDireccion_ubigeo());
	            cs.setInt("TIPO_TRANSITO", item.getTipo_transito());
	            cs.setString("DIRECCION_RECIDENCIA", item.getDireccion_recidencial());
	            cs.setString("DIRECCION_REFERENCIA", item.getDireccion_referencial());
	            cs.setString("DIRECCION_UBIGEO_PROC", item.getDireccion_ubigeo_pro());
	            cs.setInt("TIPO_TRANSITO_PROC", item.getTipo_transito_pro());
	            cs.setString("DIRECCION_RECIDENCIA_PROC", item.getDireccion_recidencial_pro());
	            cs.setString("DIRECCION_REFERENCIA_PROC", item.getDireccion_referencial_pro());
	            cs.setString("TELEFONO", item.getTelefono());
	            cs.setString("TELEFO_CELULAR", item.getCelular());
	            cs.setString("EMAIL_PRINCIPAL", item.getEmailP());
	            cs.setString("EMAIL_OPCIONAL", item.getEmailS());
	            cs.setString("NACIMIENTO_FECHA", util.convertDate(item.getFecha_nacimiento(),"dd-MM-yyyy") );
	            cs.setString("NACIMIENTO_UBIGEO", item.getNacimiento_ubigeo());
	            cs.setInt("SEXO", item.getSexo());
	            cs.setInt("ESTADO_CIVIL", item.getEstado_civil());
	            cs.setInt("PAIS", item.getPais());
	            cs.setString("TELEFONO_EMERGENCIA_01", item.getTelefono_emergencia_01());
	            cs.setString("TELEFONO_EMERGENCIA_02", item.getTelefono_emergencia_02());
	            cs.setString("TELEFONO_EMERGENCIA_03", item.getTelefono_emergencia_03());
	            cs.setString("TELEFONO_EMERGENCIA_04", item.getTelefono_emergencia_04());
	            cs.setInt("TRABAJA", item.getTrabaja());
	            cs.setInt("PROFESION", item.getProfesion());
	            cs.setInt("OCUPACION", item.getOcupacion());
	            cs.setString("CENTRO_TRABAJO", item.getCentro_trabajo());
	            cs.setString("TELEFONO_TRABAJO", item.getTelefonoTrabajo());
	            cs.setInt("TIPO_VIVIENDA", item.getTipoVivienda());
	            cs.setInt("SITUACION_VIVIENDA", item.getSituacionVivienda());
	            cs.setBoolean("SERVICIO_AGUA", item.isServicio_agua());
	            cs.setBoolean("SERVICIO_DESAGUE", item.isServicio_desague());
	            cs.setBoolean("SERVICIO_LUZ", item.isServicio_luz());
	            cs.setBoolean("SERVICIO_APUBLICO", item.isServicio_apublico());
	            cs.setInt("NUMERO_PISOS", item.getNumero_pisos());
	            cs.setInt("NUMERO_DORMITORIOS", item.getNumero_dormitorios());
	            cs.setString("MATERIAL_CONSTRUCCION", item.getMaterial_construccion());
	            cs.setString("VIVIENDA_CUENTA", item.getVivienda_cuenta());
	            cs.setString("MOVILIDAD", item.getMovilidad());
	            cs.setBoolean("ENFERMEDAD_CRONICA", item.isEnfermedad_cronica());
	            cs.setString("ENFERMEDAD_DESCRIPCION", item.getEnfermedad_descripcion());
	            cs.setInt("TIPO_DOCUMENTO", item.getTipodocumento());
	            cs.setString("NRO_DOCUMENTO", item.getNumero_documento());
	            cs.setInt("NIVEL_EDUCATIVO", item.getNivel_educativo());
	            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
	            
	            cs.setString("INSTITUCION_GRADO", item.getInstitucion_grado());
	            cs.setString("ANIO_GRADO", item.getAnio_grado());
	            cs.setString("MENSION_GRADO", item.getMension_grado());
	            
	            cs.registerOutParameter(1, Types.VARCHAR);
	            rpta = cs.executeUpdate() == 1 ;
	            if (rpta) {

	                c.commit();
	                codigo = cs.getString(1);
	                
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
	
	
	
	
		public List<PersonaNET> getPersonasCaja(String pat, String mat, String nom) {
        List<PersonaNET> per = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.CON_BUSCA_PERSONA_JAVA(?,?,?)}");
           
            cs.setString(1, pat);
            cs.setString(2, mat);
            cs.setString(3, nom);
            rs = cs.executeQuery();
            while (rs.next()) {
                PersonaNET p = new PersonaNET();
                p.setPersona(rs.getString(1));
                p.setNombres(rs.getString(2));
                per.add(p);
            }
        } catch (SQLException e) {
    
        }
        return per;
    }

    public personaC mostrarPersona(int institucion, String periodo, String malla, String carrera, String seccion, String curso) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        personaC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.* FROM SIGU.HOR_CURSO_SECCION_DOCENTE A,SIGU.PER_PERSONAL B, UPA.DAT_PERSONAS C WHERE A.INSTITUCION=? AND A.PERIODO=?  AND  A.MALLA=? AND A.CARRERA=?  AND A.SECCION=? AND A.CURSO=?  AND A.ESTADO_REGISTRO=1 AND A.PERSONAL=B.PERSONAL AND B.PERSONA=C.PERSONA");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, seccion);
            cs.setString(6, curso);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("persona"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_RECIDENCIA"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setSexo(rs.getInt("SEXO"));
                item.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                item.setNumero_documento(rs.getString("nro_documento"));
                item.setTelefono_emergencia_04(rs.getString("telefono_emergencia_04"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));
                item.setTipoVivienda(rs.getInt("TIPO_VIVIENDA"));
                item.setSituacionVivienda(rs.getInt("SITUACION_VIVIENDA"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            
            System.out.println(e.getMessage());
        }
        return item;
    }
    
    public personaC mostrarPersona(String persona) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        personaC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.DAT_PERSONAS WHERE PERSONA=?");
         
            cs.setString(1, persona);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("persona"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_RECIDENCIA"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setSexo(rs.getInt("SEXO"));
                item.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                item.setNumero_documento(rs.getString("nro_documento"));
                item.setTelefono_emergencia_04(rs.getString("telefono_emergencia_04"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));
                item.setTipoVivienda(rs.getInt("TIPO_VIVIENDA"));
                item.setSituacionVivienda(rs.getInt("SITUACION_VIVIENDA"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
        	 System.out.println(e.getMessage());
        }
        return item;
    }

   

    public String eliminarConstanciaPerson(int serie, String persona) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM DI.CONSTANCIA_DE_NO_ADEUDAR WHERE SERIE=? AND PERSONA=?  ");
            cs.setInt(1, serie);
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
        return codigo;
    }

    public List<constanciaDeNoAdeudar.detalle> mostrarConstanciaPersonal(String persona) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<constanciaDeNoAdeudar.detalle> lista = new ArrayList<>();
        constanciaDeNoAdeudar.detalle item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" SELECT *FROM DI.CONSTANCIA_DE_NO_ADEUDAR WHERE  PERSONA=?");

            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new constanciaDeNoAdeudar.detalle();
                item.setPersona(rs.getString("PERSONA"));
                item.setSerie(rs.getInt("SERIE"));
                item.setNombres(rs.getString("NOMBRES"));
                item.setInstitucion(rs.getString("INSTITUCION"));
                item.setUsuario(rs.getString("CREACION_USUARIO"));
                item.setTipo(rs.getString("TIPO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           
        	 System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<personaC> mostrarPersonal() {
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" select B.* from SIGU.PER_PERSONAL A ,UPA.DAT_PERSONAS B WHERE A.PERSONA=B.PERSONA AND A.ESTADO_REGISTRO=1 order by 2,3,4");

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
    
    
    public List<personaC> mostrarPersonaPracticaInduccion(int institucion,String carrera,String persona,String estadoRegistro) {
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT D.* FROM DI.ACA_ALUMNO_CURSO_PRACTICA A,SIGU.ACA_ALUMNO B,SIGU.ACA_ALUMNO_CARRERA C, UPA.DAT_PERSONAS D WHERE B.INSTITUCION=? AND C.CARRERA LIKE ?  AND D.PERSONA LIKE ? AND A.ESTADO_REGISTRO LIKE ? AND  A.ALUMNO=B.ALUMNO AND C.INSTITUCION=B.INSTITUCION AND C.ALUMNO=B.ALUMNO AND D.PERSONA=B.PERSONA ORDER BY  D.APELLIDO_PATERNO,D.APELLIDO_MATERNO,D.NOMBRES");
            cs.setInt(1, institucion);
            cs.setString(2, carrera);
            cs.setString(3, persona);
            cs.setString(4,estadoRegistro);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
     public List<personaC> mostrarPersonaPrincipal(int institucion, String periodo,String carrera,String malla) {
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MOSTRAR_HOR_CURSO_SECCION_DOCENTE] (3,?,?,?,?,'%','%','%')}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return lista;
    }
     
     public List<personaC> mostrarPersonaPrincipal(int institucion, String periodo,String carrera,String malla,String desPersona) {
    	 
    	 
         Connection c ;
         CallableStatement cs  ;
         ResultSet rs ;
         List<personaC> lista = new ArrayList<>();
         personaC item ;
         try {
             c = ConexionWeb();
             cs = c.prepareCall("{CALL [DI].[SP_MOSTRAR_HOR_CURSO_SECCION_DOCENTE] (3,?,?,?,?,'%','%','%','%',?)}");
             cs.setInt(1, institucion);
             cs.setString(2, periodo);
             cs.setString(3, carrera);
             cs.setString(4, malla);
             cs.setString(5, desPersona);
             rs = cs.executeQuery();
             while (rs.next()) {
                 item = new personaC();
                 item.setPersona(rs.getString("PERSONA"));
                 item.setPaterno(rs.getString("apellido_paterno"));
                 item.setMaterno(rs.getString("apellido_materno"));
                 item.setNombres(rs.getString("nombres"));
                 lista.add(item);

             }
             cerrarCall(cs);
             cerrarConexion(c);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             
         }
         return lista;
     }

    public personaC getBuscaPersonaCodigo(String codPersona) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select * from UPA.DAT_PERSONAS where persona=?");
            cs.setString(1, codPersona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));               
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));               
                item.setNacimiento_ubigeo(rs.getString("NACIMIENTO_UBIGEO")==null?"000000":rs.getString("NACIMIENTO_UBIGEO"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_UBIGEO")==null || rs.getString("DIRECCION_UBIGEO").isEmpty() || rs.getString("DIRECCION_UBIGEO").length()<6 ?"000000" :rs.getString("DIRECCION_UBIGEO"));
                item.setUbigeoDepartamento(item.getDireccion_ubigeo().substring(0,2));
                item.setUbigeoProvincia(item.getDireccion_ubigeo().substring(2,4));
                item.setUbigeoDistrito(item.getDireccion_ubigeo().substring(4,6));
                item.setDireccion_recidencial(rs.getString("DIRECCION_RECIDENCIA"));
                item.setDireccion_referencial(rs.getString("DIRECCION_REFERENCIA"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                item.setTipodocumento(rs.getInt("TIPO_DOCUMENTO"));
                item.setPais(rs.getInt("PAIS"));
                item.setProfesion(rs.getInt("PROFESION"));
                item.setSexo(rs.getInt("SEXO"));
                item.setNumero_documento(rs.getString("NRO_DOCUMENTO"));
                item.setNivel_educativo(rs.getInt("NIVEL_EDUCATIVO"));
                item.setInstitucion_grado(rs.getString("INSTITUCION_GRADO"));
                item.setAnio_grado(rs.getString("ANIO_GRADO"));
                item.setMension_grado(rs.getString("MENSION_GRADO"));
         
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
             System.out.println(e.getMessage());
        }
        return item;
    }

    public List<personaC> mostrarAlumnoCarrera(int institucion, String carrera) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" SELECT A.ALUMNO,C.APELLIDO_PATERNO ,C.APELLIDO_MATERNO ,C.NOMBRES from SIGU.ACA_ALUMNO_PERIODO A,SIGU.ACA_ALUMNO B,UPA.DAT_PERSONAS C\n"
                    + " WHERE A.INSTITUCION=? and A.CARRERA like ? AND A.ESTADO_REGISTRO=5 AND A.ALUMNO=B.ALUMNO AND B.PERSONA=C.PERSONA\n"
                    + " GROUP BY A.ALUMNO,B.PERSONA ,C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES \n"
                    + " ORDER BY 2,3,4");
            cs.setInt(1, institucion);
            cs.setString(2, carrera);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("alumno"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<personaC> mostrarAlumnoCarrera(int institucion, String periodo, String carrera) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" SELECT C.* FROM SIGU.ACA_ALUMNO_PERIODO A,SIGU.ACA_ALUMNO B,UPA.DAT_PERSONAS C\n"
                    + "WHERE A.INSTITUCION=? AND A.PERIODO=? and A.CARRERA like ? AND A.ESTADO_REGISTRO=5 \n"
                    + "AND A.INSTITUCION=B.INSTITUCION AND A.ALUMNO=B.ALUMNO AND B.PERSONA=C.PERSONA\n"
                    + "ORDER BY 2,3,4");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("persona"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_RECIDENCIA"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setSexo(rs.getInt("SEXO"));
                item.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                item.setNumero_documento(rs.getString("nro_documento"));
                item.setTelefono_emergencia_04(rs.getString("telefono_emergencia_04"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));
                item.setTipoVivienda(rs.getInt("TIPO_VIVIENDA"));
                item.setSituacionVivienda(rs.getInt("SITUACION_VIVIENDA"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<personaC> mostrarAutorizacionMatriculaPersona(int institucion, String periodo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.PERSONAL,C.APELLIDO_PATERNO ,C.APELLIDO_MATERNO ,C.NOMBRES \n"
                    + "  FROM [SIGU].[PER_PERSONAL_PERIODO] A ,SIGU.PER_PERSONAL B,UPA.DAT_PERSONAS C\n"
                    + "  where A.INSTITUCION=? and A.PERIODO=? AND A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA\n"
                    + "  AND A.AUTORIZA_MATRICULA=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONAL"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
     public List<personaC> mostrarPersonalSecundario(int institucion, String periodo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT  C.*FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN A,SIGU.PER_PERSONAL B ,UPA.DAT_PERSONAS C\n" +
                                "WHERE A.PERSONAL=B.PERSONAL AND B.PERSONA=C.PERSONA AND A.INSTITUCION=? AND A.PERIODO=? ORDER BY  C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     
     public List<personaC> mostrarDocenteContrato(int institucion, String periodo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT C.* FROM SIGU.HOR_CURSO_SECCION_DOCENTE A,SIGU.PER_PERSONAL B,UPA.DAT_PERSONAS C WHERE C.PERSONA=B.PERSONA AND B.PERSONAL=A.PERSONAL AND A.INSTITUCION=? AND A.PERIODO=? AND A.ESTADO_REGISTRO LIKE '%' ORDER BY C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }


    public List<personaC> mostrarAutorizacionNotaPersona(int institucion, int periodo) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.PERSONAL,C.APELLIDO_PATERNO ,C.APELLIDO_MATERNO ,C.NOMBRES \n"
                    + "  FROM [SIGU].[PER_PERSONAL_PERIODO] A ,SIGU.PER_PERSONAL B,UPA.DAT_PERSONAS C\n"
                    + "  where A.INSTITUCION=? and A.PERIODO=? AND A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA\n"
                    + "  AND A.AUTORIZA_NOTA=1");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONAL"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public byte[] obtenImagenPersonal(String idPersona) {
        Connection c  ;
        c = ConexionWeb();
        ResultSet rs ;
        PreparedStatement pr  ;
        byte[] buffer =null;
        try {
            String sql = "{CALL SP_FOTO(?)}";
            pr = c.prepareCall(sql);
            pr.setString(1, idPersona);
            rs = pr.executeQuery();
            while (rs.next()) {
                Blob bin = rs.getBlob("IMAGEN_FOTO");
                if (bin != null) {
                    InputStream inStream = bin.getBinaryStream();
                    int size = (int) bin.length();
                    buffer = new byte[size];
                  
                    try {
                        inStream.read(buffer, 0, size);
                    } catch (IOException ex) {
                        
                    }
                }
            }
        } catch (SQLException ex) {
            return null;
        } finally {
          
        }
        return buffer;
    }

    public String insertarImagen(String persona, String dirArchivo) throws FileNotFoundException {
        String inserto = "";
        Connection c ;

        c = ConexionWeb();
        PreparedStatement pr ;
        String sql = "{CALL DI.SP_IMAGEN_PERSONAL(?,?,?,?,?)} ";
        try {

            pr = c.prepareStatement(sql);
            pr.setString(1, persona);
            pr.setInt(2, 1);
            //Parametros de la imagen
            dirArchivo = "D:\\" + dirArchivo;
            
            File fichero = new File(dirArchivo);
            FileInputStream streamEntrada = new FileInputStream(fichero);
         //   int tamañoImagen = streamEntrada.available();
            //Establecer los parametros a la BD
            //  pr.setInt(3, tamañoImagen);
            pr.setBinaryStream(3, streamEntrada, (int) fichero.length());
            
            pr.setString(4, dirArchivo);
            pr.setInt(5, 1);
            if (pr.executeUpdate() == 1) {
                inserto = "true";
                System.out.println("SI inserto");
            } else {
                inserto = "false";
                System.out.println("NO INSERTO");
            }
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
            inserto = ex.getMessage();
        } finally {
            try {
                
                c.close();
            } catch (SQLException ex) {
                util.consolaCliente( ex.getMessage());
                
            }
        }
        return inserto;
    }
    
    
    public String insertarImagen(String persona, UploadedFile item) throws IOException {
        String inserto = "";
        Connection c ;

        c = ConexionWeb();
        PreparedStatement pr ;
        String sql = "{CALL DI.SP_IMAGEN_PERSONAL(?,?,?,?,?)} ";
        try {

            pr = c.prepareStatement(sql);
            pr.setString(1, persona);
            pr.setInt(2, 1);
            
            FileInputStream streamEntrada=(FileInputStream) item.getInputstream();
            pr.setBinaryStream(3, streamEntrada, (int) item.getSize());            
            pr.setString(4, "");
            pr.setInt(5, 1);
            if (pr.executeUpdate() == 1) {
                inserto = "true";
                System.out.println("SI inserto");
            } else {
                inserto = "false";
                System.out.println("NO INSERTO");
            }
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            inserto = ex.getMessage();
        } finally {
            try {
                
                c.close();
            } catch (SQLException ex) {
            	System.out.println(ex.getMessage());
                
            }
        }
        return inserto;
    }
    
    
    public String eliminarImagen(String persona) {
        String inserto = "";
        Connection c ;

        c = ConexionWeb();
        PreparedStatement pr = null;
        
        try {

            pr = c.prepareStatement("DELETE FROM UPA.DAT_PERSONAS_IMAGENES WHERE PERSONA=?");
            pr.setString(1, persona);
            pr.executeUpdate() ;
           
        } catch (SQLException ex) {
            inserto = ex.getMessage();
            System.out.println(ex.getMessage());
        } finally {
            try {
                pr.close();
                c.close();
            } catch (SQLException ex) {
            	System.out.println(ex.getMessage());
            }
        }
        return inserto;
    }

    public String insertarPersonal(personaC per) {
        String inserto = "";
        Connection c ;

        c = ConexionWeb();
        PreparedStatement pr = null;
        String sql = "update UPA.DAT_PERSONAS set APELLIDO_PATERNO=?,APELLIDO_MATERNO=?,nombres=?,direccion_ubigeo=?,telefono=?,\n"
                + "telefono_celular=?,email_principal=?,email_opcional=?,nro_documento=? WHERE persona=?";
        try {

            pr = c.prepareStatement(sql);
            pr.setString(1, per.getPaterno());
            pr.setString(2, per.getMaterno());
            pr.setString(3, per.getNombres());
            pr.setString(4, "");
            pr.setString(5, per.getTelefono());
            pr.setString(6, per.getCelular());
            pr.setString(7, per.getEmailP());
            pr.setString(8, per.getEmailS());
            pr.setString(9, per.getNumero_documento());
            pr.setString(10, per.getPersona());
            if (pr.executeUpdate() == 1) {
                inserto = "true";
            } else {
                inserto = "false";
            }
        } catch (SQLException ex) {
            inserto = ex.getMessage();
            util.consolaCliente( ex.getMessage());
        } finally {
            try {
                pr.close();
                c.close();
            } catch (SQLException ex) {
                util.consolaCliente( ex.getMessage());
            }
        }
        return inserto;
    }

    public List<personaC> BuscaPersona(String Apaterno, String Amaterno, String nombre) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC per ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select top 100 * from UPA.DAT_PERSONAS where APELLIDO_PATERNO like ?  and APELLIDO_MATERNO like ? AND NOMBRES like ?");
            cs.setString(1, Apaterno + "%");
            cs.setString(2, Amaterno + "%");
            cs.setString(3, nombre + "%");

            rs = cs.executeQuery();

            while (rs.next()) {

                per = new personaC();

                per.setPersona(rs.getString("persona"));
                per.setPaterno(rs.getString("apellido_paterno"));
                per.setMaterno(rs.getString("apellido_materno"));
                per.setNombres(rs.getString("nombres"));
                per.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                per.setTelefono(rs.getString("telefono"));
                per.setCelular(rs.getString("telefono_celular"));
                per.setEmailP(rs.getString("email_principal"));
                per.setEmailS(rs.getString("email_opcional"));
                per.setTipodocumento(rs.getInt("tipo_documento"));
                per.setNumero_documento(rs.getString("nro_documento"));
                lista.add(per);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }

    public List<personaC> filtroPersonal(String Apaterno, String Amaterno, String nombre) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select top 20 A.* from UPA.DAT_PERSONAS A ,SIGU.PER_PERSONAL B where B.PERSONA=A.PERSONA AND A.APELLIDO_PATERNO like ?  and A.APELLIDO_MATERNO like ? AND A.NOMBRES like ? AND A.ESTADO_REGISTRO=1 AND B.ESTADO_REGISTRO=1");
            cs.setString(1, Apaterno + "%");
            cs.setString(2, Amaterno + "%");
            cs.setString(3, nombre + "%");

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();

                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));               
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));               
                item.setNacimiento_ubigeo(rs.getString("NACIMIENTO_UBIGEO")==null?"000000":rs.getString("NACIMIENTO_UBIGEO"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_UBIGEO")==null?"000000":rs.getString("DIRECCION_UBIGEO"));
                item.setDireccion_recidencial(rs.getString("DIRECCION_RECIDENCIA"));
                item.setDireccion_referencial(rs.getString("DIRECCION_REFERENCIA"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                item.setTipodocumento(rs.getInt("TIPO_DOCUMENTO"));
                item.setPais(rs.getInt("PAIS"));
                item.setProfesion(rs.getInt("PROFESION"));
                item.setSexo(rs.getInt("SEXO"));
                item.setNumero_documento(rs.getString("NRO_DOCUMENTO"));
                item.setNivel_educativo(rs.getInt("NIVEL_EDUCATIVO"));
                item.setInstitucion_grado(rs.getString("INSTITUCION_GRADO"));
                item.setAnio_grado(rs.getString("ANIO_GRADO"));
                item.setMension_grado(rs.getString("MENSION_GRADO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    public List<personaC> filtroPersona(String Apaterno, String Amaterno, String nombre) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select top 20 * from UPA.DAT_PERSONAS where APELLIDO_PATERNO like ?  and APELLIDO_MATERNO like ? AND NOMBRES like ? AND ESTADO_REGISTRO=1");
            cs.setString(1, Apaterno + "%");
            cs.setString(2, Amaterno + "%");
            cs.setString(3, nombre + "%");

            rs = cs.executeQuery();

            while (rs.next()) {
                
                item = new personaC();

                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));               
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));               
                item.setNacimiento_ubigeo(rs.getString("NACIMIENTO_UBIGEO")==null?"000000":rs.getString("NACIMIENTO_UBIGEO"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_UBIGEO")==null?"000000":rs.getString("DIRECCION_UBIGEO"));
                item.setDireccion_recidencial(rs.getString("DIRECCION_RECIDENCIA"));
                item.setDireccion_referencial(rs.getString("DIRECCION_REFERENCIA"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                item.setTipodocumento(rs.getInt("TIPO_DOCUMENTO"));
                item.setPais(rs.getInt("PAIS"));
                item.setProfesion(rs.getInt("PROFESION"));
                item.setSexo(rs.getInt("SEXO"));
                item.setNumero_documento(rs.getString("NRO_DOCUMENTO"));
                item.setNivel_educativo(rs.getInt("NIVEL_EDUCATIVO"));
                item.setInstitucion_grado(rs.getString("INSTITUCION_GRADO"));
                item.setAnio_grado(rs.getString("ANIO_GRADO"));
                item.setMension_grado(rs.getString("MENSION_GRADO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    
     public List<personaC> filtroPersona(String nombreCompleto) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select top 20 * from UPA.DAT_PERSONAS where APELLIDO_PATERNO +' '+ APELLIDO_MATERNO +' '+ NOMBRES like ? AND ESTADO_REGISTRO=1");
            cs.setString(1,"%" + nombreCompleto + "%");
        

            rs = cs.executeQuery();

            while (rs.next()) {
                
                item = new personaC();

                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));               
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));               
                item.setNacimiento_ubigeo(rs.getString("NACIMIENTO_UBIGEO")==null?"000000":rs.getString("NACIMIENTO_UBIGEO"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_UBIGEO")==null?"000000":rs.getString("DIRECCION_UBIGEO"));
                item.setDireccion_recidencial(rs.getString("DIRECCION_RECIDENCIA"));
                item.setDireccion_referencial(rs.getString("DIRECCION_REFERENCIA"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                item.setTipodocumento(rs.getInt("TIPO_DOCUMENTO"));
                item.setPais(rs.getInt("PAIS"));
                item.setProfesion(rs.getInt("PROFESION"));
                item.setSexo(rs.getInt("SEXO"));
                item.setNumero_documento(rs.getString("NRO_DOCUMENTO"));
                item.setNivel_educativo(rs.getInt("NIVEL_EDUCATIVO"));
                item.setInstitucion_grado(rs.getString("INSTITUCION_GRADO"));
                item.setAnio_grado(rs.getString("ANIO_GRADO"));
                item.setMension_grado(rs.getString("MENSION_GRADO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return lista;
    }
     
     public List<personaC> filtroPersonaAlumno(int institucion,String periodo,String carrera, String nombres) {

         List<personaC> lista = new ArrayList<>();
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         personaC item ;

         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT TOP 50 C.PERSONA,C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES FROM SIGU.ACA_ALUMNO A,SIGU.ACA_ALUMNO_PERIODO B,UPA.DAT_PERSONAS C WHERE A.INSTITUCION=? AND  B.PERIODO=? AND B.CARRERA=? AND C.APELLIDO_PATERNO +' '+ C.APELLIDO_MATERNO +' '+C.NOMBRES LIKE ?  AND B.INSTITUCION=A.INSTITUCION AND B.ALUMNO=A.ALUMNO AND B.ESTADO_REGISTRO=5 AND C.PERSONA=A.PERSONA ORDER BY C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES");
             cs.setInt(1, institucion);
             cs.setString(2, periodo );
             cs.setString(3, carrera );
             cs.setString(4, "%" + nombres + "%");

             rs = cs.executeQuery();

             while (rs.next()) {

            	 item = new personaC();
            	 item.setPersona(rs.getString("persona"));
            	 item.setPaterno(rs.getString("apellido_paterno"));
            	 item.setMaterno(rs.getString("apellido_materno"));
            	 item.setNombres(rs.getString("nombres"));
                
                 lista.add(item);

             }
             cerrarCall(cs);
             cerrarConexion(c);

         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return lista;
     }

    public List<personaC> filtroPersonaInstitucion(int institucion, String Apaterno, String Amaterno, String nombre) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC per ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 100 A.*FROM UPA.DAT_PERSONAS A ,SIGU.ACA_ALUMNO B\n"
                    + "WHERE B.INSTITUCION=? AND A.APELLIDO_PATERNO like ?  and A.APELLIDO_MATERNO like ? AND A.NOMBRES like ?");
            cs.setInt(1, institucion);
            cs.setString(2, Apaterno + "%");
            cs.setString(3, Amaterno + "%");
            cs.setString(4, nombre + "%");

            rs = cs.executeQuery();

            while (rs.next()) {

                per = new personaC();

                per.setPersona(rs.getString("persona"));
                per.setPaterno(rs.getString("apellido_paterno"));
                per.setMaterno(rs.getString("apellido_materno"));
                per.setNombres(rs.getString("nombres"));
                per.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                per.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                per.setTelefono(rs.getString("telefono"));
                per.setCelular(rs.getString("telefono_celular"));
                per.setEmailP(rs.getString("email_principal"));
                per.setEmailS(rs.getString("email_opcional"));
                per.setOcupacion(rs.getInt("OCUPACION"));
                per.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                per.setTipodocumento(rs.getInt("tipo_documento"));
                per.setNumero_documento(rs.getString("nro_documento"));
                lista.add(per);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<personaC> BuscaAlumno(String institucion, String paterno, String materno, String nombre) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC per ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_BUSQUEDA_ALUMNO(?,?,?,?)}");
            cs.setString(1, institucion);
            cs.setString(2, paterno + "%");
            cs.setString(3, materno + "%");
            cs.setString(4, nombre + "%");

            rs = cs.executeQuery();

            while (rs.next()) {
                per = new personaC();
                per.setPersona(rs.getString("alumno"));
                per.setPaterno(rs.getString("apellido_paterno"));
                per.setMaterno(rs.getString("apellido_materno"));
                per.setNombres(rs.getString("nombres"));
                per.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                per.setTelefono(rs.getString("telefono"));
                per.setCelular(rs.getString("telefono_celular"));
                per.setEmailP(rs.getString("email_principal"));
                per.setEmailS(rs.getString("email_opcional"));
                per.setTipodocumento(rs.getInt("tipo_documento"));
                per.setNumero_documento(rs.getString("nro_documento"));
                lista.add(per);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public personaC busquedaAlumnocodigo(int institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC per =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select A.* from UPA.DAT_PERSONAS A ,SIGU.ACA_ALUMNO B \n"
                    + " WHERE A.PERSONA=B.PERSONA AND  B.INSTITUCION=? AND B.ALUMNO=?");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {

                per = new personaC();
                per.setPersona(rs.getString("persona"));
                per.setPaterno(rs.getString("apellido_paterno"));
                per.setMaterno(rs.getString("apellido_materno"));
                per.setNombres(rs.getString("nombres"));
                per.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                per.setTelefono(rs.getString("telefono"));
                per.setCelular(rs.getString("telefono_celular"));
                per.setEmailP(rs.getString("email_principal"));
                per.setEmailS(rs.getString("email_opcional"));
                per.setTipodocumento(rs.getInt("tipo_documento"));
                per.setNumero_documento(rs.getString("nro_documento"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

        }
        return per;
    }

    public personaC CodigoPersonaPersonal(String personal) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC per = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select P.* from UPA.DAT_PERSONAS AS P,[SIGU].[PER_PERSONAL] AS PL\n"
                    + "WHERE P.PERSONA=PL.PERSONA AND PL.PERSONAL=?");
            cs.setString(1, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                per = new personaC();
                per.setPersona(rs.getString("persona"));
                per.setPaterno(rs.getString("apellido_paterno"));
                per.setMaterno(rs.getString("apellido_materno"));
                per.setNombres(rs.getString("nombres"));
                per.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                per.setTelefono(rs.getString("telefono"));
                per.setCelular(rs.getString("telefono_celular"));
                per.setEmailP(rs.getString("email_principal"));
                per.setEmailS(rs.getString("email_opcional"));
                per.setTipodocumento(rs.getInt("tipo_documento"));
                per.setNumero_documento(rs.getString("nro_documento"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

        }
        return per;
    }

    public List<personaC> BuscaPersonaPeriodoInstitucion(int institucion, String periodo) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC per ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT DISTINCT C.*  FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,[SIGU].[PER_PERSONAL] B ,UPA.DAT_PERSONAS C  WHERE A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA AND A.INSTITUCION=? AND A.PERIODO=? AND A.ESTADO_REGISTRO in(1,59)  ORDER BY C.APELLIDO_PATERNO, C.APELLIDO_MATERNO;");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            rs = cs.executeQuery();

            while (rs.next()) {

                per = new personaC();
                per.setPersona(rs.getString("persona"));
                per.setPaterno(rs.getString("apellido_paterno"));
                per.setMaterno(rs.getString("apellido_materno"));
                per.setNombres(rs.getString("nombres"));
                per.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                per.setTelefono(rs.getString("telefono"));
                per.setCelular(rs.getString("telefono_celular"));
                per.setEmailP(rs.getString("email_principal"));
                per.setEmailS(rs.getString("email_opcional"));
                per.setTipodocumento(rs.getInt("tipo_documento"));
                per.setNumero_documento(rs.getString("nro_documento"));
                lista.add(per);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<personaC> BuscaPersonaPeriodo(int institucion, String periodo) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC per ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT DISTINCT C.* FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,[SIGU].[PER_PERSONAL] B ,UPA.DAT_PERSONAS C WHERE A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA AND A.INSTITUCION=? AND A.PERIODO=? AND A.ESTADO_REGISTRO in(1,59)  ORDER BY C.APELLIDO_PATERNO, C.APELLIDO_MATERNO;");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            rs = cs.executeQuery();

            while (rs.next()) {

                per = new personaC();
                per.setPersona(rs.getString("persona"));
                per.setPaterno(rs.getString("apellido_paterno"));
                per.setMaterno(rs.getString("apellido_materno"));
                per.setNombres(rs.getString("nombres"));
                per.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                per.setTelefono(rs.getString("telefono"));
                per.setCelular(rs.getString("telefono_celular"));
                per.setEmailP(rs.getString("email_principal"));
                per.setEmailS(rs.getString("email_opcional"));
                per.setTipodocumento(rs.getInt("tipo_documento"));
                per.setNumero_documento(rs.getString("nro_documento"));
                lista.add(per);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    

    public List<personaC> BuscaPersonaPeriodoCarrera(int periodo, String carrera) {
        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        personaC per ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT distinct A.ALUMNO persona, C.APELLIDO_PATERNO ,C.APELLIDO_MATERNO ,C.NOMBRES \n"
                    + "   FROM SIGU.ACA_ALUMNO_CURSO_SECCION A  ,SIGU.ACA_ALUMNO B,UPA.DAT_PERSONAS C\n"
                    + "   WHERE A.INSTITUCION=1 AND  A.PERIODO=? AND A.CARRERA=? AND B.ALUMNO =A.ALUMNO \n"
                    + "   AND B.PERSONA=C.PERSONA  order by 2");
            cs.setInt(1, periodo);
            cs.setString(2, carrera);
            rs = cs.executeQuery();

            while (rs.next()) {

                per = new personaC();
                per.setPersona(rs.getString("persona"));
                per.setPaterno(rs.getString("apellido_paterno").toUpperCase());
                per.setMaterno(rs.getString("apellido_materno").toUpperCase());
                per.setNombres(rs.getString("nombres").toUpperCase());
                lista.add(per);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
           
        }
        return lista;
    }

    public personaC BuscaPersonaAlumnoCurso(int institucion, int periodo, String alumno, String curso) {  // OJO SOLO SELECCIONA EL PRIMER REGISTRO--    

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 1 E.* FROM SIGU.ACA_ALUMNO_PERIODO A,SIGU.ACA_ALUMNO_CURSO_SECCION B,SIGU.HOR_CURSO_SECCION_DOCENTE C,SIGU.PER_PERSONAL D,UPA.DAT_PERSONAS E\n"
                    + "  WHERE A.INSTITUCION=B.INSTITUCION AND A.PERIODO=B.PERIODO AND A.ALUMNO=B.ALUMNO\n"
                    + "  AND A.INSTITUCION=C.INSTITUCION AND A.PERIODO=C.PERIODO AND B.CURSO=C.CURSO AND B.SECCION=C.SECCION\n"
                    + "  AND C.PERSONAL=D.PERSONAL\n"
                    + "  AND E.PERSONA=D.PERSONA\n"
                    + "  AND B.INSTITUCION=? AND B.PERIODO=? AND B.ALUMNO=? AND B.CURSO=? AND A.ESTADO_REGISTRO=5 AND B.ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, alumno);
            cs.setString(4, curso);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO").toUpperCase());
                item.setMaterno(rs.getString("APELLIDO_MATERNO").toUpperCase());
                item.setNombres(rs.getString("NOMBRES").toUpperCase());
                item.setEmailP(rs.getString("EMAIL_PRINCIPAL").toUpperCase());

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return item;
    }

    public List<personaC> mostrarDocenteMarcacion(String periodo, String carrera, String tipo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_BUSQUEDA_PERSONA_MARCACION(?,?,?)}");
            cs.setString(1, periodo);
            cs.setString(2, carrera);
            cs.setString(3, tipo);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("CPERSONAL"));
                item.setPaterno(rs.getString("DOCENTE"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<personaC> mostrarPersonalCarrera(String institucion, String periodo, String carrera) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select distinct A.PERSONAL,C.APELLIDO_PATERNO , C.APELLIDO_MATERNO , C.NOMBRES  from SIGU.HOR_CURSO_SECCION_DOCENTE A ,SIGU.PER_PERSONAL B, UPA.DAT_PERSONAS C WHERE A.INSTITUCION=? AND A.PERIODO=? AND CARRERA LIKE ? AND A.PERSONAL=B.PERSONAL AND B.PERSONA=C.PERSONA AND A.ESTADO_REGISTRO IN(1,59) ORDER BY 2");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONAL").toUpperCase());
                item.setPaterno(rs.getString("APELLIDO_PATERNO").toUpperCase());
                item.setMaterno(rs.getString("APELLIDO_MATERNO").toUpperCase());
                item.setNombres(rs.getString("NOMBRES").toUpperCase());

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error personal carrera " + e.getMessage());
        }
        return lista;
    }
    
    
    public List<personaC> mostrarPersonaCarrera(int institucion, String periodo, String carrera) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select distinct C.PERSONA,C.APELLIDO_PATERNO , C.APELLIDO_MATERNO , C.NOMBRES  from SIGU.HOR_CURSO_SECCION_DOCENTE A ,SIGU.PER_PERSONAL B, UPA.DAT_PERSONAS C WHERE A.INSTITUCION=? AND A.PERIODO=? AND CARRERA LIKE ? AND A.PERSONAL=B.PERSONAL AND B.PERSONA=C.PERSONA AND A.ESTADO_REGISTRO IN(1,59) ORDER BY 2");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA").toUpperCase());
                item.setPaterno(rs.getString("APELLIDO_PATERNO").toUpperCase());
                item.setMaterno(rs.getString("APELLIDO_MATERNO").toUpperCase());
                item.setNombres(rs.getString("NOMBRES").toUpperCase());

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public List<personaC> mostrarPersonalTurno(String institucion, String periodo, String turno) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT distinct(B.PERSONAL),B.PERSONA,C.APELLIDO_PATERNO , C.APELLIDO_MATERNO ,C.NOMBRES \n" +
                                "FROM DI.REGISTRO_DOCENTE_TM A ,SIGU.PER_PERSONAL B ,UPA.DAT_PERSONAS C WHERE A.INSTITUCION LIKE ? AND A.PERIODO=?  AND  A.TURNO like ? AND A.CPERSONAL=B.CPERSONAL AND C.PERSONA=B.PERSONA AND A.ESTADO_REGISTRO=1\n" +
                                "ORDER BY C.APELLIDO_PATERNO , C.APELLIDO_MATERNO ,C.NOMBRES");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, turno);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONAL").toUpperCase());
                item.setPaterno(rs.getString("APELLIDO_PATERNO").toUpperCase());
                item.setMaterno(rs.getString("APELLIDO_MATERNO").toUpperCase());
                item.setNombres(rs.getString("NOMBRES").toUpperCase());

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error personal turno " + e.getMessage());
        }
        return lista;
    }
    
    
    

    public List<asistenciaEvento.detalle> mostrarAsistenciaEvento(String evento) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<asistenciaEvento.detalle> lista = new ArrayList<>();
        asistenciaEvento.detalle item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.PERSONA,B.APELLIDO_PATERNO +' '+ B.APELLIDO_MATERNO +' '+B.NOMBRES NOMBRES,'D.N.I' DOCUMENTO,B.NRO_DOCUMENTO,CONVERT(VARCHAR(13),A.HORA_ENTRADA,108)HORA_INGRESO\n"
                    + "FROM DI.EVEN_ASISTENCIA_PERSONA A,UPA.DAT_PERSONAS B WHERE A.EVENTO=?  AND A.PERSONA=B.PERSONA ORDER BY A.HORA_ENTRADA DESC");
            cs.setString(1, evento);

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new asistenciaEvento.detalle();
                item.setPersona(rs.getString("PERSONA"));
                item.setNombres(rs.getString("NOMBRES"));
                item.setDocumento(rs.getString("DOCUMENTO"));
                item.setNumero(rs.getString("NRO_DOCUMENTO"));
                item.setHora_ingreso(rs.getString("HORA_INGRESO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public boolean insertarAsistenciaEvento(String evento, String numero) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INSERTAR_ASISTENCIA_EVENTO(?,?)}");

            cs.setString(1, evento);
            cs.setString(2, numero);

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

    public boolean eliminaAsistenciaEvento(String evento, String persona) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM DI.EVEN_ASISTENCIA_PERSONA WHERE  EVENTO=? AND PERSONA=?");

            cs.setString(1, evento);
            cs.setString(2, persona);

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

    public boolean insertarNotaEvento(int institucion, int periodo, String paquete, String persona, String examen, String nota) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INSERTA_PERSONA_EVENTO_NOTA(?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, paquete);
            cs.setString(4, persona);
            cs.setString(5, examen);
            cs.setString(6, nota);

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

    
    
    //----------------- PERSONAL DOCENTE ---------------------------
    public List<personaC> mostrarPersonalxCarrera(int institucion, String periodo, String carrera) {

        List<personaC> lista = new ArrayList<>();
        Connection c  ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT C.* FROM SIGU.HOR_CURSO_SECCION_DOCENTE A,SIGU.PER_PERSONAL B ,UPA.DAT_PERSONAS C WHERE B.PERSONAL=A.PERSONAL AND B.PERSONA=C.PERSONA AND \n"
                    + "A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? and A.ESTADO_REGISTRO=1 ORDER BY 2,3,4");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setNumero_documento(rs.getString("nro_documento"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
    //----------------- PERSONAL DOCENTE ---------------------------
    public List<personaC> mostrarTipoPersona(int tipoPersona) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.* FROM SIGU.PER_PERSONAL A,SIGU.PER_TIPO_PERSONAL B,UPA.DAT_PERSONAS C WHERE A.PERSONAL=B.PERSONAL AND  A.PERSONA=C.PERSONA AND C.ESTADO_REGISTRO=1 AND B.TIPO_PERSONA=? order by 2,3,4");
            cs.setInt(1, tipoPersona);
           

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setNumero_documento(rs.getString("nro_documento"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
    
    public List<personaC> mostrarTipoPersona(int institucion,int tipoPersona) {

        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.* FROM SIGU.PER_PERSONAL A,SIGU.PER_TIPO_PERSONAL B,UPA.DAT_PERSONAS C WHERE A.PERSONAL=B.PERSONAL AND  A.PERSONA=C.PERSONA AND C.ESTADO_REGISTRO=1 AND B.INSTITUCION=? AND  B.TIPO_PERSONA=? order by 2,3,4");
            cs.setInt(1, institucion);
            cs.setInt(2, tipoPersona);
           

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("direccion_ubigeo"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setCentro_trabajo(rs.getString("CENTRO_TRABAJO"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setNumero_documento(rs.getString("nro_documento"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
    
    
    
    
    public List<personaC> mostrarPersonaOficina(int institucion, int oficina) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.* FROM SIGU.PER_PERSONAL_OFICINA A,SIGU.PER_PERSONAL B,UPA.DAT_PERSONAS C WHERE A.INSTITUCION=? AND A.OFICINA=? AND B.PERSONAL=A.PERSONAL AND C.PERSONA=B.PERSONA ORDER BY C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES");
            cs.setInt(1, institucion);
            cs.setInt(2, oficina);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
    
    
    
    public List<personaC> mostrarPersonaChat(int tipoTrabajador, int oficina) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<personaC> lista = new ArrayList<>();
        personaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select B.* from sigu.PER_PERSONAL A,UPA.DAT_PERSONAS B,SIGU.PER_OFICINA C\n" +
                               "where A.TIPO_TRABAJADOR=? AND A.PERSONA=B.PERSONA AND A.OFICINA=C.OFICINA  AND A.OFICINA=?");
            cs.setInt(1, tipoTrabajador);
            cs.setInt(2, oficina);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
    
    
    
    
    
    
    
    public List<personaC> mostrarPersonaResponsableFirma() {
        List<personaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_RESPONSABLE_FIRMA(1)}");
            
           

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new personaC();
                item.setPersona(rs.getString("PERSONA"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
    
public List<cuentaPersona.personaInstitucion> cuentaPersonaInstituciones(String persona) {
        List<cuentaPersona.personaInstitucion> per = new ArrayList<>();
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        try {
            c = ConexionWeb();
            /*cs = c.prepareCall("{CALL [SIGU].[CON_PERSONA_INSTITUCION](1,?,'')}");*/
            cs = c.prepareCall("SELECT \n"
                    + "DISTINCT TCP.INSTITUCION,SI.DESCRIPCION,SI.ABREVIATURA,AA.ALUMNO,AAC.CARRERA,AC.DESCRIPCION DESCARRERA\n"
                    + "FROM SIGU.TES_CUENTA_PERSONA TCP JOIN SIGU.ACA_ALUMNO AA\n"
                    + "ON TCP.PERSONA=AA.PERSONA AND AA.INSTITUCION=TCP.INSTITUCION JOIN SIGU.ACA_ALUMNO_CARRERA AAC\n"
                    + "ON AA.INSTITUCION=AAC.INSTITUCION AND AA.ALUMNO=AAC.ALUMNO JOIN UPA.SYS_INSTITUCION SI\n"
                    + "ON AA.INSTITUCION=SI.INSTITUCION JOIN UPA.ACA_CARRERAS AC\n"
                    + "ON AAC.CARRERA=AC.CARRERA\n"
                    + "WHERE\n"
                    + "TCP.PERSONA = ?\n"
                    + "ORDER BY TCP.INSTITUCION");

            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                cuentaPersona.personaInstitucion p = new cuentaPersona.personaInstitucion();
                p.setInstitucion(rs.getInt("INSTITUCION"));
                p.setDesc_inst(rs.getString("DESCRIPCION"));
                p.setAbrev_inst(rs.getString("ABREVIATURA"));
                p.setCod_alumno(rs.getString("ALUMNO"));
                p.setCarrera(rs.getString("CARRERA"));
                p.setDesc_carrera(rs.getString("DESCARRERA"));
                per.add(p);
            }
        } catch (SQLException e) {
            System.out.println("persona instituciones " + e.getMessage());
        }
        return per;
    }

public personaC mostrarPersonaJurado(int institucion,String periodo,String dni) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        personaC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select TOP 1 A.* from  UPA.DAT_PERSONAS A,DI.INV_PERSONA_JURADO B where B.PERSONA=A.PERSONA AND B.INSTITUCION=? AND B.PERIODO=? AND A.NRO_DOCUMENTO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, dni);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("persona"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_RECIDENCIA"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setSexo(rs.getInt("SEXO"));
                item.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                item.setNumero_documento(rs.getString("nro_documento"));
                item.setTelefono_emergencia_04(rs.getString("telefono_emergencia_04"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));
                item.setTipoVivienda(rs.getInt("TIPO_VIVIENDA"));
                item.setSituacionVivienda(rs.getInt("SITUACION_VIVIENDA"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }



    public personaC mostrarPersona_CPersonal( String CPersonal) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        personaC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM SIGU.PER_PERSONAL A,UPA.DAT_PERSONAS B WHERE A.PERSONA=B.PERSONA AND   A.CPERSONAL =  ? ");
            
            cs.setString(1, CPersonal);
         
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("persona"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_RECIDENCIA"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setSexo(rs.getInt("SEXO"));
                item.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                item.setNumero_documento(rs.getString("nro_documento"));
                item.setTelefono_emergencia_04(rs.getString("telefono_emergencia_04"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));
                item.setTipoVivienda(rs.getInt("TIPO_VIVIENDA"));
                item.setSituacionVivienda(rs.getInt("SITUACION_VIVIENDA"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }

    public personaC mostrarPersonaPuerta(String codigo){
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_PUERTA (1,?)}");
            
            cs.setString(1, codigo);
            
            
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaC();
                item.setPersona(rs.getString("persona"));
                item.setPaterno(rs.getString("apellido_paterno"));
                item.setMaterno(rs.getString("apellido_materno"));
                item.setNombres(rs.getString("nombres"));
                item.setDireccion_ubigeo(rs.getString("DIRECCION_RECIDENCIA"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("telefono_celular"));
                item.setEmailP(rs.getString("email_principal"));
                item.setEmailS(rs.getString("email_opcional"));
                item.setTipodocumento(rs.getInt("tipo_documento"));
                item.setSexo(rs.getInt("SEXO"));
                item.setTelefonoTrabajo(rs.getString("TELEFONO_TRABAJO"));
                item.setNumero_documento(rs.getString("nro_documento"));
                item.setTelefono_emergencia_04(rs.getString("telefono_emergencia_04"));
                item.setEstado_civil(rs.getInt("ESTADO_CIVIL"));
                item.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));
                item.setTipoVivienda(rs.getInt("TIPO_VIVIENDA"));
                item.setSituacionVivienda(rs.getInt("SITUACION_VIVIENDA"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        }
        return item;
    }
    
    
    public boolean validaDatosPersona(String persona){
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        boolean bandera = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CONTROL_DATOS_PERSONA (1,?)}");            
            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {              
            	bandera=true;
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return bandera;
    }
    
    public boolean validaNumeroDocumento(String numero,String persona){
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        boolean bandera = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.DAT_PERSONAS WHERE NRO_DOCUMENTO=? AND PERSONA NOT IN (?)");            
            cs.setString(1, numero);
            cs.setString(2, persona);
            rs = cs.executeQuery();
            while (rs.next()) {              
            	bandera=true;
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return bandera;
    }
    
   

}
