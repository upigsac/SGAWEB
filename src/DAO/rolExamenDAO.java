package DAO;
import Conexiones.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ENTIDAD.rolExamenC;


public class rolExamenDAO extends Conexion {
	
	
	
	public String insertar(rolExamenC item) {     
		
		
		
		
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ROL_EXAMEN(1,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setInt(4, item.getVencimiento());
            cs.setString(5, item.getTipoExamen());
            cs.setString(6, item.getRuta());
            cs.setString(7, item.getArchivo());
            cs.setInt(8, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println("insertar rol examen :" + ex.getMessage());
        }
        return codigo;
    }
	
	
	

	public String eliminar(rolExamenC item) {     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ROL_EXAMEN(2,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setInt(4, item.getVencimiento());
            cs.setString(5, item.getTipoExamen());
            cs.setString(6, item.getRuta());
            cs.setString(7, item.getArchivo());
            cs.setInt(8, item.getEstadoRegistro());

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
	
	
	public List<rolExamenC> mostrarRolExamen(int institucion, String periodo ,String carrera,int vencimiento,String tipoExamen) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        rolExamenC item ;

        List<rolExamenC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.HOR_ROL_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=?  AND VENCIMIENTO=? AND TIPOEXAMEN=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setInt(4, vencimiento);
            cs.setString(5, tipoExamen);

            rs = cs.executeQuery();

            while (rs.next()) {

            	item = new rolExamenC();
            	item.setInstitucion(rs.getInt("INSTITUCION"));
            	item.setPeriodo(rs.getString("PERIODO"));
            	item.setCarrera(rs.getString("CARRERA"));
            	item.setVencimiento(rs.getInt("VENCIMIENTO"));
            	item.setTipoExamen(rs.getString("TIPOEXAMEN"));
            	item.setRuta(rs.getString("RUTA"));
            	item.setArchivo(rs.getString("ARCHIVO"));
            	item.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
	
}
