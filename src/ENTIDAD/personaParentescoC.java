
package ENTIDAD;

import java.io.Serializable;

public class personaParentescoC implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String persona;
    private int parentesco;
    private String personaParentesco;    
    private boolean dependedeud;
    private boolean pagaestudios;
    private boolean viveconud;
    private int tipoEnfermedad;
    private int hospital;
    private double  gastos;
    private String observacion;
    private int estadoRegistro;

    public personaParentescoC() {
    }
    
    

    public personaParentescoC(String persona, int parentesco, String personaParentesco, boolean dependedeud, boolean pagaestudios, boolean viveconud, int tipoEnfermedad, int hospital, double gastos, int estadoRegistro) {
        this.persona = persona;
        this.parentesco = parentesco;
        this.personaParentesco = personaParentesco;
        this.dependedeud = dependedeud;
        this.pagaestudios = pagaestudios;
        this.viveconud = viveconud;
        this.tipoEnfermedad = tipoEnfermedad;
        this.hospital = hospital;
        this.gastos = gastos;
        this.estadoRegistro = estadoRegistro;
    }
    
    


    public String getPersona() {
        return persona;
    }

 
    public void setPersona(String persona) {
        this.persona = persona;
    }

   
    public int getParentesco() {
        return parentesco;
    }

   
    public void setParentesco(int parentesco) {
        this.parentesco = parentesco;
    }

    public String getPersonaParentesco() {
        return personaParentesco;
    }

    
    public void setPersonaParentesco(String personaParentesco) {
        this.personaParentesco = personaParentesco;
    }

    
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

   
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

  
    public boolean isDependedeud() {
        return dependedeud;
    }

   
    public void setDependedeud(boolean dependedeud) {
        this.dependedeud = dependedeud;
    }

   
    public boolean isPagaestudios() {
        return pagaestudios;
    }

   
    public void setPagaestudios(boolean pagaestudios) {
        this.pagaestudios = pagaestudios;
    }

    public boolean isViveconud() {
        return viveconud;
    }

    public void setViveconud(boolean viveconud) {
        this.viveconud = viveconud;
    }

   
    public int getTipoEnfermedad() {
        return tipoEnfermedad;
    }

    public void setTipoEnfermedad(int tipoEnfermedad) {
        this.tipoEnfermedad = tipoEnfermedad;
    }

    
    public int getHospital() {
        return hospital;
    }

   
    public void setHospital(int hospital) {
        this.hospital = hospital;
    }

    
    public double getGastos() {
        return gastos;
    }

  
    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public String getObservacion() {
        return observacion;
    }

   
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
