
package Beans;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.component.UISelectItems;

import javax.faces.component.behavior.AjaxBehavior;

import javax.faces.component.html.HtmlCommandButton;

import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectOneMenu;


import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

import javax.faces.model.SelectItem;


@ManagedBean(name = "pageBean")

public class TestBean  {

    private HtmlPanelGroup panel;

    private HtmlCommandButton button;
  //  private List<SelectItem> itemcombo;       

    public void myAction() {

        //System.out.println("myAction called from the dynamic Button");
        util.consolaCliente( "metodo combo box");
        //retrive the dynamically added button
        //HtmlCommandButton button=(HtmlCommandButton)buttonPlaceHolder.getChildren().get(0);
        //HtmlInputText texto=(HtmlInputText)buttonPlaceHolder.getChildren().get(0);
        //HtmlSelectOneMenu combo=(HtmlSelectOneMenu)buttonPlaceHolder.getChildren().get(0);
        //util.consolaCliente( combo.getValue().toString());
        //button.setValue("nuevo botton");
        // combo.
        //util.consolaCliente( texto.getValue());

        //return "Index";
    }

    public TestBean() {
        cargar();
    }

    public void cargar() {

        panel = new HtmlPanelGroup();                                           //instancio panel -- panel esta en la vista con un binding

        //for(int i=0;i<3;i++){                    
        HtmlSelectOneMenu menu = new HtmlSelectOneMenu();                                       //instancio menu -- menu lo creo aqui tambien no esta en vista
        menu.setId("cboMenu"/*+i*/);                                            //opcional -- le doy un id al combo 
        //para darle estilos -- string        

//            MethodBinding mb = FacesContext.getCurrentInstance()
//            .getApplication().createMethodBinding("#{TestBean.myAction}", new Class[0]);
//         //commandButton.setAction(mb);
//         //texto.setValueChangeListener(mb);
//            menu.setValueChangeListener(mb);
//            
        //       ajax
        AjaxBehavior ajax = new AjaxBehavior();
        ajax.addAjaxBehaviorListener(new AjaxBehaviorListener() {
            @Override
            public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
                //util.consolaCliente( ((HtmlSelectOneMenu)event.getSource()).getValue());
                util.consolaCliente( "dd");
            }
        });

        //     se le agrega ajax al cbo
        menu.addClientBehavior("change", ajax);

//            itemcombo = new ArrayList<>();                                         //lista de los valores del combo
//            SelectItem selectItem1 = new SelectItem("first", "First Option");   //valores para el combo
//            SelectItem selectItem2 = new SelectItem("second", "Second Option"); //"itemvalue" , "itemlabel"
//            itemcombo.add(selectItem1);                                         //los añado al listado
//            itemcombo.add(selectItem2);                                         //
//
//            UISelectItems items = new UISelectItems();                          //para dibujar en la vista
//            items.setValue(itemcombo);                                          //los valores que agrege mas arriba
        UISelectItems items = new UISelectItems();
        List comboList = new ArrayList();

        comboList.add(new SelectItem("- seleccionar - ", "ss"));
        comboList.add(new SelectItem("3 mos", " mox 3"));
        comboList.add(new SelectItem("6 mos", " mox 6"));
        comboList.add(new SelectItem("9 mos", " mox 9"));

        items.setValue(comboList);

        menu.getChildren().add(items);                                      //añado los items al menu(combo)        

        panel.getChildren().add(menu);                                      //añado a mi panel ese menu

        //return  "";
    }
    public HtmlPanelGroup getPanel() {
        return panel;
    }
    public void setPanel(HtmlPanelGroup panel) {
        this.panel = panel;
    }

    public HtmlCommandButton getButton() {
        return button;
    }
    public void setButton(HtmlCommandButton button) {
        this.button = button;
    }

}
