import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		String str = br.readLine();
		String bomb = br.readLine();
		int bombLength = bomb.length();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char curr = str.charAt(i);
			int currBomb = -1;
			if (!stack.isEmpty())
				currBomb = stack.peek();
			// 폭발 문자열이 시작되거나 이어짐 : 스택에 추가
			if (curr == bomb.charAt(0) || curr == bomb.charAt(currBomb + 1)) {
				int currIndex = 0;
				if (curr == bomb.charAt(currBomb + 1))
					currIndex = currBomb + 1;
				stack.add(currIndex);
				temp.append(curr);
				// 폭발 문자열이 완성된 경우 폭파
				if (currIndex == bombLength - 1) {
					for (int j = 0; j < bombLength; j++)
						stack.pop();
					temp.delete(temp.length() - bombLength, temp.length());
				}
				continue;
			}
			// 폭발 문자열이 이어지지 않으면 스택 비우고 최종 문자열에 추가
			sb.append(temp).append(curr);
			stack.clear();
			temp = new StringBuilder();
		}
		// 끝까지 탐색하고도 스택에 원소가 남았다면
		sb.append(temp);

		String ans = sb.toString();
		// 남아있는 문자가 없다면
		if (ans.isEmpty())
			ans = "FRULA";
		bw.write(ans);
		bw.close();
	}
}
