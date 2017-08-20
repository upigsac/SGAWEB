/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.encuestaPreguntaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class encuestaPreguntaDAO {
    public List<encuestaPreguntaC> mostrarPregunta(int encuesta,int grupo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<encuestaPreguntaC> lista=new ArrayList<>();
        encuestaPreguntaC item=null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM DI.ENCUESTA_GRUPO_PREGUNTA A,DI.ENCUESTA_PREGUNTAS B WHERE B.PREGUNTA=A.PREGUNTA AND  A.ENCUESTA=? AND A.GRUPO=? ORDER BY A.ORDEN ");
            cs.setInt(1, encuesta);
            cs.setInt(2, grupo);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaPreguntaC();
                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                lista.add(item);
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
}
