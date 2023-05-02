import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        pos.add(0);
        neg.add(0);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x > 0) pos.add(x);
            if (x < 0) neg.add(-x);
        }
        pos.sort(Comparator.reverseOrder());
        neg.sort(Comparator.reverseOrder());
        System.out.println(solution(pos, neg, m));
    }

    int solution(List<Integer> pos, List<Integer> neg, int m) {
        int currPos = 0;
        int currNeg = 0;
        int answer = 0;
        while (currPos < pos.size() || currNeg < neg.size()) {
            if (currPos < pos.size()) {
                answer += 2 * pos.get(currPos);
                currPos += m;
            }
            if (currNeg < neg.size()) {
                answer += 2 * neg.get(currNeg);
                currNeg += m;
            }
        }
        return answer - Math.max(pos.get(0), neg.get(0));
    }
}
