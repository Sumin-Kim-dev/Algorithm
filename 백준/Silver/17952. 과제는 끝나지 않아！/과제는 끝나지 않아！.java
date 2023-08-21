import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림 열기
		int n = Integer.parseInt(br.readLine()); // 분기가 총 몇분인지 n 입력 받기
		int[][] work = new int[n][2]; // 일 정보 저장 : 0번째는 업무 점수, 1번째는 업무 처리하기 위해 남은 시간
		Stack<Integer> stack = new Stack<>(); // 일하는 순서를 스택에 저장(무조건 새로운 업무부터 하기 때문)
		int score = 0; // 업무 평가 점수 저장
		for (int i = 0; i < n; i++) { // n분 동안
			StringTokenizer st = new StringTokenizer(br.readLine()); // i번째 줄 입력을 공백 단위로 쪼개줌
			int info = Integer.parseInt(st.nextToken()); // 첫 입력 : 0 또는 1
			if (info == 0) { // 업무가 주어지지 않으면
				if (stack.isEmpty()) continue; // 할 일이 없는 경우에는 아무것도 하지 않는다
				int currWork = stack.peek(); // 할 일이 있다면 몇번째 일을 할지 찾는다
				work[currWork][1]--; // 현재 하고 있는 일을 1분간 처리했으므로 남은 시간 1분 감소
				if (work[currWork][1] == 0) { // 현재 하고 있는 일이 끝났다면
					score += work[currWork][0]; // 업무 평가 점수에 현재 하고 있는 일에 대한 점수를 추가한다
					stack.pop(); // 끝낸 일을 업무 스택에서 지운다
				}
				continue;
			}
			// 업무가 주어진다면
			work[i][0] = Integer.parseInt(st.nextToken()); // 주어진 업무의 만점 입력 받기
			work[i][1] = Integer.parseInt(st.nextToken()); // 주어진 업무의 소요 시간 입력 받기
			work[i][1]--; // 최근 업무부터 처리하고, 1분간 처리했으므로 남은 시간 1분 감소
			if (work[i][1] == 0) { // 처리하고 있는 업무가 끝났다면
				score += work[i][0]; // 업무 평가 점수에 해당 업무에 대한 점수를 추가하고 끝낸다
				continue;
			}
			stack.push(i); // 업무를 처리하고 있다면 업무 스택에 해당 업무를 추가한다
		}
		System.out.println(score); // 이번 분기 업무 평가 점수를 출력한다 
	}

}