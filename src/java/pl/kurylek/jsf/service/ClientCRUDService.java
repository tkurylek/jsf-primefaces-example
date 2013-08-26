package pl.kurylek.jsf.service;

import pl.kurylek.jsf.web.Callback;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.primefaces.model.SortOrder;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.infrastructure.ClientFacade;

@Stateless
public class ClientCRUDService {

    private static final Logger logger = Logger.getGlobal();
    @EJB
    private ClientValidatonService clientValidatonService;
    @EJB
    private ClientFacade clientFacade;

    public void create(Client client, Callback<Client> callback) {
        try {
            create(client);
            callback.onSuccess(client);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            callback.onFailure(e);
        }
    }

    public void create(Client client) {
        clientValidatonService.validateCountry(client);
        clientFacade.create(client);
    }

    public Client retrieveById(Long id) {
        return clientFacade.find(id);
    }

    public List<Client> retrieveAll() {
        return clientFacade.findAll();
    }

    public List<Client> retrieveAll(int start, int maxResults, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return clientFacade.findAll(start, maxResults, sortField, sortOrder, filters);
    }

    public void update(Client client, Callback<Client> callback) {
        try {
            update(client);
            callback.onSuccess(client);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            callback.onFailure(e);
        }
    }

    public void update(Client client) {
        clientValidatonService.validateCountry(client);
        clientFacade.edit(client);
    }

    public void delete(Client[] clients, Callback<Client[]> callback) {
        try {
            delete(clients);
            callback.onSuccess(clients);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            callback.onFailure(e);
        }
    }

    public void delete(Client[] clients) {
        throwExceptionWhenNoClientWasGiven(clients);
        for (Client client : clients) {
            delete(client);
        }
    }

    private void throwExceptionWhenNoClientWasGiven(Client[] clients) {
        if (clients == null || clients.length <= 0) {
            throw new IllegalArgumentException("No client was given");
        }
    }

    public void delete(Client client) {
        clientValidatonService.validateClientExist(client);
        clientFacade.remove(client);
    }

    public int count() {
        return clientFacade.count();
    }
}