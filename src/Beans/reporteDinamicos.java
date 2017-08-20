
package Beans;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.component.UISelectItems;
import javax.faces.component.behavior.AjaxBehavior;

import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectOneMenu;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;


@ManagedBean(name = "reporteDinamicosB")
@ViewScoped
public class reporteDinamicos {

    private HtmlPanelGroup buttonPlaceHolder;

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

    public reporteDinamicos() {
        addButton();
    }

    List<SelectItem> comboList;
    UISelectItems items;

    public void addButton() {

        buttonPlaceHolder = new HtmlPanelGroup();

        long indice = Math.round(Math.random() * 1000);
        //create new button object
        //HtmlCommandButton commandButton=new HtmlCommandButton();
        //  HtmlInputText texto=new HtmlInputText();
        HtmlSelectOneMenu combo = new HtmlSelectOneMenu();
	//texto.setValue("nuevo boton "+indice);
        //texto.setValue("nuevo boton "+indice);

        // assign random id to avoid duplicate id for subsquent click
        //texto.setId("button"+ indice); 
        combo.setId("nuevo" + indice);
        items = new UISelectItems();
        comboList = new ArrayList<SelectItem>();

        comboList.add(new SelectItem("- seleccionar - ", "ss"));
        comboList.add(new SelectItem("3 mos", " mox 3"));
        comboList.add(new SelectItem("6 mos", " mox 6"));
        comboList.add(new SelectItem("9 mos", " mox 9"));

        items.setValue(comboList);

        combo.getChildren().add(items); // add list to combobox 

        /*
         //bind the method
         MethodBinding mb = FacesContext.getCurrentInstance()
         .getApplication().createMethodBinding("#{reporteDinamicosB.myAction}", new Class[0]);
         //commandButton.setAction(mb);
         //texto.setValueChangeListener(mb);
         combo.setValueChangeListener(mb);
         */
        // add button the place holder tag created in the JSP
                //ajax
            //se le agrega ajax al cbo
        AjaxBehavior ajax = new AjaxBehavior();
        ajax.addAjaxBehaviorListener(new AjaxBehaviorListener() {
            @Override
            public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
                
            }
        });
        combo.addClientBehavior("change", ajax);

        buttonPlaceHolder.getChildren().add(combo);

    }

    public void listenerType(ValueChangeEvent vce) {
        util.consolaCliente( "entre");
    }

    public void imprimir() {
        String listaParam = "3-@01-03-%-%-%-%-%-%-%-%-1-";
        String[] param = listaParam.split("-");
        for (int i = 0; i < param.length; i++) {
            util.consolaCliente( param[i]);
        }

    }

    public HtmlPanelGroup getButtonPlaceHolder() {
        return buttonPlaceHolder;
    }
    public void setButtonPlaceHolder(HtmlPanelGroup buttonPlaceHolder) {
        this.buttonPlaceHolder = buttonPlaceHolder;
    }

}
