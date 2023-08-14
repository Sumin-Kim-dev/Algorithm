import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		int answer = 0;
		for (int i = 0; i < n; i++) {
			answer |= (r & (1 << i)) << (i + 1);
			answer |= (c & (1 << i)) << i;
		}
		System.out.println(answer);
	}

}