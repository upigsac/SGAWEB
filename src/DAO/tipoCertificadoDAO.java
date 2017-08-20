

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoCertificadoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.util;


public class tipoCertificadoDAO implements Serializable {
   
	private static final long serialVersionUID = 1L;

	public tipoCertificadoC mostrarTipoCertificado(int tipoCertificado) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoCertificadoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_TIPO_CERTIFICADO WHERE CERTIFICADO=?");
            cs.setInt(1, tipoCertificado);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoCertificadoC();
                item.setCertificado(rs.getInt("CERTIFICADO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setReporte(rs.getString("REPORTE"));
                item.setFormato(rs.getString("FORMATO"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
	
	
	public tipoCertificadoC mostrarTipoCertificado(int institucion,String periodo,String carrera,String malla,String curso,String seccion) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoCertificadoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM SIGU.HOR_CURSO_SECCION A,DI.SYS_TIPO_CERTIFICADO B WHERE  A.INSTITUCION LIKE ? AND A.PERIODO LIKE ? AND A.CARRERA LIKE ? AND A.MALLA LIKE ? AND A.CURSO LIKE ? AND A.SECCION LIKE ? AND B.CERTIFICADO=A.TIPO_CERTIFICADO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoCertificadoC();
                item.setCertificado(rs.getInt("CERTIFICADO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setReporte(rs.getString("REPORTE"));
                item.setFormato(rs.getString("FORMATO"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
	
	
	
public List<tipoCertificadoC> mostrarTipoCertificado() {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoCertificadoC item = null;
        List<tipoCertificadoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_TIPO_CERTIFICADO WHERE ESTADO_REGISTRO=1");
            
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoCertificadoC();
                item.setCertificado(rs.getInt("CERTIFICADO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setReporte(rs.getString("REPORTE"));
                item.setFormato(rs.getString("FORMATO"));                
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
