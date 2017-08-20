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

import ENTIDAD.tipoAulaC;


public class tipoAulaDAO {
public List<tipoAulaC> mostrarTipoAula() {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoAulaC item = null;
        List<tipoAulaC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from SIGU.LOG_TIPO_AULA WHERE ESTADO_REGISTRO=1 ORDER BY DESCRIPCION");
          
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoAulaC();
                item.setTipoAula(rs.getInt("TIPO_AULA"));
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
