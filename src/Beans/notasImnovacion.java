
package Beans;

import DAO.invCursoSeccionGrupoDAO;
import DAO.invCursoSeccionNotaDAO;
import DAO.personaDAO;
import ENTIDAD.invAlumnoCursoNotaC;
import ENTIDAD.invCursoSeccionGrupoC;

import ENTIDAD.personaC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="notasImnovacionB")
@ViewScoped
public class notasImnovacion {
    private List<detalleAlumno> detalleAlumnoL=new ArrayList<>();
    
    private List<detalleTipoExamen> detalleTipoExamenL=new ArrayList<>();
    private List<invCursoSeccionGrupoC> CursoSeccionGrupoL;
    
    private boolean  bandera;
    private boolean banderaAcceso;
    private String carreraS;
    private String cursoS;
    private String seccionS;
    private int grupoS;
    
    private String dni;
    private personaC persona=null;
            
    
    
    invCursoSeccionNotaDAO invCursoSeccionNotaD;
    invCursoSeccionGrupoDAO invCursoSeccionGrupoD;
    personaDAO personaD;
    
    //-------- CLIENTE SERVIDOR -----------
    
       @ManagedProperty("#{invServidorB}")
    private invServidor server;
       
       
    
       
       
    
    //--------------------------------------
    
    public void automatico(){
    util.consolaCliente(server.getCarrera());
    carreraS=server.getCarrera();
    cursoS=server.getCurso();
    seccionS=server.getSeccion();
    grupoS=server.getGrupo();
    mostrarNotas(grupoS);
    mostrarGrupos();
}
    
    public void acceso(){
        personaD=new personaDAO();
        persona=personaD.mostrarPersonaJurado(1, "20152", dni);
        if (persona!=null){
            banderaAcceso=true;
            util.consolaCliente("bienvenido");
        }else{
            util.consolaCliente("no se encontro");
            dni="";
        }
        
    }
    public void cerrarAcceso(){
         banderaAcceso=false;
         persona=null;
         dni="";
    }
            
    public void editar(){
        bandera=true;
    }
     public void cancelar(){
        bandera=false;
    }
     public void guardar(){
         
        bandera=false;
         for (detalleAlumno itemAlumno : detalleAlumnoL) {
             
             for (detalleNotaImnovacion  itemNota:  itemAlumno.detalleNotaImnovacionL.values()) {
                 if (itemNota.persona.equalsIgnoreCase(persona.getPersona())){
                     util.consolaCliente("alumno: "+ itemAlumno.desAlumno +" tipoExamnen:"+itemNota.tipoExamen +" valor: "+ itemNota.valor +" nota: "+  itemNota.nota); 
                     invCursoSeccionNotaD.insertar(new invAlumnoCursoNotaC(itemAlumno.institucion, itemAlumno.periodo, itemAlumno.carrera, itemAlumno.malla, itemAlumno.curso, itemAlumno.seccion, itemAlumno.alumno, itemAlumno.grupo, itemNota.tipoExamen, itemNota.valor, itemNota.nota, 1));
                 }
                 
             }
            
              // detalleNotaImnovacionL.
            
        }
    }
    public void mostrarGrupos(){
        invCursoSeccionGrupoD=new invCursoSeccionGrupoDAO();
        CursoSeccionGrupoL=invCursoSeccionGrupoD.mostrarGrupos(1, "20152", carreraS, cursoS, seccionS);
    }
         
    public void mostrarNotas(int grupo){
        invCursoSeccionNotaD=new invCursoSeccionNotaDAO(); 
        grupoS=grupo;
        
       detalleAlumnoL=new ArrayList<>();
        for (detalleAlumno itemAlumno : invCursoSeccionNotaD.mostrarCursoSeccionAlumno(1, "20152", carreraS, cursoS, seccionS, grupoS)) {
            for (detalleNotaImnovacion itemNota : invCursoSeccionNotaD.mostrarCursoSeccionAlumnoNota(itemAlumno.institucion, itemAlumno.periodo, itemAlumno.carrera, itemAlumno.curso, itemAlumno.seccion, itemAlumno.grupo,itemAlumno.alumno)) {
                itemAlumno.getDetalleNotaImnovacionL().put(itemNota.tipoExamen.concat("-").concat(itemNota.valor), itemNota);
                
            }
            detalleAlumnoL.add(itemAlumno);
        }
        
      
        detalleTipoExamenL=new ArrayList<>();
        for (detalleTipoExamen itemTipoExamen : invCursoSeccionNotaD.mostrarTipoExamen()) {        
            
            for (detalleTipoExamenValor itemTipoExamenValor : invCursoSeccionNotaD.mostrarTipoExamenValor(itemTipoExamen.tipoExamen)) {
            itemTipoExamen.getDetalleTipoExamenValorL().add(itemTipoExamenValor);
        }
        detalleTipoExamenL.add(itemTipoExamen);

        
        }
    }
    
    public notasImnovacion() {
     
     
    }
    

    /**
     * @return the detalleAlumnoL
     */
    public List<detalleAlumno> getDetalleAlumnoL() {
        return detalleAlumnoL;
    }

    /**
     * @param detalleAlumnoL the detalleAlumnoL to set
     */
    public void setDetalleAlumnoL(List<detalleAlumno> detalleAlumnoL) {
        this.detalleAlumnoL = detalleAlumnoL;
    }

  

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    /**
     * @return the detalleTipoExamenL
     */
    public List<detalleTipoExamen> getDetalleTipoExamenL() {
        return detalleTipoExamenL;
    }

    /**
     * @param detalleTipoExamenL the detalleTipoExamenL to set
     */
    public void setDetalleTipoExamenL(List<detalleTipoExamen> detalleTipoExamenL) {
        this.detalleTipoExamenL = detalleTipoExamenL;
    }

    /**
     * @return the carreraS
     */
    public String getCarreraS() {
        return carreraS;
    }

    /**
     * @param carreraS the carreraS to set
     */
    public void setCarreraS(String carreraS) {
        this.carreraS = carreraS;
    }

    /**
     * @return the cursoS
     */
    public String getCursoS() {
        return cursoS;
    }

    /**
     * @param cursoS the cursoS to set
     */
    public void setCursoS(String cursoS) {
        this.cursoS = cursoS;
    }

    /**
     * @return the seccionS
     */
    public String getSeccionS() {
        return seccionS;
    }

    /**
     * @param seccionS the seccionS to set
     */
    public void setSeccionS(String seccionS) {
        this.seccionS = seccionS;
    }

   

    /**
     * @return the CursoSeccionGrupoL
     */
    public List<invCursoSeccionGrupoC> getCursoSeccionGrupoL() {
        return CursoSeccionGrupoL;
    }

    /**
     * @param CursoSeccionGrupoL the CursoSeccionGrupoL to set
     */
    public void setCursoSeccionGrupoL(List<invCursoSeccionGrupoC> CursoSeccionGrupoL) {
        this.CursoSeccionGrupoL = CursoSeccionGrupoL;
    }

    /**
     * @return the grupoS
     */
    public int getGrupoS() {
        return grupoS;
    }

    /**
     * @param grupoS the grupoS to set
     */
    public void setGrupoS(int grupoS) {
        this.grupoS = grupoS;
    }

    

   

    /**
     * @return the server
     */
    public invServidor getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(invServidor server) {
        this.server = server;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the banderaAcceso
     */
    public boolean isBanderaAcceso() {
        return banderaAcceso;
    }

    /**
     * @param banderaAcceso the banderaAcceso to set
     */
    public void setBanderaAcceso(boolean banderaAcceso) {
        this.banderaAcceso = banderaAcceso;
    }

    /**
     * @return the persona
     */
    public personaC getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(personaC persona) {
        this.persona = persona;
    }
    
    
    public static class detalleTipoExamenValor{
        
        private String tipoExamen;
        private String valor;

        /**
         * @return the tipoExamen
         */
        public String getTipoExamen() {
            return tipoExamen;
        }

        /**
         * @param tipoExamen the tipoExamen to set
         */
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
        }

        /**
         * @return the valor
         */
        public String getValor() {
            return valor;
        }

        /**
         * @param valor the valor to set
         */
        public void setValor(String valor) {
            this.valor = valor;
        }

       
    }
    
    
    public static class detalleTipoExamen{
        private String tipoExamen;
        private String desTipoExamen;
        
        private List<detalleTipoExamenValor> detalleTipoExamenValorL=new ArrayList<>();
        
        
        
        
        public detalleTipoExamen() {
        }

        public detalleTipoExamen(String tipoExamen, String desTipoExamen) {
            this.tipoExamen = tipoExamen;
            this.desTipoExamen = desTipoExamen;
    
        }
        

        /**
         * @return the tipoExamen
         */
        public String getTipoExamen() {
            return tipoExamen;
        }

        /**
         * @param tipoExamen the tipoExamen to set
         */
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
        }

        /**
         * @return the desTipoExamen
         */
        public String getDesTipoExamen() {
            return desTipoExamen;
        }

        /**
         * @param desTipoExamen the desTipoExamen to set
         */
        public void setDesTipoExamen(String desTipoExamen) {
            this.desTipoExamen = desTipoExamen;
        }

        /**
         * @return the detalleTipoExamenValorL
         */
        public List<detalleTipoExamenValor> getDetalleTipoExamenValorL() {
            return detalleTipoExamenValorL;
        }

        /**
         * @param detalleTipoExamenValorL the detalleTipoExamenValorL to set
         */
        public void setDetalleTipoExamenValorL(List<detalleTipoExamenValor> detalleTipoExamenValorL) {
            this.detalleTipoExamenValorL = detalleTipoExamenValorL;
        }

        
    }
    
    
    
    
    public static class detalleAlumno{
        private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private String seccion;
        private int grupo;
        private String alumno;
        private String desAlumno;
        private HashMap<String,detalleNotaImnovacion> detalleNotaImnovacionL=new HashMap<>();

        public detalleAlumno() {
        }

        public detalleAlumno(int institucion, String periodo, String alumno, String desAlumno) {
            this.institucion = institucion;
            this.periodo = periodo;
            this.alumno = alumno;
            this.desAlumno = desAlumno;
        }

        /**
         * @return the institucion
         */
        public int getInstitucion() {
            return institucion;
        }

        /**
         * @param institucion the institucion to set
         */
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

      

        /**
         * @return the alumno
         */
        public String getAlumno() {
            return alumno;
        }

        /**
         * @param alumno the alumno to set
         */
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        /**
         * @return the desAlumno
         */
        public String getDesAlumno() {
            return desAlumno;
        }

        /**
         * @param desAlumno the desAlumno to set
         */
        public void setDesAlumno(String desAlumno) {
            this.desAlumno = desAlumno;
        }

        /**
         * @return the periodo
         */
        public String getPeriodo() {
            return periodo;
        }

        /**
         * @param periodo the periodo to set
         */
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

     

        /**
         * @return the carrera
         */
        public String getCarrera() {
            return carrera;
        }

        /**
         * @param carrera the carrera to set
         */
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        /**
         * @return the curso
         */
        public String getCurso() {
            return curso;
        }

        /**
         * @param curso the curso to set
         */
        public void setCurso(String curso) {
            this.curso = curso;
        }

        /**
         * @return the seccion
         */
        public String getSeccion() {
            return seccion;
        }

        /**
         * @param seccion the seccion to set
         */
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }

        /**
         * @return the grupo
         */
        public int getGrupo() {
            return grupo;
        }

        /**
         * @param grupo the grupo to set
         */
        public void setGrupo(int grupo) {
            this.grupo = grupo;
        }

        /**
         * @return the detalleNotaImnovacionL
         */
        public HashMap<String,detalleNotaImnovacion> getDetalleNotaImnovacionL() {
            return detalleNotaImnovacionL;
        }

        /**
         * @param detalleNotaImnovacionL the detalleNotaImnovacionL to set
         */
        public void setDetalleNotaImnovacionL(HashMap<String,detalleNotaImnovacion> detalleNotaImnovacionL) {
            this.detalleNotaImnovacionL = detalleNotaImnovacionL;
        }

        /**
         * @return the malla
         */
        public String getMalla() {
            return malla;
        }

        /**
         * @param malla the malla to set
         */
        public void setMalla(String malla) {
            this.malla = malla;
        }

       

        
    }
    public static class detalleNotaImnovacion{
        private String persona;
        private String tipoExamen;
        private String valor;
        private int nota;

        public detalleNotaImnovacion() {
        }

        public detalleNotaImnovacion(String tipoExamen, String valor, int nota) {
            this.tipoExamen = tipoExamen;
            this.valor = valor;
            this.nota = nota;
        }
        
        

        /**
         * @return the tipoExamen
         */
        public String getTipoExamen() {
            return tipoExamen;
        }

        /**
         * @param tipoExamen the tipoExamen to set
         */
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
        }

      

        /**
         * @return the nota
         */
        public int getNota() {
            return nota;
        }

        /**
         * @param nota the nota to set
         */
        public void setNota(int nota) {
            this.nota = nota;
        }

        /**
         * @return the valor
         */
        public String getValor() {
            return valor;
        }

        /**
         * @param valor the valor to set
         */
        public void setValor(String valor) {
            this.valor = valor;
        }

        /**
         * @return the persona
         */
        public String getPersona() {
            return persona;
        }

        /**
         * @param persona the persona to set
         */
        public void setPersona(String persona) {
            this.persona = persona;
        }

       
    }
}
