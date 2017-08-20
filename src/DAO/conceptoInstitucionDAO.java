package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Conexiones.Conexion;

import ENTIDAD.conceptoInstitucionC;

public class conceptoInstitucionDAO extends Conexion{
	public String insertar(conceptoInstitucionC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TES_CONCEPTOS_INSTITUCION(1,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getConcepto());
            cs.setInt(3, item.getTipoConcepto());            
            cs.setBoolean(4, item.isCategoria());
            cs.setInt(5, item.getEstadoRegistro());

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
	public String eliminar(conceptoInstitucionC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TES_CONCEPTOS_INSTITUCION(2,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getConcepto());
            cs.setInt(3, item.getTipoConcepto());            
            cs.setBoolean(4, item.isCategoria());
            cs.setInt(5, item.getEstadoRegistro());

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
	
	public List<conceptoInstitucionC> mostrarConceptoInstitucion(int tipoConcepto,String concepto) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        conceptoInstitucionC item ;
        List<conceptoInstitucionC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  *FROM SIGU.TES_CONCEPTOS_INSTITUCION WHERE TIPO_CONCEPTO=? AND CONCEPTO=?");
            cs.setInt(1, tipoConcepto);
            cs.setString(2, concepto);
            rs = cs.executeQuery();

            while (rs.next()) {
               
                item = new conceptoInstitucionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setCategoria(rs.getBoolean("CC_X_CATEGORIA"));                
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
