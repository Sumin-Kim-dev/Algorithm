import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] diff;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		diff = new int[n][2];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int d = Integer.parseInt(st1.nextToken()) - Integer.parseInt(st2.nextToken());
			if (d == 0) continue;
			diff[i][0] = Math.abs(d);
			diff[i][1] = d / diff[i][0];
		}
		divide(0, n);
		System.out.println(count);
	}

	private static void divide(int start, int end) {
		// diff가 0이 아닌 첫 위치 s 찾기
		int s = start;
		while (s < end && diff[s][1] == 0) s++;
		// 모든 위치의 diff가 0이면 종료
		if (s == end) return;
		// 같은 부호로 이어지는 구간의 끝 e 찾기
		int e = s;
		while (e < end && diff[s][1] == diff[e][1]) e++;
		
		// 구간 [s, e)의 최소값만큼 탭 변화
		int min = 81;
		for (int i = s; i < e; i++) {
			min = Math.min(min, diff[i][0]);
		}
		for (int i = s; i < e; i++) {
			diff[i][0] -= min;
			if (diff[i][0] == 0) diff[i][1] = 0;
		}
		count += min;
		
		divide(s, e);
		divide(e, end);
	}

}