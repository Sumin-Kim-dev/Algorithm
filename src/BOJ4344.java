import java.util.Scanner;

public class BOJ4344 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		in.nextLine();
		double score[];
		String s[];
		double average, ratio;
		for (int i = 0; i < N; i++) {
			s = in.nextLine().split(" ");
			score = new double[s.length];
			average = ratio = 0;
			for (int j = 0; j < s.length; j++) {
				score[j] = Integer.parseInt(s[j]);
				if (j != 0)
					average += score[j];
			}
			average /= score[0];
			for (int j = 1; j < s.length; j++) {
				if (score[j] > average)
					ratio += 100;
			}
			ratio /= score[0];
			System.out.printf("%.3f%%\n", ratio);
		}
		in.close();
	}
}
