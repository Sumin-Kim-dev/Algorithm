package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2477 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine());
		int length[] = new int[6];
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			length[i] = Integer.parseInt(st.nextToken());
		}
		int index = maxIndex(length);
		index = length[(index + 5) % 6] > length[(index + 1) % 6] ? (index + 5) % 6 : index;
		int area = length[index] * length[(index + 1) % 6] - length[(index + 3) % 6] * length[(index + 4) % 6];
		bw.write(area * K + "");
		bw.close();
	}

	static int maxIndex(int arr[]) {
		int maxIndex = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > arr[maxIndex])
				maxIndex = i;
		}
		return maxIndex;
	}
}
