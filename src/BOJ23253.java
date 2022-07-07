import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ23253 {
    int n, m;
    ArrayList<Integer>[] index;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        index = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            index[i] = new ArrayList<>();
            br.readLine();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                index[i].add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    boolean isAble() {
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < index[i].size(); j++) {
                if (index[i].get(j - 1) < index[i].get(j)) return false;
            }
        }
        return true;
    }

    void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (isAble()) bw.write("Yes");
        else bw.write("No");
        bw.close();
    }

    void solution() throws IOException {
        input();
        print();
    }

    public static void main(String[] args) throws IOException {
        new BOJ23253().solution();
    }
}
