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
import ENTIDAD.notaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class notaDAO {
    public List<notaC> mostrarNota() {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<notaC> lista = new ArrayList<>();
        notaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.PT_NOTA WHERE ESTADO_REGISTRO=1");            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new notaC();
                item.setNota(rs.getInt("NOTA"));
                item.setTitulo(rs.getString("TITULO"));
                item.setMensaje(rs.getString("MENSAJE"));
                item.setColor(rs.getString("COLOR"));
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
    
    public List<notaC> mostrarNota(int institucion,String periodo,String carrera,String grupo,String persona) {

        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        List<notaC> lista = new ArrayList<>();
        notaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_PT_NOTA_PERSONA (1,?,?,?,?,?)}");     
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, grupo);
            cs.setString(5, persona);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new notaC();
                item.setNota(rs.getInt("NOTA"));
                item.setTitulo(rs.getString("TITULO"));
                item.setMensaje(rs.getString("MENSAJE"));
                item.setColor(rs.getString("COLOR"));
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
