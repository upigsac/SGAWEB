package ENTIDAD;

public class constanciaMatriculaC {
	private String constanciaMatricula;	
	private int institucion;	
	private String carrera;
	private String malla;
	private String alumno;
	private int estadoRegistro;
	
	public constanciaMatriculaC() {
		// TODO Auto-generated constructor stub
	}

	public constanciaMatriculaC( String constanciaMatricula,int institucion,String carrera,String malla,String alumno,int estadoRegistro)
	{
		this.constanciaMatricula=constanciaMatricula;
		this.institucion=institucion;
		this.carrera=carrera;
		this.malla=malla;
		this.alumno=alumno;
		this.estadoRegistro=estadoRegistro;
	}

	
	public String getConstanciaMatricula() {
		return constanciaMatricula;
	}
	public void setConstanciaMatricula(String constanciaMatricula) {
		this.constanciaMatricula = constanciaMatricula;
	}
	public int getInstitucion() {
		return institucion;
	}
	public void setInstitucion(int institucion) {
		this.institucion = institucion;
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
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
