

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj17298 {

	static int a[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		a = new int[N];
		int NGE[] = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
			NGE[i] = -1;
		}
		Stack<Integer> indexStack = new Stack<>();
		for (int i = 0; i < N - 1; i++) {
			indexStack.add(i);
			while (!indexStack.isEmpty() && a[i + 1] > a[indexStack.peek()]) {
				NGE[indexStack.pop()] = a[i + 1];
			}
		}
		for (int i = 0; i < N; i++) {
			sb.append(NGE[i]);
			sb.append(" ");
		}
		bw.write(sb.toString());
		bw.close();
	}
}
