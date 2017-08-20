/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoPersonaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import java.sql.SQLException;


public class tipoPersonaDAO {
    public List<tipoPersonaC> mostrarTipoPersona() {

        List<tipoPersonaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoPersonaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.SYS_TIPO_PERSONA");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoPersonaC();
                item.setTipoPersona(rs.getInt("TIPO_PERSONA"));
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
    
    public tipoPersonaC mostrarTipoPersona(String persona) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoPersonaC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT D.* FROM UPA.DAT_PERSONAS A,SIGU.PER_PERSONAL B ,SIGU.PER_TIPO_PERSONAL C,SIGU.SYS_TIPO_PERSONA D WHERE C.INSTITUCION=1  AND   A.PERSONA=? AND B.PERSONA=A.PERSONA AND C.PERSONAL=B.PERSONAL AND D.TIPO_PERSONA=C.TIPO_PERSONA");
            cs.setString(1, persona);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoPersonaC();
                item.setTipoPersona(rs.getInt("TIPO_PERSONA"));
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
