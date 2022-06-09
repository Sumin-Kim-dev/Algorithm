package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj10844 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int step[] = new int[10];
		int temp[];
		for (int i = 1; i <= 9; i++) {
			step[i] = 1;
		}
		for (int i = 1; i < n; i++) {
			temp = step.clone();
			step[0] = temp[1];
			step[9] = temp[8];
			for (int j = 1; j < 9; j++)
				step[j] = (temp[j - 1] + temp[j + 1]) % 1000000000;
		}
		int sum = 0;
		for (int i = 0; i <= 9; i++) {
			sum += step[i];
			sum %= 1000000000;
		}
		bw.write(sum + "");
		bw.close();
	}

}
