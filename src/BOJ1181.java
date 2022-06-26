import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ1181 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String words[] = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		sort(words);
		print(words);
	}

	static void sort(String words[]) {
		for (int i = 0; i < words.length - 1; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if (words[i].length() > words[j].length()
						|| words[i].length() == words[j].length() && words[i].compareTo(words[j]) > 0) {
					String temp = new String(words[i]);
					words[i] = new String(words[j]);
					words[j] = temp;
				}
			}
		}
	}

	static void print(String words[]) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(words[0] + "\n");
		for (int i = 1; i < words.length; i++) {
			if (words[i].equals(words[i - 1]))
				continue;
			bw.write(words[i] + "\n");
		}
		bw.close();
	}
}
