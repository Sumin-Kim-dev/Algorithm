import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ2234 {
    int n, m;
    int[][] walls;
    int[][] rooms;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        walls = new int[m][n];
        rooms = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                walls[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] answer = solution();
        for (int i : answer) {
            System.out.println(i);
        }
    }

    ArrayList<Integer> setRoomSizes() {
        ArrayList<Integer> roomSizes = new ArrayList<>();
        roomSizes.add(0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] > 0) continue;
                int size = roomSize(i, j, roomSizes.size());
                roomSizes.add(size);
            }
        }
        return roomSizes;
    }

    final int[][] DIRECTION = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    int roomSize(int i, int j, int count) {
        Queue<int[]> queue = new LinkedList<>();
        int size = 1;
        queue.offer(new int[]{i, j});
        rooms[i][j] = count;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int k = 0; k < 4; k++) {
                if ((walls[curr[0]][curr[1]] & (1 << k)) != 0) continue;
                int nextI = curr[0] + DIRECTION[k][0];
                int nextJ = curr[1] + DIRECTION[k][1];
                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) continue;
                if (rooms[nextI][nextJ] > 0) continue;
                rooms[nextI][nextJ] = count;
                size++;
                queue.offer(new int[]{nextI, nextJ});
            }
        }
        return size;
    }

    int[] solution() {
        int[] answer = new int[3];
        ArrayList<Integer> roomsSizes = setRoomSizes();
        answer[0] = roomsSizes.size() - 1;
        answer[1] = Collections.max(roomsSizes);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int nextI = i + DIRECTION[k][0];
                    int nextJ = j + DIRECTION[k][1];
                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) continue;
                    if (rooms[i][j] == rooms[nextI][nextJ]) continue;
                    int curr = roomsSizes.get(rooms[i][j]) + roomsSizes.get(rooms[nextI][nextJ]);
                    if (answer[2] < curr) {
                        answer[2] = curr;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        new BOJ2234().io();
    }
}
