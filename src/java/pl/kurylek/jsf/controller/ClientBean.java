package pl.kurylek.jsf.controller;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import pl.kurylek.jsf.service.ClientCRUDService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.factory.ClientFactory;

@ManagedBean(name = "clientBean")
@RequestScoped
public class ClientBean {

    private static final String INDEX_PAGE = "index";
    @EJB
    private ClientCRUDService clientCRUDService;
    private Client client = ClientFactory.createClient();

    public String showIndexPage() {
        return INDEX_PAGE;
    }

    public String storeClient() {
        try {
            clientCRUDService.create(client);
            addGlobalMessage(SEVERITY_INFO, "Client has been added.");
        } catch (Exception e) {
            addGlobalMessage(SEVERITY_ERROR, e.getCause().getMessage());
        }
        return INDEX_PAGE;
    }
    
    private void addGlobalMessage(FacesMessage.Severity type, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(type,message, ""));
    }

    public Client getClient() {
        return client;
    }
}