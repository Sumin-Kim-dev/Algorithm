import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9184 {
	static int w[][][] = new int[21][21][21];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int a, b, c, a0, b0, c0;
		w();
		while (true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if (a == -1 && b == -1 && c == -1)
				break;
			a0 = a;
			b0 = b;
			c0 = c;
			if (a <= 0 || b <= 0 || c <= 0)
				a0 = b0 = c0 = 0;
			else if (a > 20 || b > 20 || c > 20)
				a0 = b0 = c0 = 20;
			sb.append("w(" + a + ", " + b + ", " + c + ") = " + w[a0][b0][c0] + "\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

	static void w() {
		for (int i = 0; i <= 20; i++) {
			for (int j = 0; j <= 20; j++) {
				for (int k = 0; k <= 20; k++) {
					if (i == 0 || j == 0 || k == 0)
						w[i][j][k] = 1;
					else if (i < j && j < k)
						w[i][j][k] = w[i][j][k - 1] + w[i][j - 1][k - 1] - w[i][j - 1][k];
					else
						w[i][j][k] = w[i - 1][j][k] + w[i - 1][j - 1][k] + w[i - 1][j][k - 1] - w[i - 1][j - 1][k - 1];
				}
			}
		}
	}
}
