package ENTIDAD;

import java.io.Serializable;

public class horMallaCurricularCursoReqC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int institucion;
	private String carrera;
	private String malla;
	private String curso;
	private int item;
	private int numeroRequisito;
	private String cursoRequisito;
	private int itemRequisitoAct;
	private int estadoRegistro;
	
	public horMallaCurricularCursoReqC() {
		// TODO Auto-generated constructor stub
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
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getNumeroRequisito() {
		return numeroRequisito;
	}
	public void setNumeroRequisito(int numeroRequisito) {
		this.numeroRequisito = numeroRequisito;
	}
	public String getCursoRequisito() {
		return cursoRequisito;
	}
	public void setCursoRequisito(String cursoRequisito) {
		this.cursoRequisito = cursoRequisito;
	}
	public int getItemRequisitoAct() {
		return itemRequisitoAct;
	}
	public void setItemRequisitoAct(int itemRequisitoAct) {
		this.itemRequisitoAct = itemRequisitoAct;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	
}
