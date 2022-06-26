

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj5430 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		String comb, xString;
		int n;
		ACarray x;
		for (int i = 0; i < T; i++) {
			comb = br.readLine();
			n = Integer.parseInt(br.readLine());
			xString = br.readLine();
			comb = comb.replaceAll("RR", "");
			x = new ACarray(n, xString);
			out: for (int j = 0; j < comb.length(); j++) {
				if (comb.charAt(j) == 'R')
					x.R();
				if (comb.charAt(j) == 'D') {
					x.D();
					if (!x.sb.isEmpty()) {
						break out;
					}
				}
			}
			x.print();
			bw.write(x.sb.toString());
		}
		bw.close();
	}

}

class ACarray {
	int x[], start, end, size, order;
	StringBuilder sb;

	ACarray(int n, String xString) {
		x = new int[n];
		int j = 0;
		for (int i = 1; i < xString.length() - 1; i++) {
			if (xString.charAt(i) == ',') {
				j++;
				continue;
			}
			x[j] = x[j] * 10 + xString.charAt(i) - '0';
		}
		start = end = 0;
		size = n;
		order = 1;
		sb = new StringBuilder();
	}

	public void R() {
		if (x.length == 0)
			return;
		int temp = (start - order + x.length) % x.length;
		start = (end - order + x.length) % x.length;
		end = temp;
		order *= -1;
	}

	public void D() {
		if (size == 0)
			sb.append("error\n");
		else {
			start = (start + order + x.length) % x.length;
			size--;
		}
	}

	public void print() {
		if (!sb.isEmpty())
			return;
		sb.append('[');
		int index = start;
		for (int i = 0; i < size; i++) {
			sb.append(x[index]);
			if (i != size - 1)
				sb.append(',');
			index = (index + order + x.length) % x.length;
		}
		sb.append("]\n");
	}
}
