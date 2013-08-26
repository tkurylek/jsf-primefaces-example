package pl.kurylek.jsf.controller;


import java.io.Serializable;
import pl.kurylek.jsf.service.ClientCRUDService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.web.Callback;
import pl.kurylek.jsf.service.LazyClientDataLoaderService;
import static pl.kurylek.jsf.utils.ExceptionUtils.getExceptionRootCauseMessage;
import static pl.kurylek.jsf.utils.ExceptionUtils.getExceptionRootCauseSimpleName;
import static pl.kurylek.jsf.utils.MessagesUtils.addGlobalInfoMessage;
import static pl.kurylek.jsf.utils.MessagesUtils.addGlobalErrorMessage;

@ManagedBean(name = "clientDataTableBean")
@SessionScoped
public class ClientDataTableBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @EJB
    private ClientCRUDService clientCRUDService;
    @EJB
    private LazyClientDataLoaderService lazyClientDataLoaderService;
    private Client[] selectedClients;

    public void removeSelectedClients() {
        clientCRUDService.delete(selectedClients, new Callback<Client[]>() {
            @Override
            public void onSuccess(Client[] clients) {
                addGlobalInfoMessage("Selected clients removed", clients.length + " clients have been removed");
            }

            @Override
            public void onFailure(Exception e) {
                addGlobalErrorMessage(getExceptionRootCauseSimpleName(e), getExceptionRootCauseMessage(e));
            }
        });
    }

    public void editClient(RowEditEvent event) {
        Client client = (Client) event.getObject();
        clientCRUDService.update(client, new Callback<Client>() {
            @Override
            public void onSuccess(Client client) {
                addGlobalInfoMessage("Client saved", "Client has been successfully saved");
            }

            @Override
            public void onFailure(Exception e) {
                addGlobalErrorMessage(getExceptionRootCauseSimpleName(e), getExceptionRootCauseMessage(e));
            }
        });
    }

    public void cancelEditClient(RowEditEvent event) {
        addGlobalInfoMessage("Edition cancelled", "No data was saved");
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