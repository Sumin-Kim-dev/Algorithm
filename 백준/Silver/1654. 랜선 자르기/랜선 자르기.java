import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		long lan[] = new long[K];
		for (int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(lan);

		long start = 1, end = lan[K - 1] + 1;
		while (start + 1 < end) {
			long mid = (start + end) / 2;
			if (check(mid, lan, N))
				start = mid;
			else
				end = mid;
		}
		bw.write("" + start);
		bw.close();
	}

	static boolean check(long mid, long lan[], int N) {
		int sum = 0;
		for (int i = 0; i < lan.length; i++) {
			sum += lan[i] / mid;
		}
		if (sum >= N)
			return true;
		return false;
	}

}
