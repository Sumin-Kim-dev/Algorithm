

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2110 {
	static int x[];

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		x = new int[N];
		for (int i = 0; i < N; i++) {
			x[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(x);
		bw.write(distance(C) + "");
		bw.close();
	}

	static int distance(int C) {
		int distance = 1;
		int end = x[x.length - 1] - x[0] + 1;
		int mid;
		while (distance + 1 < end) {
			mid = (distance + end) / 2;
			if (able(C, mid))
				distance = mid;
			else
				end = mid;
		}
		return distance;
	}

	static boolean able(int C, int distance) {
		int count = 1;
		int y = x[0];
		while (y + distance <= x[x.length - 1]) {
			y = x[index(y, distance)];
			count++;
		}
		return count >= C;
	}

	static int index(int y, int distance) {
		int index = x.length - 1;
		int end = 0;
		int mid;
		while (index > end + 1) {
			mid = (index + end) / 2;
			if (x[mid] >= y + distance)
				index = mid;
			else
				end = mid;
		}
		return index;
	}
}
