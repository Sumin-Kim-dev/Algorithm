import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int trees[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(trees);

		int start = 0;
		int end = trees[N - 1] + 1;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (isEnough(mid, trees, M))
				start = mid;
			else
				end = mid;
		}
		bw.write(start + "");
		bw.close();
	}

	static boolean isEnough(int h, int trees[], int M) {
		long sum = 0;
		for (int i = 0; i < trees.length; i++) {
			sum += trees[i] > h ? trees[i] - h : 0;
		}
		return sum >= M;
	}
}
