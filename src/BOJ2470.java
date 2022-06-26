

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] value = new int[n];
		for (int i = 0; i < n; i++)
			value[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(value);
		int[] neutral = neutral(value, n);
		bw.write(neutral[0] + " " + neutral[1]);
		bw.close();
	}

	static int[] neutral(int[] value, int n) {
		int first = 0, second = 1;
		// �ʱ� �� ����� Ư������ ���� ����
		int valueSum = value[0] + value[1];
		if (valueSum < 0)
			valueSum = -valueSum;
		// i�� ���� ����� Ư������ ���� ������ �ּҰ� �ǵ��� �Ѵ�
		for (int i = 0; i < n - 1; i++) {
			// �̺�Ž��
			int j = i + 1;
			int end = n;
			while (j + 1 < end) {
				int mid = (j + end) / 2;
				// ��Ȯ�� 0�� �Ǹ� ����
				if (value[i] + value[mid] == 0)
					return new int[] { value[i], value[mid] };
				// 0���� ���� �ִ��� ���� ã��
				if (value[i] + value[mid] > 0)
					end = mid;
				if (value[i] + value[mid] < 0)
					j = mid;
			}
			int vCan = value[i] + value[j];
			if (vCan < 0)
				vCan = -vCan;
			if (j != n - 1 && value[i] + value[j + 1] < vCan) {
				vCan = value[i] + value[j + 1];
				j = j + 1;
			}
			if (vCan < valueSum) {
				first = i;
				second = j;
				valueSum = vCan;
			}
		}
		return new int[] { value[first], value[second] };
	}
}
