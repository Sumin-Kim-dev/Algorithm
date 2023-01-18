import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14719 {
    public static void main(String[] args) throws IOException {
        new BOJ14719().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] blocks = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(h, w, blocks));
    }

    int solution(int h, int w, int[] blocks) {
        List<Integer>[] fills = new List[h];
        for (int i = 0; i < h; i++) {
            fills[i] = new ArrayList<>();
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < blocks[i]; j++) {
                fills[j].add(i);
            }
        }
        int count = 0;
        for (int i = 0; i < h; i++) {
            if (fills[i].size() < 2) continue;
            for (int j = 1; j < fills[i].size(); j++) {
                count += (fills[i].get(j) - fills[i].get(j - 1) - 1);
            }
        }
        return count;
    }
}
