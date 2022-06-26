import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10757 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String a, b;
		a = st.nextToken();
		b = st.nextToken();
		int A[] = stringToArray(a);
		int B[] = stringToArray(b);
		System.out.println(arrayToString(add(A, B)));
	}

	static int[] stringToArray(String a) {
		int[] a_array = new int[10000];
		for (int i = 0; i < a.length(); i++) {
			a_array[i] = a.charAt(a.length() - i - 1) - '0';
		}
		return a_array;
	}

	static String arrayToString(int[] a) {
		String str = "";
		int i = 10000;
		while (a[i] == 0)
			i--;
		for (int j = i; j >= 0; j--) {
			str += a[j];
		}
		return str;
	}

	static int[] add(int[] a, int[] b) {
		int[] aPlusb = new int[10001];
		int[] carry = new int[10001];
		for (int i = 0; i < 10000; i++) {
			aPlusb[i] = (a[i] + b[i] + carry[i]) % 10;
			carry[i + 1] = (a[i] + b[i] + carry[i]) / 10;
		}
		aPlusb[10000] = carry[10000];
		return aPlusb;
	}
}
