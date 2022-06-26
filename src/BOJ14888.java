import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ14888 {

	static int N;
	static int a[], opNum[], operator[], opCount[];
	static int max = -1000000001, min = 1000000001, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		opNum = new int[4];
		for (int i = 0; i < 4; i++) {
			opNum[i] = Integer.parseInt(st.nextToken());
		}
		opCount = new int[4];
		operator = new int[N - 1];
		answer(0);
		bw.write(max + "\n" + min);
		bw.close();
	}

	static void answer(int depth) {
		if (depth == N - 1) {
			ans = a[0];
			for (int i = 0; i < N - 1; i++) {
				switch (operator[i]) {
				case 0 -> ans = ans + a[i + 1];
				case 1 -> ans = ans - a[i + 1];
				case 2 -> ans = ans * a[i + 1];
				case 3 -> ans = ans / a[i + 1];
				}
			}
			if (ans > max)
				max = ans;
			if (ans < min)
				min = ans;
			return;
		}
		for (int i = 0; i < 4; i++) {
			operator[depth] = i;
			opCount[i]++;
			if (opCount[i] <= opNum[i])
				answer(depth + 1);
			opCount[i]--;
		}
	}
}
