import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n == 1) {
			System.out.println(0);
			return;
		}
		int[] visited = new int[n + 1];
		Queue<Integer> queue = new LinkedList<>();
		visited[n] = 1;
		queue.offer(n);
		int answer = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (curr % 3 == 0 && visited[curr / 3] == 0) {
				if (curr == 3) {
					answer = visited[curr];
					break;
				}
				visited[curr / 3] = visited[curr] + 1;
				queue.offer(curr / 3);
			}
			if (curr % 2 == 0 && visited[curr / 2] == 0) {
				if (curr == 2) {
					answer = visited[curr];
					break;
				}
				visited[curr / 2] = visited[curr] + 1;
				queue.offer(curr / 2);
			}
			if (curr > 1 && visited[curr - 1] == 0) {
				visited[curr - 1] = visited[curr] + 1;
				queue.offer(curr - 1);
			}
		}
		System.out.println(answer);
	}

}