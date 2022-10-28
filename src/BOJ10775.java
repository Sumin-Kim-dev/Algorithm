import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ10775 {
    int g, p;
    int[] gi;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        gi = new int[p];
        for (int i = 0; i < p; i++) {
            gi[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution());
    }

    boolean[] gate;
    int[] set;

    int solution() {
        int count = 0;
        gate = new boolean[g + 1];
        set = new int[g + 1];
        Arrays.fill(set, -1);
        while (count < p) {
            int curr = findGate(gi[count]);
            if (curr == 0) break;
            gate[curr] = true;
            union(curr, curr - 1);
            count++;
        }
        return count;
    }


    int findGate(int a) {
        if (set[a] < 0) return a;
        else return set[a] = findGate(set[a]);
    }

    void union(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int setB = findGate(b);
        set[a] = setB;
    }

    public static void main(String[] args) throws IOException {
        new BOJ10775().io();
    }
}
