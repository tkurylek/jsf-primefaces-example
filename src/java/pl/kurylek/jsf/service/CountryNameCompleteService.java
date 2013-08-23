package pl.kurylek.jsf.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.kurylek.jsf.infrastructure.CountryFacade;

@Stateless
public class CountryNameCompleteService {
    
    @EJB
    private CountryFacade countryFacade;

    public List<String> complete(String keyword) {
        return countryFacade.findCountryNamesLike("%" + keyword + "%");
    }
}
