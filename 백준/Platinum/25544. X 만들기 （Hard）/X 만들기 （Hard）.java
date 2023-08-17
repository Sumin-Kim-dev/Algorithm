import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] points;
	
	public static final int MAX = 1_000_000_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		points = new int[n][6];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		setParts();
		int max = 0;
		for (int i = 0; i < n; i++) {
			int curr = 1;
			for (int j = 1; j <= 4; j++) {
				if (points[i][j + 1] == 0) {
					curr = 0;
					break;
				}
				curr += points[i][j + 1];
			}
			max = Math.max(max, curr);
		}
		System.out.println(max > 0 ? n - max : -1);
	}

	private static void setParts() {
		Arrays.sort(points, (o1, o2) -> {
			if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
			return -Integer.compare(o1[1], o2[1]);
		});
		lis(1, -1, 1);
		lis(3, 1, 1);
		Arrays.sort(points, (o1, o2) -> {
			if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
			return Integer.compare(o1[1], o2[1]);
		});
		lis(2, 1, -1);
		lis(4, -1, -1);
	}

	private static void lis(int q, int order, int increase) {
		int[] last = new int[n + 1];
		last[0] = -MAX * order * increase;
		for (int i = 1; i <= n; i++) {
			last[i] = MAX * order * increase;
		}
		for (int i = 0; i < n; i++) {
			int curr = (n - 1) * (1 - order) / 2 + order * i;
			points[curr][q + 1] = find(last, points[curr][1], order * increase);
			last[points[curr][q + 1] + 1] = points[curr][1];
		}
	}

	private static int find(int[] arr, int a, int order) {
		int start = 0;
		int end = arr.length;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if ((a - arr[mid]) * order > 0) start = mid;
			else end = mid;
		}
		return start;
	}
	
}