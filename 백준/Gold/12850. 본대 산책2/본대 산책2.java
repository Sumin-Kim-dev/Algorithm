import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static final long MOD = 1_000_000_007;
	static long[][] MATRIX = {
			{0, 1, 1, 0, 0, 0, 0, 0},
			{1, 0, 1, 1, 0, 0, 0, 0},
			{1, 1, 0, 1, 1, 0, 0, 0},
			{0, 1, 1, 0, 1, 1, 0, 0},
			{0, 0, 1, 1, 0, 1, 0, 1},
			{0, 0, 0, 1, 1, 0, 1, 0},
			{0, 0, 0, 0, 0, 1, 0, 1},
			{0, 0, 0, 0, 1, 0, 1, 0}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int d = Integer.parseInt(br.readLine());
		long[][] answer = pow(MATRIX, d);
		System.out.println(answer[0][0]);
	}

	private static long[][] pow(long[][] matrix, int d) {
		if (d == 1) return matrix;
		long[][] pow2 = pow(matrix, d >> 1);
		if ((d & 1) == 0) return matmul(pow2, pow2); 
		return matmul(matmul(matrix, pow2), pow2);
	}

	private static long[][] matmul(long[][] a, long[][] b) {
		long[][] result = new long[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[i].length; k++) {
					result[i][j] += a[i][k] * b[k][j];
					result[i][j] %= MOD;
				}
			}
		}
		return result;
	}
}