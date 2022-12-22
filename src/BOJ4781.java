import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4781 {
    int n;
    Candy[] candies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (new BOJ4781().io(br)) ;
    }

    boolean io(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = money(st.nextToken());
        candies = new Candy[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int p = money(st.nextToken());
            candies[i] = new Candy(c, p);
        }
        if (n == 0 && m == 0) return false;
        System.out.println(solution(m));
        return true;
    }

    int[] max = new int[10001];
    int solution(int m) {
        if (m <= 0) return 0;
        if (max[m] > 0) return max[m];
        for (Candy candy : candies) {
            if (candy.price > m) continue;
            max[m] = Math.max(max[m], solution(m - candy.price) + candy.calory);
        }
        return max[m];
    }

    int money(String str) {
        String[] split = str.split("\\.");
        return Integer.parseInt(split[0]) * 100 + Integer.parseInt(split[1]);
    }

    static class Candy {
        int calory;
        int price;

        public Candy(int calory, int price) {
            this.calory = calory;
            this.price = price;
        }
    }
}
