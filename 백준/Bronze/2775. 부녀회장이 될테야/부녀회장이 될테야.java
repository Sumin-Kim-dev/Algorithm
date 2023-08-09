import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int k, n;
		for (int i = 0; i < T; i++) {
			k = Integer.parseInt(br.readLine());
			n = Integer.parseInt(br.readLine());
            int[][] answer = new int[k + 1][n + 1];
			System.out.println(people(k, n, answer));
		}
	}

	static int people(int k, int n, int[][] answer) {
        if (answer[k][n] > 0)
            return answer[k][n];
		if (k == 0)
			return answer[0][n] = n;
        if (n == 0)
            return 0;
		return answer[k][n] = people(k - 1, n, answer) + people(k, n - 1, answer);
	}
}
