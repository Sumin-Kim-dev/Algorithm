import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2096 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] max = new int[3], min = new int[3], score = new int[3], temp = new int[3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				score[j] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 3; j++)
				temp[j] = max[j];
			max[0] = score[0] + max(temp[0], temp[1]);
			max[1] = score[1] + max(temp[0], temp[1], temp[2]);
			max[2] = score[2] + max(temp[1], temp[2]);

			for (int j = 0; j < 3; j++)
				temp[j] = min[j];
			min[0] = score[0] + min(temp[0], temp[1]);
			min[1] = score[1] + min(temp[0], temp[1], temp[2]);
			min[2] = score[2] + min(temp[1], temp[2]);
		}
		sb.append(max(max[0], max[1], max[2])).append(' ').append(min(min[0], min[1], min[2]));
		bw.write(sb.toString());
		bw.close();

	}

	static int max(int a, int b) {
		return a > b ? a : b;
	}

	static int max(int a, int b, int c) {
		return max(max(a, b), c);
	}

	static int min(int a, int b) {
		return a > b ? b : a;
	}

	static int min(int a, int b, int c) {
		return min(min(a, b), c);
	}

}
