

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2839 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		System.out.println(bag(N));
	}

	static int bag(int N) {
		if (N < 3 || N == 4 || N == 7)
			return -1;
		else if (N % 5 == 0)
			return N / 5;
		else if (N % 5 == 1 || N % 5 == 3)
			return N / 5 + 1;
		else
			return N / 5 + 2;
	}
}
