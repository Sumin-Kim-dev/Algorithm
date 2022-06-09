package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj18870 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		// 좌표 입력 받기 & 좌표 집합 만들기
		int points[] = new int[N];
		HashSet<Integer> pointSet = new HashSet<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			points[i] = x;
			pointSet.add(x);
		}

		// 좌표 집합을 array로 변환 : toArray 사용해도 되지만 속도가 느림(Integer 떄문)
		int pointList[] = new int[pointSet.size()];
		int i = 0;
		for (int point : pointSet) {
			pointList[i++] = point;
		}

		// 좌표 압축 및 출력
		Arrays.sort(pointList);
		StringBuilder sb = new StringBuilder();
		for (int point : points) {
			int xModified = xModified(point, pointList);
			sb.append(xModified + " ");
		}
		bw.write(sb.toString());
		bw.close();
	}

	// 이진탐색
	static int xModified(int point, int pointList[]) {
		int xModified = 0, end = pointList.length;
		while (xModified + 1 < end) {
			int mid = (xModified + end) / 2;
			if (point >= pointList[mid])
				xModified = mid;
			else
				end = mid;
		}
		return xModified;
	}
}
