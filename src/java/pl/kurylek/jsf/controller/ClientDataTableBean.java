package pl.kurylek.jsf.controller;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import pl.kurylek.jsf.service.ClientCRUDService;
import javax.ejb.EJB;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.service.Callback;
import pl.kurylek.jsf.service.LazyClientDataLoaderService;
import static pl.kurylek.jsf.utils.ExceptionUtils.getExceptionRootCauseMessage;
import static pl.kurylek.jsf.utils.ExceptionUtils.getExceptionRootCauseSimpleName;
import static pl.kurylek.jsf.utils.MessagesUtils.addGlobalMessage;

@ManagedBean(name = "clientDataTableBean")
@ViewScoped
public class ClientDataTableBean {

    @EJB
    private ClientCRUDService clientCRUDService;
    @EJB
    private LazyClientDataLoaderService lazyClientDataLoaderService;
    private Client[] selectedClients;

    public void removeSelectedClients() {
        clientCRUDService.delete(selectedClients, new Callback() {
            
            @Override
            public void onSuccess() {
                addGlobalMessage(SEVERITY_INFO, "Clients removed", 
                        "Selected clients have been successfully removed from the database");
            }

            @Override
            public void onFailure(Exception e) {
                addGlobalMessage(SEVERITY_ERROR, getExceptionRootCauseSimpleName(e), getExceptionRootCauseMessage(e));
            }
        });
    }

    public void editClient(RowEditEvent event) {
        Client client = (Client) event.getObject();
        clientCRUDService.update(client, new Callback() {
            
            @Override
            public void onSuccess() {
                addGlobalMessage(SEVERITY_INFO, "Success", "Client has been successfully saved");
            }

            @Override
            public void onFailure(Exception e) {
                addGlobalMessage(SEVERITY_ERROR, getExceptionRootCauseSimpleName(e), getExceptionRootCauseMessage(e));
            }
        });
    }

    public void cancelEditClient(RowEditEvent event) {
        addGlobalMessage(SEVERITY_INFO, "Edition cancelled", "No data was saved");
    }

    public LazyClientDataLoaderService getLazyClientDataLoader() {
        return lazyClientDataLoaderService;
    }

    public Client[] getSelectedClients() {
        return selectedClients;
    }

    public void setSelectedClients(Client[] selectedClients) {
        this.selectedClients = selectedClients;
    }
}