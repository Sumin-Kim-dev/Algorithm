import java.io.*;

public class Main {

	static long N;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		long k = Integer.parseInt(br.readLine());
		bw.write(answer(k) + "");
		bw.close();
	}

	static long answer(long k) {
		long i = 0;
		while (i * i < k)
			i++;
		long max = i * i + 1;
		long min = 1;
		long mid;
		while (min + 1 < max) {
			mid = (max + min) / 2;
			if (underM(mid) >= k)
				max = mid;
			else
				min = mid;
		}
		if (underM(min) != k)
			min++;
		return min;
	}

	static long underM(long m) {
		long underM = 0;
		for (int i = 1; i <= N; i++) {
			underM += m / i <= N ? m / i : N;
		}
		return underM;
	}
}
