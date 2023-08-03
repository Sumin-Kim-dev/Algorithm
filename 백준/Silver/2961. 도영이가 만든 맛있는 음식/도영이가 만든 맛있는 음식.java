import java.util.Scanner;

public class Main {
	
	static int n;
	static int[][] taste;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		taste = new int[n][2];
		for (int i = 0; i < n; i++) {
			taste[i][0] = sc.nextInt();
			taste[i][1] = sc.nextInt();
		}
		subset(0, 1, 0, 0);
		System.out.println(min);
	}
	
	private static void subset(int cnt, int sour, int bitter, int num) {
		if (cnt == n) {
			if (num > 0) {
				min = Math.min(min, Math.abs(sour - bitter));
			}
			return;
		}
		subset(cnt + 1, sour * taste[cnt][0], bitter + taste[cnt][1], num + 1);
		subset(cnt + 1, sour, bitter, num);
	}
}