package pl.kurylek.jsf.service.exception;

public class ClientRemoveException extends RuntimeException {

    public ClientRemoveException(String format, Object... objects) {
        super(String.format(format, objects));
    }
}