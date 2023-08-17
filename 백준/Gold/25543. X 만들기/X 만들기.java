import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	
	static int n;
	static int[][] increasingP;
	static int[][] decreasingP;
	static int[] lastPoint;
	
	public static final int MAX = 1_000_000_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		increasingP = new int[n][2];
		decreasingP = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			increasingP[i][0] = Integer.parseInt(st.nextToken());
			increasingP[i][1] = Integer.parseInt(st.nextToken());
			decreasingP[i][0] = increasingP[i][0];
			decreasingP[i][1] = increasingP[i][1];
		}
		Arrays.sort(increasingP, (o1, o2) -> {
			if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
			return Integer.compare(o1[1], o2[1]);
		});
		Arrays.sort(decreasingP, (o1, o2) -> {
			if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
			return -Integer.compare(o1[1], o2[1]);
		});
		
		int max = 0;
		for (int p = 0; p < n; p++) {
			max = Math.max(max, makeX(p));
		}
		System.out.println(max > 0 ? n - max : -1);
	}

	private static int makeX(int p) {
		int p0 = increasingP[p][0];
		int p1 = increasingP[p][1];
		List<Integer>[] quadrants = new List[4];
		for (int i = 0; i < 4; i++) {
			quadrants[i] = new ArrayList<>();
		}
		for (int i = 0; i < n; i++) {
			if ((decreasingP[i][0] - p0) > 0 && (decreasingP[i][1] - p1) > 0) {
				quadrants[0].add(i);
			} else if ((decreasingP[i][0] - p0) < 0 && (decreasingP[i][1] - p1) < 0) {
				quadrants[2].add(i);
			}
			if ((increasingP[i][0] - p0) < 0 && (increasingP[i][1] - p1) > 0) {
				quadrants[1].add(i);
			} else if ((increasingP[i][0] - p0) > 0 && (increasingP[i][1] - p1) < 0) {
				quadrants[3].add(i);
			}
		}
		
		int count = 1;
		for (int i = 0; i < 4; i++) {
			int part = lis(quadrants[i], 1 - 2 * (i % 2));
			if (part == 0) return 0;
			count += part;
		}
		return count;
	}

	private static int lis(List<Integer> list, int order) {
		int size = list.size();
		lastPoint = new int[size + 1];
		lastPoint[0] = -MAX * order;
		for (int i = 1; i <= size; i++) {
			lastPoint[i] = MAX * order;
		}
		int[][] points;
		if (order == 1) points = decreasingP;
		else points = increasingP;
		int l = 0;
		for (int i : list) {
			int curr = find(points[i][1], order) + 1;
			lastPoint[curr] = points[i][1];
			if (curr > l) l = curr;
		}
		return l;
	}

	private static int find(int p, int order) {
		int start = 0;
		int end = lastPoint.length + 1;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if ((p - lastPoint[mid]) * order > 0) start = mid;
			else end = mid;
		}
		return start;
	}
}