package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj7579 {

	static App apps[];
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		apps = new App[n];
		int memorySum = 0;
		int priceSum = 0;
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int memory, price;
		for (int i = 0; i < n; i++) {
			memory = Integer.parseInt(st1.nextToken());
			memorySum += memory;
			price = Integer.parseInt(st2.nextToken());
			priceSum += price;
			apps[i] = new App(memory, price);
		}
		bw.write(priceSum - keep(memorySum - m) + "");
		bw.close();
	}

	static int keep(int memory) {
		int keep[] = new int[memory + 1];
		int m, p;
		for (int i = 0; i < n; i++) {
			m = apps[i].memory;
			p = apps[i].price;
			for (int j = memory; j >= m; j--) {
				if (keep[j - m] + p > keep[j])
					keep[j] = keep[j - m] + p;
			}
		}
		return keep[memory];
	}
}

class App {
	int memory, price;

	App(int memory, int price) {
		this.memory = memory;
		this.price = price;
	}
}
