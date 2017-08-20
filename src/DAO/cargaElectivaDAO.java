/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.cargaElectiva;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import java.io.Serializable;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class cargaElectivaDAO implements  Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<cargaElectiva.detalleCursosNivelC> mostrarCursoNivel(int institucion,String periodo,String malla,String carrera,String nivel,String seccion) {
       
        
        List<cargaElectiva.detalleCursosNivelC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cargaElectiva.detalleCursosNivelC item;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_ASIGNAR_CARGA_ELECTIVA(?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, nivel);
            cs.setString(6, seccion);
            cs.setString(7, "0");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cargaElectiva.detalleCursosNivelC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setMalla(rs.getString("MALLA"));
                item.setHoraPractica(rs.getInt("HORAS_PRACTICA"));
                item.setHoraTeoria(rs.getInt("HORAS_TEORIA"));
                item.setCreditos(rs.getInt("CREDITOS"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setEstado(rs.getInt("ESTADO"));
                item.setEstadoContrato(rs.getInt("ESTADO_CONTRATO"));
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

