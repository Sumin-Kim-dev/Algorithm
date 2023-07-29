import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int w;
	static int l;
	static int[][] a;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		a = new int[n][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i][0] = Integer.parseInt(st.nextToken());
		}

		Queue<Integer> bridge = new LinkedList<>();
		int weight = 0;
		int t = 0;
		for (int i = 0; i < n; i++) {
			while (!bridge.isEmpty()) {
				int time = a[bridge.peek()][1];
				if (time + w <= t) {
					weight -= a[bridge.poll()][0];
				}
				else break;
			}
			if (weight + a[i][0] <= l) {
				weight += a[i][0];
				bridge.offer(i);
				a[i][1] = t + 1;
				t = a[i][1];
			} else {
				int curr = 0;
				while (weight + a[i][0] > l) {
					curr = bridge.poll();
					weight -= a[curr][0];
				}
				bridge.offer(i);
				weight += a[i][0];
				a[i][1] = a[curr][1] + w;
				t = a[i][1];
			}
		}
		System.out.println(a[n - 1][1] + w);
	}

}
