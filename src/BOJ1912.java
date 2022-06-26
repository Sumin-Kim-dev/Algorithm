import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1912 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		int maxSum[] = new int[n + 1];
		int max = -1001;
		for (int i = 1; i <= n; i++) {
			maxSum[i] = maxSum[i - 1] > 0 ? maxSum[i - 1] + a[i - 1] : a[i - 1];
			if (maxSum[i] > max)
				max = maxSum[i];
		}
		bw.write(max + "");
		bw.close();
	}

}
