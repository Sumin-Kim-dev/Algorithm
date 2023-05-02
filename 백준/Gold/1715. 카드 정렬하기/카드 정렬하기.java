import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    int n;
    PriorityQueue<Integer> cards;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cards = new PriorityQueue<>();
        for(int i = 0; i < n; i++)
            cards.add(Integer.parseInt(br.readLine()));
    }

    long count() {
        long count = 0;
        while(cards.size() > 1) {
            int first = cards.poll();
            int second = cards.poll();
            cards.add(first + second);
            count += first + second;
        }
        return count;
    }

    void solution() throws IOException {
        input();
        System.out.println(count());
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
