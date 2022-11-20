import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1068 {
    int n;
    int root;
    List<Integer>[] children;

    public static void main(String[] args) throws IOException {
        new BOJ1068().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) root = i;
            else children[parent].add(i);
        }
        int erase = Integer.parseInt(br.readLine());
        System.out.println(solution(erase));
    }

    int solution(int erase) {
        if (erase == root) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        int leafs = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            int count = 0;
            for (int child : children[curr]) {
                if (child == erase) continue;
                stack.push(child);
                count++;
            }
            if (count == 0) leafs++;
        }
        return leafs;
    }
}
