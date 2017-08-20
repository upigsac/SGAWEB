
package Beans;

import DAO.alumnoCursoNotaDAO;
import DAO.alumnoCursoSeccionDAO;
import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.docente_notaDAO;
import DAO.formulaDAO;
import DAO.formulaExamenDAO;
import DAO.personaDAO;

import DAO.seccionDAO;
import DAO.semanaExamenDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.alumnoCursoNotaC;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.formulaC;
import ENTIDAD.formulaExamenC;
import ENTIDAD.personaC;
import ENTIDAD.seccionC;
import ENTIDAD.semanaExamenC;
import ENTIDAD.tipoExamenC;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="registroNotaDocenteB")
@ViewScoped
public class registroNotaDocente implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<cursoSeccionDocenteC> cursoSeccionDocenteL;
    private List<cursoPrincipalSecundario> cursoPrincipalSecundarioL=new ArrayList<>();
    private cursoPrincipalSecundario cursoPrincipalSecundario;
    private List<alumnoCursoSeccionPersona> alumnoCursoSeccionPersonaL=new ArrayList<>();
    private List<cabeceraNota> cabeceraNotaL=new ArrayList<>();
    private String tipoExamenPadre=null;
  
    
    
  
    public registroNotaDocente() {
      
    }
    
    
    
    @ManagedProperty(value = "#{usuarioB}")
    private usuario usuarioS;

    
    
     @PostConstruct
    public void init() {
        mostrarCursoDocente();
    }
    
    
    public void insertarNota(alumnoCursoNotaC itemAlumnoCursoNota ,int indice){
        String promedio="";
    
        if(itemAlumnoCursoNota.getNotaT().equals(""))
        {
    
            promedio=new alumnoCursoNotaDAO().eliminar(itemAlumnoCursoNota);
        }
        else{
            itemAlumnoCursoNota.setNota(Double.parseDouble(itemAlumnoCursoNota.getNotaT()));
            
            promedio=new alumnoCursoNotaDAO().inserta(itemAlumnoCursoNota);
            
            
            
        }
        System.out.println("promedio:" + promedio);
       util.script("$('.tbNota tbody tr:nth-child("+ (indice )+") td:nth-last-child(2)').text('"+promedio+"');");
    }
    
    
    public void mostrarCursoDocente(){
       
       
      
        cursoPrincipalSecundario itemCursoPrincipalSecundario;
        for (cursoSeccionDocenteC itemCursoSeccionDocenteP : new cursoSeccionDocenteDAO().mostrarCursoSeccionDocentePrincipal("%", usuarioS.getPeriodoS(), "%", "%", "%", "%", usuarioS.getPersonal().getPersonal())) {
            
            itemCursoPrincipalSecundario=new cursoPrincipalSecundario(itemCursoSeccionDocenteP);
            itemCursoPrincipalSecundario.cursos=new cursoDAO().mostrarCurso(itemCursoSeccionDocenteP.getCurso());
            itemCursoPrincipalSecundario.carrera=new carrerasDAO().mostrarCarrerasCodigo(itemCursoSeccionDocenteP.getCarrera());
            itemCursoPrincipalSecundario.seccion=new seccionDAO().mostrarSeccion(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getSeccion());
            itemCursoPrincipalSecundario.formula=new formulaDAO().mostrarFormula(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getPeriodo(), itemCursoSeccionDocenteP.getCarrera(), itemCursoSeccionDocenteP.getMalla(), itemCursoSeccionDocenteP.getCurso());
            cursoSecundario itemCursoSecundario;
            for (cursoSeccionDocenteC itemCursoSeccionDocenteS : new cursoSeccionDocenteDAO().mostrarCursoSeccionDocenteSecundario(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getPeriodo(), itemCursoSeccionDocenteP.getCarrera(), itemCursoSeccionDocenteP.getMalla(), itemCursoSeccionDocenteP.getCurso(), itemCursoSeccionDocenteP.getSeccion(), itemCursoSeccionDocenteP.getPersonal())) {
                itemCursoSecundario=new cursoSecundario(itemCursoSeccionDocenteS);                
                itemCursoSecundario.curso=new cursoDAO().mostrarCurso(itemCursoSeccionDocenteS.getCurso());
                itemCursoSecundario.carrera=new carrerasDAO().mostrarCarrerasCodigo(itemCursoSeccionDocenteS.getCarrera());
                itemCursoSecundario.seccion=new seccionDAO().mostrarSeccion(itemCursoSeccionDocenteS.getInstitucion(), itemCursoSeccionDocenteS.getSeccion());
                itemCursoPrincipalSecundario.getCursoSeccionDocenteSecundarioL().add(itemCursoSecundario);
                //itemCursoPrincipalSecundario.getCursoSeccionDocenteSecundarioL().add(itemCursoSeccionDocenteS);
            }
            cursoPrincipalSecundarioL.add(itemCursoPrincipalSecundario);
        }
    }
    public void seleccionCursoSeccion(cursoPrincipalSecundario itemCursoPrincipalSecundario){
        
        cursoPrincipalSecundario=itemCursoPrincipalSecundario;
        tipoExamenPadre=null;
        // CABECERA
        mostrarNotaPadre();
        // ALUMNO
        mostrarAlumnoCursoNotaPadre();
        
          
    }
    public void mostrarAlumnoCursoNotaPadre(){
        
      
       
        alumnoCursoSeccionPersonaL=new ArrayList<>();
        alumnoCursoSeccionPersona itemAlumnoCursoSeccionPersona;
      
        System.out.println("==============================================================================================");
        for(alumnoCursoSeccionC itemAlumnoCursoSeccion : new alumnoCursoSeccionDAO().mostrarAlumnoCursoSeccion(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), "%"))
        {
           
        
        	itemAlumnoCursoSeccionPersona=new alumnoCursoSeccionPersona(itemAlumnoCursoSeccion, new personaDAO().busquedaAlumnocodigo(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getAlumno()));        	
        	itemAlumnoCursoSeccionPersona.carrera=new carrerasDAO().mostrarCarrerasCodigo(itemAlumnoCursoSeccion.getCarrera());
        	
        	for(alumnoCursoNotaC itemAlumnoCursoNota :new  alumnoCursoNotaDAO().mostrarAlumnoCursoNotaP(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getPeriodo(), itemAlumnoCursoSeccion.getCarrera(), itemAlumnoCursoSeccion.getMalla(), itemAlumnoCursoSeccion.getCurso(), itemAlumnoCursoSeccion.getSeccion(), itemAlumnoCursoSeccion.getAlumno(),"%"))
            {
           
                itemAlumnoCursoSeccionPersona.alumnoCursoNotaL.put(itemAlumnoCursoNota.getTipoExamen(), itemAlumnoCursoNota);
            }   
                   
            alumnoCursoSeccionPersonaL.add(itemAlumnoCursoSeccionPersona);   
          
        }
    
        System.out.println("==============================================================================================");
    }
    
     public void mostrarAlumnoCursoNotaHijo(){
       
      
        alumnoCursoSeccionPersonaL=new ArrayList<>();
        alumnoCursoSeccionPersona itemAlumnoCursoSeccionPersona;
        for(alumnoCursoSeccionC itemAlumnoCursoSeccion : new alumnoCursoSeccionDAO().mostrarAlumnoCursoSeccion(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), "%"))
        {
            itemAlumnoCursoSeccionPersona=new alumnoCursoSeccionPersona(itemAlumnoCursoSeccion, new personaDAO().busquedaAlumnocodigo(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getAlumno()));        
            for(alumnoCursoNotaC itemAlumnoCursoNota:new alumnoCursoNotaDAO().mostrarAlumnoCursoNotaH(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getPeriodo(), itemAlumnoCursoSeccion.getCarrera(), itemAlumnoCursoSeccion.getMalla(), itemAlumnoCursoSeccion.getCurso(), itemAlumnoCursoSeccion.getSeccion(), itemAlumnoCursoSeccion.getAlumno(),tipoExamenPadre))
            {
                itemAlumnoCursoSeccionPersona.alumnoCursoNotaL.put(itemAlumnoCursoNota.getTipoExamen(), itemAlumnoCursoNota);
            }
            
            alumnoCursoSeccionPersonaL.add(itemAlumnoCursoSeccionPersona);
            
           
        }
    }
    
    public void mostrarNotaPadre(){
            tipoExamenPadre=null;
          
            cabeceraNota itemCabeceraNota;
            cabeceraNotaL=new ArrayList<>();
            
            //for(formulaExamenC itemFormulaExamen:formulaExamenD.mostrarFormulaExamen(itemCursoSeccionDocente.getInstitucion(), itemCursoSeccionDocente.getPeriodo(), itemCursoSeccionDocente.getCarrera(), itemCursoSeccionDocente.getMalla(), itemCursoSeccionDocente.getCurso(),  "P"))
            for(formulaExamenC itemFormulaExamen:new formulaExamenDAO().mostrarFormulaExamenPadre(cursoPrincipalSecundario.formula.getFormula(), "%", "P", "%"))
            {
                itemCabeceraNota=new cabeceraNota(itemFormulaExamen);
                itemCabeceraNota.tipoExamen=new tipoExamenDAO().mostrarTipoExamen(itemFormulaExamen.getTipoExamen());
                
                itemCabeceraNota.semanaExamen=new semanaExamenDAO().mostrarSemanaExamen(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), itemFormulaExamen.getTipoExamen());                
                itemCabeceraNota.validaEdicion=new docente_notaDAO().controlNotaLibre(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), itemFormulaExamen.getTipoExamen(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPersonal());
                cabeceraNotaL.add(itemCabeceraNota);
                
            }
            
             util.script("$('.pnPersonaCurso').removeClass('bloqueo')");
    }
    
    public void mostrarNotaHijo(String tipoExamenPadre){
            this.tipoExamenPadre=tipoExamenPadre;
         
            cabeceraNota itemCabeceraNota;
            cabeceraNotaL=new ArrayList<>();
            //for(formulaExamenC itemFormulaExamen:formulaExamenD.mostrarFormulaExamen(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getMalla(), cursoSeccionDocente.getCurso(),  "H",tipoExamenPadre))
            for(formulaExamenC itemFormulaExamen:new formulaExamenDAO().mostrarFormulaExamenHijo(cursoPrincipalSecundario.formula.getFormula(), "%", "H", tipoExamenPadre))
            {
                itemCabeceraNota=new cabeceraNota(itemFormulaExamen);
                itemCabeceraNota.tipoExamen=new tipoExamenDAO().mostrarTipoExamen(itemFormulaExamen.getTipoExamen());
                itemCabeceraNota.semanaExamen=new semanaExamenDAO().mostrarSemanaExamen(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), itemFormulaExamen.getTipoExamen());                
                itemCabeceraNota.validaEdicion=new docente_notaDAO().controlNotaLibre(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), itemFormulaExamen.getTipoExamen(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPersonal());
                cabeceraNotaL.add(itemCabeceraNota);
                
            }
             mostrarAlumnoCursoNotaHijo();
             util.script("$('.pnPersonaCurso').addClass('bloqueo')");
            //-----------------------------------------------------------
                        
    }
    public void actualizar(){
        if (tipoExamenPadre == null){
            mostrarAlumnoCursoNotaPadre();
        }else{
            mostrarAlumnoCursoNotaHijo();
        }
    }
    public void atras(){
    	tipoExamenPadre=null;
        // CABECERA
        mostrarNotaPadre();
        // ALUMNO
        mostrarAlumnoCursoNotaPadre();
    }
    public List<cursoPrincipalSecundario> getCursoPrincipalSecundarioL() {
        return cursoPrincipalSecundarioL;
    }
    public void setCursoPrincipalSecundarioL(List<cursoPrincipalSecundario> cursoPrincipalSecundarioL) {
        this.cursoPrincipalSecundarioL = cursoPrincipalSecundarioL;
    }
    public List<alumnoCursoSeccionPersona> getAlumnoCursoSeccionPersonaL() {
        return alumnoCursoSeccionPersonaL;
    }
    public void setAlumnoCursoSeccionPersonaL(List<alumnoCursoSeccionPersona> alumnoCursoSeccionPersonaL) {
        this.alumnoCursoSeccionPersonaL = alumnoCursoSeccionPersonaL;
    }
    public List<cabeceraNota> getCabeceraNotaL() {
        return cabeceraNotaL;
    }
    public void setCabeceraNotaL(List<cabeceraNota> cabeceraNotaL) {
        this.cabeceraNotaL = cabeceraNotaL;
    }
    public cursoPrincipalSecundario getCursoPrincipalSecundario() {
        return cursoPrincipalSecundario;
    }
    public void setCursoPrincipalSecundario(cursoPrincipalSecundario cursoPrincipalSecundario) {
        this.cursoPrincipalSecundario = cursoPrincipalSecundario;
    }
    public String getTipoExamenPadre() {
        return tipoExamenPadre;
    }
    public void setTipoExamenPadre(String tipoExamenPadre) {
        this.tipoExamenPadre = tipoExamenPadre;
    }
    public usuario getUsuarioS() {
        return usuarioS;
    }
    public void setUsuarioS(usuario usuarioS) {
        this.usuarioS = usuarioS;
    }
    
    
    public class cabeceraNota{
        private formulaExamenC formulaExamen;
        private tipoExamenC tipoExamen=new tipoExamenC();
        private semanaExamenC semanaExamen;
         private boolean validaEdicion;
        public cabeceraNota() {
        }

        public cabeceraNota(formulaExamenC formulaExamen) {
            this.formulaExamen = formulaExamen;
        }
        public formulaExamenC getFormulaExamen() {
            return formulaExamen;
        }

       
        public void setFormulaExamen(formulaExamenC formulaExamen) {
            this.formulaExamen = formulaExamen;
        }

       
        public tipoExamenC getTipoExamen() {
            return tipoExamen;
        }

       
        public void setTipoExamen(tipoExamenC tipoExamen) {
            this.tipoExamen = tipoExamen;
        }

       
        public semanaExamenC getSemanaExamen() {
            return semanaExamen;
        }

        
        public void setSemanaExamen(semanaExamenC semanaExamen) {
            this.semanaExamen = semanaExamen;
        }

       
        public boolean isValidaEdicion() {
            return validaEdicion;
        }

       
        public void setValidaEdicion(boolean validaEdicion) {
            this.validaEdicion = validaEdicion;
        }
        
    }
    
    
    
    public class alumnoCursoSeccionPersona{
        private alumnoCursoSeccionC alumnoCursoSeccion;
        private personaC persona;
        private carrerasC carrera;
        
     

		private HashMap<String,alumnoCursoNotaC> alumnoCursoNotaL=new HashMap<>();
        public alumnoCursoSeccionPersona() {
        }

        public alumnoCursoSeccionPersona(alumnoCursoSeccionC alumnoCursoSeccion, personaC persona) {
            this.alumnoCursoSeccion = alumnoCursoSeccion;
            this.persona = persona;
        }
        
        public carrerasC getCarrera() {
			return carrera;
		}

		public void setCarrera(carrerasC carrera) {
			this.carrera = carrera;
		}

        
        public alumnoCursoSeccionC getAlumnoCursoSeccion() {
            return alumnoCursoSeccion;
        }

       
        public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
            this.alumnoCursoSeccion = alumnoCursoSeccion;
        }

        
        public personaC getPersona() {
            return persona;
        }

        
        public void setPersona(personaC persona) {
            this.persona = persona;
        }

        
        public HashMap<String,alumnoCursoNotaC> getAlumnoCursoNotaL() {
            return alumnoCursoNotaL;
        }

        
        public void setAlumnoCursoNotaL(HashMap<String,alumnoCursoNotaC> alumnoCursoNotaL) {
            this.alumnoCursoNotaL = alumnoCursoNotaL;
        }
    }
    
    
    public class cursoSecundario{
        private  cursoSeccionDocenteC cursoSeccionDocente;
        private cursosC curso=new cursosC();
        private carrerasC carrera=new carrerasC();
        private seccionC seccion=new seccionC(); 

        public cursoSecundario() {
        }

        public cursoSecundario(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }

      

       
        public cursosC getCurso() {
            return curso;
        }

       
        public void setCurso(cursosC curso) {
            this.curso = curso;
        }

      
        public carrerasC getCarrera() {
            return carrera;
        }

        
         
         
        public void setCarrera(carrerasC carrera) {
            this.carrera = carrera;
        }

        
        public seccionC getSeccion() {
            return seccion;
        }

        
        public void setSeccion(seccionC seccion) {
            this.seccion = seccion;
        }

      
        public cursoSeccionDocenteC getCursoSeccionDocente() {
            return cursoSeccionDocente;
        }

       
        public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }
    }
    
    
    public class cursoPrincipalSecundario{
        private cursoSeccionDocenteC cursoSeccionDocentePrincipal;
        private carrerasC carrera;
        private cursosC cursos;
        private seccionC seccion;
        private formulaC formula;
       
        private List<cursoSecundario> cursoSeccionDocenteSecundarioL=new ArrayList<>();

        public cursoPrincipalSecundario() {
        }

        public cursoPrincipalSecundario(cursoSeccionDocenteC cursoSeccionDocentePrincipal) {
            this.cursoSeccionDocentePrincipal = cursoSeccionDocentePrincipal;
        }

    

    
       

      
        public cursoSeccionDocenteC getCursoSeccionDocentePrincipal() {
            return cursoSeccionDocentePrincipal;
        }

       
        public void setCursoSeccionDocentePrincipal(cursoSeccionDocenteC cursoSeccionDocentePrincipal) {
            this.cursoSeccionDocentePrincipal = cursoSeccionDocentePrincipal;
        }

        
        public cursosC getCursos() {
            return cursos;
        }

      
        public void setCursos(cursosC cursos) {
            this.cursos = cursos;
        }

      
        public carrerasC getCarrera() {
            return carrera;
        }

        
        public void setCarrera(carrerasC carrera) {
            this.carrera = carrera;
        }

        
        public seccionC getSeccion() {
            return seccion;
        }

        
        public void setSeccion(seccionC seccion) {
            this.seccion = seccion;
        }

        
        public formulaC getFormula() {
            return formula;
        }

       
        public void setFormula(formulaC formula) {
            this.formula = formula;
        }

       
        public List<cursoSecundario> getCursoSeccionDocenteSecundarioL() {
            return cursoSeccionDocenteSecundarioL;
        }

        
        public void setCursoSeccionDocenteSecundarioL(List<cursoSecundario> cursoSeccionDocenteSecundarioL) {
            this.cursoSeccionDocenteSecundarioL = cursoSeccionDocenteSecundarioL;
        }

    }
    
    


    
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }

    
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }
    
    
    
}
