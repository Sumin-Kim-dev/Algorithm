

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Stuff stuffs[] = new Stuff[N];
		int weight, value;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			weight = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			stuffs[i] = new Stuff(weight, value);
		}
		Arrays.sort(stuffs);

		int w, v, temp[];
		int bag[] = new int[K + 1];
		for (int i = 1; i <= N; i++) {
			w = stuffs[i - 1].weight;
			v = stuffs[i - 1].value;
			temp = bag.clone();
			for (int j = w; j <= K; j++) {
				if (temp[j - w] + v > bag[j])
					bag[j] = temp[j - w] + v;
			}
		}
		bw.write(bag[K] + "");
		bw.close();
	}

}

class Stuff implements Comparable<Stuff> {
	int weight, value;

	Stuff(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	public int compareTo(Stuff s) {
		return this.weight - s.weight;
	}
}