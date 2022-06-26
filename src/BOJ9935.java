

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
			// ���� ���ڿ��� ���۵ǰų� �̾��� : ���ÿ� �߰�
			if (curr == bomb.charAt(0) || curr == bomb.charAt(currBomb + 1)) {
				int currIndex = 0;
				if (curr == bomb.charAt(currBomb + 1))
					currIndex = currBomb + 1;
				stack.add(currIndex);
				temp.append(curr);
				// ���� ���ڿ��� �ϼ��� ��� ����
				if (currIndex == bombLength - 1) {
					for (int j = 0; j < bombLength; j++)
						stack.pop();
					temp.delete(temp.length() - bombLength, temp.length());
				}
				continue;
			}
			// ���� ���ڿ��� �̾����� ������ ���� ���� ���� ���ڿ��� �߰�
			sb.append(temp).append(curr);
			stack.clear();
			temp = new StringBuilder();
		}
		// ������ Ž���ϰ� ���ÿ� ���Ұ� ���Ҵٸ�
		sb.append(temp);

		String ans = sb.toString();
		// �����ִ� ���ڰ� ���ٸ�
		if (ans.isEmpty())
			ans = "FRULA";
		bw.write(ans);
		bw.close();
	}
}
