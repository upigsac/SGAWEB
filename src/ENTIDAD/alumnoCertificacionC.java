package ENTIDAD;
import java.io.Serializable;
import java.util.Date;


public class alumnoCertificacionC implements Serializable {
	
    
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String alumno;
    private int grupo;
    private Date fechaImpresion;
    private Date fechaEntrega;
    private int item;
    private String libro;
    private String folio;
    private int estadoRegistro;

    public alumnoCertificacionC() {
    }

    public alumnoCertificacionC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno, int grupo, Date fechaImpresion, Date fechaEntrega, int item, String libro,String folio,int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.alumno = alumno;
        this.grupo = grupo;
        this.fechaImpresion = fechaImpresion;
        this.fechaEntrega = fechaEntrega;
        this.item = item;
        this.libro=libro;
        this.folio=folio;
        this.estadoRegistro = estadoRegistro;
    }

    
    
    
    public int getInstitucion() {
        return institucion;
    }

   
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    
    public String getPeriodo() {
        return periodo;
    }


    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

   
    public String getCarrera() {
        return carrera;
    }

   
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

  
    public String getMalla() {
        return malla;
    }

   
    public void setMalla(String malla) {
        this.malla = malla;
    }

  
    public String getCurso() {
        return curso;
    }

   
    public void setCurso(String curso) {
        this.curso = curso;
    }

  
    public String getSeccion() {
        return seccion;
    }

   
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

   
    public String getAlumno() {
        return alumno;
    }

   
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

   
    public int getGrupo() {
        return grupo;
    }

   
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    
    public Date getFechaImpresion() {
        return fechaImpresion;
    }

    
    public void setFechaImpresion(Date fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

   
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

  
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

   
    public int getItem() {
        return item;
    }

    
    public void setItem(int item) {
        this.item = item;
    }
    
    
    public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public int getEstadoRegistro() {
        return estadoRegistro;
    }

    
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
