package zw.co.veritran.publications.utils.dates;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by tyamakura on 2/12/2016.
 */
public class DateUtils {

    private DateUtils() {
        super();
    }

    public static Date getEndOfDay(Date date) {
        if(date == null) {
            return date;
        }
        final LocalDateTime localDateTime = dateToLocalDateTime(date);
        final LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    public static Date getStartOfDay(Date date) {
        if(date == null) {
            return date;
        }
        final LocalDateTime localDateTime = dateToLocalDateTime(date);
        final LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    private static Date localDateTimeToDate(LocalDateTime startOfDay) {
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }
}
