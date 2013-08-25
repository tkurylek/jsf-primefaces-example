package pl.kurylek.jsf.utils;

public class ExceptionUtils {

    public static String getExceptionRootCauseMessage(Exception e) {
        return getExceptionRootCause(e).getMessage();
    }

    public static String getExceptionRootCauseSimpleName(Exception e) {
        return getExceptionRootCause(e).getClass().getSimpleName();
    }

    public static Throwable getExceptionRootCause(Exception e) {
        if (e.getCause() == null) {
            return e;
        }
        Throwable rootCause = e.getCause();
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }
}
