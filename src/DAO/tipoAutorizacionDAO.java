/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoAutorizacionC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class tipoAutorizacionDAO {
    public List<tipoAutorizacionC> mostrarTipoAutorizacion() {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoAutorizacionC item ;
        List<tipoAutorizacionC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.SYS_TIPO_AUTORIZACION WHERE ESTADO_REGISTRO=1");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoAutorizacionC();
                item.setTipoAutorizacion(rs.getInt("TIPO_AUTORIZACION"));
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
