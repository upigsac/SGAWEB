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


import ENTIDAD.horMallaCurricularCursoReqC;

public class horMallaCurricularCursoReqDAO {

	public List<horMallaCurricularCursoReqC> mostrarMallaCurricularCurso(int institucion, String carrera, String malla,String curso) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horMallaCurricularCursoReqC item ;
        List<horMallaCurricularCursoReqC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_MALLA_CURRICULAR_CURSO_REQ WHERE INSTITUCION=? AND CARRERA=? AND MALLA=? AND CURSO=?");
            cs.setInt(1, institucion);
            cs.setString(2, carrera);
            cs.setString(3, malla);
            cs.setString(4, curso);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new horMallaCurricularCursoReqC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setCursoRequisito(rs.getString("CURSO_REQUISITO"));
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
