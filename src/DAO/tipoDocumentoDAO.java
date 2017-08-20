/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoDocumentoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tipoDocumentoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public tipoDocumentoC mostrarTipoEnfermedad(String codigo) {

        Connection c;
        CallableStatement cs;
        ResultSet rs ;
        tipoDocumentoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.SYS_TIPOS_DOCUMENTOS WHERE TIPO_DOCUMENTO=? ");
            cs.setString(1, codigo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoDocumentoC();
                item.setTipoDocumento(rs.getInt("TIPO_DOCUMENTO"));
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
    
    public List<tipoDocumentoC> mostrarTipoDocumento(boolean persona) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoDocumentoC item ;
        List<tipoDocumentoC> lista =new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.SYS_TIPOS_DOCUMENTOS WHERE PERSONA=? ");
            cs.setBoolean(1, persona);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoDocumentoC();
                item.setTipoDocumento(rs.getInt("TIPO_DOCUMENTO"));
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
    
    public List<tipoDocumentoC> mostrarTipoDocumento(int estado) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoDocumentoC item ;
        List<tipoDocumentoC> lista =new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.SYS_TIPOS_DOCUMENTOS WHERE ESTADO_REGISTRO=? ");
            cs.setInt(1, estado);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoDocumentoC();
                item.setTipoDocumento(rs.getInt("TIPO_DOCUMENTO"));
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
