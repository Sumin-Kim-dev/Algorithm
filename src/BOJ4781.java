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

    int solution(int m) {
        int[] max = new int[m + 1];
        for (Candy candy : candies) {
            for (int p = candy.price; p <= m; p++) {
                max[p] = Math.max(max[p], max[p - candy.price] + candy.calory);
            }
        }
        return max[m];
    }

    int money(String str) {
        return (int) (Double.parseDouble(str) * 100 + 0.5);
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
