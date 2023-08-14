import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int m;
	static List<int[]> houses;
	static List<int[]> chickens;
	
	static int[] remain;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		remain = new int[m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int c = Integer.parseInt(st.nextToken());
				if (c == 1) houses.add(new int[] {i, j});
				if (c == 2) chickens.add(new int[] {i, j});
			}
		}
		combi(0, 0);
		System.out.println(min);
	}

	private static void combi(int cnt, int start) {
		if (cnt == m) {
			min = Math.min(min, dist());
			return;
		}
		for (int i = start; i < chickens.size(); i++) {
			remain[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	private static int dist() {
		int dist = 0;
		for (int[] house : houses) {
			int min = Integer.MAX_VALUE;
			for (int chickenNo : remain) {
				min = Math.min(min, dist(house, chickens.get(chickenNo)));
			}
			dist += min;
		}
		return dist;
	}

	private static int dist(int[] house, int[] chicken) {
		return Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
	}
}