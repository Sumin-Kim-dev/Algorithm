import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	static Map<Long, Integer> map = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		map.put(1L, 0);
		System.out.println(dp(n));
	}

	private static int dp(long n) {
		if (map.containsKey(n)) return map.get(n);
		int answer;
		if (n % 6 == 0) answer = Math.min(dp(n / 2) + 1, dp(n / 3) + 1);
		else if (n % 6 == 2) answer = Math.min(dp(n / 2) + 1, dp(n - 1) + 1);
		else if (n % 6 == 3) answer = Math.min(dp(n / 3) + 1, dp(n - 1) + 1);
		else if (n % 6 == 4) answer = Math.min(dp(n / 2) + 1, dp(n - 1) + 1);
		else answer = dp(n - 1) + 1;
		map.put(n, answer);
		return answer;
	}
}