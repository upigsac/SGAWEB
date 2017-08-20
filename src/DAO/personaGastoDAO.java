/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.fichaIngresante;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.personaGastosC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class personaGastoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<fichaIngresante.detalleGastos> mostrarGastosPerona() {
        List<fichaIngresante.detalleGastos> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        fichaIngresante.detalleGastos item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_GASTOS where estado_registro=1");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new fichaIngresante.detalleGastos();
                item.setGasto(rs.getInt("GASTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public boolean insertarPersonaGastos(personaGastosC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_PER_GASTO(?,?,?,?)}");

            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getGasto());
            cs.setDouble(3, item.getMonto());
            cs.setInt(4, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return rpta;
    }
}
