import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] people = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(people);
		long start = 0;
		long end = 1_000_000_000L * 50;
		while (start + 1 < end) {
			long mid = (start + end) / 2;
			if (isAble(n, k, people, mid)) start = mid;
			else end = mid;
		}
		System.out.println(start);
	}

	private static boolean isAble(int n, int k, int[] people, long groups) {
		long tot = 0;
		int start = n - 1;
		while (start >= 0 && tot < groups * k) {
			if (people[start] > groups) {
				tot += groups;
			} else tot += people[start];
			start--;
		}
		return tot >= groups * k;
	}

}