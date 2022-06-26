import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1806 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = Integer.parseInt(st.nextToken());
		bw.write(minLength(a, s) + "");
		bw.close();
	}

	static int minLength(int[] a, int s) {
		int l = 0, f = 1;
		int sum = a[0], minLength = a.length + 1;
		while (l < f) {
			if (sum >= s) {
				if (f - l < minLength)
					minLength = f - l;
				sum -= a[l];
				l++;
			}
			if (sum < s) {
				if (f == a.length)
					break;
				sum += a[f];
				f++;
			}
		}
		if (minLength == a.length + 1)
			return 0;
		return minLength;
	}
}
