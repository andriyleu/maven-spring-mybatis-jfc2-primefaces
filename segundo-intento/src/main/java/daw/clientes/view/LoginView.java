package daw.clientes.view;


import static daw.clientes.UnLogger.log;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import daw.clientes.service.ClienteService;

@ManagedBean
@Controller
@ViewScoped
public class LoginView {


    @Autowired
    private ClienteService elClienteService;

	public LoginView() {
	    log( "Crean un LoginView");
	}

	@NotNull
	@Size(min = 1, max=20)
	private String name;

	@NotNull
	@Size(min = 1, max=20)
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
	    log( "LoginView.setName:" + name );
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
	    log( "LoginView.setPassword:" + password );
		this.password = password;
	}
	
  public String login() {
      log( "LoginView.login");
      boolean found = elClienteService.getClienteByLogin(getName(), getPassword());
      log( "  found:" + found );
      if (found) {                
          return "clientes?faces-redirect=true";
      } else {         
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario no corresponde con la contraseña"));
          return "";
      }
  }
}
