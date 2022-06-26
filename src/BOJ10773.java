import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ10773 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int K = Integer.parseInt(br.readLine());
		Stack<Integer> number = new Stack<>();
		for (int i = 0; i < K; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0)
				number.pop();
			else
				number.add(n);
		}

		int sum = 0;
		int size = number.size();
		for (int i = 0; i < size; i++) {
			sum += number.pop();
		}
		bw.write("" + sum);
		bw.close();
	}

}
