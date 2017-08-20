/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoCentroEducativoC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tipoCentroEducativoDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<tipoCentroEducativoC> mostrarTipoCentroEducativo() {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoCentroEducativoC item ;
        List<tipoCentroEducativoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.SYS_TIPO_CENTRO_EDUCATIVO WHERE  TITULA=1 AND ESTADO_REGISTRO=1");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoCentroEducativoC();
                item.setTipoCentroEducativo(rs.getInt("TIPO_CENTRO_EDUCATIVO"));
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
    
    public tipoCentroEducativoC mostrarTipoCentroEducativo(int tipoCentroEducativo) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoCentroEducativoC item=null ;
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.SYS_TIPO_CENTRO_EDUCATIVO WHERE TIPO_CENTRO_EDUCATIVO=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, tipoCentroEducativo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoCentroEducativoC();
                item.setTipoCentroEducativo(rs.getInt("TIPO_CENTRO_EDUCATIVO"));
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
}
