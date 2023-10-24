import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		makeArr(arr, 0, n, 1, 0);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(arr[i]).append(' ');
		}
		System.out.println(sb);
	}

	private static void makeArr(int[] arr, int start, int length, int a, int b) {
		if (length == 1) {
			arr[start] = a + b;
			return;
		}
		makeArr(arr, start, (length + 1) / 2, 2 * a, -a + b);
		makeArr(arr, start + (length + 1) / 2, length / 2, 2 * a, b);
	}

}