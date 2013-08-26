package pl.kurylek.jsf.web;

public interface Callback {
    
    void onSuccess();
    
    void onFailure(Exception e);
}
