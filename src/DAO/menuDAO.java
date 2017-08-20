
package DAO;


import Conexiones.Conexion;

import ENTIDAD.menuC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class menuDAO extends Conexion implements Serializable {

	private static final long serialVersionUID = 1L;

	//      MENU PADRE
    public List<menuC> getModulo(int op, int prog, String usuario, int mod) {

        List<menuC> m = new ArrayList<>();
        Connection c ;
        ResultSet rs ;
        CallableStatement cs ;
        try {

            c = ConexionWeb();
           
            cs = c.prepareCall("SELECT WM.* FROM SIGU.WEB_MODULOS WM, SIGU.WEB_ACCESOS SA WHERE SA.PROGRAMA=WM.SIGU AND SA.MODULO=WM.ID AND WM.TIPO='M' and SA.USUARIO= ? AND   WM.SIGU=? AND SA.ESTADO_REGISTRO=1  ORDER BY POS");

            cs.setString(1, usuario);
            cs.setInt(2, op);
            rs = cs.executeQuery();
            while (rs.next()) {
                menuC modu = new menuC();
                modu.setSigu(rs.getInt("SIGU"));
                modu.setId(rs.getInt("ID"));
                modu.setTipoIcono(rs.getInt("TIPOICONO"));
                modu.setModulo(rs.getString("MODULO"));
                modu.setNombre(rs.getString("NOMBRE"));
                modu.setImgMod(rs.getString("IMGMOD"));                
                modu.setClassIcono(rs.getString("CLASSICONO"));                
                modu.setUrl(rs.getString("URL"));
                modu.setTipo(rs.getString("TIPO"));
                modu.setmPadre(rs.getString("MPADRE"));
                modu.setIconoTitle(rs.getString("IMGTITU"));
                modu.setOrden(rs.getInt("POS"));
                
                m.add(modu);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
    
            System.out.println(e.getMessage());
        }
        return m;
    }

    public List<menuC> mostrarMenu(String programa, String tipo) {

        List<menuC> lista = new ArrayList<>();
        Connection c ;
        ResultSet rs ;
        CallableStatement cs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.WEB_MODULOS WHERE SIGU=? AND TIPO LIKE ?");
            cs.setString(1, programa);
            cs.setString(2, tipo);

            rs = cs.executeQuery();
            while (rs.next()) {

                menuC modu = new menuC();
                modu.setModulo(rs.getString("MODULO"));
                modu.setNombre(rs.getString("NOMBRE"));
                modu.setUrl(rs.getString("URL"));
                modu.setmPadre(rs.getString("MPADRE"));
                modu.setImgMod(rs.getString("IMGMOD"));
                modu.setTipo(rs.getString("TIPO"));
                modu.setClassIcono(rs.getString("CLASSICONO")); 
                lista.add(modu);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<menuC> getModuloAcceso(int modulo) {

        List<menuC> lista = new ArrayList<>();
        Connection c ;
        ResultSet rs ;
        CallableStatement cs ;
        try {

            c = ConexionWeb();
         
            cs = c.prepareCall("SELECT * FROM SIGU.WEB_MODULOS WM  WHERE   WM.TIPO='M'    AND WM.SIGU=? ORDER BY POS");

            cs.setInt(1, modulo);
            rs = cs.executeQuery();

            while (rs.next()) {

                menuC item = new menuC();
                item.setSigu(rs.getInt("SIGU"));
                item.setId(rs.getInt("ID"));
                item.setModulo(rs.getString("MODULO"));
                item.setNombre(rs.getString("NOMBRE"));
                item.setUrl(rs.getString("URL"));
                item.setmPadre(rs.getString("MPADRE"));
                item.setImgMod(rs.getString("IMGMOD"));
                item.setTipo(rs.getString("TIPO"));
                item.setClassIcono(rs.getString("CLASSICONO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }

    // MENU HIJO 
    public List<menuC> getModuloHijo(int op, String moduloPadre, String usuario) {

        List<menuC> lista = new ArrayList<>();
        Connection c ;
        ResultSet rs ;
        CallableStatement cs ;
       
        try {

            c = ConexionWeb();
            cs = c.prepareCall(" select wm.* from SIGU.WEB_MODULOS WM,SIGU.WEB_ACCESOS WA where WA.PROGRAMA=WM.SIGU AND WA.MODULO=WM.ID AND WM.MPADRE=? and WM.SIGU=?  AND wa.USUARIO=? and WM.TIPO='S' and wa.estado_registro=1 order by wm.pos");
            cs.setString(1, moduloPadre);
            cs.setInt(2, op);
            cs.setString(3, usuario);
            rs = cs.executeQuery();

            while (rs.next()) {
                menuC item = new menuC();
                item.setModulo(rs.getString("MODULO"));
                item.setUrl(rs.getString("URL"));
                item.setTipoIcono(rs.getInt("TIPOICONO"));
                item.setIconoTitle(rs.getString("IMGTITU"));
                item.setClassIcono(rs.getString("CLASSICONO"));    
                item.setNombre(rs.getString("NOMBRE").trim());
                item.setmPadre(rs.getString("MPADRE"));
                item.setImgMod(rs.getString("IMGMOD"));
                item.setTipo(rs.getString("TIPO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
            
        }
        return lista;
    }

    public List<menuC> mostrarSubmenuUsuario(int op, String padre, String usuario, String tipo) {

        List<menuC> m = new ArrayList<>();
        Connection c ;
        ResultSet rs ;
        CallableStatement cs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM SIGU.WEB_ACCESOS A,SIGU.WEB_MODULOS B\n"
                    + "WHERE A.PROGRAMA=B.SIGU AND A.MODULO=B.ID AND A.PROGRAMA=? AND MPADRE=? and A.USUARIO=? AND B.TIPO=? ");
            cs.setInt(1, op);
            cs.setString(2, padre);
            cs.setString(3, usuario);
            cs.setString(4, tipo);
            rs = cs.executeQuery();

            while (rs.next()) {

                menuC modu = new menuC();
                modu.setModulo(rs.getString("MODULO"));
                modu.setNombre(rs.getString("NOMBRE").trim());
                modu.setUrl(rs.getString("URL"));
                modu.setmPadre(rs.getString("MPADRE"));
                modu.setClassIcono(rs.getString("CLASSICONO")); 
                modu.setImgMod(rs.getString("IMGMOD"));
                modu.setTipo(rs.getString("TIPO"));
                m.add(modu);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return m;
    }

    public boolean insertarMenuAcceso(String usuario, int programa, int modulo, int estado) {
        Connection c = null;
        CallableStatement cs = null;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL SP_CONTROL_ACCESO_JAVA (?,?,?,?)}");
            cs.setString(1, usuario);
            cs.setInt(2, programa);
            cs.setInt(3, modulo);
            cs.setInt(4, estado);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
           
        }
        return rpta;
    }

    public List<menuC> getModuloHijoAcceso(int programa, String padre) {

        List<menuC> lista = new ArrayList<>();
        Connection c;
        ResultSet rs ;
        CallableStatement cs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT A.* FROM SIGU.WEB_MODULOS A where   A.SIGU=? AND A.MPADRE=?  AND   A.TIPO='S'  order by A.pos");
            cs.setInt(1, programa);
            cs.setString(2, padre);

            rs = cs.executeQuery();

            while (rs.next()) {

                menuC item = new menuC();
                item.setId(rs.getInt("ID"));
                item.setSigu(rs.getInt("SIGU"));
                item.setModulo(rs.getString("MODULO"));
                item.setNombre(rs.getString("NOMBRE"));
                item.setUrl(rs.getString("URL"));
                item.setmPadre(rs.getString("MPADRE"));
                item.setImgMod(rs.getString("IMGMOD"));
                item.setTipo(rs.getString("TIPO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
    

    public boolean getModuloItemAcceso(String programa, String usuario, int modulo) {

        boolean item = false;
        Connection c ;
        ResultSet rs ;
        CallableStatement cs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT ESTADO_REGISTRO FROM SIGU.WEB_ACCESOS  WHERE USUARIO=? AND PROGRAMA=? AND MODULO=? ");

            cs.setString(1, usuario);
            cs.setString(2, programa);
            cs.setInt(3, modulo);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = rs.getInt("ESTADO_REGISTRO") == 1 ;

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return item;
    }

    public List<menuC> mostrarMenuHijo(int programa, int modulo) {

        List<menuC> lista = new ArrayList<>();
        Connection c ;
        ResultSet rs ;
        CallableStatement cs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT * FROM SIGU.WEB_ACCESOS  WHERE  PROGRAMA=? AND MODULO=? ");

            cs.setInt(1, programa);

            cs.setInt(2, modulo);

            rs = cs.executeQuery();

            while (rs.next()) {

                menuC modu = new menuC();
                modu.setId(rs.getInt("ID"));
                modu.setModulo(rs.getString("MODULO"));
                modu.setNombre(rs.getString("NOMBRE"));

                modu.setUrl(rs.getString("URL"));
                modu.setmPadre(rs.getString("MPADRE"));
                modu.setImgMod(rs.getString("IMGMOD"));
                modu.setTipo(rs.getString("TIPO"));
                lista.add(modu);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }

}
