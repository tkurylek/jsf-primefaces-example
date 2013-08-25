package pl.kurylek.jsf.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtils {
    
    private static final String EMPTY_DETAILS_MESSAGE = "";

    public static void addGlobalMessage(FacesMessage.Severity type, String message) {
        addGlobalMessage(type, message, EMPTY_DETAILS_MESSAGE);
    }

    public static void addGlobalMessage(FacesMessage.Severity type, String message, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(type, message, details));
    }
}
