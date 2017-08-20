/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.carpetaInstitucionC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class carpetaInstitucionDAO {
    public List<carpetaInstitucionC> mostrarCarpetaInstitucion(int institucion, int periodo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carpetaInstitucionC item;
        List<carpetaInstitucionC> lista=new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.GES_CARPETA_INSTITUCION WHERE INSTITUCION=? AND PERIODO=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
           

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new carpetaInstitucionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarpeta(rs.getInt("CARPETA"));
                item.setPesoMaximo(rs.getInt("PERO_MAXIMO"));
                item.setPesoMinimo(rs.getInt("PESO_MINIMO"));
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
