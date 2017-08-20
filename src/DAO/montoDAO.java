
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.montoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;



public class montoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public montoC mostrarMonto(int grado, int experiencia) {

        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        montoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_MONTOS WHERE GRADO_INSTRUCCION=? AND  ? between  EXP_DESDE AND EXP_HASTA");

            cs.setInt(1, grado);
            cs.setInt(2, experiencia);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new montoC();
                item.setMonto(rs.getInt("MONTO"));
                item.setGradoInstruccion(rs.getInt("GRADO_INSTRUCCION"));
                item.setDesde(rs.getInt("EXP_DESDE"));
                item.setHasta(rs.getInt("EXP_HASTA"));
                item.setTotalMonto(rs.getDouble("TOTAL_MONTO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        return item;
    }
	
	public montoC mostrarMonto(String persona) {

        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        montoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM DI.SYS_MONTOS A,(SELECT PERSONA,GRADO,B.DESCRIPCION,INSTITUCION,AB.NOMBRE,DENOMINACION,FECHA FROM ( SELECT *,         ROW_NUMBER() OVER (PARTITION BY PERSONA ORDER BY FECHA DESC) AS ORDEN   FROM DI.PERSONA_GRADO_ACADEMICO)A LEFT JOIN  DI.SYS_CENTRO_EDUCATIVO AB ON AB.CENTRO_EDUCATIVO=A.INSTITUCION ,SIGU.SYS_GRADO_INSTRUCCION B WHERE A.ORDEN=1 AND B.GRADO_INSTRUCCION=A.GRADO) B WHERE B.GRADO=A.GRADO_INSTRUCCION AND DATEDIFF(YEAR,B.FECHA,GETDATE()) BETWEEN A.EXP_DESDE AND A.EXP_HASTA AND B.PERSONA=?");

            cs.setString (1, persona);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new montoC();
                item.setMonto(rs.getInt("MONTO"));
                item.setGradoInstruccion(rs.getInt("GRADO_INSTRUCCION"));
                item.setDesde(rs.getInt("EXP_DESDE"));
                item.setHasta(rs.getInt("EXP_HASTA"));
                item.setTotalMonto(rs.getDouble("TOTAL_MONTO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        return item;
    }
}
