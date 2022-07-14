import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class BOJ1301 {
    int n, sum;
    int[] color;
    HashMap<Count, Long> count = new HashMap<>();

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        color = new int[n];
        sum = 0;
        for (int i = 0; i < n; i++) {
            color[i] = Integer.parseInt(br.readLine());
            sum += color[i];
        }
    }

    class Count {
        int[] countColor;
        int secondToLast, last;

        Count(int[] countColor, int secondToLast, int last) {
            this.countColor = new int[n];
            this.countColor = countColor.clone();
            this.secondToLast = secondToLast;
            this.last = last;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Count))
                return false;
            Count count = (Count) o;
            if (secondToLast != count.secondToLast) return false;
            if (last != count.last) return false;
            for (int i = 0; i < n; i++)
                if (countColor[i] != count.countColor[i]) return false;
            return true;
        }

        public int hashCode() {
            return Objects.hash(Arrays.hashCode(countColor), secondToLast, last);
        }
    }

    long DP(Count curr) {
        if (count.get(curr) != null)
            return count.get(curr);
        int[] beforeCountColor = curr.countColor.clone();
        beforeCountColor[curr.last]--;
        long ans = 0;
        for (int k = 0; k < n; k++) {
            if (k == curr.last || k == curr.secondToLast || beforeCountColor[k] == 0) continue;
            Count before = new Count(beforeCountColor, k, curr.secondToLast);
            ans += DP(before);
        }
        count.put(curr, ans);
        return ans;
    }

    void initializeDP() {
        int[] countColor = new int[n];
        for (int i = 0; i < n; i++) {
            countColor[i]++;
            for (int j = 0; j < n; j++) {
                countColor[j]++;
                Count curr = new Count(countColor, i, j);
                if (i != j) count.put(curr, 1L);
                countColor[j]--;
            }
            countColor[i]--;
        }
    }

    void solution() throws IOException {
        input();
        initializeDP();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                Count curr = new Count(color, j, i);
                ans += DP(curr);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        new BOJ1301().solution();
    }
}
