
package DAO;




import ENTIDAD.tipoContratoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexiones.Conexion;


public class tipoContratoDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public String insertar(tipoContratoC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TIPO_CONTRATO(?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("TIPO_CONTRATO", item.getTipoContrato());
            cs.setString("DESCRIPCION", item.getDescripcion());
            cs.setString("ABREVIATURA", item.getAbreviatura());
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
	public String eliminar(tipoContratoC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TIPO_CONTRATO(?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("TIPO_CONTRATO", item.getTipoContrato());
            cs.setString("DESCRIPCION", item.getDescripcion());
            cs.setString("ABREVIATURA", item.getAbreviatura());
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
	
	public List<tipoContratoC> mostrarTipoContrato() {

        List<tipoContratoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoContratoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.SYS_TIPO_CONTRATO  WHERE ESTADO_REGISTRO=1 ORDER BY DESCRIPCION");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoContratoC();
                item.setTipoContrato(rs.getInt("TIPO_CONTRATO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
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
    
    
    public List<tipoContratoC> mostrarTipoContratoTipoPersona(int tipoPersona) {

        List<tipoContratoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoContratoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM DI.SYS_TIPO_CONTRATO A,DI.PER_TIPO_PERSONA_TIPO_CONTRATO B WHERE A.TIPO_CONTRATO=B.TIPO_CONTRATO AND B.TIPO_PERSONA=? ORDER BY A.DESCRIPCION ");
            cs.setInt(1, tipoPersona);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoContratoC();
                item.setTipoContrato(rs.getInt("TIPO_CONTRATO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
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
    
    public List<tipoContratoC> mostrarTipoContratoNopTipoPersona(int tipoPersona) {

        List<tipoContratoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoContratoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.TIPO_CONTRATO,A.DESCRIPCION,A.ABREVIATURA,ISNULL(AA.ESTADO_REGISTRO,0)ESTADO_REGISTRO FROM DI.SYS_TIPO_CONTRATO A  LEFT JOIN DI.PER_TIPO_PERSONA_TIPO_CONTRATO  AA ON A.TIPO_CONTRATO=AA.TIPO_CONTRATO AND AA.TIPO_PERSONA=?  WHERE A.ESTADO_REGISTRO=1");
            cs.setInt(1, tipoPersona);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoContratoC();
                item.setTipoContrato(rs.getInt("TIPO_CONTRATO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
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
    
    public tipoContratoC mostrarTipoContrato(int contrato) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoContratoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_TIPO_CONTRATO WHERE TIPO_CONTRATO=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, contrato);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoContratoC();
                item.setTipoContrato(rs.getInt("TIPO_CONTRATO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
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
