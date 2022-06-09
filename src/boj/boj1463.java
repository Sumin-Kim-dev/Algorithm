package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj1463 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int min[] = new int[N]; // 0으로 초기화 됨
		for (int i = 1; i <= N; i++) {
			if (i % 3 == 0)
				min[i - 1] = min[i / 3 - 1] + 1;
			if (i % 2 == 0 && (min[i - 1] == 0 || min[i - 1] > min[i / 2 - 1]))
				min[i - 1] = min[i / 2 - 1] + 1;
			if (i > 1 && (min[i - 1] == 0 || min[i - 1] > min[i - 2]))
				min[i - 1] = min[i - 2] + 1;
		}
		bw.write(min[N - 1] + "");
		bw.close();
	}

}
