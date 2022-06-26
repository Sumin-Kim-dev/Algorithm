

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj17478 {
	public static final String str0 = "��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.";
	public static final String str1 = "\"����Լ��� ������?\"";
	public static final String str2 = "\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.";
	public static final String str3 = "���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.";
	public static final String str4 = "���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"";
	public static final String str5 = "\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"";
	public static final String str6 = "��� �亯�Ͽ���.";
	public static final String string[] = new String[] { str1, str2, str3, str4, str5, str6 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(str0);
		System.out.println(recursion(N));
	}

	static String recursion(int N) {
		String string_recurse = "";
		String string_recurse_split[];

		if (N == 0) {
			string_recurse = string[0] + "\n" + string[4] + "\n" + string[5] + "\n";
			return string_recurse;
		}

		string_recurse += string[0] + "\n" + string[1] + "\n" + string[2] + "\n" + string[3] + "\n";

		string_recurse_split = recursion(N - 1).split("\n");
		for (int i = 0; i < string_recurse_split.length; i++) {
			string_recurse_split[i] = "____" + string_recurse_split[i];
			string_recurse += string_recurse_split[i] + "\n";
		}

		string_recurse += string[5] + "\n";

		return string_recurse;
	}
}