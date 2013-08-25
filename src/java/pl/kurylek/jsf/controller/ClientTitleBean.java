package pl.kurylek.jsf.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.kurylek.jsf.domain.Title;

@ManagedBean(name = "clientTitleBean")
@SessionScoped
public class ClientTitleBean {

    public Title[] getTitles() {
        return Title.values();
    }
}