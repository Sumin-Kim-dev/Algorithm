import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[m][n];
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    map[j][i] = -1;
                }
            }
        }
        int a = 0;
        List<Integer> areas = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) continue;
                int area = bfs(i, j, map, ++a);
                areas.add(area);
            }
        }
        Collections.sort(areas);
        StringBuilder sb = new StringBuilder();
        sb.append(a).append("\n");
        for (int area : areas) {
            sb.append(area).append(" ");
        }
        System.out.println(sb);
    }
    
    static int bfs(int i, int j, int[][] map, int k) {
        int area = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {i, j});
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (map[curr[0]][curr[1]] != 0) continue;
            map[curr[0]][curr[1]] = k;
            area++;
            for (int d = 0; d < 4; d++) {
                int[] next = {curr[0] + dr[d], curr[1] + dc[d]};
                if (next[0] < 0 || next[0] == map.length || next[1] < 0 || next[1] == map[0].length) continue;
                if (map[next[0]][next[1]] != 0) continue;
                queue.offer(next);
            }
        }
        return area;
    }
}