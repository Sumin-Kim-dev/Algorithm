import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158 {
    int n, k;
    Queue<Integer> people = new LinkedList<>();
    int[] seq;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++)
            people.add(i + 1);
        seq = new int[n];
    }

    void makeSequence() {
        int index = 0;
        while (index < n) {
            for (int i = 1; i < k; i++)
                people.add(people.poll());
            seq[index++] = people.poll();
        }
    }

    void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        for (int i = 0; i < n; i++) {
            sb.append(seq[i]);
            if (i != n - 1) sb.append(", ");
        }
        sb.append('>');
        bw.write(sb.toString());
        bw.close();
    }

    void solution() throws IOException {
        input();
        makeSequence();
        print();
    }

    public static void main(String[] args) throws IOException {
        new BOJ1158().solution();
    }
}
