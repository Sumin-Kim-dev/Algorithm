

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj1316 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int count = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			if (isGroup(s))
				count++;
		}
		bw.write("" + count);
		bw.flush();
		bw.close();
	}

	static boolean isGroup(String s) {
		if (s.isBlank())
			return false;

		// ������ �ߺ� ���� ����
		String delRepeat = "" + s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == delRepeat.charAt(delRepeat.length() - 1))
				continue;
			delRepeat += s.charAt(i);
		}
		// �ߺ� ���� ������ �׷칮�� �ƴ�
		for (int i = 0; i < delRepeat.length(); i++) {
			char delRepeat_i = delRepeat.charAt(i);
			for (int j = 0; j < i; j++) {
				if (delRepeat_i == delRepeat.charAt(j))
					return false;
			}
		}
		return true;
	}
}
