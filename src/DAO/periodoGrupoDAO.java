/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.periodoGrupoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class periodoGrupoDAO {
     public periodoGrupoC mostrarPeriodoGrupo(int institucion, String periodo,String carrera,String seccion) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        periodoGrupoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.ACA_PERIODO_GRUPO A,SIGU.HOR_PERIODO_SECCION B  WHERE A.INSTITUCION=B.INSTITUCION AND B.PERIODO=A.PERIODO AND B.CARRERA=A.CARRERA AND B.NIVEL_MODULAR=A.NIVEL_MODULAR AND  B.INSTITUCION=? AND B.PERIODO=? AND B.CARRERA=? AND SECCION=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new periodoGrupoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setEstadoRegistro(rs.getInt("ESTAFO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
        }
        return item;
    }
}
