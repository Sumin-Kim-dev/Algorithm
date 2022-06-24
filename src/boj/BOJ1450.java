package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ1450 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] value = new int[n];
		for (int i = 0; i < n; i++)
			value[i] = Integer.parseInt(st.nextToken());
		bw.write(ans(value, c) + "");
		bw.close();
	}

	static long ans(int[] value, long c) {
		long ans = 0;
		int[] value1 = Arrays.copyOfRange(value, 0, value.length / 2);
		int[] value2 = Arrays.copyOfRange(value, value.length / 2, value.length);
		ArrayList<long[]> count1 = count(value1);
		ArrayList<long[]> count2 = count(value2);
		for (int i = 1; i < count2.size(); i++) {
			long sum = count2.get(i - 1)[1];
			count2.set(i, new long[] { count2.get(i)[0], count2.get(i)[1] + sum });
		}
		for (int i = 0; i < count1.size(); i++) {
			long max2 = c - count1.get(i)[0];
			if (max2 < 0)
				break;
			int j = 0, end = count2.size();
			while (j + 1 < end) {
				int mid = (j + end) / 2;
				if (count2.get(mid)[0] <= max2)
					j = mid;
				if (count2.get(mid)[0] > max2)
					end = mid;
			}
			ans += count1.get(i)[1] * count2.get(j)[1];
		}
		return ans;
	}

	static int[] seq;

	static ArrayList<long[]> count(int[] value) {
		TreeMap<Long, Long> map = new TreeMap<>();
		map.put(0l, 1l);
		seq = new int[value.length];
		backtracking(value, map, 0);
		return mapToList(map);
	}

	static long sum = 0;

	static void backtracking(int[] value, TreeMap<Long, Long> map, int depth) {
		int n = value.length;
		if (depth == n)
			return;
		for (int i = 0; i < n; i++) {
			if (depth == 0 || seq[depth - 1] < i) {
				sum += value[i];
				seq[depth] = i;
				map.put(sum, map.getOrDefault(sum, 0l) + 1);
				backtracking(value, map, depth + 1);
				sum -= value[i];
				seq[depth] = -1;
			}
		}
	}

	static ArrayList<long[]> mapToList(TreeMap<Long, Long> map) {
		ArrayList<long[]> mapToList = new ArrayList<>();
		ArrayList<Long> keys = new ArrayList<Long>(map.keySet());
		ArrayList<Long> values = new ArrayList<Long>(map.values());
		for (int i = 0; i < keys.size(); i++)
			mapToList.add(new long[] { keys.get(i), values.get(i) });
		return mapToList;
	}
}
