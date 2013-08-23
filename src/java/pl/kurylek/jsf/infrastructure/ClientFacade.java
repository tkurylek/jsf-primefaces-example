package pl.kurylek.jsf.infrastructure;

import javax.ejb.Stateless;
import pl.kurylek.jsf.domain.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    public ClientFacade() {
        super(Client.class);
    }
}