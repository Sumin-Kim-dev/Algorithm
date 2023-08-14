import java.util.Scanner;

public class Main {
	static int n, r, c;
	static int answer = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		recur(0, 0, (int)Math.pow(2, n));

	}
	static void recur(int x, int y, int n) {
		if (x == r && y == c) {
			System.out.println(answer);
			System.exit(0);
		}
		
		if (n == 1) {
			answer += 1;
			return;
		}
	    
		if(!(x <= r && r < x + n && y <= c && c < y + n)) {
			answer += n * n;
			return;
		}
		
		int length = n / 2;
		
		recur(x, y, length);
		recur(x, y + length, length);
		recur(x + length, y, length);
		recur(x + length, y + length, length);
	}

}