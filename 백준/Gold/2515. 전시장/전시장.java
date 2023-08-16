import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int s;
	static int[][] pictures;
	static int[] price;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		pictures = new int[n][2];
		price = new int[n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pictures[i][0] = Integer.parseInt(st.nextToken());
			pictures[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(pictures, (o1, o2) -> {
			if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
			return Integer.compare(o1[1], o2[1]);
		});
		
		for (int i = n - 1; i >= 0; i--) {
			price[i] = Math.max(price[i + 1], price[find(pictures[i][0] + s)] + pictures[i][1]);
		}
		System.out.println(price[0]);
	}
	
	private static int find(int h) {
		int start = 0;
		int end = n;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (pictures[mid][0] >= h) end = mid;
			else start = mid;
		}
		return end;
	}
}