
package Conexiones;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public  class Conexion {    
    
       public static Connection ConexionWeb()  {   
        Connection c = null;      
        
          Properties propiedades = new Properties();
          InputStream entrada = null;
          
        try {
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            String usuario = (String) session.getAttribute("usuarioBD");
            String clave = (String) session.getAttribute("claveBD");            
                     
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          
            	 String ruta=Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"Conexiones/config.properties";
                 entrada = new FileInputStream(ruta.replace("%20"," "));
                  propiedades.load(entrada);
                if (usuario == null && clave == null ){    
                            
                			c = DriverManager.getConnection("jdbc:sqlserver://"+propiedades.getProperty("servidor")+":1433;databaseName="+ propiedades.getProperty("baseDato")+";applicationName=SGAWEB", propiedades.getProperty("usuario"), propiedades.getProperty("clave"));                               
        
                    }
                    else{          
                            c = DriverManager.getConnection("jdbc:sqlserver://"+propiedades.getProperty("servidor")+":1433;databaseName="+ propiedades.getProperty("baseDato")+";applicationName=SGAWEB", usuario, clave);              
                    } 
        
            
            
            
            
            
        } catch (ClassNotFoundException | SQLException e) {                 
        		System.out.println(e.getMessage());
                   
                           
        }  catch (FileNotFoundException ex) {
               Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println(ex.getMessage());
           } catch (IOException ex) {
               Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println(ex.getMessage());
           }
        finally{
        	
        }
        return c;
    }  
       
      
       
       
       
    public static synchronized void cerrarConexion(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
        }
    }

    public static synchronized void cerrarConexion(Connection cn) {
        try {
            cn.close();
        } catch (SQLException e) {
        }
    }

    public static synchronized void cerrarCall(CallableStatement cl) {
        try {
            cl.close();
        } catch (SQLException e) {
        }
    }

    public static synchronized void deshacerCambios(Connection cn) {
        try {
            cn.rollback();
        } catch (SQLException e) {
        }
    }

    

 

  
}
