package pl.kurylek.jsf.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtils {

    public static void addGlobalMessage(FacesMessage.Severity type, String message) {
        addGlobalMessage(type, message, "");
    }

    public static void addGlobalMessage(FacesMessage.Severity type, String message, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(type, message, details));
    }
}
