import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ2981 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int papers[] = new int[N];
		for (int i = 0; i < N; i++) {
			papers[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(papers);

		int newPapers[] = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			newPapers[i] = papers[i + 1] - papers[0];
		}

		int M = gcd(newPapers);
		for (int i = 2; i <= M; i++) {
			if (M % i == 0)
				sb.append(i + " ");
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int gcd(int a[]) {
		int i = 0;
		while (a[i] == 0)
			i++;
		if (i == a.length - 1)
			return a[a.length - 1];
		for (int j = i + 1; j < a.length; j++) {
			a[j] %= a[i];
		}
		Arrays.sort(a);
		return gcd(a);
	}
}