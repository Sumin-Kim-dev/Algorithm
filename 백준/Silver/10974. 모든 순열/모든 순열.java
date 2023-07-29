import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] nums;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		nums = new int[n];
		visited = new boolean[n];
		perm(0);
		System.out.println(sb);
		sc.close();
	}

	private static void perm(int depth) {
		if (depth == n) {
			for (int i = 0; i < n; i++) {
				sb.append(nums[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			nums[depth] = i + 1;
			perm(depth + 1);
			visited[i] = false;
		}
	}
	
}
