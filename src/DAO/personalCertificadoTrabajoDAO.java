package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;
import ENTIDAD.personalCertificadoTrabajoC;


public class personalCertificadoTrabajoDAO {
	public boolean insertar(personalCertificadoTrabajoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_CERTIFICADO_TRABAJO(?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("CERTIFICADO", item.getCertificado());
            cs.setInt("TIPO", item.getTipo());
            cs.setInt("TIPO_PERSONAL", item.getTipoPersonal());
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("FECHA_INICIO", util.convertDate(item.getFechaInicio(), "dd-MM-yyyy") );
            cs.setString("FECHA_FINAL", util.convertDate(item.getFechaFinal(),"dd-MM-yyyy"));
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
        return rpta;
    }
	
	public boolean eliminar(personalCertificadoTrabajoC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_CERTIFICADO_TRABAJO(?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("CERTIFICADO", item.getCertificado());
            cs.setInt("TIPO", item.getTipo());
            cs.setInt("TIPO_PERSONAL", item.getTipoPersonal());
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("FECHA_INICIO", util.convertDate(item.getFechaInicio(), "dd-MM-yyyy") );
            cs.setString("FECHA_FINAL", util.convertDate(item.getFechaFinal(),"dd-MM-yyyy"));
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
        return rpta;
    }
	
	
	public List<personalCertificadoTrabajoC> mostrarPersonalCertificadoTrabajo(String personal) {
        List<personalCertificadoTrabajoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalCertificadoTrabajoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_PERSONAL_CERTIFICADO_TRABAJO WHERE PERSONAL=?");
            cs.setString(1, personal);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personalCertificadoTrabajoC();
                item.setCertificado(rs.getInt("CERTIFICADO"));
                item.setTipo(rs.getInt("TIPO"));
                item.setTipoPersonal(rs.getInt("TIPO_PERSONAL"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFinal(rs.getDate("FECHA_FINAL"));
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
