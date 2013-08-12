package pl.kurylek.jsf.infrastructure;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.kurylek.jsf.domain.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(name = "mysql-development")
    private EntityManager entityManager;

    public ClientFacade() {
        super(Client.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}