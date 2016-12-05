package zw.co.veritran.publications.utils.constants;

import java.text.SimpleDateFormat;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class SystemConstants {

    public static final String LOCALE_LANGUAGE = "language";

    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_DELETED = "DELETED";
    public static final String STATUS_INACTIVE = "INACTIVE";

    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String JSON_DATE_FORMAT = "yyyyMMddHHmmss";
    //public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private SystemConstants() {
        super();
    }

    public static void main(String[] args) throws Exception{
        System.out.println("Date : " + new SimpleDateFormat(JSON_DATE_FORMAT).parse("20161203010959"));
    }
}
