import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2606 {

	static ArrayList<Integer> connected[];
	static boolean check[];
	static int virus = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int cnt = Integer.parseInt(br.readLine());
		connected = new ArrayList[n];
		check = new boolean[n];
		for (int i = 0; i < n; i++)
			connected[i] = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int com1 = Integer.parseInt(st.nextToken());
			int com2 = Integer.parseInt(st.nextToken());
			connected[com1 - 1].add(com2 - 1);
			connected[com2 - 1].add(com1 - 1);
		}
		virus(0);
		bw.write(virus + "");
		bw.close();
	}

	static void virus(int num) {
		check[num] = true;
		for (int i = 0; i < connected[num].size(); i++) {
			int next = connected[num].get(i);
			if (!check[next]) {
				check[next] = true;
				virus(next);
				virus++;
			}
		}
	}
}
