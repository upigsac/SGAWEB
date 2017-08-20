/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="conexionAplicacionB")
public class conexionAplicacion {
    private String baseDato;
    private String usuario;
    private String contraseña;
    private String ruta=Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"Conexiones/config.properties";

    public conexionAplicacion() throws IOException {
           leer();
        
    }
    
    
    public void leer() throws FileNotFoundException, IOException{
        Properties propiedades = new Properties();
        InputStream entrada = null;

         entrada = new FileInputStream(ruta.replace("%20"," "));
                       
                        // cargamos el archivo de propiedades
                        propiedades.load(entrada);

                        // obtenemos las propiedades y las imprimimos
                      baseDato=propiedades.getProperty("baseDato");
                      usuario=propiedades.getProperty("usuario");
                      contraseña=propiedades.getProperty("clave");
                    
    }
    
    public void guardar(){
         Properties propiedades = new Properties();
         OutputStream salida = null;

    try {
        salida = new FileOutputStream(ruta.replace("%20"," "));

        // asignamos los valores a las propiedades
        propiedades.setProperty("baseDato", baseDato);
        propiedades.setProperty("usuario", usuario);
        propiedades.setProperty("clave", contraseña);

        // guardamos el archivo de propiedades en la carpeta de aplicaciÃ³n
        propiedades.store(salida, null);

    } catch (IOException io) {
        io.printStackTrace();
    } finally {
        if (salida != null) {
            try {
                salida.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    }

    /**
     * @return the baseDato
     */
    public String getBaseDato() {
        return baseDato;
    }

    /**
     * @param baseDato the baseDato to set
     */
    public void setBaseDato(String baseDato) {
        this.baseDato = baseDato;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contraseÃ±a
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseÃ±a the contraseÃ±a to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
