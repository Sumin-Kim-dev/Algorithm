import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;
import java.util.stream.Collectors;

public class BOJ2251 {
    public static void main(String[] args) throws IOException {
        new BOJ2251().io();
    }

    int[] capacity;

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        capacity = new int[3];
        for (int i = 0; i < 3; i++) {
            capacity[i] = Integer.parseInt(st.nextToken());
        }
        print(solution());
    }

    List<Integer> solution() {
        final int[][] PATH = {{0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}};
        Queue<State> queue = new LinkedList<>();
        Set<State> set = new HashSet<>();
        State initial = new State(new int[]{0, 0, capacity[2]});
        queue.offer(initial);
        set.add(initial);
        Set<Integer> result = new HashSet<>();
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (curr.state[0] == 0) result.add(curr.state[2]);
            for (int[] path : PATH) {
                State next = pour(curr, path);
                if (set.contains(next)) continue;
                queue.offer(next);
                set.add(next);
            }
        }
        return result.stream().sorted().collect(Collectors.toList());
    }

    State pour(State curr, int[] path) {
        int start = path[0];
        int end = path[1];
        int[] next = curr.state.clone();
        int move = Math.min(capacity[end] - curr.state[end], curr.state[start]);
        if (move == 0) return curr;
        next[start] -= move;
        next[end] += move;
        return new State(next);
    }

    void print(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(i -> sb.append(i).append(' '));
        System.out.println(sb);
    }

    static class State {
        int[] state;

        public State(int[] state) {
            this.state = state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(state[0], state[1], state[2]);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof State)) return false;
            State s = (State) obj;
            return state[0] == s.state[0] && state[1] == s.state[1] && state[2] == s.state[2];
        }
    }
}
