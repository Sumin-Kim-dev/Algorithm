

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2941 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String word = br.readLine();
		int length = word.length();
		int count = 0;
		for (int i = 0; i < length; i++) {
			char alphabet = word.charAt(i);
			if (i == length - 1) {
				count++;
				continue;
			}
			char nextAlphabet = word.charAt(i + 1);
			if (alphabet == 'c') {
				if (nextAlphabet == '-' || nextAlphabet == '=')
					i++;
			} else if (alphabet == 'd') {
				if (nextAlphabet == '-')
					i++;
				if (nextAlphabet == 'z') {
					if (i < length - 2 && word.charAt(i + 2) == '=')
						i += 2;
				}
			} else if ((alphabet == 'l' || alphabet == 'n') && nextAlphabet == 'j')
				i++;
			else if ((alphabet == 's' || alphabet == 'z') && nextAlphabet == '=')
				i++;
			count++;
		}
		bw.write("" + count);
		bw.flush();
		bw.close();
	}

}
