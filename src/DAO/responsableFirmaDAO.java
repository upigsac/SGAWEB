/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.responsableFirmaC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class responsableFirmaDAO implements Serializable {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List< responsableFirmaC> mostrarResponsableFirma() {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<responsableFirmaC> lista = new ArrayList<>();
        responsableFirmaC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT  * FROM DI.HOR_RESPONSABLE_FIRMA WHERE ESTADO_REGISTRO=1");

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new responsableFirmaC();
                item.setTipoCertificado(rs.getInt("TIPOCERTIFICACION"));
                item.setPersona(rs.getString("PERSONA"));
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setEstadoResgistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
