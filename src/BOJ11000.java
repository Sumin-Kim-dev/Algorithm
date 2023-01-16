import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11000 {
    public static final int START = 0, END = 1;

    public static void main(String[] args) throws IOException {
        new BOJ11000().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<int[]> classes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            classes.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        System.out.println(solution(classes));
    }

    int solution(List<int[]> classes) {
        classes.sort(Comparator.comparingInt(o -> o[START]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[END]));
        for (int[] aClass : classes) {
            if (!pq.isEmpty() && aClass[START] >= pq.peek()[END]) {
                pq.poll();
            }
            pq.offer(aClass);
        }
        return pq.size();
    }
}
