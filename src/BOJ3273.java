

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(br.readLine());
		Arrays.sort(a);
		bw.write(pair(a, x) + "");
		bw.close();
	}

	static int pair(int[] a, int x) {
		int i = 0, j = a.length - 1, count = 0;
		while (i < j) {
			if (a[i] + a[j] == x)
				count++;
			if (a[i] + a[j] >= x)
				j--;
			if (a[i] + a[j] < x)
				i++;
		}
		return count;
	}
}
