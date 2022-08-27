import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2143 {
    int t;
    int[] a, b;
    HashMap<Integer, Integer> sumA;
    long count = 0;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        a = setArr(br);
        sumA = setSum(a);
        b = setArr(br);
    }

    int[] setArr(BufferedReader br) throws IOException {
        int length = Integer.parseInt(br.readLine()) + 1;
        int[] a = new int[length];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < length; i++) {
            a[i] = a[i - 1] + Integer.parseInt(st.nextToken());
        }
        return a;
    }

    HashMap<Integer, Integer> setSum(int[] a) {
        HashMap<Integer, Integer> sum = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                sum.put(a[i] - a[j], sum.getOrDefault(a[i] - a[j], 0) + 1);
            }
        }
        return sum;
    }

    void solution() throws IOException {
        input();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < i; j++) {
                count += sumA.getOrDefault(t - (b[i] - b[j]), 0);
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new BOJ2143().solution();
    }
}
