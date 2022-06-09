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
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int memory, price;
		for (int i = 0; i < n; i++) {
			memory = Integer.parseInt(st1.nextToken());
			price = Integer.parseInt(st2.nextToken());
			apps[i] = new App(memory, price);
		}
		bw.write(price(m) + "");
		bw.close();
	}

	// 특정 비용 이내로 메모리 확보 최대로 되게 하는 배낭문제
	// 비용에 따른 메모리 확보량은 증가함수이므로 메모리 m 이상을 확보하는 최소의 비용 찾기 가능
	static int price(int m) {
		int memory[] = new int[10001];
		int mi, pi;
		for (int i = 0; i < n; i++) {
			mi = apps[i].memory;
			pi = apps[i].price;
			for (int j = 10000; j >= pi; j--)
				if (memory[j] < memory[j - pi] + mi)
					memory[j] = memory[j - pi] + mi;
		}
		int price = 0;
		while (memory[price] < m)
			price++;
		return price;
	}
}

class App {
	int memory, price;

	App(int memory, int price) {
		this.memory = memory;
		this.price = price;
	}
}
