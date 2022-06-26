import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ9095 {
	static int answer[] = new int[12];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int n;
		answer[0] = 1;
		for (int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			answer(n);
			sb.append(answer[n]);
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

	static void answer(int n) {
		int i = 0;
		while (answer[i] != 0)
			i++;
		while (i <= n) {
			for (int j = 3; j >= 1; j--) {
				if (i - j < 0)
					continue;
				answer[i] += answer[i - j];
			}
			i++;
		}
	}
}
