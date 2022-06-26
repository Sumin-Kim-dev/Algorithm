

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class boj2667 {

	static int n, house[][];
	static int xAdd[] = { -1, 1, 0, 0 }, yAdd[] = { 0, 0, -1, 1 };
	static boolean check[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		house = new int[n][n];
		check = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++)
				house[i][j] = s.charAt(j) - '0';
		}
		print();
	}

	static void print() throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		ArrayList<Integer> nums = new ArrayList<>();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				int loc[] = { i, j };
				if (!check[i][j] && house[i][j] == 1)
					nums.add(dfs(loc));
			}
		Collections.sort(nums);
		sb.append(nums.size()).append('\n');
		Iterator<Integer> iter = nums.iterator();
		while (iter.hasNext()) {
			int num = iter.next();
			sb.append(num).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int dfs(int loc[]) {
		int num = 1;
		int x = loc[0], y = loc[1];
		check[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int newX = x + xAdd[i];
			int newY = y + yAdd[i];
			int newXY[] = { newX, newY };
			if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
				if (house[newX][newY] == 1 && !check[newX][newY]) {
					num += dfs(newXY);
				}
			}
		}
		return num;
	}
}
