package pl.kurylek.jsf.controller;

import javax.faces.bean.ManagedBean;
import pl.kurylek.jsf.domain.Title;

@ManagedBean(name = "clientTitleBean")
public class ClientTitleBean {

    public Title[] getTitles() {
        return Title.values();
    }
}