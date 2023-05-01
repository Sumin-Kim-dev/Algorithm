import java.io.*;
import java.util.*;

public class Main {

	static int n, connected[][];
	static boolean check[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		connected = new int[n][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			connected[v1 - 1][v2 - 1] = connected[v2 - 1][v1 - 1] = 1;
		}
		check = new boolean[n];
		int min = 0, KBNum[] = new int[n];
		for (int i = 0; i < n; i++) {
			KBNum[i] = num(i);
			if (KBNum[i] < KBNum[min])
				min = i;
		}
		bw.write(min + 1 + "");
		bw.close();
	}

	static int num(int v) {
		int totalKBNum = 0;
		int kbNum[] = new int[n];
		check = new boolean[n];
		check[v] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		while (!queue.isEmpty()) {
			int index = queue.poll();
			for (int i = 0; i < n; i++) {
				if (!check[i] && connected[index][i] == 1) {
					kbNum[i] = kbNum[index] + 1;
					check[i] = true;
					queue.add(i);
				}
			}
		}
		for (int i = 0; i < n; i++)
			totalKBNum += kbNum[i];
		return totalKBNum;
	}
}
