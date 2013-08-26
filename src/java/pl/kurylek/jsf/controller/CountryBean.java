package pl.kurylek.jsf.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import pl.kurylek.jsf.service.CountryNameCompleteService;

@ManagedBean(name = "countryBean")
@ApplicationScoped
public class CountryBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private CountryNameCompleteService countryNameCompleteService;
    
    public List<String> completeCountry(String qery) {
        return countryNameCompleteService.complete(qery);
    }
}