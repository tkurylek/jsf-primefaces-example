package pl.kurylek.jsf.service;

public interface Callback {
    
    void onSuccess();
    
    void onFailure(Exception e);
}
