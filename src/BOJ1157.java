import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1157 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String s = bf.readLine().toUpperCase();
		int alphabet[] = new int[26];
		for (int i = 0; i < s.length(); i++) {
			alphabet[s.charAt(i) - 'A']++;
		}

		int count = 0, maxIndex = 0;
		for (int i = 0; i < 26; i++) {
			if (alphabet[i] > count) {
				maxIndex = i;
				count = alphabet[i];
			}
		}

		char max = (char) ('A' + maxIndex);
		for (int i = 0; i < 26; i++) {
			if (i == maxIndex)
				continue;
			if (alphabet[i] == alphabet[maxIndex])
				max = '?';
		}
		System.out.println(max);
	}

}
