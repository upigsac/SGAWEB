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
import ENTIDAD.registroReprogramacionmC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class registroReprogramacionmDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String insertar(registroReprogramacionmC item) {

        
        Connection c ;
        CallableStatement cs ;
    
        String cadena = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_REPROGRAMACION (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getReprogracion());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCarrera());
            cs.setString(6, item.getCurso());
            cs.setString(7, item.getSeccion());
            cs.setInt(8, item.getTurno());
            cs.setString(9, item.getCiclo());
            cs.setString(10, item.getAula());
            cs.setString(11, item.getCpersonal());
            cs.setString(12, util.convertDate(item.getFechaReprogramacion()));
            cs.setString(13, util.convertTimes(item.getHoraInicio()));
            cs.setString(14, util.convertTimes(item.getHoraFin()));
            cs.setString(15, item.getFut());
            cs.setString(16, item.getObservacion());
            cs.setInt(17, item.getEstadoRegistro());

            cs.executeUpdate() ;
            c.commit();
            cadena = cs.getString(18);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error guardar:  " + e.getMessage());
            e.printStackTrace();
        }
        return cadena;
    }

    public List<registroReprogramacionmC> mostrarRecuperacionRegistrotm(int institucion, String periodo, int estado) {
        List<registroReprogramacionmC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        registroReprogramacionmC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.REGISTRO_DOCENTE_TM WHERE INSTITUCION=? AND PERIODO=? AND ESTADO_REGISTRO =? ORDER BY CREACION_FECHA");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, estado);

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new registroReprogramacionmC();
                item.setReprogracion(rs.getString("REPROGRAMACION"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setTurno(rs.getInt("TURNO"));
                item.setCiclo(rs.getString("CICLO"));
                item.setAula(rs.getString("AULA"));
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setFechaReprogramacion(rs.getDate("FECHA_INICIO"));
                item.setHoraInicio(rs.getTime("HOR_ING"));
                item.setHoraFin(rs.getTime("HOR_SAL"));

                item.setFut(rs.getString("FUT"));

                item.setObservacion(rs.getString("OBSERVACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( "erroress" + e.getMessage());
        }
        return lista;
    }
}
