import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int cost[][] = new int[n][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int total_cost[] = new int[3], temp[];
		int cost1, cost2;
		for (int i = 1; i <= n; i++) {
			temp = total_cost.clone();
			for (int j = 0; j < 3; j++) {
				cost1 = temp[(j + 1) % 3] + cost[i - 1][j];
				cost2 = temp[(j + 2) % 3] + cost[i - 1][j];
				total_cost[j] = cost1 > cost2 ? cost2 : cost1;
			}
		}
		int min = total_cost[0];
		for (int i = 1; i < 3; i++) {
			if (total_cost[i] < min)
				min = total_cost[i];
		}
		bw.write(min + "");
		bw.close();
	}

}
