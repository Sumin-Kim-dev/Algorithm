import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		Stack<Integer> seq = new Stack<>();
		Queue<String> operator = new LinkedList<>();
		int j = 1;
		int a;
		for (int i = 0; i < n; i++) {
			a = Integer.parseInt(br.readLine());
			if (seq.contains(a)) {
				if (a == seq.pop())
					operator.add("-");
				else {
					operator.clear();
					operator.add("NO");
					break;
				}
			} else {
				while (seq.isEmpty() || seq.peek() != a) {
					seq.add(j);
					operator.add("+");
					j++;
				}
				seq.pop();
				operator.add("-");
			}
		}
		while (!operator.isEmpty())
			bw.write(operator.poll() + "\n");
		bw.close();
	}

}
