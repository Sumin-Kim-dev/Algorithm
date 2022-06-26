

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2166 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] zeroPoint = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		long[][] point = new long[n - 1][2];
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken()) - zeroPoint[0];
			point[i][1] = Integer.parseInt(st.nextToken()) - zeroPoint[1];
		}
		double area = 0;
		for (int i = 1; i < n - 1; i++) {
			area += point[i - 1][0] * point[i][1] - point[i - 1][1] * point[i][0];
		}
		area = Math.abs(area) / 2;
		bw.write(String.format("%.1f", area));
		bw.close();
	}
}
