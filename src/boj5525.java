

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj5525 {

	static int n, m;
	static String s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		s = br.readLine();
		bw.write(count() + "");
		bw.close();
	}

	static int count() {
		int count = 0;
		int length[] = new int[m];
		for (int i = m - 1; i >= 0; i--) {
			if (s.charAt(i) == 'I') {
				if (i + 2 >= m || s.charAt(i + 1) != 'O' || s.charAt(i + 2) != 'I')
					length[i] = 1;
				else
					length[i] = length[i + 2] + 1;
			}
		}
		for (int i = 0; i < m; i++)
			if (length[i] > n)
				count++;
		return count;
	}
}
