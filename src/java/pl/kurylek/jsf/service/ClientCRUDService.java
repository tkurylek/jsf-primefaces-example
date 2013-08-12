package pl.kurylek.jsf.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.infrastructure.ClientFacade;

@Stateless
public class ClientCRUDService {

    @EJB
    private ClientFacade clientFacade;

    public void create(Client client) {
        clientFacade.create(client);
    }

    public List<Client> retrieveAll() {
        return clientFacade.findAll();
    }

    public void update(Client client) {
        clientFacade.edit(client);
    }

    public void delete(Client client) {
        clientFacade.remove(client);
    }
}