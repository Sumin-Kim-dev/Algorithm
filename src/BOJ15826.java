import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ15826 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        int size = 0;
        while (true) {
            int info = Integer.parseInt(br.readLine());
            if (info == -1) break;
            if (info == 0) {
                queue.poll();
                size--;
                continue;
            }
            if (size < n) {
                queue.offer(info);
                size++;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            sb.append(queue.poll()).append(' ');
        }
        System.out.println(sb);
    }
}
