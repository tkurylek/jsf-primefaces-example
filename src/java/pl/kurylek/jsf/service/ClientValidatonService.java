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
        String countryName = client.getAddress().getCountry().getName();
        Country country = countryFacade.findByName(countryName);
        throwExceptionWhenCountryDoesNotExist(country, countryName);
        client.getAddress().setCountry(country);
    }

    private void throwExceptionWhenCountryDoesNotExist(Country country, String countryName) {
        if (country == null) {
            throw new CountryBindingException("Given country '%s' is not recognisable", countryName);
        }
    }

    public void validateClientExist(Client client) {
        throwExceptionWhenClientIsNull(client);
        throwExceptionWhenClientDoesNotExist(client);
    }

    private void throwExceptionWhenClientIsNull(Client client) {
        if(client == null) {
            throw new ClientRemoveException("Client cannot be null");
        }
    }

    private void throwExceptionWhenClientDoesNotExist(Client client) {
        assert client != null;
        if (clientFacade.find(client.getId()) == null) {
            throw new ClientRemoveException("Client with id %i does not exist", client.getId());
        }
    }
}
