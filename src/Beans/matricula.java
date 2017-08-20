
package Beans;

import DAO.alumnoCarreraDAO;
import DAO.carreraCreditoDAO;
import DAO.carrerasDAO;
import DAO.estadoDAO;
import DAO.facultadDAO;
import DAO.matriculaDAO;

import DAO.personaDAO;
import DAO.seccionDAO;
import DAO.usuarioDAO;
import ENTIDAD.alumnoCarreraC;
import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.carreraCreditoC;
import ENTIDAD.carrerasC;
import ENTIDAD.cursosC;
import ENTIDAD.estadoC;
import ENTIDAD.facultadC;

import ENTIDAD.personaC;
import ENTIDAD.seccionC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "matriculaB")
@ViewScoped
public class matricula implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<personaC> listaAlumno = new ArrayList<personaC>();

    private String bpaterno;
    private String bmaterno;
    private String bnombre;
    private String periodo;
   
    private estadoC estadoRegistro=new estadoC(1, "Activo", "", 1);
    private personaC seleccionBusqueda;
    private String seccionSeleccionado;
    private List<seccionC> listaSeccion;
   
    private carreraCreditoC carreraCredito;
    private alumnoCarreraC alumnoCarrera;
    private alumnoPeriodoC alumnoPeriodo = new alumnoPeriodoC();
    private alumnoPeriodoC alumnoUltimoPeriodo = new alumnoPeriodoC();
    private List<cursosC> cursosExtraL;
    private String cursoExtraS;
    private String seccionExtraS;
    private String grupoExtraS = "0";
    private String foto;
    private String codigo="";
    private String alumno;
    private String carrera;

    private String documento;
    private String observacion;
   
    private List<tablaCursos> listacursos = new ArrayList<>();
    private List<ArrayList<String>> listaMatricula = new ArrayList<>();
    private List<tablaCursos> cursosSeleccionado;
    private List<carrerasC> carreraL;
    private boolean alumnoRegular;
    private boolean bandera = true;
    private int institucion;
    private int cantCreditos;

    private List<personaC> autorizadoL;
    private String autorizacion;
    private String observacionAuto;
    private String detalleAuto;
    
    private facultadC facultad;

    private int varAutoriazacion;
    
    
    
    
    
    
    
    
    

    public estadoC getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(estadoC estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public List<carrerasC> getCarreraL() {
		return carreraL;
	}

	public void setCarreraL(List<carrerasC> carreraL) {
		this.carreraL = carreraL;
	}

	public void refrescarInstitucionPeriodo(int institucion, String periodo) {
        this.periodo = periodo;
        this.institucion = institucion;

    }
    
    public void load(int institucion, String periodo) {
        this.periodo = periodo;
        this.institucion = institucion;
      
        
        if (!codigo.toString().isEmpty()) {
            alumnoPeriodo = new matriculaDAO().alumnoMatricula(institucion, periodo, codigo);
            estadoRegistro=new estadoDAO().mostrarEstadoCodigo(alumnoPeriodo != null?alumnoPeriodo.getEstado_registro():1);          
            mostrarMatriculaAlumno();
            carreraL=new carrerasDAO().mostrarListaCarreraAlumno(institucion, codigo);
        }

    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

 

    public facultadC getFacultad() {
		return facultad;
	}

	public void setFacultad(facultadC facultad) {
		this.facultad = facultad;
	}

	public String getBnombre() {
        return bnombre;
    }

    public void setBnombre(String bnombre) {
        this.bnombre = bnombre;
    }


 

   
  
   
  

    public void grupo(tablaCursos item) {

        if (!item.seccion.isEmpty()) {

            //   cantCreditos=cantCreditos + item.creditos;
            if (cantCreditos > 30) {

                RequestContext.getCurrentInstance().execute("msg('ha superado el maximo de creditos','alerta')");
                cantCreditos = cantCreditos - item.creditos;
                item.seccion = "";
            } else {
                item.grupo = item.seccion.substring(item.seccion.length() - 1);
            }

        } else {
            setCantCreditos(getCantCreditos() - item.creditos);
            item.grupo = "";
        }

    }

    public void autorizacion() {

        if (varAutoriazacion == 1) {
            listacursos.clear();
            listacursos.add(new tablaCursos(cursoExtraS, "", seccionExtraS, "0", 3));
            if (new matriculaDAO().InsertaMatricula(institucion, periodo, alumnoCarrera.getMalla(), new carrerasDAO().mostrarCarreraAlumno(institucion, codigo).getCarrera(), codigo, 0, 0, listacursos)) {
                listaMatricula = new matriculaDAO().mostrarMatriculaAlumno(institucion, periodo, codigo);
                alumnoUltimoPeriodo = new matriculaDAO().ultimaMatricula(institucion, codigo);
                estadoRegistro=new estadoDAO().mostrarEstadoCodigo(alumnoUltimoPeriodo.getEstado_registro());
                
                mostrarCursosAlumno();
                RequestContext.getCurrentInstance().execute("msg('LOS CURSOS SE INGRESARON CORRECTAMENTE','correcto')");
                bandera = true;
            } else if (varAutoriazacion == 2) {
                insertarMatricula(4);
            }

        }

    }

    public void busqueda() {
     
     
        seleccionBusqueda = new personaDAO().busquedaAlumnocodigo(institucion, codigo);
        estadoRegistro=new estadoC(1, "Activo", "", 1);
        if (seleccionBusqueda != null) {

            // -------- cantidad de creditos permitidos --------
            cantCreditos = 0;
            foto = seleccionBusqueda.getPersona();
            alumnoUltimoPeriodo = new matriculaDAO().ultimaMatricula(institucion, codigo);
            alumnoPeriodo = new matriculaDAO().alumnoMatricula(institucion, periodo, codigo);
            if (alumnoPeriodo != null) {
              
                estadoRegistro=new estadoDAO().mostrarEstadoCodigo( alumnoPeriodo.getEstado_registro());
                documento = alumnoPeriodo.getAut_doc_matricula();
                observacion = alumnoPeriodo.getAut_obs_matricula();
                //   cantCreditos=alumnoPeriodo.getCreditos_matriculados() ;
            }
            alumnoCarrera = new alumnoCarreraDAO().mostrarAlumnoCarrera(institucion, codigo);
            carreraL=new carrerasDAO().mostrarListaCarreraAlumno(institucion, codigo);
            if(carreraL.size()!=0){
            	carrera = carreraL.get(0).getCarrera();	
            	facultad=new facultadDAO().mostrarEstadoCodigo(institucion, carrera);
            }
            
            alumno = seleccionBusqueda.getPaterno() + ' ' + seleccionBusqueda.getMaterno() + ' ' + seleccionBusqueda.getNombres();

            
           
            
            carreraCredito = new carreraCreditoDAO().mostrarCarreras(institucion, periodo, alumnoCarrera.getCarrera().toString(), 1);

            mostrarMatriculaAlumno();
            

        } else {
            limpiar();
            
            util.script("notificacion('EL CODIGO INGRESADO NO SE ENCONTRO',500,5000,'error')");

        }

    }

    public void limpiarBusqueda() {

        bpaterno = "";
        bmaterno = "";
        bnombre = "";
        listaAlumno.clear();
        util.script("msgModal.show()");
    }

    public void limpiar() {
        listaMatricula.clear();
        listacursos.clear();
        alumnoUltimoPeriodo.setPeriodo("0");
        alumnoUltimoPeriodo.setSeccion_referencial("");
        alumnoUltimoPeriodo.setCursos_matriculados(0);
        alumnoUltimoPeriodo.setCursos_aprobados(0);
        
        alumno = "";
        carrera = "";

    }

  



    public void mostrarCursosAlumno() {
   
        listacursos = new matriculaDAO().mostrarCursosAlumno(institucion, periodo, alumnoCarrera.getMalla(), carrera, codigo);
        bandera = false;
    }

    public void mostrarCursosAlumnoExtras() {
        varAutoriazacion = 1;
        cursosExtraL = new matriculaDAO().mostrarCursosAlumnoExtra(institucion, periodo, alumnoCarrera.getMalla(), carrera, codigo);

    }

    public void mostrarMatriculaAlumno() {
        if (codigo != null) {

            listaMatricula = new matriculaDAO().mostrarMatriculaAlumno(institucion, periodo, codigo);
            bandera = true;

        }

    }

    public void elimina(String curso) {
    	String msg=new matriculaDAO().eliminarCursoMatricula(institucion, periodo, codigo, curso);
        if ( msg.isEmpty()  ) {
            mostrarMatriculaAlumno();
            alumnoPeriodo = new matriculaDAO().alumnoMatricula(institucion, periodo, codigo);
            if (alumnoPeriodo == null) {                
                estadoRegistro=new estadoC(1, "activo", "", 1);
            }
            util.script("notificacion('EL CURSO FUE ELIMINADO',500,5000,'correcto')");
         
        }else{
        	 util.script("notificacion('" + msg + "',500,5000,'error')");
        }

    }

  

    public void insertarMatricula(int estado) {

        if (new matriculaDAO().insertarMatriculaFinal(institucion, periodo, codigo, estado)) {
            alumnoUltimoPeriodo = new matriculaDAO().ultimaMatricula(institucion, codigo);
        
            estadoRegistro=new estadoDAO().mostrarEstadoCodigo( alumnoUltimoPeriodo.getEstado_registro());
        }

    }
    
    public void insertarMatricula() {

        if (new matriculaDAO().insertarMatriculaFinal(institucion, periodo, codigo, 5)) {
            alumnoUltimoPeriodo = new matriculaDAO().ultimaMatricula(institucion, codigo);
        
            estadoRegistro=new estadoDAO().mostrarEstadoCodigo( 5);
        }

    }

    public void insertarCursos() {

        int totalCreditos = 0;
        int totalMatriculados = 0;
        if (new matriculaDAO().InsertaMatricula(institucion, periodo, alumnoCarrera.getMalla(), new carrerasDAO().mostrarCarreraAlumno(institucion, codigo).getCarrera(), codigo, totalMatriculados, totalCreditos, listacursos)) {
            listaMatricula = new matriculaDAO().mostrarMatriculaAlumno(institucion, periodo, codigo);
            alumnoUltimoPeriodo = new matriculaDAO().ultimaMatricula(institucion, codigo);
          
            estadoRegistro=new estadoDAO().mostrarEstadoCodigo( alumnoUltimoPeriodo.getEstado_registro());
            mostrarCursosAlumno();
            RequestContext.getCurrentInstance().execute("msg('LOS CURSOS SE INGRESARON CORRECTAMENTE','correcto')");
            bandera = true;
        }

    }

    public List<seccionC> mostrarSeccionCurso(String curso) {
      
 
        listaSeccion = new seccionDAO().seccionPeriodoCurso(institucion, periodo, curso);
        return listaSeccion;
    }

    public void busquedaAlumno() {

     

        
        listaAlumno = new personaDAO().BuscaAlumno(""+institucion, bpaterno.trim(), bmaterno.trim(), bnombre.trim());

    }

    public void insertarPensiones() {
   
        if (new matriculaDAO().insertarPensiones(institucion, periodo, codigo)) {

            RequestContext.getCurrentInstance().execute("msg('LAS PENSIONES SE GENERRARON','correcto')");
        }
    }

    usuarioDAO usu;

    public void onRowSelect(SelectEvent event) {
        codigo = ((personaC) event.getObject()).getPersona();

        if (institucion == 8 || institucion == 11 || institucion == 6 || institucion == 16 || institucion == 14 || institucion == 7 || institucion == 15 || institucion == 18 || institucion == 2) {
        
            new matriculaDAO().insertarAlumnoInstitucion(institucion, codigo);
        }
        busqueda();

    }

    public List<personaC> mostrarAutorizacion() {

        
        autorizadoL = new personaDAO().mostrarAutorizacionMatriculaPersona(institucion, periodo);
        return autorizadoL;
    }

    public void onEdit(RowEditEvent event) {
    
    }

    public void onCellEdit(CellEditEvent event) {

    }

    public List<tablaCursos> getListacursos() {
        return listacursos;
    }
    public void setListacursos(List<tablaCursos> listacursos) {
        this.listacursos = listacursos;
    }
    public List<tablaCursos> getCursosSeleccionado() {
        return cursosSeleccionado;
    }
    public void setCursosSeleccionado(List<tablaCursos> cursosSeleccionado) {
        this.cursosSeleccionado = cursosSeleccionado;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
  
 
    public alumnoPeriodoC getAlumnoPeriodo() {
        return alumnoPeriodo;
    }
    public void setAlumnoPeriodo(alumnoPeriodoC alumnoPeriodo) {
        this.alumnoPeriodo = alumnoPeriodo;
    }
    public alumnoPeriodoC getAlumnoUltimoPeriodo() {
        return alumnoUltimoPeriodo;
    }
    public void setAlumnoUltimoPeriodo(alumnoPeriodoC alumnoUltimoPeriodo) {
        this.alumnoUltimoPeriodo = alumnoUltimoPeriodo;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public boolean isAlumnoRegular() {
        return alumnoRegular;
    }
    public void setAlumnoRegular(boolean alumnoRegular) {
        this.alumnoRegular = alumnoRegular;
    }
    public int getCantCreditos() {
        return cantCreditos;
    }
    public void setCantCreditos(int cantCreditos) {
        this.cantCreditos = cantCreditos;
    }
    public List<cursosC> getCursosExtraL() {
        return cursosExtraL;
    }
    public void setCursosExtraL(List<cursosC> cursosExtraL) {
        this.cursosExtraL = cursosExtraL;
    }
    public String getCursoExtraS() {
        return cursoExtraS;
    }
    public void setCursoExtraS(String cursoExtraS) {
        this.cursoExtraS = cursoExtraS;
    }
    public String getAutorizacion() {
        return autorizacion;
    }
    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }
    public String getObservacionAuto() {
        return observacionAuto;
    }
    public void setObservacionAuto(String observacionAuto) {
        this.observacionAuto = observacionAuto;
    }
    public String getDetalleAuto() {
        return detalleAuto;
    }
    public void setDetalleAuto(String detalleAuto) {
        this.detalleAuto = detalleAuto;
    }
    public String getSeccionExtraS() {
        return seccionExtraS;
    }
    public void setSeccionExtraS(String seccionExtraS) {
        this.seccionExtraS = seccionExtraS;
    }
    public String getGrupoExtraS() {
        return grupoExtraS;
    }
    public void setGrupoExtraS(String grupoExtraS) {
        this.grupoExtraS = grupoExtraS;
    }
    public List<personaC> getAutorizadoL() {

        return autorizadoL;
    }
    public void setAutorizadoL(List<personaC> autorizadoL) {

        this.autorizadoL = autorizadoL;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public carreraCreditoC getCarreraCredito() {
        return carreraCredito;
    }
    public void setCarreraCredito(carreraCreditoC carreraCredito) {
        this.carreraCredito = carreraCredito;
    }
    public alumnoCarreraC getAlumnoCarrera() {
        return alumnoCarrera;
    }
    public void setAlumnoCarrera(alumnoCarreraC alumnoCarrera) {
        this.alumnoCarrera = alumnoCarrera;
    }

    public static class tablaCursos {

        private String curso;
        private String descurso;
        private String seccion;
        private String grupo;
        private int creditos;

        public tablaCursos() {
        }

        public tablaCursos(String curso, String descurso, String seccion, String grupo, int creditos) {
            this.curso = curso;
            this.descurso = descurso;
            this.seccion = seccion;
            this.grupo = grupo;
            this.creditos = creditos;
        }

        public int getCreditos() {
            return creditos;
        }

        public void setCreditos(int creditos) {
            this.creditos = creditos;
        }

        public String getCurso() {
            return curso;
        }

        public void setCurso(String curso) {
            this.curso = curso;
        }

        public String getDescurso() {
            return descurso;
        }

        public void setDescurso(String descurso) {
            this.descurso = descurso;
        }

        public String getSeccion() {
            return seccion;
        }

        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }

        public String getGrupo() {
            return grupo;
        }

        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }

    }

    public List<personaC> getListaAlumno() {
        return listaAlumno;
    }
    public void setListaAlumno(List<personaC> listaAlumno) {
        this.listaAlumno = listaAlumno;
    }
    public String getBpaterno() {
        return bpaterno;
    }
    public void setBpaterno(String bpaterno) {
        this.bpaterno = bpaterno;
    }
    public String getBmaterno() {
        return bmaterno;
    }
    public void setBmaterno(String bmaterno) {
        this.bmaterno = bmaterno;
    }
    public personaC getSeleccionBusqueda() {
        return seleccionBusqueda;
    }
    public void setSeleccionBusqueda(personaC seleccionBusqueda) {
        this.seleccionBusqueda = seleccionBusqueda;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public List<seccionC> getListaSeccion() {
        return listaSeccion;
    }
    public void setListaSeccion(List<seccionC> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }
    public String getSeccionSeleccionado() {
        return seccionSeleccionado;
    }
    public void setSeccionSeleccionado(String seccionSeleccionado) {
        this.seccionSeleccionado = seccionSeleccionado;
    }
    public List<ArrayList<String>> getListaMatricula() {
        return listaMatricula;
    }
    public void setListaMatricula(List<ArrayList<String>> listaMatricula) {
        this.listaMatricula = listaMatricula;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

}
