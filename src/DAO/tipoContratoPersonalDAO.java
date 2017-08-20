package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexiones.Conexion;

import ENTIDAD.tipoContratoPersonalC;

public class tipoContratoPersonalDAO extends Conexion {
	public String insertar(tipoContratoPersonalC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TIPO_PERSONA_TIPO_CONTRATO(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("TIPO_CONTRATO", item.getTipoContrato());
            cs.setInt("TIPO_PERSONA", item.getTipoPersona());
            cs.setString("OBSERVACION", item.getObservacion());
            cs.setString("SECCION1", item.getSeccion1());
            cs.setString("SECCION2", item.getSeccion2());
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
	
	public String eliminar(tipoContratoPersonalC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TIPO_PERSONA_TIPO_CONTRATO(?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("TIPO_CONTRATO", item.getTipoContrato());
            cs.setInt("TIPO_PERSONA", item.getTipoPersona());
            cs.setString("OBSERVACION", item.getObservacion());
            cs.setString("SECCION1", item.getSeccion1());
            cs.setString("SECCION2", item.getSeccion2());
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());


            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
	
	
	
public tipoContratoPersonalC mostrarTipoContratoPersonal(int tipoPersonal,int tipoContrato) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoContratoPersonalC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.PER_TIPO_PERSONA_TIPO_CONTRATO WHERE TIPO_PERSONA=? AND TIPO_CONTRATO=?");
            cs.setInt(1, tipoPersonal);
            cs.setInt(2, tipoContrato);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoContratoPersonalC();
                item.setTipoContrato(rs.getInt("TIPO_CONTRATO"));
                item.setTipoPersona(rs.getInt("TIPO_PERSONA"));
                item.setSeccion1(rs.getString("SECCION_1"));
                item.setSeccion2(rs.getString("SECCION_2"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }
	
}
