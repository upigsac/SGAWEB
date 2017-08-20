/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.resultadoPostulante;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class resultadoPostulanteDAO {
    public resultadoPostulante.postulanteC mostrarResultado(int institucion, String periodo, String postulante) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        resultadoPostulante.postulanteC item=null ;

      

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_RESULTADO_POSTULANTE (1,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, postulante);
            

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new  resultadoPostulante.postulanteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setPostulante(rs.getString("POSTULANTE"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                
                item.setDesEstado(rs.getString("DESESTADO"));
                
             

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
        }
        return item;
    }
}
