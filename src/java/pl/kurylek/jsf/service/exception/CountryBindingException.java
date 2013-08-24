package pl.kurylek.jsf.service.exception;

public class CountryBindingException extends RuntimeException {

    public CountryBindingException(String format, Object... args) {
        super(String.format(format, args));
    }
}