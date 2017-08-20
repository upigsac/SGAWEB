
package DAO;

import Beans.mesaParte;
import Beans.tramTramitePersona;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.tramTramitePersonaC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;


public class tramTramitePersonaDAO implements Serializable        {
	
	
	
	private static final long serialVersionUID = 1L;

	public String insertar(tramTramitePersonaC item) {
    
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_PERSONA](1,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getExpediente());
            cs.setString(3, item.getFut());
            cs.setInt(4, item.getTipoTramite());
            cs.setString(5, item.getTipoPersona());
            cs.setString(6, item.getPersonaEmpresa());
            cs.setString(7, item.getAlumno());
            cs.setInt(8, item.getTramite());
            cs.setString(9, item.getObservacion());
            cs.setInt(10, item.getEstadoRegistro());
            
            cs.registerOutParameter(2, Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                codigo = cs.getString(2);
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
        	System.out.println(ex);
            
         
        }
        return codigo;
    }
	
	public String eliminar(tramTramitePersonaC item) {
	    
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_TRAM_TRAMITE_PERSONA](2,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getExpediente());
            cs.setString(3, item.getFut());
            cs.setInt(4, item.getTipoTramite());
            cs.setString(5, item.getTipoPersona());
            cs.setString(6, item.getPersonaEmpresa());
            cs.setString(7, item.getAlumno());
            cs.setInt(8, item.getTramite());
            cs.setString(9, item.getObservacion());
            cs.setInt(10, item.getEstadoRegistro());
            
            cs.registerOutParameter(2, Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                codigo = cs.getString(2);
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
        	System.out.println(ex);
        }
        return codigo;
    }
     
     public List<tramTramitePersonaC> mostrarTramitePersona(String institucion,String persona) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramitePersonaC> lista =new ArrayList<>();
        tramTramitePersonaC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_TRAMITE_PERSONA where INSTITUCION like ? AND PERSONA_EMPRESA LIKE ? ORDER BY EXPEDIENTE DESC  ");
            cs.setString(1, institucion);
            cs.setString(2, persona);
           
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersonaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setPersonaEmpresa(rs.getString("PERSONA_EMPRESA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTramite(rs.getInt("TRAMITE"));              
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                item.setHoraIngreso(rs.getTime("HORA_INGRESO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
               
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
        	System.out.println(e);
        }
        return lista;
    }
     
     public List<tramTramitePersonaC> mostrarTramitePersona(String persona,int tramite) {

         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         
         tramTramitePersonaC item =null;
         List<tramTramitePersonaC> lista=new ArrayList<>();
         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT *FROM  DI.TRAM_TRAMITE_PERSONA WHERE PERSONA_EMPRESA LIKE ? AND TRAMITE=? AND ESTADO_REGISTRO=1 ");
             cs.setString(1, persona);
             cs.setInt(2, tramite);
            
             
             rs = cs.executeQuery();

             while (rs.next()) {
                 item = new tramTramitePersonaC();                 
                 item.setInstitucion(rs.getInt("INSTITUCION"));
                 item.setPeriodo(rs.getString("PERIODO"));
                 item.setExpediente(rs.getString("EXPEDIENTE"));
                 item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                 item.setTipoPersona(rs.getString("TIPO_PERSONA"));
                 item.setPersonaEmpresa(rs.getString("PERSONA_EMPRESA"));
                 item.setAlumno(rs.getString("ALUMNO"));
                 item.setTramite(rs.getInt("TRAMITE"));              
                 item.setObservacion(rs.getString("OBSERVACION"));                
                 item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                 item.setHoraIngreso(rs.getTime("HORA_INGRESO"));
                 item.setFechaFin(rs.getDate("FECHA_FIN"));
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
     
     public tramTramitePersonaC mostrarTramitePersona(String expediente) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        tramTramitePersonaC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT   A.*FROM(SELECT ROW_NUMBER() OVER(ORDER BY  expediente) AS ITEM, * FROM   DI.TRAM_TRAMITE_PERSONA  )A    WHERE EXPEDIENTE = ?   ");
            cs.setString(1, expediente);
           
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersonaC();
                item.setItem(rs.getInt("ITEM"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setFut(rs.getString("FUT"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setTipoPersona(rs.getString("TIPO_PERSONA"));
                item.setPersonaEmpresa(rs.getString("PERSONA_EMPRESA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTramite(rs.getInt("TRAMITE"));              
                item.setObservacion(rs.getString("OBSERVACION"));                
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                item.setHoraIngreso(rs.getTime("HORA_INGRESO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            System.out.println(e.getMessage());
        }
        return item;
    }
     public tramTramitePersonaC mostrarUltimoTramitePersona() {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        tramTramitePersonaC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 1 A.*FROM(SELECT ROW_NUMBER() OVER(ORDER BY  expediente) AS ITEM, * FROM   DI.TRAM_TRAMITE_PERSONA  )A ORDER BY EXPEDIENTE  DESC");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersonaC();
                item.setItem(rs.getInt("ITEM"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setFut(rs.getString("FUT"));
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setTipoPersona(rs.getString("TIPO_PERSONA"));                
                item.setPersonaEmpresa(rs.getString("PERSONA_EMPRESA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTramite(rs.getInt("TRAMITE"));              
                item.setObservacion(rs.getString("OBSERVACION"));                
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                item.setHoraIngreso(rs.getTime("HORA_INGRESO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
     
     
     
     public tramTramitePersonaC mostrarDesplazamientoTramitePersona(int fila) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        tramTramitePersonaC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM (SELECT ROW_NUMBER() OVER(ORDER BY EXPEDIENTE ) AS ITEM, *FROM  di.TRAM_TRAMITE_PERSONA)A WHERE   A.ITEM =?");
            cs.setInt(1, fila);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersonaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setFut(rs.getString("FUT"));
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setPersonaEmpresa(rs.getString("PERSONA_EMPRESA"));
                item.setTipoPersona(rs.getString("TIPO_PERSONA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTramite(rs.getInt("TRAMITE"));              
                item.setObservacion(rs.getString("OBSERVACION"));                
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                item.setHoraIngreso(rs.getTime("HORA_INGRESO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
     
     public List<tramTramitePersonaC> mostrarFiltroTramitePersona(int opcion,String cadena) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramitePersonaC> lista=new ArrayList<>();
        tramTramitePersonaC item ;
        
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_FILTRO_TRAMITE(?,?)}");
            cs.setInt(1, opcion);
            cs.setString(2, cadena );
           
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersonaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setFut(rs.getString("FUT"));
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setPersonaEmpresa(rs.getString("PERSONA_EMPRESA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setTramite(rs.getInt("TRAMITE"));              
                item.setObservacion(rs.getString("OBSERVACION"));
                
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                item.setHoraIngreso(rs.getTime("HORA_INGRESO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
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
     
     
     public tramTramitePersona.personaEmpresa mostrarPersonaEmpresa(int opcion,String numero) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        tramTramitePersona.personaEmpresa item =null;
        
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_BUSQUEDA_PERSONA_TRAMITE(?,?)}");
            cs.setInt(1, opcion);
            cs.setString(2, numero );
           
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersona.personaEmpresa();
                item.setCodigo(rs.getString("CODIGO"));
                item.setNombre(rs.getString("NOMBRES"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setCorreo(rs.getString("EMAIL"));                
                
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
     
     
     
  
     
     public tramTramitePersona.personaEmpresa buscarTipoPersona(String tipoPersona,String codigo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        tramTramitePersona.personaEmpresa item =null;
        
        
        try {

            c = ConexionWeb();
            if (tipoPersona.endsWith("1")){
                cs = c.prepareCall("SELECT PERSONA CODIGO,NRO_DOCUMENTO NUMERO,APELLIDO_PATERNO +' '+ APELLIDO_MATERNO +' '+ NOMBRES NOMBRES,TELEFONO,TELEFONO_CELULAR,DIRECCION_RECIDENCIA DIRECCION,EMAIL_PRINCIPAL EMAIL FROM UPA.DAT_PERSONAS where  PERSONA = ? ");
            }else{
                cs = c.prepareCall("SELECT  EMPRESA CODIGO,RUC NUMERO,RAZON_SOCIAL NOMBRES,TELEFONO,DIRECCION,'' EMAIL,''TELEFONO_CELULAR FROM SIGU.DAT_EMPRESA  WHERE EMPRESA = ?");
            }
            cs.setString(1, codigo );
                       
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersona.personaEmpresa();
                item.setCodigo(rs.getString("CODIGO"));
                item.setNombre(rs.getString("NOMBRES"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("TELEFONO_CELULAR"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setCorreo(rs.getString("EMAIL"));                
                
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
            util.consolaCliente( e.getMessage());
            
        }
        return item;
    }
     public List<tramTramitePersona.personaEmpresa> filtroEmpresa(String numero) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramitePersona.personaEmpresa> lista=new ArrayList<>();
        tramTramitePersona.personaEmpresa item =null;
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 50 EMPRESA CODIGO,RUC NUMERO,RAZON_SOCIAL NOMBRES,TELEFONO,DIRECCION,'' EMAIL FROM SIGU.DAT_EMPRESA  WHERE RAZON_SOCIAL LIKE ?");
            
            cs.setString(1,"%" + numero +"%" );
           
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersona.personaEmpresa();
                item.setCodigo(rs.getString("CODIGO"));
                item.setNumero(rs.getString("NUMERO"));
                item.setNombre(rs.getString("NOMBRES"));
                item.setNombreWeb(rs.getString("NOMBRES").replaceAll(numero, "<b style='color:red'>"+ numero +"</b>"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setCorreo(rs.getString("EMAIL"));                
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
      public List<tramTramitePersona.personaEmpresa> filtroPersona(String nombre) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramitePersona.personaEmpresa> lista=new ArrayList<>();
        tramTramitePersona.personaEmpresa item =null;
        
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 50 PERSONA CODIGO,NRO_DOCUMENTO NUMERO,APELLIDO_PATERNO +' '+ APELLIDO_MATERNO +' '+ NOMBRES NOMBRES,TELEFONO,DIRECCION_RECIDENCIA DIRECCION,EMAIL_PRINCIPAL EMAIL FROM UPA.DAT_PERSONAS where  APELLIDO_PATERNO +' '+ APELLIDO_MATERNO +' '+ NOMBRES LIKE ?  COLLATE Modern_Spanish_CI_AI");
            
            cs.setString(1,"%" + nombre +"%" );
           
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramitePersona.personaEmpresa();
                item.setCodigo(rs.getString("CODIGO"));
                item.setNumero(rs.getString("NUMERO"));
                item.setNombre(rs.getString("NOMBRES"));
                item.setNombreWeb(rs.getString("NOMBRES").replaceAll(nombre, "<b style='color:red'>"+ nombre +"</b>"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setCorreo(rs.getString("EMAIL"));                
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
      
       public List<mesaParte.detalleTramite> filtroMesaParte(Date fechaInicio,Date fechaFin,String oficina,String busPersonaEmpresa) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<mesaParte.detalleTramite> lista=new ArrayList<>();
        mesaParte.detalleTramite item =null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_MESA_PARTE(?,?,?,?)}");
            cs.setString(1, util.convertDate(fechaInicio) );
            cs.setString(2, util.convertDate(fechaFin) );
            cs.setString(3, oficina );
            cs.setString(4, busPersonaEmpresa );
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new mesaParte.detalleTramite();
                item.setExpediente(rs.getString("EXPEDIENTE"));
                item.setDesTipoTramite(rs.getString("DESTIPOTRAMITE"));
                item.setDesTramite(rs.getString("DESTRAMITE"));
                item.setPersona(rs.getString("PERSONA_EMPRESA"));
                item.setDesPersona(rs.getString("DESPERSONAEMPRESA"));
                item.setDesOficinaActual(rs.getString("DESOFICINA"));
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));   
                item.setFechaEntrega(rs.getDate("FECHA_ENTREGA"));   
                item.setFechaFin(rs.getDate("FECHA_FIN"));   
                item.setNumeroDias(rs.getInt("DIAS"));   
                item.setHoraIngreso(rs.getTime("HORA_INGRESO"));                
                item.setFut(rs.getString("FUT"));                
                item.setObservacion(rs.getString("OBSERVACION"));                
                item.setDesEstadoTramite(rs.getString("DESESTADOREGISTRO"));                
                
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
           
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista;
    }

}
