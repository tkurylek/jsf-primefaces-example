package pl.kurylek.jsf.loader;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.service.ClientCRUDService;

@Stateless
@ViewScoped
public class LazyClientDataLoader extends LazyDataModel<Client> {

    private static final int ROW_COUNT = 10;
    @EJB
    private ClientCRUDService clientCRUDService;

    @Override
    public Object getRowKey(Client car) {  
        return car.getId();  
    }

    @Override
    public int getRowCount() {
        return ROW_COUNT;
    }
    
    
    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        }
        else
            super.setRowIndex(rowIndex % getPageSize());
    }
    
    @Override
    public List<Client> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return clientCRUDService.retrieveAll(first, pageSize, sortField, sortOrder, filters);
    }
}