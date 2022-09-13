import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1700 {
    int n, k;
    int[] order;
    Queue<Integer>[] orderList;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        order = new int[k];
        orderList = new Queue[k];
        for (int i = 0; i < k; i++) {
            orderList[i] = new LinkedList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            order[i] = Integer.parseInt(st.nextToken()) - 1;
            orderList[order[i]].offer(i);
        }
    }

    int count() {
        HashSet<Integer> multitap = new HashSet<>();
        int i = 0;
        while (multitap.size() < n) {
            multitap.add(order[i]);
            orderList[order[i]].poll();
            i++;
        }
        int count = 0;
        for (; i < k; i++) {
            if (multitap.contains(order[i])) {
                orderList[order[i]].poll();
                continue;
            }
            int max = -1, maxIndex = 0;
            int next;
            for (int curr : multitap) {
                if (orderList[curr].isEmpty()) {
                    maxIndex = curr;
                    break;
                }
                next = orderList[curr].peek();
                if (next > max) {
                    max = next;
                    maxIndex = curr;
                }
            }
            multitap.remove(maxIndex);
            count++;
            multitap.add(order[i]);
            orderList[order[i]].poll();
        }
        return count;
    }

    void solution() throws IOException {
        input();
        System.out.println(count());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1700().solution();
    }
}
