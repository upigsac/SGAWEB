/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class presupuestoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ArrayList<String>> mostrarPresupuesto(String periodo, String grupo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            //cs = c.prepareCall( "{CALL  DI.SP_LISTADO_PADRE (?,?)}");       
            cs = c.prepareCall("{CALL  [DI].[SP_PRE_LISTADO_GRUPO_PADRE] (?,?)}");
            cs.setString(1, periodo);
            cs.setString(2, grupo);
            rs = cs.executeQuery();
            rss = rs.getMetaData();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();

                for (int i = 1; i <= rss.getColumnCount(); i++) {
                    array.add(rs.getString(i));
                }

                lista.add(array);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

            //util.consolaCliente( "ERROR" + e.getMessage());
            System.out.println("ERROR EN MOSTRAR PRESUPUESTO " + e.getMessage() + "************************************************************************************************************");
        }
        return lista;
    }

    public List<ArrayList<String>> mostrarPresupuestoPadre(String periodo, String grupo, String padre) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            //cs = c.prepareCall( "{CALL [DI].[SP_PRESUPUESTO_HIJOS] (?,?,?)}"); 
            cs = c.prepareCall("{CALL [DI].[SP_PRE_LISTADO_PADRE_HIJO] (?,?,?)}");

            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            rs = cs.executeQuery();
            rss = rs.getMetaData();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();

                for (int i = 1; i <= rss.getColumnCount(); i++) {
                    array.add(rs.getString(i));
                }
                lista.add(array);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( "ERROR hijo AQUI" + e.getMessage());
            System.out.println("ERROR EN MOSTRAR PRESUPUESTO PADRE " + e.getMessage() + "**************************************************************************************");
        }
        return lista;
    }

    public List<ArrayList<String>> cabecera(String periodo, String mes) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();

            //cs = c.prepareCall( "SELECT * FROM DI.GRUPO_PRESUPUESTO where PERIODO=? and GRUPO=? order by mes");            
            cs = c.prepareCall("SELECT * FROM DI.PRE_GRUPO where PERIODO=? and GRUPO=? order by ORDEN");
            cs.setString(1, periodo);
            cs.setString(2, mes);
            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("DESCRIPCION_GRUPO"));
                array.add(rs.getString("MES"));
                lista.add(array);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

            //util.consolaCliente( "cabecera" + e.getMessage());
            System.out.println("ERROR EN CABECERA " + e.getMessage() + "************************************************************************************************************");
        }
        return lista;
    }
    /*
     public List<ArrayList<String>> columnas(String periodo,String grupo,String padre,String hijo,String nieto,String bisnieto) {
        
     Connection c = null;
     CallableStatement cs = null;
     ResultSet rs = null;                
     List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();             
     try {
     c = ConexionWeb();                      
            
     cs = c.prepareCall( 
     " select B.*from di.COLUMAS_ADICIONALES A ,DI.COLUMNAS_DESCRIPCION B WHERE A.ID_COLUMNA=B.ID AND  GRUPO=? and PADRE=? and HIJO=? and NIETO=? AND BISNIETO=? ");            
            
     cs.setString(1, grupo);
     cs.setString(2, padre);
     cs.setString(3, hijo);
     cs.setString(4, nieto);
     cs.setString(5, bisnieto);
            
     rs = cs.executeQuery();
           
     while (rs.next()) {
     ArrayList<String> array=new ArrayList<String>();
     array.add(rs.getString("ID"));
     array.add(rs.getString("NOMBRE_COLUMNA"));
     array.add(rs.getString("EDITABLE"));
                
              
     lista.add(array);
                
     }
     cerrarCall(cs);
     cerrarConexion(c);
              
     } catch (Exception e) {
           
     //util.consolaCliente( "cabecera" + e.getMessage());
     System.out.println("ERROR EN COLUMNAS "+e.getMessage()+"*************************************************************************************");
     }
     return lista;
     }
     */

    public List<ArrayList<String>> mostrarPresupuestoTotal(String periodo, String grupo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            //cs = c.prepareCall( "{CALL DI.[SP_PRESUPUESTO_TOTALES] (?,?)}");            
            cs = c.prepareCall("{CALL [DI].[SP_PRE_GRUPO_TOTAL] (?,?)}");

            cs.setString(1, periodo);
            cs.setString(2, grupo);

            rs = cs.executeQuery();
            rss = rs.getMetaData();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                for (int i = 1; i <= rss.getColumnCount(); i++) {
                    array.add(rs.getString(i));
                }

                lista.add(array);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( "ERROR hijoSSS" + e.getMessage());
            System.out.println("ERROR EN MOSTRAR PRESUPUESTO TOTAL " + e.getMessage() + "*************************************************************************************");
        }
        return lista;
    }

    public List<ArrayList<String>> mostrarPresupuestoSubGrupo(String periodo, String grupo, String padre, String hijo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            //cs = c.prepareCall( "{CALL [DI].SP_PRESUPUESTO_NIETO (?,?,?,?)}");           
            cs = c.prepareCall("{CALL [DI].[SP_PRE_LISTADO_HIJO_NIETO] (?,?,?,?)}");

            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            rs = cs.executeQuery();
            rss = rs.getMetaData();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                for (int i = 1; i < rss.getColumnCount(); i++) {
                    array.add(rs.getString(i));
                }

                lista.add(array);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( "ERROR NIETO " + e.getMessage());
            System.out.println("ERROR EN MOSTRAR PRESUPUESTO SUBGRUPO " + e.getMessage() + "*************************************************************************************");
        }
        return lista;
    }

    public List<ArrayList<String>> mostrarPresupuestoSubGrupoNieto(String periodo, String grupo, String padre, String hijo, String nieto) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            //cs = c.prepareCall( "{CALL [DI].SP_PRESUPUESTO_BISNIETO (?,?,?,?,?)}");  
            cs = c.prepareCall("{CALL [DI].[SP_PRE_NIETO_BISNIETO] (?,?,?,?,?)}");

            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            cs.setString(5, nieto);
            rs = cs.executeQuery();
            rss = rs.getMetaData();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                for (int i = 1; i <= rss.getColumnCount(); i++) {
                    array.add(rs.getString(i));
                }

                lista.add(array);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( e.getMessage());
            
        }
        return lista;
    }

    public boolean insertarSubGrupo(String periodo, String grupo, String padre, String hijo, String nieto, String bisnieto, String mes, String valor/*,String tipo*/) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            //cs = c.prepareCall("{CALL DI.SP_INSERTAR_SUBGRUPO(?,?,?,?,?,?,?,?)}" );
            cs = c.prepareCall("{CALL [DI].[SP_INSERTAR_NIETO_BISNIETO] (?,?,?,?,?,?,?,?)}");

            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            cs.setString(5, nieto);
            cs.setString(6, bisnieto);
            cs.setString(7, mes);
            cs.setString(8, valor);
            //cs.setString(9, tipo);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "ERROR INSERTAR "+ e.getMessage());
            System.out.println("ERROR EN INSERTAR SUBGRUPO " + e.getMessage() + "*************************************************************************************");
            //e.printStackTrace();
        }
        return rpta;
    }

    public boolean editarSubGrupo(String periodo, String grupo, String padre, String hijo, String nieto, String posicion, String valor) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            //cs = c.prepareCall("update DI.GRUPO_SUBGRUPO_PRESUPUESTO set "+posicion+"=? WHERE PERIODO=? AND GRUPO=? AND PADRE=? AND HIJO=? AND NIETO=?" );
            cs = c.prepareCall("update DI.PRE_NIETO set " + posicion + "=? WHERE PERIODO=? AND GRUPO=? AND PADRE=? AND HIJO=? AND NIETO=?");
            cs.setString(2, periodo);
            cs.setString(3, grupo);
            cs.setString(4, padre);
            cs.setString(5, hijo);
            cs.setString(6, nieto);
            cs.setString(1, valor);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "ERROR INSERTAR 2"+ e.getMessage());
            System.out.println("ERROR EN EDITAR SUBGRUPO " + e.getMessage() + "*************************************************************************************");
        }
        return rpta;
    }

    public boolean editarNieto(String periodo, String grupo, String padre, String hijo, String nieto, String bisnieto, String posicion, String valor) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            //cs = c.prepareCall("update DI.GRUPO_NIETO_PRESUPUESTO set "+posicion+"=? WHERE PERIODO=? AND GRUPO=? AND PADRE=? AND HIJO=? AND NIETO=? AND BISNIETO=?" );
            cs = c.prepareCall("update DI.PRE_BISNIETO set " + posicion + "=? WHERE PERIODO=? AND GRUPO=? AND PADRE=? AND HIJO=? AND NIETO=? AND BISNIETO=?");

            cs.setString(2, periodo);
            cs.setString(3, grupo);
            cs.setString(4, padre);
            cs.setString(5, hijo);
            cs.setString(6, nieto);
            cs.setString(7, bisnieto);
            cs.setString(1, valor);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "ERROR INSERTAR 2"+ e.getMessage());
            System.out.println("ERROR EN EDITAR NIETO " + e.getMessage() + "*************************************************************************************");
        }
        return rpta;
    }

    public boolean nuevoNieto(String periodo, String grupo, String padre, String hijo, String nieto) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            /*
             cs = c.prepareCall(
             "insert into di.grupo_nieto_presupuesto (PERIODO,GRUPO,PADRE,HIJO,NIETO,BISNIETO,DESCRIPCION_BISNIETO,ESTADO_REGISTRO) "
             + "values(?,?,?,?,?,(select isnull(MAX(bisnieto)+1,1) "
             + "from di.grupo_nieto_presupuesto where grupo='"+ grupo +"' and padre='"+ padre +"' and hijo='"+ hijo +"' and nieto='"+ nieto +"'),'...',1)" );      
             */
            cs = c.prepareCall(
                    "insert into DI.PRE_BISNIETO (PERIODO,GRUPO,PADRE,HIJO,NIETO,BISNIETO,DESCRIPCION_BISNIETO,ESTADO_REGISTRO) "
                    + "values(?,?,?,?,?,(select isnull(MAX(bisnieto)+1,1) "
                    + "from DI.PRE_BISNIETO where periodo='" + periodo + "' and grupo='" + grupo + "' and padre='" + padre + "' and hijo='" + hijo + "' and nieto='" + nieto + "'),'...',1)");

            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            cs.setString(5, nieto);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "ERROR ELIMINAR "+ e.getMessage());
            System.out.println("ERROR EN NUEVO NIETO " + e.getMessage() + "*************************************************************************************");
            //e.printStackTrace();
        }
        return rpta;
    }

    public boolean nuevoHijo(String periodo, String grupo, String padre, String hijo) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            //cs = c.prepareCall("{CALL DI.SP_INSERTAR_NIETO (?,?,?,?)}" );
            cs = c.prepareCall("{CALL [DI].[SP_INSERTAR_NIETO] (?,?,?,?)}");

            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "ERROR  "+ e.getMessage());
            System.out.println("ERROR EN NUEVO HIJO " + e.getMessage() + "*************************************************************************************");
            //e.printStackTrace();
        }
        return rpta;
    }

    //SUMINISTROS*************************************************************************************************************************************************************
    public List<ArrayList<String>> mostrarSuministros(String periodo, String grupo, String padre, String hijo, String nieto) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_LISTADO_SUMINISTROS (?,?,?,?,?)}");
            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            cs.setString(5, nieto);
            rs = cs.executeQuery();
            rss = rs.getMetaData();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                for (int i = 1; i <= rss.getColumnCount(); i++) {
                    array.add(rs.getString(i));
                }
                lista.add(array);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( "mostrarSuministros "+e.getMessage());
            System.out.println("ERROR EN MOSTRAR SUMINISTROS " + e.getMessage() + "*************************************************************************************");
        }
        return lista;
    }

    public void insertarSuministros(String periodo, String grupo, String padre, String hijo, String nieto) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INSERTAR_SUMINISTROS (1,?,?,?,?,?,'','','','','','')}");
            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            cs.setString(5, nieto);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "insertar suminis "+ e.getMessage());
            System.out.println("ERROR EN INSERTAR SUMINISTROS " + e.getMessage() + "*************************************************************************************");
        }
    }

    public void actualizarSuministros(String periodo, String grupo, String padre, String hijo, String nieto, String bisnieto, String descripcion, String tipo_unid, String pu) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INSERTAR_SUMINISTROS (2,?,?,?,?,?,?,?,?,?,'','')}");
            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            cs.setString(5, nieto);
            cs.setString(6, bisnieto);
            cs.setString(7, descripcion);
            cs.setString(8, tipo_unid);
            cs.setString(9, pu);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "ACTUALIZAR suminis "+ e.getMessage());
            System.out.println("ERROR EN ACTUALIZAR SUMINISTROS " + e.getMessage() + "*************************************************************************************");
        }
    }

    public void ingresarSuministrosMes(
        String periodo, String grupo, String padre, String hijo, String nieto, String bisnieto, String descripcion, String tipo_unid, String pu, String mes, String cantidad) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INSERTAR_SUMINISTROS (3,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, periodo);
            cs.setString(2, grupo);
            cs.setString(3, padre);
            cs.setString(4, hijo);
            cs.setString(5, nieto);
            cs.setString(6, bisnieto);
            cs.setString(7, descripcion);
            cs.setString(8, tipo_unid);
            cs.setString(9, pu);
            cs.setString(10, mes);
            cs.setString(11, cantidad);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            //util.consolaCliente( "ingresar suminis mes"+ e.getMessage());
            System.out.println("ERROR EN INSERTAR SUMINISTROS MES" + e.getMessage() + "*************************************************************************************");
        }
    }

    //FIN SUMINISTROS*************************************************************************************************************************************************************
}
