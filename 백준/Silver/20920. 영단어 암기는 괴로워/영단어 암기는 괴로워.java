import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<String> words = new ArrayList<>();
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (word.length() < m) continue;
            if (freq.containsKey(word)) {
                freq.put(word, freq.get(word) + 1);
            } else {
                freq.put(word, 1);
                words.add(word);
            }
        }
        Collections.sort(words, (w1, w2) -> {
            int f1 = freq.getOrDefault(w1, 0);
            int f2 = freq.getOrDefault(w2, 0);
            if (f1 != f2) return f2 - f1;
            if (w1.length() != w2.length()) return w2.length() - w1.length();
            return w1.compareTo(w2);
        });
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append('\n');
        }
        System.out.println(sb);
    }
}