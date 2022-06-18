package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {

	final static int[][] knight = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 },
			{ 2, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int l = Integer.parseInt(br.readLine());
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int[] start = { Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()) };
			int[] end = { Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()) };
			sb.append(bfs(start, end, l)).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int bfs(int[] start, int[] end, int l) {

		if (start[0] == end[0] && start[1] == end[1])
			return 0;

		int maxDist = 1;
		int[][] dist = new int[l][l];

		Queue<int[]> queue = new LinkedList<>();
		queue.add(start);
		dist[start[0]][start[1]] = 1;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int i = 0; i < knight.length; i++) {
				int x = curr[0] + knight[i][0];
				int y = curr[1] + knight[i][1];
				if (isIn(x, y, l) && dist[x][y] == 0) {
					queue.add(new int[] { x, y });
					dist[x][y] = dist[curr[0]][curr[1]] + 1;
					if (maxDist < dist[x][y])
						maxDist++;
					if (x == end[0] && y == end[1])
						return maxDist - 1;
				}
			}
		}
		return -1;
	}

	static boolean isIn(int i, int j, int l) {
		return i >= 0 && i < l && j >= 0 && j < l;
	}
}
