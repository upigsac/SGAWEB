/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.carpetaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class carpetaDAO {
    public List<carpetaC> mostrarCarpeta(int institucion, String periodo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carpetaC item;
        List<carpetaC> lista=new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM  DI.GES_CARPETA A, DI.GES_CARPETA_INSTITUCION  B WHERE A.CARPETA=B.CARPETA AND  B.INSTITUCION=? AND B.PERIODO=? AND B.ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
           

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new carpetaC();
                
                item.setCarpeta(rs.getInt("CARPETA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        }
        return lista;
    }
}
