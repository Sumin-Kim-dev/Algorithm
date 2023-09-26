import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] set;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			set = new int[n];
			Arrays.fill(set, -1);
			int count = n;
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				count -= union(i, j);
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

	private static int union(int i, int j) {
		i = find(i);
		j = find(j);
		if (i == j) return 0;
		if (-set[i] < -set[j]) {
			int temp = i;
			i = j;
			j = temp;
		}
		set[i] += set[j];
		set[j] = i;
		return 1;
	}

	private static int find(int i) {
		if (set[i] < 0) return i;
		return set[i] = find(set[i]);
	}

}