package Main.Completeable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.time.*;

public class Test {

  public static String getTimeZone(String timezone) {
    return TimeZone.getTimeZone(timezone).getID();
  }
  public static int getOffset(String timezone, long requestedStartTime){
    return TimeZone.getTimeZone(timezone).getOffset(requestedStartTime) / 1000;
  }
  public static LocalDateTime getLocal(Long timestamp, String timezone){
    return LocalDateTime.ofEpochSecond((timestamp / 1000), 0,
            ZoneOffset.ofTotalSeconds(getOffset(timezone, timestamp)));
  }

  public static void main(String[] args) {
    System.out.println(ZonedDateTime.of(getLocal(1723464900000L,"Asia/Kolkata"),ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy 'at' hh:mm a zzz")));
  }
}
