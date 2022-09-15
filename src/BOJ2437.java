import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2437 {
    int n;
    int maxWeight;
    int[] weights;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        weights = new int[n];
        maxWeight = 0;
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            maxWeight += weights[i];
        }
        Arrays.sort(weights);
    }

    int minWeight() {
        int weightSum = 1;
        for (int i = 0; i < n; i++) {
            if (weights[i] > weightSum) return weightSum;
            weightSum += weights[i];
        }
        return weightSum;
    }

    void solution() throws IOException {
        input();
        System.out.println(minWeight());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2437().solution();
    }
}
