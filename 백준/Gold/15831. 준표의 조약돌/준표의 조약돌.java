import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		char[] road = br.readLine().toCharArray();
		
		int start = 0;
		int end = 0;
		int max = 0;
		int cntB = 0;
		while (true) {
			int length = end - start;
			if (cntB <= b) {
				if (length - cntB >= w) max = Math.max(max, end - start);
				if (end == n) break;
				if (road[end++] == 'B') cntB++;
			} else {
				if (road[start++] == 'B') cntB--;
			}
		}
		System.out.println(max);
	}

}