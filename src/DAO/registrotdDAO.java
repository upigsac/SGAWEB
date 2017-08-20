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
import ENTIDAD.registrotdC;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class registrotdDAO {
    public String insertar(registrotdC item) {
        
       
        Connection c ;
        CallableStatement cs;
       
        String cadena = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  [DI].[SP_MANTEMIENTO_REGISTRO_TM] (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,? ,?,?,?,?,? )}");
            
            cs.setInt(1, item.getId());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setInt(4, item.getDia());
            cs.setInt(5, item.getSemana());
            cs.setString(6, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString(7, util.convertDate(item.getHora_ing(),"HH:mm"));
            cs.setString(8, util.convertDate(item.getHora_sal(),"HH:mm"));
            cs.setString(9, item.getCpersonal());
            cs.setString(10, item.getCarrera());
            cs.setString(11, item.getCurso());
            cs.setString(12, item.getSeccion());
            cs.setInt(13, item.getTurno());
            cs.setString(14, item.getCiclo());
            cs.setString(15, item.getAula());
            cs.setString(16, item.getTipo());
            cs.setInt(17, item.getSesion());
            cs.setString(18, item.getTema());
            cs.setString(19, item.getHora_ingreso());
            cs.setString(20, item.getHora_salida());
            cs.setInt(21, item.getTardanza());
            cs.setInt(22, item.getEstudiante());
            cs.setInt(23, item.getTipoHora());
            cs.setInt(24, item.getTipoClase());
            cs.setInt(25, item.getEstadoRegistro());
            
             cs.executeUpdate();
            c.commit();
            
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error guardar registroTD:  " + e.getMessage());
            
        }
        return cadena;
    }
    
    
    public registrotdC mostrarRegistrotd(int institucion, String periodo, String carrera, String curso, String seccion,Date fecha) {
      
        
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        registrotdC item =null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.REGISTRO_DOCENTE_TD WHERE INSTITUCION=? AND  PERIODO=? AND CARRERA=? AND CURSO=? AND SECCION=? AND FECHA_INGRESO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, util.convertDate(fecha, "dd-MM-yyyy"));

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new registrotdC();
                item.setId(rs.getInt("ID"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setFecha(rs.getDate("FECHA_INGRESO"));
                item.setDia(rs.getInt("DIA"));               
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));                
                item.setTurno(rs.getInt("TURNO"));
                item.setAula(rs.getString("AULA"));
                item.setTipo(rs.getString("TIPO"));
                item.setTema(rs.getString("TEMA_DESARROLLADO"));
                item.setTipoHora(rs.getInt("TIPO_HORA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return item;
    }
    
    
public List<registrotdC> mostrarRegistrotd(int institucion, String periodo, String carrera, String curso, String seccion) {
      
        
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        registrotdC item =null;
        List<registrotdC> lista=new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_REGISTRO_TD (1,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new registrotdC();
                item.setId(rs.getInt("ID"));
                item.setPadre(rs.getInt("PADRE"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setFecha(rs.getDate("FECHA_INGRESO"));
                item.setHora_ing(rs.getTimestamp("HOR_ING"));
                item.setHora_sal(rs.getTimestamp("HOR_SAL"));                
                item.setDia(rs.getInt("DIA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));                
                item.setTurno(rs.getInt("TURNO"));
                item.setAula(rs.getString("AULA"));
                
                item.setTema(rs.getString("TEMA_DESARROLLADO"));
                item.setTipoHora(rs.getInt("TIPO_HORA"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
