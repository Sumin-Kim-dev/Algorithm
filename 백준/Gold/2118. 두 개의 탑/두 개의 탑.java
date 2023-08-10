import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static int[] dist;
	static int sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dist = new int[n];
		sum = 0;
		for (int i = 0; i < n; i++) {
			dist[i] = Integer.parseInt(br.readLine());
			sum += dist[i];
		}
		int start = 0;
		int end = 1;
		int curr = dist[0];
		int max = d(curr);
		while (start < end && end < n) {
			if (curr <= sum / 2) {
				curr += dist[end++];
				max = Math.max(max, d(curr));
			} else {
				curr -= dist[start++];
				max = Math.max(max, d(curr));
			}
		}
		System.out.println(max);
	}
	
	private static int d(int subsum) {
		return Math.min(subsum, sum - subsum);
	}
}