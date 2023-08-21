import java.util.Scanner;

public class Main {
	
	public static final int MAX = 1_000_000_001;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] combi = new int[n + m + 1][n + 1];
		setCombi(combi, n + m, n);
		
		if (k > combi[n + m][n]) {
			System.out.println(-1);
			return;
		}
		StringBuilder sb = new StringBuilder();
		while (m > 0) {
			int i = find(combi, n, m, k);
			while (n > i) {
				sb.append('a');
				n--;
			}
			sb.append('z');
			if (i > 0) k -= combi[m + i - 1][i - 1];
			m--;
		}
		while (n-- > 0) {
			sb.append('a');
		}
		System.out.println(sb);
	}

	private static void setCombi(int[][] combi, int n, int m) {
		combi[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			combi[i][0] = 1;
			for (int j = 1; j <= m; j++) {
				combi[i][j] = combi[i - 1][j - 1] + combi[i - 1][j];
				if (combi[i][j] >= MAX) combi[i][j] = MAX;
			}
		}
	}
	
	private static int find(int[][] combi, int n, int m, int k) {
		if (k == 1) return 0;
		int start = 0;
		int end = n;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (combi[m + mid][mid] < k) start = mid;
			else end = mid;
		}
		return end;
	}

}