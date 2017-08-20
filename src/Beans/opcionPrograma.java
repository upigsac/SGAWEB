
package Beans;

import DAO.opcionProgramaDAO;
import ENTIDAD.opcionProgramaC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "opcionProgramaB")
public class opcionPrograma{

    private List<opcionProgramaC> programaL;
    private static int Seleccion;

    private static int tipo;

    public opcionPrograma() {

        MostrarPrograma();
        // Seleccion=1;
        tipo = 1;

    }

    opcionProgramaDAO programa;

    public void MostrarPrograma() {
        programa = new opcionProgramaDAO();
        programaL = programa.MostarOpcion();

    }

    public List<opcionProgramaC> getProgramaL() {
        return programaL;
    }

    public void setProgramaL(List<opcionProgramaC> programaL) {
        this.programaL = programaL;
    }

  
    public int getSeleccion() {
        return Seleccion;
    }

    public void setSeleccion(int Seleccion) {
        this.Seleccion = Seleccion;
    }

    
    public int getTipo() {
        return tipo;
    }

  
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
