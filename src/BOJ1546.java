import java.util.Scanner;

public class BOJ1546 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int score = 0;
		int sum = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			score = in.nextInt();
			sum += score;
			if (score >= max)
				max = score;
		}
		System.out.println(100.0 * sum / max / N);
		in.close();
	}

}
