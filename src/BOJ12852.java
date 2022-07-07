import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ12852 {
    final static int MAX = 1000001;
    int n;
    HashMap<Integer, Node> map = new HashMap<>();

    class Node {
        int before, dist;

        Node(int before, int dist) {
            this.before = before;
            this.dist = dist;
        }
    }

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        map.put(n, new Node(MAX, 0));
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == 1) break;
            int currDist = map.get(curr).dist;
            if (curr % 3 == 0 && !map.containsKey(curr / 3)) {
                queue.add(curr / 3);
                map.put(curr / 3, new Node(curr, currDist + 1));
            }
            if (curr % 2 == 0 && !map.containsKey(curr / 2)) {
                queue.add(curr / 2);
                map.put(curr / 2, new Node(curr, currDist + 1));
            }
            if (curr > 1 && !map.containsKey(curr - 1)) {
                queue.add(curr - 1);
                map.put(curr - 1, new Node(curr, currDist + 1));
            }
        }
    }

    String path() {
        StringBuilder sb = new StringBuilder();
        int curr = 1;
        while (curr <= n) {
            sb.insert(0, curr + " ");
            curr = map.get(curr).before;
        }
        return sb.toString();
    }

    void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        sb.append(map.get(1).dist).append('\n');
        sb.append(path());
        bw.write(sb.toString());
        bw.close();
    }

    void solution() throws IOException {
        input();
        bfs();
        print();
    }

    public static void main(String[] args) throws IOException {
        new BOJ12852().solution();
    }
}
