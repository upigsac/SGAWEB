

package Beans;

import DAO.maquinaMarcacionDAO;
import ENTIDAD.maquinaMarcacionC;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="maquinaPermitidaB")
@ViewScoped
public class maquinaPermitida  implements  Runnable{

    public maquinaPermitida() {
         maquinaMarcacionD=new maquinaMarcacionDAO();
        maquinaMarcacionL=maquinaMarcacionD.mostrarMaquinaMarcacion();
    }
    
    
    private List<maquinaMarcacionC> maquinaMarcacionL;
    private List<maquinaMarcacionC> maquinaMarcacionScaneadas;
    private String mascaraRed="172.16.1.1";
    maquinaMarcacionDAO maquinaMarcacionD;
    Thread hilo1;
    
    public void mostrarMaquinas(){
        maquinaMarcacionD=new maquinaMarcacionDAO();
        maquinaMarcacionL=maquinaMarcacionD.mostrarMaquinaMarcacion();
    }
    
    public void mostrarRed() throws UnknownHostException, IOException{
        //hilo1.start();
        run();
        maquinaMarcacionScaneadas=new ArrayList<>();
        InetAddress inAdd;
        for (int i = 2; i < 10; i++) {
          System.out.println("IP: " + i);
            inAdd = InetAddress.getByName("172.16.1." + i);
            if (inAdd.isReachable(1500)) {
                maquinaMarcacionScaneadas.add(new maquinaMarcacionC(i, null, inAdd.getHostName(), inAdd.getHostAddress(), i));
                System.out.println("IP: " + inAdd.getHostAddress());
                System.out.println("HOST: " + inAdd.getHostName());
                System.out.println();
                util.alert("entre");
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmMaquinaMarcacion:pnMaquinaEncontradas");
            }
        }
    }

    /**
     * @return the maquinaMarcacionL
     */
    public List<maquinaMarcacionC> getMaquinaMarcacionL() {
        return maquinaMarcacionL;
    }

    /**
     * @param maquinaMarcacionL the maquinaMarcacionL to set
     */
    public void setMaquinaMarcacionL(List<maquinaMarcacionC> maquinaMarcacionL) {
        this.maquinaMarcacionL = maquinaMarcacionL;
    }

    /**
     * @return the maquinaMarcacionScaneadas
     */
    public List<maquinaMarcacionC> getMaquinaMarcacionScaneadas() {
        return maquinaMarcacionScaneadas;
    }

    /**
     * @param maquinaMarcacionScaneadas the maquinaMarcacionScaneadas to set
     */
    public void setMaquinaMarcacionScaneadas(List<maquinaMarcacionC> maquinaMarcacionScaneadas) {
        this.maquinaMarcacionScaneadas = maquinaMarcacionScaneadas;
    }

    /**
     * @return the mascaraRed
     */
    public String getMascaraRed() {
        return mascaraRed;
    }

    /**
     * @param mascaraRed the mascaraRed to set
     */
    public void setMascaraRed(String mascaraRed) {
        this.mascaraRed = mascaraRed;
    }

    @Override
    public void run() {
        
        Thread ct = Thread.currentThread();
 
        while (ct == hilo1) {
 
        System.out.println("Corredor 1: " );
 
    }
        
    }
}
