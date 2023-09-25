import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		long answer = 0;
		int i = 0;
		int j = 1;
		int[] curr = new int[100000];
		curr[a[0]] = 1;
		while (j < n) {
			if (curr[a[j]] == 0) {
				curr[a[j]]++;
				j++;
			} else {
				answer += (j - i);
				curr[a[i]]--;
				i++;
			}
		}
		answer += 1L * (n - i) * (n - i + 1) / 2;
		System.out.println(answer);
	}
}