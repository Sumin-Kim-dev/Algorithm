import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			String str = br.readLine();
			int[] input = new int[str.length()];
			for (int i = 0; i < str.length(); i++) {
				input[i] = str.charAt(i) - '0';
			}
			System.out.println(isAble(input, 0, input.length) ? "YES" : "NO");
		}
	}

	private static boolean isAble(int[] input, int start, int end) {
		int n = end - start;
		if (n == 1) return true;
		for (int i = 0; i < n / 2; i++) {
			if (input[start + i] == input[end - 1 - i]) return false;
		}
		return isAble(input, start, start + n / 2);
	}

}