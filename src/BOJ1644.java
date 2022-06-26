import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ1644 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		bw.write(primeSum(n) + "");
		bw.close();
	}

	static int primeSum(int n) {
		if (n == 1)
			return 0;
		ArrayList<Integer> prime = prime(n);
		int count = 0, size = prime.size();
		int i = 0, j = 1, sum = prime.get(0);
		while (i < j) {
			if (sum == n)
				count++;
			if (sum <= n) {
				if (j == size)
					break;
				sum += prime.get(j);
				j++;
			}
			if (sum > n) {
				sum -= prime.get(i);
				i++;
			}
		}
		return count;
	}

	static ArrayList<Integer> prime(int n) {
		ArrayList<Integer> prime = new ArrayList<>();
		boolean[] isNotPrime = new boolean[n + 1];
		for (int i = 2; i * i <= n; i++) {
			for (int j = 2 * i; j <= n; j += i)
				isNotPrime[j] = true;
		}
		for (int i = 2; i <= n; i++)
			if (!isNotPrime[i])
				prime.add(i);
		return prime;
	}
}
