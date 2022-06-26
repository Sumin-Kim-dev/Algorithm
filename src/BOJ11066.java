

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11066 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		int s[];
		for (int i = 0; i < t; i++) {
			int k = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			s = new int[k];
			for (int j = 0; j < k; j++)
				s[j] = Integer.parseInt(st.nextToken());
			sb.append(min(s, k)).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static int min(int s[], int k) {
		int min[][] = new int[k][k];
		int size[][] = new int[k][k];
		for (int i = 0; i < k; i++) {
			size[i][i] = s[i];
		}
		for (int j = 1; j < k; j++) {
			for (int i = 0; i < k - j; i++) {
				size[i][i + j] = size[i][i] + size[i + 1][i + j];
				min[i][i + j] = min[i][i] + min[i + 1][i + j];
				for (int iter = 1; iter < j; iter++)
					if (min[i][i + iter] + min[i + iter + 1][i + j] < min[i][i + j])
						min[i][i + j] = min[i][i + iter] + min[i + iter + 1][i + j];
				min[i][i + j] += size[i][i + j];
			}
		}
		return min[0][k - 1];
	}
}
