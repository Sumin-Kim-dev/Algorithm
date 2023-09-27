import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			List<Integer>[] adj = new List[n];
			for (int i = 0; i < n; i++) {
				adj[i] = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						adj[i].add(j);
					}
				}
			}
			int min = n * n;
			for (int i = 0; i < n; i++) {
				int cc = 0;
				boolean[] isVisited = new boolean[n];
				isVisited[i] = true;
				int start = 0;
				int size = 0;
				int[] queue = new int[n];
				queue[start + size++] = i;
				int d = 0;
				while (size > 0) {
					int qSize = size;
					cc += d * qSize;
					for (int j = 0; j < qSize; j++) {
						int curr = queue[start++];
						size--;
						for (int next : adj[curr]) {
							if (isVisited[next]) continue;
							isVisited[next] = true;
							queue[start + size++] = next;
						}
					}
					d++;
				}
				min = Math.min(min, cc);
			}
			sb.append('#').append(t).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

}