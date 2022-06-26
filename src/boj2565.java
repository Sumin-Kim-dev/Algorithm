

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2565 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		Line line[] = new Line[n];
		int a, b;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			line[i] = new Line(a, b);
		}
		Arrays.sort(line);
		int seq[] = new int[n];
		seq[0] = 1;
		int temp, max = 1;
		for (int i = 1; i < n; i++) {
			temp = 1;
			for (int j = 0; j < i; j++) {
				if (line[i].b > line[j].b && seq[j] + 1 > temp)
					temp = seq[j] + 1;
			}
			seq[i] = temp;
			if (seq[i] > max)
				max = seq[i];
		}
		bw.write(n - max + "");
		bw.close();
	}

}

class Line implements Comparable<Line> {
	int a, b;

	Line(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int compareTo(Line line) {
		return this.a - line.a;
	}
}