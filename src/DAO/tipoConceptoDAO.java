/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoConceptoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tipoConceptoDAO implements Serializable {
    
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<tipoConceptoC> mostrarTipoConcepto() {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoConceptoC item ;
        List<tipoConceptoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.TES_TIPOS_CONCEPTOS");
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoConceptoC();
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
    public tipoConceptoC mostrarTipoConcepto(int tipoConcepto) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoConceptoC item = null;
       
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.TES_TIPOS_CONCEPTOS WHERE TIPO_CONCEPTO=?");
           cs.setInt(1, tipoConcepto);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoConceptoC();
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
