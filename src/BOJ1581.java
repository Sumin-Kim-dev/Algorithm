import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1581 {
    int ff, fs, sf, ss;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ff = Integer.parseInt(st.nextToken());
        fs = Integer.parseInt(st.nextToken());
        sf = Integer.parseInt(st.nextToken());
        ss = Integer.parseInt(st.nextToken());
    }

    int ans() {
        if (ff == 0 && fs == 0) return ss + Math.min(sf, 1);
        if (fs == 0) return ff;
        int changeTempo;
        if (fs > sf) changeTempo = 2 * sf + 1;
        else changeTempo = 2 * fs;
        return ff + ss + changeTempo;
    }

    void solution() throws IOException {
        input();
        System.out.println(ans());
    }

    public static void main(String[] args) throws IOException {
        new BOJ1581().solution();
    }
}
