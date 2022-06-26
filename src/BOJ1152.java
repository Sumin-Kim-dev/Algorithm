

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1152 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String s = bf.readLine();
		String words[] = s.trim().split(" ");
		int length;
		if (s.isBlank())
			length = 0;
		else
			length = words.length;
		System.out.println(length);
	}
}
