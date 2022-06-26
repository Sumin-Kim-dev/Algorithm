

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class boj4949 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = "";

		while (true) {
			str = br.readLine();
			if (str.equals("."))
				break;

			if (isBalance(str))
				bw.write("yes\n");
			else
				bw.write("no\n");
		}
		bw.close();
	}

	static boolean isBalance(String str) {
		Stack<Character> left = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(' || str.charAt(i) == '[')
				left.add(str.charAt(i));
			if (str.charAt(i) == ')') {
				if (left.isEmpty() || left.pop() != '(')
					return false;
			}
			if (str.charAt(i) == ']') {
				if (left.isEmpty() || left.pop() != '[')
					return false;
			}
		}
		if (left.isEmpty())
			return true;
		return false;
	}
}
