/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.grupoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class grupoDAO {
    public List<grupoC> mostrarGrupo(int examen) {
        Connection c ;
        List<grupoC> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        grupoC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A .* FROM DI.EXAM_GRUPO A,DI.EXAM_EXAMEN_GRUPO B WHERE  B.GRUPO=A.GRUPO AND B.EXAMEN=?");
            cs.setInt(1, examen);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new grupoC();
                item.setGrupo(rs.getInt("INSTITUCION"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
