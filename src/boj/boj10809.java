package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ10809 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = bf.readLine();
		int alphabet[] = new int[26];
		Arrays.fill(alphabet, -1);
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (alphabet[index] == -1)
				alphabet[index] = i;
		}
		for (int i = 0; i < 26; i++) {
			bw.write("" + alphabet[i] + " ");
		}
		bw.flush();
		bw.close();
	}

}
