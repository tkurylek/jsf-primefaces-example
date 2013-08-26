/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kurylek.jsf.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import javax.faces.bean.ManagedBean;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.factory.ClientFactory;
import pl.kurylek.jsf.web.Callback;
import pl.kurylek.jsf.service.ClientCRUDService;
import static pl.kurylek.jsf.utils.ExceptionUtils.getExceptionRootCauseMessage;
import static pl.kurylek.jsf.utils.ExceptionUtils.getExceptionRootCauseSimpleName;
import static pl.kurylek.jsf.utils.MessagesUtils.addGlobalMessage;

@ManagedBean(name = "clientStorageBean")
@RequestScoped
public class ClientStorageBean {

    private static final String INDEX_PAGE = "index";
    @EJB
    private ClientCRUDService clientCRUDService;
    private Client client = ClientFactory.createClient();

    public String showIndexPage() {
        return INDEX_PAGE;
    }

    public void storeClient() {
        clientCRUDService.create(client, new Callback() {
            
            @Override
            public void onSuccess() {
                addGlobalMessage(SEVERITY_INFO, "Client has been added.");
            }

            @Override
            public void onFailure(Exception e) {
                addGlobalMessage(SEVERITY_ERROR, getExceptionRootCauseSimpleName(e), getExceptionRootCauseMessage(e));
            }
        });
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
