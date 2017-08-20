/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.formulaC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class formulaDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public formulaC mostrarFormula(int formula) {
      
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        formulaC item=null;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_FORMULA WHERE FORMULA=? ");
            cs.setInt(1, formula);
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new formulaC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setDes_formula(rs.getString("DES_FORMULA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return item;
    }
    
    
    
    public formulaC mostrarFormula(int institucion,String periodo,String carrera,String malla,String curso) {
      
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        formulaC item=null;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT B.* FROM SIGU.HOR_CURSO_PERIODO A,SIGU.HOR_FORMULA B WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA = ? AND A.MALLA LIKE ?  AND A.CURSO LIKE ? AND  B.FORMULA=A.FORMULA ");
            cs.setInt(1, institucion);
            cs.setString(2,periodo);
            cs.setString(3,carrera);
            cs.setString(4,malla);
            cs.setString(5,curso);
            
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new formulaC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setDes_formula(rs.getString("DES_FORMULA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return item;
    }
    
    public List<formulaC> mostrarFormula() {
      
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        formulaC item;
        List<formulaC> lista=new ArrayList<>();
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_FORMULA WHERE ESTADO_REGISTRO=1");
            
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new formulaC();
                item.setFormula(rs.getInt("FORMULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setDes_formula(rs.getString("DES_FORMULA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
