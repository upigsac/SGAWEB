/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.formulaExamenC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class formulaExamenDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<formulaExamenC> mostrarFormulaExamen(int formula) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<formulaExamenC> lista = new ArrayList<>();
        formulaExamenC item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("select *from sigu.HOR_FORMULA_EXAMEN where FORMULA=?");
            cs.setInt(1, formula);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new formulaExamenC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setOrden(rs.getInt("ORDEN"));
                item.setNumeroDecimal(rs.getInt("NRO_DECIMAL"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    public formulaExamenC mostrarFormulaExamen(int formula,String tipoExamen) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
        formulaExamenC item=null ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_FORMULA_EXAMEN WHERE  FORMULA=? AND TIPO_EXAMEN=?");
            cs.setInt(1, formula);
            cs.setString(2, tipoExamen);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new formulaExamenC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setOrden(rs.getInt("ORDEN"));
                item.setNumeroDecimal(rs.getInt("NRO_DECIMAL"));
                item.setNumeroMinimo(rs.getInt("NRO_MIN"));
                item.setNumeroMaximo(rs.getInt("NRO_MAX"));
                item.setSubFormula(rs.getString("SUB_FORMULA"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return item;
    }
    
    
    public List<formulaExamenC> mostrarFormulaExamen(int institucion,String periodo,String carrera,String malla,String curso,String decendecia) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<formulaExamenC> lista = new ArrayList<>();
        formulaExamenC item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_FORMULA_EXAMEN B WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.MALLA=?  AND A.CURSO=?  AND B.DECENDENCIA=? AND  B.FORMULA=A.FORMULA  ORDER BY B.ORDEN");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
           
            cs.setString(6, decendecia);
            
            
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new formulaExamenC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setOrden(rs.getInt("ORDEN"));
                item.setNumeroDecimal(rs.getInt("NRO_DECIMAL"));
                item.setNumeroMinimo(rs.getInt("NRO_MIN"));
                item.setNumeroMaximo(rs.getInt("NRO_MAX"));
                item.setDependencia(rs.getString("DECENDENCIA"));
                item.setSubFormula(rs.getString("SUB_FORMULA"));
                item.setLectura(rs.getBoolean("LECTURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println("Error : "  + ex.getMessage());
        }
        return lista;
    }
    
    
    public List<formulaExamenC> mostrarFormulaExamen(int institucion,String periodo,String carrera,String malla,String curso,String decendecia,String tipoExamenPadre) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<formulaExamenC> lista = new ArrayList<>();
        formulaExamenC item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_FORMULA_EXAMEN B WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND A.MALLA=?  AND A.CURSO=?  AND B.DECENDENCIA=? AND B.TIPO_EXAMEN_PADRE=? AND  B.FORMULA=A.FORMULA  ORDER BY B.ORDEN");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
           
            cs.setString(6, decendecia);
            cs.setString(7, tipoExamenPadre);
            
            
            
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new formulaExamenC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setOrden(rs.getInt("ORDEN"));
                item.setNumeroDecimal(rs.getInt("NRO_DECIMAL"));
                item.setNumeroMinimo(rs.getInt("NRO_MIN"));
                item.setNumeroMaximo(rs.getInt("NRO_MAX"));
                item.setDependencia(rs.getString("DECENDENCIA"));
                item.setSubFormula(rs.getString("SUB_FORMULA"));
                item.setLectura(rs.getBoolean("LECTURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println("Error mostrarFormulaExamen: "  + ex.getMessage());
        }
        return lista;
    }
    
    public List<formulaExamenC> mostrarFormulaExamenPadre(int formula,String tipoExamen,String decendencia,String tipoExamenPadre) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<formulaExamenC> lista = new ArrayList<>();
        formulaExamenC item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_FORMULA_EXAMEN(1,?,?,?,?)}");
            cs.setInt(1, formula);
            cs.setString(2, tipoExamen);
            cs.setString(3, decendencia);
            cs.setString(4, tipoExamenPadre);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new formulaExamenC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setOrden(rs.getInt("ORDEN"));
                item.setNumeroDecimal(rs.getInt("NRO_DECIMAL"));
                item.setNumeroMinimo(rs.getInt("NRO_MIN"));
                item.setNumeroMaximo(rs.getInt("NRO_MAX"));
                item.setDependencia(rs.getString("DECENDENCIA"));
                item.setSubFormula(rs.getString("SUB_FORMULA"));
                item.setLectura(rs.getBoolean("LECTURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println("Error mostrarFormulaExamen: "  + ex.getMessage());
        }
        return lista;
    }
    public List<formulaExamenC> mostrarFormulaExamenHijo(int formula,String tipoExamen,String decendencia,String tipoExamenPadre) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<formulaExamenC> lista = new ArrayList<>();
        formulaExamenC item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_FORMULA_EXAMEN(2,?,?,?,?)}");
            cs.setInt(1, formula);
            cs.setString(2, tipoExamen);
            cs.setString(3, decendencia);
            cs.setString(4, tipoExamenPadre);
           
            
            
            
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new formulaExamenC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setOrden(rs.getInt("ORDEN"));
                item.setNumeroDecimal(rs.getInt("NRO_DECIMAL"));
                item.setNumeroMinimo(rs.getInt("NRO_MIN"));
                item.setNumeroMaximo(rs.getInt("NRO_MAX"));
                item.setDependencia(rs.getString("DECENDENCIA"));
                item.setSubFormula(rs.getString("SUB_FORMULA"));
                item.setLectura(rs.getBoolean("LECTURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println("Error mostrarFormulaExamen: "  + ex.getMessage());
        }
        return lista;
    }
}
