import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 {

	static ArrayList<int[]> house = new ArrayList<>(), chicken = new ArrayList<>();
	static int[] seq;
	static int[][] dist;
	static int n, m, numHouse, numChicken, minDist = 10000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1)
					house.add(new int[] { i, j });
				if (num == 2)
					chicken.add(new int[] { i, j });
			}
		}
		numHouse = house.size();
		numChicken = chicken.size();
		dist = new int[numHouse][numChicken];
		seq = new int[m];
		for (int i = 0; i < numHouse; i++) {
			int[] currHouse = house.get(i);
			for (int j = 0; j < numChicken; j++) {
				int[] currChicken = chicken.get(j);
				dist[i][j] = dist(currHouse, currChicken);
			}
		}

		backTracking(0);
		bw.write(minDist + "");
		bw.close();
	}

	static int totalDist() {
		int totalDist = 0;
		for (int i = 0; i < numHouse; i++) {
			int minDist = 100;
			for (int j = 0; j < seq.length; j++) {
				int currDist = dist[i][seq[j]];
				if (currDist < minDist)
					minDist = currDist;
			}
			totalDist += minDist;
		}
		return totalDist;
	}

	static void backTracking(int depth) {
		if (depth == m) {
			int totalDist = totalDist();
			if (totalDist < minDist)
				minDist = totalDist;
			return;
		}
		for (int i = 0; i < numChicken; i++) {
			if (depth != 0 && seq[depth - 1] >= i)
				continue;
			seq[depth] = i;
			backTracking(depth + 1);
		}
	}

	static int dist(int[] house, int[] chicken) {
		int distX = house[0] - chicken[0];
		if (distX < 0)
			distX = -distX;
		int distY = house[1] - chicken[1];
		if (distY < 0)
			distY = -distY;
		return distX + distY;
	}
}
