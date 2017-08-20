
package Beans;

import DAO.personaBienesDAO;
import DAO.personaDAO;
import DAO.personaGastoDAO;
import DAO.personaParentescoDAO;

import ENTIDAD.personaC;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "fichaIngresanteB")
@ViewScoped
public class fichaIngresante  {

    private List<detalleFamiliar> detalleL = new ArrayList<detalleFamiliar>();
    private personaC persona = new personaC();
    private List<String> bienesL = new ArrayList<String>();
    private List<detalleGastos> gastosL;
    private List<detalleGastos> gastosSeleccionL;

   
    personaParentescoDAO personaParentescoD;
    personaDAO personaD;
    personaBienesDAO personaBienesD;
    personaGastoDAO personaGastosD;

    public List<detalleGastos> mostrarPerGastos() {
        personaGastosD = new personaGastoDAO();
        gastosL = personaGastosD.mostrarGastosPerona();
        return gastosL;
    }

    public void guardarTodo(personaC persona, String codpersona) {
        personaParentescoD = new personaParentescoDAO();
        personaD = new personaDAO();
        personaBienesD = new personaBienesDAO();
        personaGastosD = new personaGastoDAO();
        util.consolaCliente( "entre");

        for (int x = 0; x <= gastosL.size() - 1; x++) {
            util.consolaCliente(""+ gastosL.get(x).descripcion);
            util.consolaCliente(""+ gastosL.get(x).monto);

            //personaGastosD.insertarPersonaGastos(new personaGastosC(codpersona,gastosSeleccionL.get(x).gasto , gastosSeleccionL.get(x).monto, 1));
        }

        for (int x = 0; x <= bienesL.size() - 1; x++) {

         //   personaBienesD.insertarPersonaBienes(new personaBienesC(codpersona, Integer.parseInt(bienesL.get(x).toString()) , 1));
        }

        for (detalleFamiliar item : detalleL) {
            if (item.getPersona().endsWith("-1")) {
               // String rpersona = "";

            //    rpersona=personaD.insertarPersona(new personaC(item.persona,item.aPaterno,item.aMaterno,item.nombres,null,0,null,null,null,0,null,null,null,null,null,null,item.fechaNacimiento,null,0,0,0,null,null,null,null,0,0,0,null,null,0,0,false,false,false,false,0,0,null,null,null,false,null,0,null,0,0));
                //     personaParentescoD.insertarParentesco(new personaParentescoC(codpersona, item.parentesco, rpersona, item.dependedeud, item.pagaesrudios, false, item.getEstadoCivil(), item.hospital, item.gastos, 1));
            } else {
                //       personaParentescoD.insertarParentesco(new personaParentescoC(codpersona, item.parentesco, item.persona, item.dependedeud, item.pagaesrudios, false, item.getEstadoCivil(), item.hospital, item.gastos, 1));
            }

        }

        //  personaD.insertarPersona(persona);
    }

    public void quitarFila(int indice) {

        detalleL.remove(indice);
    }

    public void ingresarFamiliar() {

        Date fecha = null;

        detalleL.add(new detalleFamiliar("-1", persona.getPaterno(), persona.getMaterno(), persona.getNombres(), fecha, false, false, 0, 0, 0.0, persona.getEstado_civil(), persona.getOcupacion(), 0, persona.getCentro_trabajo()));
    }

    public void onRowSelect(SelectEvent event) {

        personaC item = ((personaC) event.getObject());

        detalleL.add(new detalleFamiliar(item.getPersona(), item.getPaterno(), item.getMaterno(), item.getNombres(), item.getFecha_nacimiento(), false, false, 0, 0, 0.0, item.getEstado_civil(), item.getOcupacion(), 1, item.getCentro_trabajo()));
    }

    public List<detalleFamiliar> getDetalleL() {

        return detalleL;
    }
    public void setDetalleL(List<detalleFamiliar> detalleL) {
        this.detalleL = detalleL;
    }
    public personaC getPersona() {
        return persona;
    }
    public void setPersona(personaC persona) {
        this.persona = persona;
    }
    public List<String> getBienesL() {
        return bienesL;
    }
    public void setBienesL(List<String> bienesL) {
        this.bienesL = bienesL;
    }
    public List<detalleGastos> getGastosL() {
        return gastosL;
    }
    public void setGastosL(List<detalleGastos> gastosL) {
        this.gastosL = gastosL;
    }
    public List<detalleGastos> getGastosSeleccionL() {
        return gastosSeleccionL;
    }
    public void setGastosSeleccionL(List<detalleGastos> gastosSeleccionL) {
        this.gastosSeleccionL = gastosSeleccionL;
    }

    public static class detalleGastos {

        private int gasto;
        private String descripcion;
        private double monto;

        public int getGasto() {
            return gasto;
        }

        public void setGasto(int gasto) {
            this.gasto = gasto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getMonto() {
            return monto;
        }

        public void setMonto(double monto) {
            this.monto = monto;
        }
    }

    public static class detalleFamiliar {

        private String persona;
        private String aPaterno;
        private String aMaterno;
        private String nombres;

        private Date fechaNacimiento;
        private boolean dependedeud;
        private boolean pagaesrudios;
        private int tipoEnfermedad;
        private int hospital;
        private double gastos;
        private int estadoCivil;
        private int ocupacion;
        private int parentesco;
        private String centroLaboral;

        public detalleFamiliar() {
        }

        public detalleFamiliar(String persona, String aPaterno, String aMaterno, String nombres, Date fechaNacimiento, boolean dependedeud, boolean pagaesrudios, int tipoEnfermedad, int hospital, double gastos, int estadoCivil, int ocupacion, int parentesco, String centroLaboral) {
            this.persona = persona;
            this.aPaterno = aPaterno;
            this.aMaterno = aMaterno;
            this.nombres = nombres;
            this.fechaNacimiento = fechaNacimiento;
            this.dependedeud = dependedeud;
            this.pagaesrudios = pagaesrudios;
            this.tipoEnfermedad = tipoEnfermedad;
            this.hospital = hospital;
            this.gastos = gastos;
            this.estadoCivil = estadoCivil;
            this.ocupacion = ocupacion;
            this.parentesco = parentesco;
            this.centroLaboral = centroLaboral;
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
        public int getEstadoCivil() {
            return estadoCivil;
        }
        public void setEstadoCivil(int estadoCivil) {
            this.estadoCivil = estadoCivil;
        }
        public int getOcupacion() {
            return ocupacion;
        }
        public void setOcupacion(int ocupacion) {
            this.ocupacion = ocupacion;
        }
        public String getCentroLaboral() {
            return centroLaboral;
        }
        public void setCentroLaboral(String centroLaboral) {
            this.centroLaboral = centroLaboral;
        }
        public boolean isDependedeud() {
            return dependedeud;
        }
        public void setDependedeud(boolean dependedeud) {
            this.dependedeud = dependedeud;
        }

        public boolean isPagaesrudios() {
            return pagaesrudios;
        }
        public void setPagaesrudios(boolean pagaesrudios) {
            this.pagaesrudios = pagaesrudios;
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
        public String getaPaterno() {
            return aPaterno;
        }
        public void setaPaterno(String aPaterno) {
            this.aPaterno = aPaterno;
        }
        public String getaMaterno() {
            return aMaterno;
        }
        public void setaMaterno(String aMaterno) {
            this.aMaterno = aMaterno;
        }
        public String getNombres() {
            return nombres;
        }
        public void setNombres(String nombres) {
            this.nombres = nombres;
        }
        public Date getFechaNacimiento() {
            return fechaNacimiento;
        }
        public void setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }
    }
}
