
package DAO;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.centroEducativoC;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


public class centroEducativoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 
    public String insertar(centroEducativoC item) {
        
       
        Connection c ;
        CallableStatement cs ;
        String codigo="";
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CENTRO_EDUCATIVO(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("CENTROEDUACTIVO", item.getCentroEducativo());            
            cs.setString("NOMBRE", item.getDescripcion());
            cs.setString("ABREVIATURA", item.getAbreviatura());            
            cs.setInt("REGIMEN", item.getRegimen());            
            cs.setInt("TIPOCENTRO", item.getTipo());            
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
            cs.registerOutParameter("CENTROEDUACTIVO", Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
                codigo=cs.getString("CENTROEDUACTIVO");
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());            
        }
        return codigo;
    }
    
    
    
    public String eliminar(centroEducativoC item) {
        
        
        Connection c ;
        CallableStatement cs ;
        String codigo="";
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CENTRO_EDUCATIVO(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setString("CENTROEDUACTIVO", item.getCentroEducativo());            
            cs.setString("NOMBRE", item.getDescripcion());
            cs.setString("ABREVIATURA", item.getAbreviatura());            
            cs.setInt("REGIMEN", item.getRegimen());            
            cs.setInt("TIPOCENTRO", item.getTipo());            
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
            cs.registerOutParameter("CENTROEDUACTIVO", Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
                codigo=cs.getString("CENTROEDUACTIVO");
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());            
        }
        return codigo;
    }

	

	public List<centroEducativoC> mostrarCentroEducativo(int tipo) {

        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        List<centroEducativoC> lista = new ArrayList<>();
        centroEducativoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.SYS_CENTRO_EDUCATIVO WHERE TIPO_CENTRO_EDUCATIVO=?  AND ESTADO_REGISTRO=1 ORDER BY NOMBRE");
            cs.setInt(1, tipo);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new centroEducativoC();
                item.setCentroEducativo(rs.getString("CENTRO_EDUCATIVO"));
                item.setDescripcion(rs.getString("NOMBRE"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setRegimen(rs.getInt("REGIMEN"));
                item.setTipo(rs.getInt("TIPO_CENTRO_EDUCATIVO"));
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
    
    public List<centroEducativoC> filtrarCentroEducativo(String descripcion) {
         
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<centroEducativoC> lista = new ArrayList<>();
        centroEducativoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 50 * FROM DI.SYS_CENTRO_EDUCATIVO WHERE NOMBRE LIKE ? ");
            cs.setString(1, "%"+descripcion + "%");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new centroEducativoC();
                item.setCentroEducativo(rs.getString("CENTRO_EDUCATIVO"));
                item.setDescripcion(rs.getString("NOMBRE"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setRegimen(rs.getInt("REGIMEN"));
                item.setTipo(rs.getInt("TIPO_CENTRO_EDUCATIVO"));
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
    
    
    public List<centroEducativoC> mostrarCentroEducativo() {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<centroEducativoC> lista = new ArrayList<>();
        centroEducativoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.SYS_CENTRO_EDUCATIVO ORDER BY TIPO_CENTRO_EDUCATIVO, NOMBRE  ");  
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new centroEducativoC();
                item.setCentroEducativo(rs.getString("CENTRO_EDUCATIVO"));
                item.setDescripcion(rs.getString("NOMBRE"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setRegimen(rs.getInt("REGIMEN"));
                item.setTipo(rs.getInt("TIPO_CENTRO_EDUCATIVO"));
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
    
    public List<centroEducativoC> mostrarCentroEducativo(int regimen,int tipo) {
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        centroEducativoC item ;
        List<centroEducativoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from DI.SYS_CENTRO_EDUCATIVO WHERE  ES_PRIVADO LIKE ? AND TIPO_CENTRO_EDUCATIVO=? AND ESTADO_REGISTRO=1");
            
            cs.setInt(1, regimen);
            cs.setInt(2, tipo);
            

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new centroEducativoC();
                item.setCentroEducativo(rs.getString("CENTRO_EDUCATIVO"));
                item.setDescripcion(rs.getString("NOMBRE"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setRegimen(rs.getInt("REGIMEN"));
                item.setTipo(rs.getInt("TIPO_CENTRO_EDUCATIVO"));
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
    
    public centroEducativoC mostrarCentroEducativo(String centroEducativo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        centroEducativoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from DI.SYS_CENTRO_EDUCATIVO where CENTRO_EDUCATIVO=?   AND ESTADO_REGISTRO=1");
            cs.setString(1, centroEducativo);
            
            

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new centroEducativoC();
                item.setCentroEducativo(rs.getString("CENTRO_EDUCATIVO"));
                item.setDescripcion(rs.getString("NOMBRE"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setRegimen(rs.getInt("REGIMEN"));
                item.setTipo(rs.getInt("TIPO_CENTRO_EDUCATIVO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           
        	 System.out.println(e.getMessage());
        }
        return item;
    }
   
}
