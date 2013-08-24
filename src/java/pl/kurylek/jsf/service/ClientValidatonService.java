package pl.kurylek.jsf.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.domain.Country;
import pl.kurylek.jsf.infrastructure.CountryFacade;
import pl.kurylek.jsf.service.exception.CountryBindingException;

@Stateless
public class ClientValidatonService {
    
    @EJB
    private CountryFacade countryFacade;
    
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
}
