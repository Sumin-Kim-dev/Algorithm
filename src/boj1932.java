

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1932 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int triangle[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int maxSum[] = triangle[0].clone();
		int temp[];
		for (int i = 1; i < n; i++) {
			temp = maxSum.clone();
			maxSum[0] += triangle[i][0];
			for (int j = 1; j <= i; j++)
				maxSum[j] = (temp[j] > temp[j - 1] ? temp[j] : temp[j - 1]) + triangle[i][j];
		}
		int max = maxSum[0];
		for (int j = 1; j < n; j++)
			if (maxSum[j] > max)
				max = maxSum[j];
		bw.write(max + "");
		bw.close();
	}

}
