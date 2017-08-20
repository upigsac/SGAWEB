/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

import java.io.Serializable;


public class alumnoC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String alumno;
    private String persona;
    private String codigoExterno;
    private int estadoRegistro;

    public alumnoC() {
    }

    public alumnoC(int institucion, String alumno, String persona, String codigoExterno, int estadoRegistro) {
        this.institucion = institucion;
        this.alumno = alumno;
        this.persona = persona;
        this.codigoExterno = codigoExterno;
        this.estadoRegistro = estadoRegistro;
    }
    
    

    
    
     
    
    
    
    
    public int getInstitucion() {
        return institucion;
    }

    
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    
    public String getAlumno() {
        return alumno;
    }

   
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

   
    public String getPersona() {
        return persona;
    }

    
    public void setPersona(String persona) {
        this.persona = persona;
    }

   
    public String getCodigoExterno() {
        return codigoExterno;
    }

   
    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }

  
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

  
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
