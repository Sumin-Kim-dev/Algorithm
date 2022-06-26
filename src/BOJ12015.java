import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ12015 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		final int DEFAULT = 1000001;
		// f(i, x) : a[1] ~ a[i]의 길이 x인 증가하는 부분 수열의 끝 원소의 최솟값
		// lis(i) : a[1] ~ a[i]의 증가하는 부분 수열의 최대 길이
		int f[] = new int[n + 1], lis = 0;
		f[0] = 0;
		// ���� ū ���� �ʱ�ȭ(� ������ ���Һ��ٵ� ũ�� ���)
		for (int i = 1; i <= n; i++)
			f[i] = DEFAULT;

		int start, end, mid;
		for (int i = 1; i <= n; i++) {
			start = 0;
			end = lis;
			// f(i, x) <= f(i, y) if x <= y
			// f(i, x) = f(i-1, x) if a[i] <= f(i-1, x-1)
			// f(i, x) = min{a[i], f(i-1, x)} if f(i-1, x-1) < a[i]
			if (a[i - 1] > f[lis]) {
				f[lis + 1] = a[i - 1];
				lis++;
			} else {
				// f[x] < a[i-1]인 최대의 x 찾기
				while (start + 1 < end) {
					mid = (start + end) / 2;
					if (f[mid] >= a[i - 1])
						end = mid;
					else
						start = mid;
				}
				if (a[i - 1] < f[end])
					f[end] = a[i - 1];
			}
		}
		bw.write(lis + "");
		bw.close();
	}

}
