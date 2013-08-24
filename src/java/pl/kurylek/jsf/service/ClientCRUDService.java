package pl.kurylek.jsf.service;

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

    private static final Logger logger = Logger.getLogger(ClientCRUDService.class.getSimpleName());
    @EJB
    private ClientValidatonService clientValidatonService;
    @EJB
    private ClientFacade clientFacade;

    public void create(Client client) {
        clientValidatonService.validateCountry(client);
        clientFacade.create(client);
    }
    
    public void create(Client client, Callback callback) {
        try {
            create(client);
            callback.onSuccess();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            callback.onFailure(e);
        }
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
    
    public void update(Client client) {
        clientValidatonService.validateCountry(client);
        clientFacade.edit(client);
    }
    
    public void update(Client client, Callback callback) {
        try {
            update(client);
            callback.onSuccess();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            callback.onFailure(e);
        }
    }

    public void delete(Client client) {
        clientFacade.remove(client);
    }
    
    public void delete(Client client, Callback callback) {
        try {
            delete(client);
            callback.onSuccess();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            callback.onFailure(e);
        }
    }
    
    public int count() {
        return clientFacade.count();
    }
}