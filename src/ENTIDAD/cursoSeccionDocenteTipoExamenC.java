

package ENTIDAD;

import java.util.Date;

public class cursoSeccionDocenteTipoExamenC {
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private int grupo;
    private String personal;
    private String tipoExamen;
    private int grupoExamen;
  
    private Date fechaInicio;
    private Date fechaFin;
      private String centro;
    private int estadoRegistro;

    public cursoSeccionDocenteTipoExamenC() {
    }

    public cursoSeccionDocenteTipoExamenC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, int grupo, String personal, String tipoExamen, int grupoExamen, Date fechaInicio, Date fechaFin, String centro, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.grupo = grupo;
        this.personal = personal;
        this.tipoExamen = tipoExamen;
        this.grupoExamen = grupoExamen;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.centro = centro;
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

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public int getGrupoExamen() {
        return grupoExamen;
    }

    public void setGrupoExamen(int grupoExamen) {
        this.grupoExamen = grupoExamen;
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getCentro() {
        return centro;
    }
    public void setCentro(String centro) {
        this.centro = centro;
    }
    
}
