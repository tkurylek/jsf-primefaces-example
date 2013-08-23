package pl.kurylek.jsf.infrastructure;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;
import pl.kurylek.jsf.domain.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    public ClientFacade() {
        super(Client.class);
    }

    public List<Client> findAll(int start, int maxResults, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> client = criteriaQuery.from(Client.class);
        if(hasSortingInformation(sortField, sortOrder)) {
            switch(sortOrder) {
                case ASCENDING : 
                    criteriaQuery.orderBy(criteriaBuilder.asc(client.get(sortField)));
                    break;
                case DESCENDING : 
                    criteriaQuery.orderBy(criteriaBuilder.desc(client.get(sortField)));
                    break;
            }
        }
        Query query = em.createQuery(criteriaQuery);
        query.setFirstResult(start);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    private boolean hasSortingInformation(String sortField, SortOrder sortOrder) {
        return sortField != null && sortOrder != null;
    }
}