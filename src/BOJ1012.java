import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {

	static Location field[];
	static int m, n, k;
	static boolean check[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			field = new Location[k];
			check = new boolean[k];
			for (int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				field[j] = new Location(x, y);
			}
			sb.append(count()).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int count() {
		int count = 0;
		for (int i = 0; i < k; i++) {
			if (!check[i]) {
				bfs(i);
				count++;
			}
		}
		return count;
	}

	static void bfs(int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		while (!queue.isEmpty()) {
			int index = queue.poll();
			check[index] = true;
			for (int j = 0; j < k; j++) {
				if (!check[j] && field[index].isConnected(field[j])) {
					check[j] = true;
					queue.add(j);
				}
			}
		}
	}
}

class Location {
	int x, y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	boolean isConnected(Location loc) {
		return (this.x - loc.x) * (this.x - loc.x) + (this.y - loc.y) * (this.y - loc.y) == 1;
	}
}
