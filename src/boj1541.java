

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class boj1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();
		String num_string[] = str.split("[+-]");
		int num[] = new int[num_string.length];
		for (int i = 0; i < num_string.length; i++) {
			for (int j = 0; j < num_string[i].length(); j++) {
				num[i] = num[i] * 10 + (num_string[i].charAt(j) - '0');
			}
		}
		String operator = str.replaceAll("[0-9]", "");
		Stack<Integer> minus = new Stack<>();
		minus.add(num[0]);
		for (int i = 1; i < num.length; i++) {
			if (operator.charAt(i - 1) == '-')
				minus.add(num[i]);
			else
				minus.add(minus.pop() + num[i]);
		}
		int ans = 0;
		while (minus.size() >= 2) {
			ans += minus.pop();
		}
		ans = minus.pop() - ans;
		bw.write(ans + "");
		bw.close();
	}
}
