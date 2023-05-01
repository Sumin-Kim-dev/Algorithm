import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int x1, y1, r1, x2, y2, r2, dSquare;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());
			if (x1 == x2 && y1 == y2) {
				if (r1 != r2)
					sb.append("0\n");
				else if (r1 == 0)
					sb.append("1\n");
				else
					sb.append("-1\n");
			} else {
				dSquare = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
				if (dSquare > (r1 + r2) * (r1 + r2))
					sb.append("0\n");
				else if (dSquare == (r1 + r2) * (r1 + r2) || dSquare == (r1 - r2) * (r1 - r2))
					sb.append("1\n");
				else if (dSquare > (r1 - r2) * (r1 - r2))
					sb.append("2\n");
				else
					sb.append("0\n");

			}
		}
		bw.write(sb.toString());
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}