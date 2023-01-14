import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            new BOJ9205().io(br);
        }
    }

    void io(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] points = new int[n + 2][2];
        for (int i = 0; i < n + 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(n, points));
    }

    String solution(int n, int[][] points) {
        boolean[] isVisited = new boolean[n + 2];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        isVisited[0] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i = 0; i < n + 2; i++) {
                if (isVisited[i]) continue;
                if (Math.abs(points[i][0] - points[curr][0]) + Math.abs(points[i][1] - points[curr][1]) > 1000)
                    continue;
                if (i == n + 1) return "happy";
                isVisited[i] = true;
                queue.offer(i);
            }
        }
        return "sad";
    }
}
