import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ13022 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        System.out.println(new BOJ13022().isCorrect(word) ? 1 : 0);
    }

    boolean isCorrect(String word) {
        if (word.charAt(0) != 'w') return false;
        char[] order = "wolf".toCharArray();
        List<Integer>[] letters = new List[4];
        for (int i = 0; i < 4; i++) {
            letters[i] = new ArrayList<>();
        }
        int index = 3;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (order[index] == c) {
                int count = letters[index].get(letters[index].size() - 1) + 1;
                letters[index].set(letters[index].size() - 1, count);
            }
            else if (order[(index + 1) % 4] == c) {
                index = (index + 1) % 4;
                letters[index].add(1);
            }
            else return false;
        }
        int length = letters[0].size();
        for (int i = 1; i < 4; i++) {
            if (letters[i].size() != length) return false;
        }
        for (int i = 0; i < length; i++) {
            int n = letters[0].get(i);
            for (int j = 1; j < 4; j++) {
                if (letters[j].get(i) != n) return false;
            }
        }
        return true;
    }
}
