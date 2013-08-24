package pl.kurylek.jsf.controller;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import pl.kurylek.jsf.service.ClientCRUDService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.factory.ClientFactory;
import pl.kurylek.jsf.service.Callback;
import pl.kurylek.jsf.service.LazyClientDataLoaderService;
import static pl.kurylek.jsf.utils.MessagesUtils.addGlobalMessage;

@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean {

    private static final String INDEX_PAGE = "index";
    @EJB
    private ClientCRUDService clientCRUDService;
    @EJB
    private LazyClientDataLoaderService lazyClientDataLoaderService;
    private Client[] selectedClients;
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
                addGlobalMessage(SEVERITY_ERROR, e.getCause().getMessage());
            }
        });
    }

    public void removeSelectedClients() {
        if (selectedClients.length <= 0) {
            addGlobalMessage(SEVERITY_ERROR, "No client selected", "Please select clients to be removed");
            return;
        }
        for(final Client client : selectedClients) {
            clientCRUDService.delete(client, new Callback() {

                @Override
                public void onSuccess() {
                    addGlobalMessage(SEVERITY_INFO, 
                            String.format("%s %s", client.getFirstName(), client.getLastName()), 
                            "has been successfully removed from the database");
                }

                @Override
                public void onFailure(Exception e) {
                    addGlobalMessage(SEVERITY_INFO, "Error", e.getMessage());
                }
            });    
        }
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
                addGlobalMessage(SEVERITY_INFO, "Error", e.getMessage());
            }
        });
    }

    public void cancelEditClient(RowEditEvent event) {
        addGlobalMessage(SEVERITY_INFO, "Edition cancelled", "No data was saved");
    }

    public LazyClientDataLoaderService getLazyClientDataLoader() {
        return lazyClientDataLoaderService;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client[] getSelectedClients() {
        return selectedClients;
    }

    public void setSelectedClients(Client[] selectedClients) {
        this.selectedClients = selectedClients;
    }
}