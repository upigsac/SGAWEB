
package Beans;

import DAO.archivoFtpDAO;

import java.io.IOException;

import javax.faces.bean.ManagedBean;

import javax.servlet.http.Part;


@ManagedBean(name = "archivoFtpB")
public class archivoFtp {

    private Part archivo;
    private String ruta;

    archivoFtpDAO archivoD = null;

    public void InsertaFTP() {

        String ms = "";
        archivoD = new archivoFtpDAO();
        ms = archivoD.insertarReporte("prueba", "D:\\reportes\\" + ruta);
        //util.consolaCliente( ms);
        if (ms.equals("true")) {
            //util.consolaCliente( "Archivo subido correctamente");
        } else {
            //util.consolaCliente( "No se puede Subir");

        }

    }

    public void subir() {

        try {

            archivo.write("D:\\reportes\\" + ruta.replaceAll("C:\\\\fakepath\\\\", ""));

            InsertaFTP();

        } catch (IOException e) {
          //util.consolaCliente( e.getMessage());

        }
    }
    public String getRuta() {
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public Part getArchivo() {
        return archivo;
    }
    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }

}
