import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		if (n == k) {
			System.out.println(0);
			return;
		}
		
		int[] visited = new int[100001];
		Queue<Integer> queue = new ArrayDeque<>();
		visited[n] = 1;
		queue.offer(n);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (2 * curr <= 100000 && visited[2 * curr] == 0) {
				if (2 * curr == k) {
					System.out.println(visited[curr]);
					return;
				}
				queue.offer(2 * curr);
				visited[2 * curr] = visited[curr] + 1;
			}
			if (curr - 1 >= 0 && visited[curr - 1] == 0) {
				if (curr - 1 == k) {
					System.out.println(visited[curr]);
					return;
				}
				queue.offer(curr - 1);
				visited[curr - 1] = visited[curr] + 1;
			}
			if (curr + 1 <= 100000 && visited[curr + 1] == 0) {
				if (curr + 1 == k) {
					System.out.println(visited[curr]);
					return;
				}
				queue.offer(curr + 1);
				visited[curr + 1] = visited[curr] + 1;
			}
		}
	}

}