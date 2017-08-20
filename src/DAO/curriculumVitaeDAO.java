package DAO;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.primefaces.model.UploadedFile;
import Conexiones.Conexion;
import ENTIDAD.curriculumVitaeC;

public class curriculumVitaeDAO extends Conexion {

	
	public String insertar(curriculumVitaeC item) {
        String inserto = "";
        Connection c ;

        c = ConexionWeb();
        CallableStatement cs ;

        try {
       
        	cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PER_CURRICULUM_VITAE(?,?,?,?,?,?,?,?,?,?,?)} ");
        	cs.setInt("ITEMWEB", 1);
        	cs.setString("PERSONAL", item.getPersonal());
        	cs.setString("UBICACION", item.getUbicacion());
        	cs.setString("RUTA", item.getRuta());
        	cs.setInt("PESO", item.getPeso());
        	cs.setString("FORMATO", item.getFormato());
            cs.setBinaryStream("ARCHIVO",null);            
            cs.setInt("FOLIOS", item.getFolio());   
            cs.setInt("GRADO_FOLIO_DESDE", item.getFolioDesde());   
            cs.setInt("GRADO_FOLIO_HASTA", item.getFolioHasta());   
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
            if (cs.executeUpdate() == 1) {
                inserto = "true";
                
            } else {
                inserto = "false";
                
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
	
	
	public String insertar(curriculumVitaeC item, UploadedFile cv) throws IOException {
        String inserto = "";
        Connection c ;

        c = ConexionWeb();
        CallableStatement cs ;

        try {
        	FileInputStream streamEntrada=(FileInputStream) cv.getInputstream();
        	cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PER_CURRICULUM_VITAE(?,?,?,?,?,?,?,?,?,?,?)} ");
        	cs.setInt("ITEMWEB", 1);
        	cs.setString("PERSONAL", item.getPersonal());
        	cs.setString("UBICACION", item.getUbicacion());
        	cs.setString("RUTA", item.getRuta());
        	cs.setInt("PESO", item.getPeso());
        	cs.setString("FORMATO", item.getFormato());
            cs.setBinaryStream("ARCHIVO", streamEntrada, (int) cv.getSize());            
            cs.setInt("FOLIOS", item.getFolio());         
            cs.setInt("GRADO_FOLIO_DESDE", item.getFolioDesde());   
            cs.setInt("GRADO_FOLIO_HASTA", item.getFolioHasta());  
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
            if (cs.executeUpdate() == 1) {
                inserto = "true";
                
            } else {
                inserto = "false";
                
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
	
	
	public String eliminar(curriculumVitaeC item)  {
        String inserto = "";
        Connection c ;

        c = ConexionWeb();
        CallableStatement cs ;

        try {
        	
        	cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PER_CURRICULUM_VITAE(?,?,?,?,?,?,?,?,?,?,?)} ");
        	cs.setInt("ITEMWEB", 2);
        	cs.setString("PERSONAL", item.getPersonal());
        	cs.setString("UBICACION", item.getUbicacion());
        	cs.setString("RUTA", item.getRuta());
        	cs.setInt("PESO", item.getPeso());
        	cs.setString("FORMATO", item.getFormato()); 
        	cs.setBinaryStream("ARCHIVO", null); 
            cs.setInt("FOLIOS", item.getFolio());      
            cs.setInt("GRADO_FOLIO_DESDE", item.getFolioDesde());   
            cs.setInt("GRADO_FOLIO_HASTA", item.getFolioHasta()); 
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
            if (cs.executeUpdate() == 1) {
                inserto = "true";
                
            } else {
                inserto = "false";
                
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
	
	public curriculumVitaeC mostrarCurriculumVitae( String personal) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs;    
        curriculumVitaeC item =null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_CURRICULUM_VITAE WHERE PERSONAL=?");           
            cs.setString(1, personal);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new curriculumVitaeC();              
                item.setPersonal(rs.getString("PERSONAL"));
                item.setUbicacion(rs.getString("UBICACION"));
                item.setRuta(rs.getString("RUTA"));
                item.setFormato(rs.getString("FORMATO"));
                item.setPeso(rs.getInt("PESO"));
                item.setFolio(rs.getInt("FOLIOS"));
                item.setFolioHasta(rs.getInt("GRADO_FOLIO_HASTA"));
                item.setFolioDesde(rs.getInt("GRADO_FOLIO_DESDE"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));             
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return item;
    }
	
	
}
