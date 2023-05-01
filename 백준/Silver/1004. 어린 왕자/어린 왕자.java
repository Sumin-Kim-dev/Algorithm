import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(br.readLine());
			int cx[] = new int[n], cy[] = new int[n], r[] = new int[n];
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				cx[j] = Integer.parseInt(st.nextToken());
				cy[j] = Integer.parseInt(st.nextToken());
				r[j] = Integer.parseInt(st.nextToken());
			}
			sb.append(minNum(x1, y1, x2, y2, cx, cy, r));
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int minNum(int x1, int y1, int x2, int y2, int cx[], int cy[], int r[]) {
		int minNum = 0;
		boolean isIn1[] = new boolean[cx.length], isIn2[] = new boolean[cx.length];
		for (int i = 0; i < cx.length; i++) {
			isIn1[i] = isIn(x1, y1, cx[i], cy[i], r[i]);
			isIn2[i] = isIn(x2, y2, cx[i], cy[i], r[i]);
			if (isIn1[i] ^ isIn2[i])
				minNum++;
		}
		return minNum;
	}

	static boolean isIn(int x, int y, int cx, int cy, int r) {
		return (x - cx) * (x - cx) + (y - cy) * (y - cy) < r * r;
	}
}
