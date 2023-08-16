import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int s;
	static int[] pictures;
	static int[] price;
	
	static int min;
	static int max;
	
	public static final int MAX = 20000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		pictures = new int[MAX + 1];
		price = new int[MAX + 1];
		min = MAX;
		max = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (h < min) min = h;
			if (h > max) max = h;
			if (pictures[h] < c) pictures[h] = c;
		}
		
		price[max] = pictures[max];
		for (int h = max; h >= min; h--) {
			price[h] = Math.max(price[h + 1], price[h + s] + pictures[h]);
		}
		System.out.println(price[min]);
	}
}