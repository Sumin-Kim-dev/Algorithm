import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int work, score, minute;
	static int now_score, now_time;
	static int answer;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		// 스택 자료구조를 이용
		Stack<int[]> stack = new Stack<>();
		
		// 정답을 위한 변수선언
		answer = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			work = Integer.parseInt(st.nextToken());
			
			if(work == 1) {
				score = Integer.parseInt(st.nextToken());
				minute = Integer.parseInt(st.nextToken());
				minute -= 1;
				// 만약 바로 일처리가 가능하다면 점수를 획득
				if (minute <= 0) {
					answer += score;
				}
				else {
					// 남은 일처리를 스택에 넣어줌
					stack.push(new int[] {score, minute});
				}
			}else {
				// 큐가 비어있을 경우 예외처리
				if(stack.isEmpty()) continue;
				
				// 0일경우 스택에서 꺼내서 일처리를 함
				int[] now = stack.pop();
				now_score = now[0];
				now_time = now[1];
				
				// time을 1 감소시킴
				now_time -= 1;
				
				// 만약 일처리가 끝났다면 스택에 넣지않고 점수만 계산
				if(now_time <= 0) {
					answer += now_score;
					continue;
				}
				// 일처리가 남았다면 다시 스택에 넣어줌
				else {
					stack.push(new int[] {now_score, now_time});
				}
				
			};
			
			
		}
		System.out.println(answer);
	}
}