

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj5622 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += time(s.charAt(i));
		}
		System.out.println(sum);
	}

	static int time(char c) {
		int time = switch (c) {
		case 'A', 'B', 'C' -> 3;
		case 'D', 'E', 'F' -> 4;
		case 'G', 'H', 'I' -> 5;
		case 'J', 'K', 'L' -> 6;
		case 'M', 'N', 'O' -> 7;
		case 'P', 'Q', 'R', 'S' -> 8;
		case 'T', 'U', 'V' -> 9;
		case 'W', 'X', 'Y', 'Z' -> 10;
		default -> 0;
		};
		return time;
	}
}
