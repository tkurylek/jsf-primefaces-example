package pl.kurylek.jsf.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.domain.Country;
import pl.kurylek.jsf.infrastructure.ClientFacade;
import pl.kurylek.jsf.infrastructure.CountryFacade;
import pl.kurylek.jsf.service.exception.ClientRemoveException;
import pl.kurylek.jsf.service.exception.CountryBindingException;

@Stateless
public class ClientValidatonService {
    
    @EJB
    private CountryFacade countryFacade;
    @EJB
    private ClientFacade clientFacade;
    
    public void validateCountry(Client client) {
        Country country = findClientCountry(client);
        throwExceptionWhenCountryDoesNotExist(country);
        client.getAddress().setCountry(country);
    }

    private Country findClientCountry(Client client) {
        return countryFacade.findByName(client.getAddress().getCountry().getName());
    }

    private void throwExceptionWhenCountryDoesNotExist(Country country) {
        if (country == null) {
            throw new CountryBindingException("Given country is not recognisable");
        }
    }

    public void validateClientsExist(Client[] clients) {
        throwExceptionWhenNoClientWasGiven(clients);
        throwExceptionWhenClientDoesNotExist(clients);
    }
    
    private void throwExceptionWhenNoClientWasGiven(Client[] clients) {
        if (clients.length <= 0) {
            throw new ClientRemoveException("No client was given");
        }
    }
    
    private void throwExceptionWhenClientDoesNotExist(Client[] clients) {
        for(Client client : clients) {
            throwExceptionWhenClientDoesNotExist(client);
        }
    }

    private void throwExceptionWhenClientDoesNotExist(Client client) {
        if(client == null || client.getId() == null || clientFacade.find(client.getId()) == null) {
            throw new ClientRemoveException("Client with id %i does not exist", client);
        }
    }
}
