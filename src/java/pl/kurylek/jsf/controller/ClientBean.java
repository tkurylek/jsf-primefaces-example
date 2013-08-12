package pl.kurylek.jsf.controller;

import pl.kurylek.jsf.service.ClientCRUDService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
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
        clientCRUDService.create(client);
        return INDEX_PAGE;
    }

    public Client getClient() {
        return client;
    }
}