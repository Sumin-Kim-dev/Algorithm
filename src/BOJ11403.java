import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11403 {

	static int n, G[][], path[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		G = new int[n][n];
		path = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				G[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			path(i);
			for (int j = 0; j < n; j++)
				sb.append(path[i][j]).append(' ');
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static void path(int v) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		boolean check[] = new boolean[n];
		while (!queue.isEmpty()) {
			int index = queue.poll();
			for (int i = 0; i < n; i++) {
				if (!check[i] && G[index][i] == 1) {
					check[i] = true;
					queue.add(i);
					path[v][i] = 1;
				}
			}
		}
	}

}
