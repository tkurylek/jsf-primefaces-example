package pl.kurylek.jsf.infrastructure;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pl.kurylek.jsf.domain.Country;

@Stateless
public class CountryFacade extends AbstractFacade<Country> {
    public static final int FIRST = 0;

    public CountryFacade() {
        super(Country.class);
    }
    
    public List<String> findCountryNamesLike(String keyword) {
        Query query = em.createQuery("SELECT c.name FROM Country c WHERE UPPER(c.name) LIKE :keyword");
        query.setParameter("keyword", keyword.toUpperCase());
        query.setMaxResults(10);
        return query.getResultList();
    } 
    
    public Country findByName(String countryName) {
        Query query = em.createQuery("SELECT c FROM Country c WHERE c.name = :countryName");
        query.setParameter("countryName", countryName);
        query.setMaxResults(1);
        List<Country> results = query.getResultList();
        return (results.size() > 0)? results.get(FIRST) : null;
    }
}