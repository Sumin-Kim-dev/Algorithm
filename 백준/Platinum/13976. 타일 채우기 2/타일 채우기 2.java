import java.util.Scanner;

public class Main {

	public static final long MOD = 1_000_000_007;
	public static final long[][] MATRIX = {{4, -1}, {1, 0}};
	public static final long[][] IDENTITY = {{1, 0}, {0, 1}};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		if (n % 2 == 1) {
			System.out.println(0);
			return;
		}
		long[][] pow = pow(MATRIX, n / 2 - 1);
		System.out.println((4 * pow[0][0] + pow[0][1] - 4 * pow[1][0] - pow[1][1] + 5 * MOD) % MOD);
	}

	private static long[][] pow(long[][] matrix, long n) {
		if (n == 0) return IDENTITY;
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