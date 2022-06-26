

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11650 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Point2d points[] = new Point2d[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point2d(x, y);
		}
		Arrays.sort(points);
		for (int i = 0; i < N; i++) {
			bw.write(points[i].x + " " + points[i].y + "\n");
		}
		bw.close();
	}

}

class Point2d implements Comparable<Point2d> {
	int x, y;

	public Point2d(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point2d p) {
		if (this.x != p.x)
			return this.x - p.x;
		return this.y - p.y;
	}
}
