package pl.kurylek.jsf.controller;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import pl.kurylek.jsf.domain.Title;

@ManagedBean(name = "clientTitleBean")
@ApplicationScoped
public class ClientTitleBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public Title[] getTitles() {
        return Title.values();
    }
}