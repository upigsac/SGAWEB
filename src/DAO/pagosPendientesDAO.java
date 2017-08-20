/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class pagosPendientesDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ArrayList<String>> pagosPendientesAlumno(String codigoPersona, String periodo) {

        List<ArrayList<String>> listado = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT CP.INSTITUCION ,CP.CARRERA , C.DESCRIPCION   ,\n"
                    + "CONVERT(VARCHAR(5),CP.NUM_CUOTA) +'-'+ CONVERT(VARCHAR(5),CP.NUM_PARTE) + '/' + CONVERT(VARCHAR(5),C.TIPO_CONCEPTO)CUOTA,\n"
                    + "CP.PERIODO ,CP.MONTO_PAGO ,CP.MONTO_PARTE ,CP.MONTO_DESCUENTO ,CP.MONTO_INTERES , CP.MONTO_TOTAL  ,\n"
                    + "CP.FECHA_VENCIMIENTO,CP.ESTADO_REGISTRO,CP.CONCEPTO \n"
                    + "FROM [SIGU].[TES_CUENTA_PERSONA]CP,[SIGU].[TES_CONCEPTOS]C ,[UPA].[ACA_CARRERAS] CA,UPA.ACA_PERIODOS AP,\n"
                    + "[UPA].[SYS_INSTITUCION] I WHERE CP.PERIODO=AP.PERIODO AND  CP.PERSONA =? and  AP.FECHA_DESDE<GETDATE() \n"
                    + "AND CA.CARRERA =CP.CARRERA AND I.INSTITUCION =CP.INSTITUCION AND C.CONCEPTO =CP.CONCEPTO AND CP.ESTADO_REGISTRO=27 \n"
                    + "and cp.TIPO_CONCEPTO=1 AND  CP.CONCEPTO  IN ('0000000040') AND AP.INSTITUCION=1\n"
                    + "AND CP.FECHA_VENCIMIENTO < GETDATE() ORDER BY PERIODO,CP.NUM_CUOTA");

            cs.setString(1, codigoPersona);
          //  cs.setString(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<String>();

                array.add(rs.getString("INSTITUCION"));
                array.add(rs.getString("CARRERA"));
                array.add(rs.getString("DESCRIPCION"));
                array.add(rs.getString("CUOTA"));
                array.add(rs.getString("PERIODO"));
                array.add(rs.getString("MONTO_PAGO"));
                array.add(rs.getString("MONTO_PARTE"));
                array.add(rs.getString("MONTO_DESCUENTO"));
                array.add(rs.getString("MONTO_INTERES"));
                array.add(rs.getString("MONTO_TOTAL"));
                array.add(rs.getString("FECHA_VENCIMIENTO"));
                array.add(rs.getString("ESTADO_REGISTRO"));
                array.add(rs.getString("CONCEPTO"));
                listado.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        return listado;
    }

    public List<ArrayList<String>> cronogramaPagosAlumno(int institucion, int periodo, String usuario) {

        List<ArrayList<String>> listado = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select B.CONCEPTO,b.DESCRIPCION,A.NUM_CUOTA,A.MONTO_PAGO, convert(varchar(12),FECHA_VENCIMIENTO,101)FECHA_VENCIMIENTO,\n"
                    + "DATEDIFF(dy ,GETDATE(),FECHA_VENCIMIENTO)DIA_VENCER,C.DESCRIPCION ESTADO\n"
                    + "FROM sigu.TES_CUENTA_PERSONA A ,SIGU.TES_CONCEPTOS B,UPA.SYS_ESTADO_REGISTROS  C \n"
                    + "where A.INSTITUCION=? AND  A.PERIODO=? AND A.ALUMNO=? AND A.CONCEPTO=40 \n"
                    + "AND A.CONCEPTO=B.CONCEPTO\n"
                    + "AND A.ESTADO_REGISTRO=C.ESTADO_REGISTRO\n"
                    + "ORDER BY A.NUM_CUOTA");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, usuario);

            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();

                array.add(rs.getString("CONCEPTO"));
                array.add(rs.getString("DESCRIPCION"));
                array.add(rs.getString("NUM_CUOTA"));
                array.add(rs.getString("MONTO_PAGO"));
                array.add(rs.getString("FECHA_VENCIMIENTO"));
                array.add(rs.getString("DIA_VENCER"));
                array.add(rs.getString("ESTADO"));
                listado.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            // e.printStackTrace();
        }
        return listado;
    }

}
