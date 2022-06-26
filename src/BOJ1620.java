import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1620 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> poketmonSet = new HashMap<>();
		String poketmonList[] = new String[N];
		for (int i = 0; i < N; i++) {
			String poketmon = br.readLine();
			poketmonSet.put(poketmon, i + 1);
			poketmonList[i] = poketmon;
		}
		for (int i = 0; i < M; i++) {
			String question = br.readLine();
			try {
				int questionInt = Integer.parseInt(question);
				sb.append(poketmonList[questionInt - 1]);
				sb.append("\n");

			} catch (NumberFormatException e) {
				sb.append(poketmonSet.get(question));
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.close();
	}

}
