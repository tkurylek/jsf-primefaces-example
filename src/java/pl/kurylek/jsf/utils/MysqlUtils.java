package pl.kurylek.jsf.utils;

public class MysqlUtils {
    
    public static String contains(String phrase) {
        return "%"+phrase+"%";
    }
}
