package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;
import Conexiones.Conexion;
import ENTIDAD.centroCostoC;



public class centroCostoDAO extends Conexion{
	

	public String insertar(centroCostoC item) {
		Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CNT_CENTRO_COSTO(1,?,?,?,?,?,?)}");
            cs.setString(1, item.getCentroCosto());
            cs.setString(2, item.getCentroCostoPadre());
            cs.setInt(3, item.getNivel());
            cs.setString(4, item.getDescripcion());
            cs.setString(5, item.getAbreviatura());            
            cs.setInt(6, item.getEstadoRegistro());

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
	
	public String eliminar(centroCostoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CNT_CENTRO_COSTO(2,?,?,?,?,?,?)}");
            cs.setString(1, item.getCentroCosto());
            cs.setString(2, item.getCentroCostoPadre());
            cs.setInt(3, item.getNivel());
            cs.setString(4, item.getDescripcion());
            cs.setString(5, item.getAbreviatura());
            
            cs.setInt(6, item.getEstadoRegistro());

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

	
	
	public List<centroCostoC> mostrarCentroCosto() {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        centroCostoC item ;
        List<centroCostoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select  *from SIGU.CNT_CENTRO_COSTO ORDER BY DESCRIPCION");
          
            rs = cs.executeQuery();

            while (rs.next()) {
               
                item = new centroCostoC();
                item.setCentroCosto(rs.getString("CENTRO_COSTO"));
                item.setCentroCostoPadre(rs.getString("CENTRO_COSTO_PADRE"));
                item.setNivel(rs.getInt("NIVEL"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                
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
	
	public List<centroCostoC> filtroCentroCosto(String costoPadre, String nivel,String descripcion) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        centroCostoC item ;
        List<centroCostoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  TOP 100 *FROM SIGU.CNT_CENTRO_COSTO  WHERE CENTRO_COSTO_PADRE LIKE ? AND NIVEL LIKE ? AND DESCRIPCION LIKE ? ORDER BY DESCRIPCION");
            cs.setString(1, "%" + costoPadre + "%");
            cs.setString(2, nivel);
            cs.setString(3, "%" + descripcion + "%");
            rs = cs.executeQuery();

            while (rs.next()) {
               
                item = new centroCostoC();
                item.setCentroCosto(rs.getString("CENTRO_COSTO"));
                item.setCentroCostoPadre(rs.getString("CENTRO_COSTO_PADRE"));
                item.setNivel(rs.getInt("NIVEL"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                
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
}
