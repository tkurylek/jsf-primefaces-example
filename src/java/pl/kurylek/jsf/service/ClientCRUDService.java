package pl.kurylek.jsf.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.domain.Country;
import pl.kurylek.jsf.infrastructure.ClientFacade;
import pl.kurylek.jsf.infrastructure.CountryFacade;
import pl.kurylek.jsf.service.exception.CountryBindingException;

@Stateless
public class ClientCRUDService {
    
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private CountryFacade countryFacade;

    public void create(Client client) {
        final String countryName = client.getAddress().getCountry().getName();
        Country country = countryFacade.findByName(countryName);
        throwExceptionWhenCountryDoesNotExist(country);
        client.getAddress().setCountry(country);
        clientFacade.create(client);
    }
    
    private void throwExceptionWhenCountryDoesNotExist(Country country) {
        if(country == null) {
            throw new CountryBindingException("Given country is not recognisable.");
        }
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