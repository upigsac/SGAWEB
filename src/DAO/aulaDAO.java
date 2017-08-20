/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.aulaC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class aulaDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public List<aulaC> mostrarAula() {
        Connection c ;
        List<aulaC> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        aulaC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.LOG_AULA  WHERE ESTADO_REGISTRO=1");
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new aulaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setSede(rs.getInt("SEDE"));
                item.setAula(rs.getString("AULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTipoAula(rs.getInt("TIPO_AULA"));
                item.setCapacidad(rs.getInt("CAPACIDAD"));
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
	
	
	
	public aulaC mostrarAula(String decripcion) {
        Connection c ;
        
        CallableStatement cs ;
        ResultSet rs ;
        aulaC item =null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.LOG_AULA  WHERE DESCRIPCION=? AND  ESTADO_REGISTRO=1");
            cs.setString(1, decripcion);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new aulaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setSede(rs.getInt("SEDE"));
                item.setAula(rs.getString("AULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTipoAula(rs.getInt("TIPO_AULA"));
                item.setCapacidad(rs.getInt("CAPACIDAD"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	   System.out.println(e.getMessage());
        }
        return item;
    }

    public List<aulaC> mostrarAula(int tipoAula) {

        Connection c ;
        List<aulaC> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        aulaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.LOG_AULA where TIPO_AULA=? AND ESTADO_REGISTRO=1 ORDER BY DESCRIPCION ");

            cs.setInt(1, tipoAula);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new aulaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setSede(rs.getInt("SEDE"));
                item.setAula(rs.getString("AULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTipoAula(rs.getInt("TIPO_AULA"));
                item.setCapacidad(rs.getInt("CAPACIDAD"));
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
    
    
    
    
    public List<aulaC> mostrarAulaDisponibilidad( int dia,Date fechaInicio, Date fechaFin, Date horaInicio, Date horaFin) {

        
 
    	
    	
    	
    	
        Connection c ;
        List<aulaC> lista = new ArrayList<>();
        CallableStatement cs;
        ResultSet rs;
        aulaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_DISPONIBILIDAD_AULA (2,?,?,?,?,?)}");
            cs.setInt(1, dia);
            cs.setString(2, util.convertDate(fechaInicio,"dd-MM-yyyy"));
            cs.setString(3, util.convertDate(fechaFin,"dd-MM-yyyy"));            
            cs.setString(4, util.convertTimes(horaInicio));
            cs.setString(5, util.convertTimes(horaFin));
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new aulaC();
                item.setSede(rs.getInt("SEDE"));
                item.setAula(rs.getString("AULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setTipoAula(rs.getInt("TIPO_AULA"));
                item.setCapacidad(rs.getInt("CAPACIDAD")); 
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
    
    
    
    
    
    
    
    
   
    

    public List<aulaC> mostrarAula(Date fechaInicio, Date fechaFin, int dia, Date horaInicio, Date horaFin) {

      
        Connection c ;
        List<aulaC> lista = new ArrayList<>();
        CallableStatement cs;
        ResultSet rs;
        aulaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.INSTITUCION,A.SEDE,A.AULA,A.DESCRIPCION,A.ABREVIATURA,A.TIPO_AULA,A.CAPACIDAD,\n"
                    + "CASE WHEN  B.AULA IS NULL THEN 0 ELSE 1 END ESTADO_REGISTRO\n"
                    + "from SIGU.LOG_AULA A LEFT JOIN (\n"
                    + "SELECT * FROM DI.REGISTRO_DOCENTE_TM WHERE (?  BETWEEN FECHA_INICIO AND FECHA_FIN OR ?  BETWEEN FECHA_INICIO AND FECHA_FIN) AND  DIA=?  \n"
                    + "AND (?  BETWEEN HOR_ING AND HOR_SAL OR ?  BETWEEN HOR_ING AND HOR_SAL)\n"
                    + "AND ESTADO_REGISTRO=1 )B\n"
                    + "ON A.ABREVIATURA=B.AULA WHERE A.ESTADO_REGISTRO=1 ORDER BY A.ABREVIATURA");
            cs.setString(1, util.convertDate(fechaInicio));
            cs.setString(2, util.convertDate(fechaFin));
            cs.setInt(3, dia);
            cs.setString(4, util.convertTimes(horaInicio));
            cs.setString(5, util.convertTimes(horaFin));

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new aulaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setSede(rs.getInt("SEDE"));
                item.setAula(rs.getString("AULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTipoAula(rs.getInt("TIPO_AULA"));
                item.setCapacidad(rs.getInt("CAPACIDAD"));
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
    
    
    
    
    public List<aulaC> mostrarAulaPabellonPiso(String pabellon,int piso) {
        Connection c ;
        List<aulaC> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        aulaC item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.*FROM  DI.SPT_PABELLON A,DI.SPT_PABELLON_PISO_AULA B,SIGU.LOG_AULA C WHERE B.PABELLON=A.PABELLON AND A.PABELLON=? AND B.PISO=? AND C.AULA=B.AULA AND C.INSTITUCION=1");
            cs.setString(1, pabellon);
            cs.setInt(2, piso);
            rs = cs.executeQuery();
         
            while (rs.next()) {
                item = new aulaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setSede(rs.getInt("SEDE"));
                item.setAula(rs.getString("AULA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTipoAula(rs.getInt("TIPO_AULA"));
                item.setCapacidad(rs.getInt("CAPACIDAD"));
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
