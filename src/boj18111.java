

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj18111 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		// ó�� ���� ���� �Է�
		int ground[] = new int[N * M];
		int index = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ground[index] = Integer.parseInt(st.nextToken());
				index++;
			}
		}
		Arrays.sort(ground);

		// ������ ���� �߸���
		ArrayList<Integer> heights = new ArrayList<>();
		for (int h = ground[0]; h <= ground[N * M - 1]; h++) {
			if (isAble(h, ground, B))
				heights.add(h);
		}

		// ������ ���̸� �ð�-���� ���̷� ����
		Collections.sort(heights, new Comparator<Integer>() {
			@Override
			public int compare(Integer h1, Integer h2) {
				// �ð��� ��������
				if (time(h1, ground) != time(h2, ground))
					return time(h1, ground) - time(h2, ground);
				// ���� ���̴� ��������
				return h2 - h1;
			}
		});

		// ���
		int height = heights.get(0);
		int time = time(height, ground);
		bw.write(time + " " + height);
		bw.close();
	}

	static int time(int h, int ground[]) {
		int time = 0;
		for (int i = 0; i < ground.length; i++) {
			if (ground[i] >= h)
				time += 2 * (ground[i] - h);
			else
				time += h - ground[i];
		}
		return time;
	}

	static boolean isAble(int h, int ground[], int B) {
		int blockPlus = 0, blockMinus = 0;
		for (int i = 0; i < ground.length; i++) {
			if (ground[i] >= h)
				blockPlus += ground[i] - h;
			else
				blockMinus += h - ground[i];
		}
		return B + blockPlus - blockMinus >= 0;
	}
}
