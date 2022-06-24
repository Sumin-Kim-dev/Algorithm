package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1111 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int arr[] = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		bw.write(ans(arr, n));
		bw.close();
	}

	static String ans(int[] arr, int n) {
		if (n == 1)
			return "A";
		if (n == 2 && arr[0] != arr[1])
			return "A";
		if (arr[0] == arr[1]) {
			for (int i = 1; i < n; i++) {
				if (arr[i] != arr[0])
					return "B";
			}
			return Integer.toString(arr[0]);
		}
		if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0)
			return "B";
		int a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
		int b = (arr[1] * arr[1] - arr[0] * arr[2]) / (arr[1] - arr[0]);
		for (int i = 1; i < n; i++) {
			int expect = a * arr[i - 1] + b;
			if (expect != arr[i])
				return "B";
		}
		return Integer.toString(a * arr[n - 1] + b);
	}
}
