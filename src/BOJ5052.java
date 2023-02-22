import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[n];
            for (int i = 0; i < n; i++) {
                phoneNumbers[i] = br.readLine();
            }
            answer.append(solution(n, phoneNumbers) ? "YES" : "NO").append('\n');
        }
        System.out.println(answer);
    }

    public static boolean solution(int n, String[] phoneNumbers) {
        Node root = new Node(false);
        for (int i = 0; i < n; i++) {
            Node curr = root;
            for (int j = 0; j < phoneNumbers[i].length(); j++) {
                char c = phoneNumbers[i].charAt(j);
                Node node = curr.findChild(c);
                if (node != null) {
                    if (j + 1 == phoneNumbers[i].length()) return false;
                    if (node.isEnd) return false;
                    curr = node;
                    continue;
                }
                node = new Node(j + 1 == phoneNumbers[i].length());
                curr.children.put(c, node);
                curr = node;
            }
        }
        return true;
    }

    static class Node {
        private final Map<Character, Node> children;
        private final boolean isEnd;

        public Node(boolean isEnd) {
            children = new HashMap<>();
            this.isEnd = isEnd;
        }

        public Node findChild(char c) {
            return children.get(c);
        }
    }
}
