
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.oficinaC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class oficinaDAO implements Serializable {
	
    
	private static final long serialVersionUID = 1L;


	public List<oficinaC> mostrarOficinas(int institucion) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista = new ArrayList<>();
        oficinaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.PER_OFICINA WHERE INSTITUCION=? AND ESTADO_REGISTRO=1  ORDER BY DESCRIPCION");
            cs.setInt(1, institucion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
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
	
    public List<oficinaC> mostrarOficinas() {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista = new ArrayList<>();
        oficinaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.PER_OFICINA WHERE  ESTADO_REGISTRO=1  ORDER BY DESCRIPCION");
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
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
    public List<oficinaC> filtroOficinas(String institucion, String descripcion) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista = new ArrayList<>();
        oficinaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.PER_OFICINA WHERE INSTITUCION LIKE ? AND DESCRIPCION LIKE ? AND  ESTADO_REGISTRO=1  ORDER BY DESCRIPCION");
            cs.setString(1, institucion);
            cs.setString(2, descripcion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setClassIcon(rs.getString("CLASSICON"));
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
    
    public List<oficinaC> mostrarOficinas(int institucion,int tipoTrabajador) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista = new ArrayList<>();
        oficinaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select DISTINCT C.* from sigu.PER_PERSONAL A,SIGU.PER_OFICINA C where C.INSTITUCION=? and  A.TIPO_TRABAJADOR=? AND  A.OFICINA=C.OFICINA AND A.ESTADO_REGISTRO=1\n" +
                               "ORDER BY 2");
            cs.setInt(1, institucion);
            cs.setInt(2, tipoTrabajador);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setAnexo(rs.getString("ANEXO"));
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
    
    
    public List<oficinaC> mostrarOficinaTramite(int institucion,int tramite,int oficinaEmisor) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista = new ArrayList<>();
        oficinaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_TRAMITE_OFICINA(?,?)}");            
            cs.setInt(1, tramite);
            cs.setInt(2, oficinaEmisor);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setAnexo(rs.getString("ANEXO"));
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
    public List<oficinaC> mostrarOficinaTramite(int institucion,String expediente) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista = new ArrayList<>();
        oficinaC item ;

        try {

        	
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM DI.TRAM_TRAMITE_SEGUIMIENTO A,SIGU.PER_OFICINA B WHERE  A.EXPEDIENTE=? AND B.OFICINA=A.OFICINA ORDER BY ITEM");            
     
            cs.setString(1, expediente);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setAnexo(rs.getString("ANEXO"));
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
    
    public List<oficinaC> mostrarOficinaTramite(int institucion,String expediente,int estado) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista = new ArrayList<>();
        oficinaC item ;

        try {

        	
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM DI.TRAM_TRAMITE_SEGUIMIENTO A,SIGU.PER_OFICINA B WHERE  A.EXPEDIENTE=? AND A.ESTADO_REGISTRO=? AND B.OFICINA=A.OFICINA ORDER BY ITEM");            
     
            cs.setString(1, expediente);
            cs.setInt(2, estado);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setAnexo(rs.getString("ANEXO"));
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
    
    public oficinaC mostrarOficina(int institucion,int oficina) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        
        oficinaC item=null ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.PER_OFICINA  WHERE INSTITUCION=? AND OFICINA=?");
            cs.setInt(1, institucion);
            cs.setInt(2, oficina);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setAnexo(rs.getString("ANEXO"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
               
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	 System.out.println(e.getMessage());
        }
        return item;
    }
    
    public List<oficinaC> mostrarOficinaPersonal(String institucion,String personal ) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<oficinaC> lista=new ArrayList<>();
        oficinaC item=null ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM SIGU.PER_PERSONAL_OFICINA A,SIGU.PER_OFICINA B WHERE B.INSTITUCION=A.INSTITUCION AND B.OFICINA=A.OFICINA AND A.INSTITUCION LIKE ? AND A.PERSONAL=?");
            cs.setString(1, institucion);
            cs.setString(2, personal);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new oficinaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setAnexo(rs.getString("ANEXO"));
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
}
