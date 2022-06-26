import java.util.Scanner;

public class BOJ8958 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		in.nextLine();
		int score;
		String answer[];
		for (int i = 0; i < N; i++) {
			score = 0;
			answer = in.nextLine().split("X");
			for (int j = 0; j < answer.length; j++) {
				score += (answer[j].length()) * (answer[j].length() + 1) / 2;
			}
			System.out.println(score);
		}
		in.close();
	}
}
