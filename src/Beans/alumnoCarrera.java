/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.alumnoCarreraDAO;
import ENTIDAD.alumnoCarreraC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="alumnoCarreraB")
@ViewScoped
public class alumnoCarrera {
    private alumnoCarreraC alumnoCarrera;
    private List<alumnoCarreraC> alumnoCarreraL;
    
    
    
    
    alumnoCarreraDAO alumnoCarreraD;
    public List<alumnoCarreraC> mostrarAlumnoCarrera(int institucion,String alumno){
        alumnoCarreraD =new alumnoCarreraDAO();
        alumnoCarreraL=alumnoCarreraD.mostrarAlumnoCarreras(institucion, alumno);
       
        return alumnoCarreraL;
    }
    
    public List<alumnoCarreraC> mostrarAlumnoCarrera(int institucion){
        alumnoCarreraD =new alumnoCarreraDAO();
        alumnoCarreraL=alumnoCarreraD.mostrarAlumnoCarreras(institucion);
       
        return alumnoCarreraL;
    }

    public alumnoCarreraC getAlumnoCarrera() {
        return alumnoCarrera;
    }
    public void setAlumnoCarrera(alumnoCarreraC alumnoCarrera) {
        this.alumnoCarrera = alumnoCarrera;
    }
    public List<alumnoCarreraC> getAlumnoCarreraL() {
        return alumnoCarreraL;
    }
    public void setAlumnoCarreraL(List<alumnoCarreraC> alumnoCarreraL) {
        this.alumnoCarreraL = alumnoCarreraL;
    }
}
