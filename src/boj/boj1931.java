package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj1931 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Meeting meeting[] = new Meeting[N];
		int start, end;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			meeting[i] = new Meeting(start, end);
		}
		Arrays.sort(meeting);
		int i = 0;
		Stack<Meeting> able = new Stack<>();
		while (i < N) {
			if (able.isEmpty() || meeting[i].start >= able.peek().end) {
				able.add(meeting[i]);
			}
			i++;
		}
		bw.write(able.size() + "");
		bw.close();
	}
}

class Meeting implements Comparable<Meeting> {
	int start, end;

	Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int compareTo(Meeting m) {
		if (this.end != m.end)
			return this.end - m.end;
		return this.start - m.start;
	}
}