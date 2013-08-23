package pl.kurylek.jsf.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import pl.kurylek.jsf.service.CountryNameCompleteService;

@ManagedBean(name = "countryBean")
@RequestScoped
public class CountryBean {

    @EJB
    private CountryNameCompleteService countryCompleteService;
    
    public List<String> completeCountry(String qery) {
        return countryCompleteService.complete(qery);
    }
}