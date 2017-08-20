/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package runTime;

import java.io.Serializable;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringEscapeUtils;
 
@ManagedBean
@ViewScoped


public class ChatView implements  Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final  EventBus eventBus = EventBusFactory.getDefault().eventBus();
 
    @ManagedProperty("#{chatUsers}")
    private ChatUsers users;
 
    private String privateMessage;
     
    private String globalMessage;
     
    private String username;

     
    private boolean loggedIn;
     
    private String privateUser;
     
    private final static String CHANNEL = "/{room}/";
   
    
    
    public void notificar(String CANAL) {

        String summary = "Nuevo Elemento";
        String detail = "NUEVO USUARIO SE CONECTO";
       
        eventBus.publish(CANAL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(detail)));
    
        
         
       
        
    }
    public ChatUsers getUsers() {
        return users;
    }
 
    public void setUsers(ChatUsers users) {
        this.users = users;
    }
     
    public String getPrivateUser() {
        return privateUser;
    }
 
    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }
 
    public String getGlobalMessage() {
        return globalMessage;
    }
 
    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }
 
    public String getPrivateMessage() {
        return privateMessage;
    }
 
    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
    }
     
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
     
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
 
    public void sendGlobal() {
     //  EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL + "*", username + ": " + globalMessage );         
        globalMessage = null;
    }
     
    public void sendPrivate() {
    //   EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);
        eventBus.publish(CHANNEL + username , "[PM] " + privateUser + ": " + privateMessage);
        System.out.println("enviado privado " + CHANNEL + privateUser + "[PM] " + username + ": " + privateMessage);
        privateMessage = null;
    }
    
 
    public void login() {
        RequestContext requestContext = RequestContext.getCurrentInstance();     
            users.add(username);           
            requestContext.execute("PF('subscriber').connect('/" + username + "')"); 
            // eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": me conecte" );
           eventBus.publish(CHANNEL + privateUser , new Message(String.format("%s Nuevo usuario '%s'",  username, privateUser), true));
    
    }
    
    
    public void loginServer() {
        RequestContext requestContext = RequestContext.getCurrentInstance();         
        if(users.contains(username)) {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
            requestContext.update("growl");
        }
        else {
            
            users.add(username);
            requestContext.execute("PF('subscriber').connect('/" + username + "')");
            notificar("/notify");
            loggedIn = true;
        }
    }
    
  
     
    public void disconnect() {
      
        //remove user and update ui
        users.remove(username);
        RequestContext.getCurrentInstance().update("form:users");
         
        //push leave information
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL + "*", username + " left the channel.");
         
        //reset state
        loggedIn = false;
        username = null;
    }
}
