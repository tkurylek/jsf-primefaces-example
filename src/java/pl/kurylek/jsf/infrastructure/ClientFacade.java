package pl.kurylek.jsf.infrastructure;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.utils.MysqlUtils;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    public static final String DOT = "\\.";

    public ClientFacade() {
        super(Client.class);
    }

    public List<Client> findAll(int start, int maxResults, String sortingFieldPath, SortOrder sortingOrder, Map<String, String> filtering) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> client = criteriaQuery.from(Client.class);
        if (hasSortingInformation(sortingFieldPath, sortingOrder)) {
            switch (sortingOrder) {
                case ASCENDING:
                    criteriaQuery.orderBy(criteriaBuilder.asc(getNestedField(client, sortingFieldPath)));
                    break;
                case DESCENDING:
                    criteriaQuery.orderBy(criteriaBuilder.desc(getNestedField(client, sortingFieldPath)));
                    break;
            }
        }
        if(hasFilteringInformation(filtering)) {
            for(String fieldPath : filtering.keySet()) {
                Path<String> field = getNestedField(client, fieldPath);
                String pattern = MysqlUtils.contains(filtering.get(fieldPath));
                criteriaQuery.where(criteriaBuilder.like(field, pattern));
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

    private boolean hasFilteringInformation(Map<?, ?> filters) {
        return filters != null;
    }
    
    private <K, V, M extends Map<K, V>> Path<String> getNestedField(Root<?> client, String fieldPath) {
        String[] fieldsNames = fieldPath.split(DOT);
        Path<String> field = client.<String>get(fieldsNames[0]);
        for (int i = 1; i < fieldsNames.length; i++) {
            field = field.get(fieldsNames[i]);
        }
        return field;
    }
}