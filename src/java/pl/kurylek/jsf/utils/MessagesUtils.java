package pl.kurylek.jsf.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtils {

    private static final String EMPTY_DETAILS_MESSAGE = "";

    public static void addGlobalInfoMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_INFO, message, EMPTY_DETAILS_MESSAGE);
    }

    public static void addGlobalInfoMessage(String message, String details) {
        addGlobalMessage(FacesMessage.SEVERITY_INFO, message, details);
    }
    
    public static void addGlobalWarningMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_WARN, message, EMPTY_DETAILS_MESSAGE);
    }

    public static void addGlobalWarningMessage(String message, String details) {
        addGlobalMessage(FacesMessage.SEVERITY_WARN, message, details);
    }

    public static void addGlobalErrorMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_ERROR, message, EMPTY_DETAILS_MESSAGE);
    }

    public static void addGlobalErrorMessage(String message, String details) {
        addGlobalMessage(FacesMessage.SEVERITY_ERROR, message, details);
    }
    
    public static void addGlobalFatalMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_FATAL, message, EMPTY_DETAILS_MESSAGE);
    }

    public static void addGlobalFatalMessage(String message, String details) {
        addGlobalMessage(FacesMessage.SEVERITY_FATAL, message, details);
    }

    public static void addGlobalMessage(FacesMessage.Severity type, String message) {
        addGlobalMessage(type, message, EMPTY_DETAILS_MESSAGE);
    }

    public static void addGlobalMessage(FacesMessage.Severity type, String message, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(type, message, details));
    }
}
