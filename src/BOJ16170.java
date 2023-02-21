import java.time.LocalDateTime;
import java.time.ZoneId;

public class BOJ16170 {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        System.out.println(now.getYear());
        System.out.println(now.getMonth().getValue());
        System.out.println(now.getDayOfMonth());
    }
}
