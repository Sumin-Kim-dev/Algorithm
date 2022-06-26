import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ17387 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		long[][] points = new long[4][2];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			points[i / 2][i % 2] = Integer.parseInt(st1.nextToken());
			points[i / 2 + 2][i % 2] = Integer.parseInt(st2.nextToken());
		}
		long[] calc = calc(points);
		int ans = 0;
		if ((double) calc[0] * calc[1] < 0 && (double) calc[2] * calc[3] < 0)
			ans = 1;
		if (calc[2] == 0 && (points[0][0] - points[2][0]) * (points[0][0] - points[3][0]) <= 0
				&& (points[0][1] - points[2][1]) * (points[0][1] - points[3][1]) <= 0)
			ans = 1;
		if (calc[3] == 0 && (points[1][0] - points[2][0]) * (points[1][0] - points[3][0]) <= 0
				&& (points[1][1] - points[2][1]) * (points[1][1] - points[3][1]) <= 0)
			ans = 1;
		if (calc[0] == 0 && (points[2][0] - points[0][0]) * (points[2][0] - points[1][0]) <= 0
				&& (points[2][1] - points[0][1]) * (points[2][1] - points[1][1]) <= 0)
			ans = 1;
		if (calc[1] == 0 && (points[3][0] - points[0][0]) * (points[3][0] - points[1][0]) <= 0
				&& (points[3][1] - points[0][1]) * (points[3][1] - points[1][1]) <= 0)
			ans = 1;
		bw.write(ans + "");
		bw.close();
	}

	static long[] calc(long[][] points) {
		long[] calc = new long[4];
		// point2는 point0, 1 잇는 직선과 위치 비교
		calc[0] = (points[1][1] - points[0][1]) * (points[2][0] - points[0][0])
				+ (points[0][0] - points[1][0]) * (points[2][1] - points[0][1]);
		// point3은 point0, 1 잇는 직선과 위치 비교
		calc[1] = (points[1][1] - points[0][1]) * (points[3][0] - points[0][0])
				+ (points[0][0] - points[1][0]) * (points[3][1] - points[0][1]);
		// point0은 point1, 2 잇는 직선과 위치 비교
		calc[2] = (points[3][1] - points[2][1]) * (points[0][0] - points[2][0])
				+ (points[2][0] - points[3][0]) * (points[0][1] - points[2][1]);
		// point1은 point1, 2 잇는 직선과 위치 비교
		calc[3] = (points[3][1] - points[2][1]) * (points[1][0] - points[2][0])
				+ (points[2][0] - points[3][0]) * (points[1][1] - points[2][1]);
		return calc;
	}
}