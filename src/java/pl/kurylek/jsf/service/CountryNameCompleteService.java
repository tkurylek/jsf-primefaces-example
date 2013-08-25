package pl.kurylek.jsf.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.kurylek.jsf.infrastructure.CountryFacade;
import pl.kurylek.jsf.utils.MysqlUtils;

@Stateless
public class CountryNameCompleteService {
    
    @EJB
    private CountryFacade countryFacade;

    public List<String> complete(String keyword) {
        return countryFacade.findCountryNamesLike(MysqlUtils.contains(keyword));
    }
}
