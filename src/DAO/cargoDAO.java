/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cargoC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import java.io.Serializable;


public class cargoDAO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<cargoC> mostrarCargo() {

        List<cargoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cargoC carre ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.PER_CARGO ORDER BY 2");
            rs = cs.executeQuery();

            while (rs.next()) {
                carre = new cargoC();
                carre.setCargo(rs.getInt("CARGO"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    public cargoC mostrarCargo(int cargo) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cargoC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.PER_CARGO WHERE CARGO=? ORDER BY 2");
            cs.setInt(1, cargo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cargoC();
                item.setCargo(rs.getInt("CARGO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
    
    
    public int insertarCargo(cargoC item) {

        Connection c ;
        CallableStatement cs ;
        int codigo=0;
        boolean rpta ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_CARGO](?,?,?,?)}");
            cs.setInt(1, item.getCargo());
            cs.setString(2, item.getDescripcion());
            cs.setString(3, item.getAbreviatura());            
            cs.setInt(4, item.getEstadoRegistro());
            cs.registerOutParameter(1, Types.INTEGER);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
                codigo=cs.getInt(1);
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR MANTENIMIENTO CARGO " + e.getMessage());

            //e.printStackTrace();
        }
        return codigo;
    }
}
