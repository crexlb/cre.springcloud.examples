package routing.getway.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
    /**
     * Parse formatted String "yyyy-MM-dd HH:mm:ss" to ZonedDateTime
     * @param time
     * @return
     */
    public static ZonedDateTime parse(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_TIME);
        return LocalDateTime.parse(time, formatter).atZone(shanghaiZoneId);
    }

    /**
     * Parse formatted String "yyyy-MM-dd" to the start of day as ZonedDateTime
     */
    public static ZonedDateTime parseToStartOfDay(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        return LocalDate.parse(time, formatter).atStartOfDay().atZone(shanghaiZoneId);
    }

    /**
     * Parse formatted String "yyyy-MM-dd" to the end of day as ZonedDateTime
     */
    public static ZonedDateTime parseToEndOfDay(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        return LocalDate.parse(time, formatter).atStartOfDay().plusDays(1).atZone(shanghaiZoneId).minusSeconds(1L);
    }

}
