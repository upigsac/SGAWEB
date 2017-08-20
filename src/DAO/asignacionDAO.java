
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ENTIDAD.asignacionC;


public class asignacionDAO {
    
	
	
    public List<asignacionC> mostrarComisionContrato(String personal,int contrato) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        asignacionC item ;
        List<asignacionC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_COMISION(1,?,?)}");
            cs.setString(1, personal);
            cs.setInt(2, contrato);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new asignacionC();
                item.setAsignacion(rs.getInt("ASIGNACION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
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
    
    
    
    
    public List<asignacionC> mostrarAsignacion(int comision) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        asignacionC item ;
        List<asignacionC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM DI.SYS_ASIGNACION A,DI.SYS_COMISION_ASIGNACION B WHERE B.ASIGNACION=A.ASIGNACION AND B.COMISION=?");
    
            cs.setInt(1, comision);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new asignacionC();
                item.setAsignacion(rs.getInt("ASIGNACION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
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
}
