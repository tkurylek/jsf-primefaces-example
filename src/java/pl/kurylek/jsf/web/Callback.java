package pl.kurylek.jsf.web;

public interface Callback<T>  {
    
    void onSuccess(T object);
    
    void onFailure(Exception e);
}
