
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.personalDocenteC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class personalDocenteDAO {
     public String insertar(personalDocenteC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo="";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PER_PERSONAL_DOCENTE (?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("CPERSONAL", item.getCpersonal());
            cs.setString("PERSONAL", item.getPersonal());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("MODALIDAD", item.getModalidad());
            cs.setString("INGRESO", util.convertDate(item.getIngreso(), "dd-MM-yyyy") );
            cs.setBoolean("VIGENCIA", item.isVigencia());
            cs.setString("REGIMEN", item.getRegimen());
            cs.setInt("CATEGORIA", item.getCategoria());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setBoolean("DINA", item.isDina());
            cs.setBoolean("INVESTIGA", item.isVigencia());            
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());          
            cs.registerOutParameter("CPERSONAL", Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
                codigo = cs.getString("CPERSONAL");
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
     public String eliminar(personalDocenteC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo="";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PER_PERSONAL_DOCENTE (?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setString("CPERSONAL", item.getCpersonal());
            cs.setString("PERSONAL", item.getPersonal());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("MODALIDAD", item.getModalidad());
            cs.setString("INGRESO", util.convertDate(item.getIngreso(), "dd-MM-yyyy") );
            cs.setBoolean("VIGENCIA", item.isVigencia());
            cs.setString("REGIMEN", item.getRegimen());
            cs.setInt("CATEGORIA", item.getCategoria());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setBoolean("DINA", item.isDina());
            cs.setBoolean("INVESTIGA", item.isVigencia());            
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());           
            cs.registerOutParameter("CPERSONAL", Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
                codigo=cs.getString("CPERSONAL"); 
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
     
     
     
     public personalDocenteC mostrarPersonalDocente(String personal) {

        Connection c ;
        CallableStatement cs;
        ResultSet rs;
        personalDocenteC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.PER_PERSONAL_DOCENTE WHERE PERSONAL=?");
            cs.setString(1, personal);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personalDocenteC();
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setModalidad(rs.getString("MODALIDAD"));
                item.setIngreso(rs.getDate("INGRESO"));
                item.setVigencia(rs.getBoolean("VIGENCIA"));
                item.setRegimen(rs.getString("REGIMEN"));
                item.setCategoria(rs.getInt("CATEGORIA"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setDina(rs.getBoolean("DINA"));
                item.setInvestiga(rs.getBoolean("INVESTIGA"));                
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
