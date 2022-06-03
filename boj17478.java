package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj17478 {
	public static final String str0 = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	public static final String str1 = "\"재귀함수가 뭔가요?\"";
	public static final String str2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	public static final String str3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	public static final String str4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	public static final String str5 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	public static final String str6 = "라고 답변하였지.";
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