import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n = 6;
	static int[][] table = new int[n][3];
	static boolean isAble;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int k = 0; k < 4; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			isAble = false;
			boolean keep = true;
			for (int i = 0; i < n; i++) {
				int sum = 0;
				for (int j = 0; j < 3; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
					sum += table[i][j];
				}
				keep = keep & (sum == n - 1);
			}
			if (keep) backtracking(0);
			System.out.print((isAble ? 1 : 0) + " ");
		}
	}

	private static void backtracking(int t) {
		if (isAble) return;
		if (t == n - 1) {
			isAble = true;
			return;
		}
		int[] perm = new int[n - t - 1];
		for (int i = table[t][0]; i < n - t - 1; i++) {
			if (i < table[t][0] + table[t][1]) perm[i] = 1;
			else perm[i] = 2;
		}
		do {
			boolean keep = true;
			for (int i = t + 1; i < n; i++) {
				int result = 2 - perm[i - t - 1];
				keep = keep && table[i][result] > 0;
				table[i][result]--;
			}
			if (keep) backtracking(t + 1);
			for (int i = t + 1; i < n; i++) {
				int result = 2 - perm[i - t - 1];
				table[i][result]++;
			}
		} while (np(perm));
	}

	private static boolean np(int[] perm) {
		int n = perm.length;
		int i = n - 1;
		while (i > 0 && perm[i - 1] >= perm[i]) i--;
		if (i == 0) return false;
		
		int j = n - 1;
		while (perm[i - 1] >= perm[j]) j--;
		
		int temp = perm[i - 1];
		perm[i - 1] = perm[j];
		perm[j] = temp;
		
		int k = n - 1;
		while (i < k) {
			temp = perm[i];
			perm[i] = perm[k];
			perm[k] = temp;
			i++;
			k--;
		}
		return true;
	}
}