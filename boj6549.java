package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj6549 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			int h[] = new int[n];
			for (int i = 0; i < n; i++)
				h[i] = Integer.parseInt(st.nextToken());
			sb.append(largest(h, 0, n)).append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}

	static long largest(int h[], int start, int end) {
		if (start + 1 == end)
			return h[start];
		int midIndex = (start + end) / 2;
		long height = h[midIndex - 1] > h[midIndex] ? h[midIndex] : h[midIndex - 1];
		long largest = height * 2;
		int i = midIndex - 1, j = midIndex;
		while (j - i + 1 < end - start) {
			if (j + 1 < end && (i == start || h[j + 1] >= height)) {
				if (h[j + 1] < height)
					height = h[j + 1];
				j++;
			} else if (i > start && (j + 1 == end || h[i - 1] >= height)) {
				if (h[i - 1] < height)
					height = h[i - 1];
				i--;
			} else if (h[i - 1] >= h[j + 1]) {
				height = h[i - 1];
				i--;
			} else {
				height = h[j + 1];
				j++;
			}
			if (largest < height * (j - i + 1))
				largest = height * (j - i + 1);
		}
		long largestCandidate1 = largest(h, start, midIndex);
		long largestCandidate2 = largest(h, midIndex, end);
		if (largestCandidate1 > largest)
			largest = largestCandidate1;
		if (largestCandidate2 > largest)
			largest = largestCandidate2;
		return largest;
	}
}
