import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Integer[] multitabs = new Integer[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			multitabs[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(multitabs, Comparator.reverseOrder());
		int answer = multitabs[0];
		if (n >= 2) answer += (multitabs[(n + 2) / 3] - 1);
		if (n >= 3) answer += (multitabs[(n + 2) / 3 + (n + 1) / 3] - 1);
		if (n >= 3 && multitabs[(n + 2) / 3 + (n + 1) / 3] > 1) answer--;
		System.out.println(answer);
	}

}