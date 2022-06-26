import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1002 {

	int x1, y1, r1, x2, y2, r2, dSquare;
	StringBuilder sb = new StringBuilder();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		r1 = Integer.parseInt(st.nextToken());
		x2 = Integer.parseInt(st.nextToken());
		y2 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
	}

	private void solve() {
		if (x1 == x2 && y1 == y2) { // 두 점이 같은 경우
			if (r1 != r2)
				sb.append("0\n");
			else if (r1 == 0)
				sb.append("1\n");
			else
				sb.append("-1\n");
			return;
		} // 두 점이 다른 경우
		dSquare = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		if (dSquare > (r1 + r2) * (r1 + r2))
			sb.append("0\n"); // 두 원이 서로 안 만 남
		else if (dSquare == (r1 + r2) * (r1 + r2) || dSquare == (r1 - r2) * (r1 - r2))
			sb.append("1\n"); // 두 원이 접함
		else if (dSquare > (r1 - r2) * (r1 - r2))
			sb.append("2\n"); // 두 원의 교점이 두개
		else
			sb.append("0\n"); // 두 원이 서로 포함 관계
	}

	private void print() throws IOException {
		bw.write(sb.toString());
		bw.close();
	}

	private void solution() throws IOException {
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			input();
			solve();
		}
		print();
	}

	public static void main(String[] args) throws IOException {
		new BOJ1002().solution();
	}
}