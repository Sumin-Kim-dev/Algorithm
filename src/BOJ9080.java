import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9080 {
    static final int DAY = 480;
    static final int NIGHT = 1320;
    static final int WHOLE = 1440;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            new BOJ9080().io(br);
        }
    }

    void io(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String[] time = st.nextToken().split(":");
        int h = Integer.parseInt(time[0]);
        int m = Integer.parseInt(time[1]);
        int d = Integer.parseInt(st.nextToken());
        System.out.println(money(h, m, d));
    }

    int money(int h, int m, int d) {
        int s = h * 60 + m;
        int money = 0;
        int night = Math.min(s + d, DAY) - s;
        if (s < DAY && night >= 300) {
            money += 5000;
            if (s + d <= DAY) return money;
            s = DAY;
            d -= night;
        }
        if (s + d <= NIGHT + 300 || s > NIGHT && Math.min(s + d, WHOLE + DAY) - s < 300) {
            return money + dayMoney(d);
        }
        int day = Math.min(s + d, NIGHT) - s;
        money += dayMoney(day);
        if (s < NIGHT) {
            s = NIGHT;
            d -= day;
        }
        money += 5000;
        if (s + d <= WHOLE + DAY) return money;
        if (s + d <= WHOLE + NIGHT + 300) return money + dayMoney(s + d - WHOLE - DAY);
        money += 14000 + 5000;
        if (s + d <= WHOLE * 2 + DAY) return money;
        if (s + d <= WHOLE * 2 + NIGHT + 300) return money + dayMoney(s + d - WHOLE * 2 - DAY);
        money += 14000 + 5000;
        return money + dayMoney(s + d - WHOLE * 3 - DAY);
    }

    int dayMoney(int minute) {
        if (minute <= 0) return 0;
        return (minute + 59) / 60 * 1000;
    }
}
