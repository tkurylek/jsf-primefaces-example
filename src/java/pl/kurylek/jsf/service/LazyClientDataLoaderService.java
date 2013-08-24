package pl.kurylek.jsf.service;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import pl.kurylek.jsf.domain.Client;

@Stateless
public class LazyClientDataLoaderService extends LazyDataModel<Client> {

    @EJB
    private ClientCRUDService clientCRUDService;

    @Override
    public Client getRowData(String key) {
        return clientCRUDService.retrieveById(Long.valueOf(key));
    }

    @Override
    public Object getRowKey(Client client) {
        return client.getId();
    }

    @Override
    public int getRowCount() {
        return clientCRUDService.count();
    }

    @Override
    public List<Client> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return clientCRUDService.retrieveAll(first, pageSize, sortField, sortOrder, filters);
    }
    
    // ArithmeticException: / by zero - workaround :/
    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}