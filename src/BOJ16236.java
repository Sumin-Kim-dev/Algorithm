

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {

	static int[] xAdd = { -1, 0, 0, 1 }, yAdd = { 0, -1, 1, 0 };
	static int[][] space;
	static int n;
	static Shark shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		space = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int fish = Integer.parseInt(st.nextToken());
				if (fish == 9)
					shark = new Shark(i, j);
				if (fish != 9)
					space[i][j] = fish;
			}
		}
		bw.write(total() + "");
		bw.close();
	}

	static int total() {
		int totalTime = 0;
		while (true) {
			int time = move();
			if (time == -1)
				break;
			totalTime += time;
		}
		return totalTime;
	}

	static int move() {
		int[][] dist = new int[n][n];
		boolean[][] check = new boolean[n][n];
		Queue<int[]> queue = new LinkedList<>();
		ArrayList<int[]> candidate = new ArrayList<>();
		int x = shark.x, y = shark.y;
		queue.add(new int[] { x, y });
		check[x][y] = true;
		int maxDist = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int i = 0; i < 4; i++) {
				int newX = curr[0] + xAdd[i];
				int newY = curr[1] + yAdd[i];
				if (isAble(newX, newY) && !check[newX][newY]) {
					check[newX][newY] = true;
					if (shark.size >= space[newX][newY]) {
						queue.add(new int[] { newX, newY });
						dist[newX][newY] = dist[curr[0]][curr[1]] + 1;
						if (maxDist < dist[newX][newY]) {
							if (!candidate.isEmpty()) {
								shark.eat(candidate);
								space[shark.x][shark.y] = 0;
								return maxDist;
							}
							maxDist++;
						}
						if (shark.size > space[newX][newY] && space[newX][newY] != 0) {
							candidate.add(new int[] { newX, newY });
						}
					}
				}
			}
		}
		if (!candidate.isEmpty()) {
			shark.eat(candidate);
			space[shark.x][shark.y] = 0;
			return maxDist;
		}
		return -1;
	}

	static boolean isAble(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}

class Shark {
	int x, y, size, curr;

	Shark(int x, int y) {
		this.x = x;
		this.y = y;
		this.size = 2;
		this.curr = 0;
	}

	void eat(ArrayList<int[]> candidate) {
		this.x = candidate.get(0)[0];
		this.y = candidate.get(0)[1];
		for (int i = 1; i < candidate.size(); i++) {
			if (this.x > candidate.get(i)[0]) {
				this.x = candidate.get(i)[0];
				this.y = candidate.get(i)[1];
			}
			if (this.x == candidate.get(i)[0] && this.y > candidate.get(i)[1])
				this.y = candidate.get(i)[1];
		}
		this.curr++;
		if (this.curr == this.size) {
			this.curr = 0;
			this.size++;
		}
	}
}
