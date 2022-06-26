

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class boj1992 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int video[][] = new int[N][N];
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				video[i][j] = str.charAt(j) - '0';
			}
		}
		bw.write(zip(video) + "\n");
		bw.close();
	}

	static String zip(int video[][]) {
		StringBuilder sb = new StringBuilder();
		int N = video.length;
		if (sum(video) % (N * N) == 0) {
			sb.append(sum(video) / (N * N));
			return sb.toString();
		}

		else {
			int split00[][] = new int[N / 2][N / 2];
			int split01[][] = new int[N / 2][N / 2];
			int split10[][] = new int[N / 2][N / 2];
			int split11[][] = new int[N / 2][N / 2];
			for (int i = 0; i < N / 2; i++) {
				split00[i] = Arrays.copyOfRange(video[i], 0, N / 2);
				split01[i] = Arrays.copyOfRange(video[i], N / 2, N);
				split10[i] = Arrays.copyOfRange(video[i + N / 2], 0, N / 2);
				split11[i] = Arrays.copyOfRange(video[i + N / 2], N / 2, N);
			}
			sb.append('(');
			sb.append(zip(split00));
			sb.append(zip(split01));
			sb.append(zip(split10));
			sb.append(zip(split11));
			sb.append(')');
		}
		return sb.toString();
	}

	static int sum(int video[][]) {
		int N = video.length;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sum += video[i][j];
		}
		return sum;
	}
}
