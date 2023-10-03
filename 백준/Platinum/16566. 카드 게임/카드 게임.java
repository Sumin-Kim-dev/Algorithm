import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int m, k;
    int[] cards;
    int[] magician;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);
        cards = new int[m];
        magician = new int[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            cards[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cards);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++)
            magician[i] = Integer.parseInt(st.nextToken());
    }

    int binarySearch(int n) {
        int start = m - 1;
        int end = -1;
        while (start > end + 1) {
            int mid = (start + end) / 2;
            if (cards[mid] > n) start = mid;
            else end = mid;
        }
        return start;
    }

    int find(int n, int[] set) {
        if (set[n] < 0) return n;
        return set[n] = find(set[n], set);
    }

    void union(int a, int b, int[] set) {
        if (b >= m) return;
        set[a] = b;
    }

    void solution() throws IOException {
        input();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] set = new int[m];
        Arrays.fill(set, -1);
        for (int card : magician) {
            int position = binarySearch(card);
            position = find(position, set);
            union(position, position + 1, set);
            bw.write(cards[position] + "");
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
