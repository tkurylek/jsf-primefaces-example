package pl.kurylek.jsf.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.kurylek.jsf.service.CountryNameCompleteService;

@ManagedBean(name = "countryBean")
@SessionScoped
public class CountryBean {

    @EJB
    private CountryNameCompleteService countryCompleteService;
    
    public List<String> completeCountry(String qery) {
        return countryCompleteService.complete(qery);
    }
}