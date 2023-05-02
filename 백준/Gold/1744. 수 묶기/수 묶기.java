import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    int n;
    int numOfZeros = 0;
    int numOf1 = 0;
    ArrayList<Integer> biggerThan1 = new ArrayList<>();
    ArrayList<Integer> negative = new ArrayList<>();


    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int curr = Integer.parseInt(br.readLine());
            if (curr == 0) numOfZeros++;
            if (curr == 1) numOf1++;
            if (curr > 1) biggerThan1.add(curr);
            if (curr < 0) negative.add(curr);
        }
        Collections.sort(biggerThan1);
        Collections.sort(negative, Collections.reverseOrder());
    }

    int maxOfPositive() {
        int maxOfPositive = 0;
        int size = biggerThan1.size();
        int start = size % 2;
        for (int i = start; i < size; i += 2) {
            maxOfPositive += biggerThan1.get(i) * biggerThan1.get(i + 1);
        }
        if (start == 1) maxOfPositive += biggerThan1.get(0);
        maxOfPositive += numOf1;
        return maxOfPositive;
    }

    int maxOfNegative() {
        int maxOfNegative = 0;
        int size = negative.size();
        int start = size % 2;
        for (int i = start; i < size; i += 2) {
            maxOfNegative += negative.get(i) * negative.get(i + 1);
        }
        if (start == 1 && numOfZeros == 0)
            maxOfNegative += negative.get(0);
        return maxOfNegative;
    }

    void solution() throws IOException {
        input();
        System.out.println(maxOfPositive() + maxOfNegative());
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
