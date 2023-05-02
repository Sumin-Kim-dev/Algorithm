import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashSet<String> hear = new HashSet<>();
		for (int i = 0; i < N; i++) {
			hear.add(br.readLine());
		}

		int count = 0;
		ArrayList<String> hearSee = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			String see = br.readLine();
			if (hear.contains(see)) {
				hearSee.add(see);
				count++;
			}
		}
		Collections.sort(hearSee);
		sb.append(count);
		sb.append("\n");

		for (int i = 0; i < hearSee.size(); i++) {
			sb.append(hearSee.get(i));
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

}
