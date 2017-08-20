package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import ENTIDAD.tipoProductoC;

public class tipoProductoDAO {
	public List<tipoProductoC> mostrarTipoProducto() {
        Connection c ;
        List<tipoProductoC> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        tipoProductoC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.VTS_TIPO_PRODUCTO  WHERE ESTADO_REGISTRO=1");
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new tipoProductoC();
                item.setTipoProducto(rs.getInt("TIPO_PRODUCTO"));
                
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
	
	
	public List<tipoProductoC> mostrarTipoProducto(int institucion,String periodo,String carrera) {
        Connection c ;
        List<tipoProductoC> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        tipoProductoC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT C.* FROM SIGU.HOR_PERIODO_SECCION A,SIGU.VTS_PRODUCTO B, SIGU.VTS_TIPO_PRODUCTO C WHERE A.INSTITUCION=? AND  A.PERIODO=? AND  A.CARRERA=? AND A.PRODUCTO<>'0000000000' AND A.ESTADO_REGISTRO IN(40,42) AND B.INSTITUCION=A.INSTITUCION AND B.PRODUCTO=A.PRODUCTO AND C.TIPO_PRODUCTO=B.TIPO_PRODUCTO ORDER BY DESCRIPCION");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new tipoProductoC();
                item.setTipoProducto(rs.getInt("TIPO_PRODUCTO"));                
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
}
