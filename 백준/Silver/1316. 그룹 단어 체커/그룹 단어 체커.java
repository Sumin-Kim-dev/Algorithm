import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

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

		// 연속한 중복 문자 제거
		String delRepeat = "" + s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == delRepeat.charAt(delRepeat.length() - 1))
				continue;
			delRepeat += s.charAt(i);
		}
		// 중복 문자 있으면 그룹문자 아님
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