import java.util.Scanner;

public class Main {
	
	public static final int MOD = 1_000_000_007;
	public static final long[][] MATRIX = {{1, 1}, {1, 0}};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long[][] pow = pow(MATRIX, n);
		System.out.println((pow[0][0] * pow[1][0]) % MOD);
	}

	private static long[][] pow(long[][] matrix, long n) {
		if (n == 1) return matrix;
		long[][] halfPow = pow(matrix, n / 2);
		if (n % 2 == 0) return matMul(halfPow, halfPow);
		else return matMul(matrix, matMul(halfPow, halfPow));
	}

	private static long[][] matMul(long[][] a, long[][] b) {
		if (a[0].length != b.length) return null;
		long[][] c = new long[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
				}
			}
		}
		return c;
	}

}