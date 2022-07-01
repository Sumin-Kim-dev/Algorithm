import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1835 {
    int n;
    Queue<Integer> cards = new LinkedList<>();
    HashMap<Integer, Integer> cardOrder = new HashMap<>();

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            cards.add(i);
        }
    }

    void repeat() {
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j++)
                cards.add(cards.poll());
            cardOrder.put(cards.poll(), i);
        }
    }

    void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(cardOrder.get(i)).append(' ');
        }
        bw.write(sb.toString());
        bw.close();
    }

    void solution() throws IOException {
        input();
        repeat();
        print();
    }
    public static void main(String[] args) throws IOException{
        new BOJ1835().solution();
    }
}
