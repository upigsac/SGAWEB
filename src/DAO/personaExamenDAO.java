/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;

import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.personaExamenC;
import ENTIDAD.personaExamenRespuestaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


public class personaExamenDAO {
    public String insertar(personaExamenC item,List<personaExamenRespuestaC> lista) {
           
        Connection c ;
        c = ConexionWeb();
        CallableStatement cs =null;
        CallableStatement css =null;
        String codigo = "";
        try {                   
            c.setAutoCommit(false);            
            cs = c.prepareCall(" {CALL DI.SP_MATENIMIENTO_EXAM_PERSONA(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona_examen());
            cs.setString(2, item.getApellido_paterno());
            cs.setString(3, item.getApellido_materno());
            cs.setString(4, item.getNombres());
            cs.setString(5, item.getDni());
            cs.setString(6, item.getTelefono_fijo());
            cs.setString(7, item.getTelefono_celular());
            cs.setString(8, item.getGrado_instruccion());
            cs.setString(9, item.getNombre_institucion());
            cs.setString(10, item.getCarerra());
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.executeUpdate() ;
            codigo=cs.getString(1);
            util.consolaCliente(codigo);
            
            for (personaExamenRespuestaC itemRespuesta : lista) {
                css = c.prepareCall("{CALL DI.SP_MATENIMIENTO_EXAM_PERSONA_RESPUESTAS(?,?,?,?,?)}");
                css.setInt(1, itemRespuesta.getExamen());
                css.setInt(2, itemRespuesta.getGrupo());
                css.setInt(3, itemRespuesta.getPregunta());
                css.setInt(4, itemRespuesta.getRespuesta());
                css.setString(5, codigo);
               css.executeUpdate()  ;
                
            }
            
            c.commit();   
            } catch (SQLException e) {           
              util.consolaCliente("erro" + e.getMessage());
             if (c != null) {
                try {
                    deshacerCambios(c);                    
                } catch (Exception ex) {
                    util.consolaCliente( ex.getMessage());                    
                }
            }
        }finally{
          
            cerrarConexion(c);
        }
        return codigo;
    }
}
