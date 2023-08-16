import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<int[]> list = new LinkedList<>();
	static StringTokenizer st = null;
	static List<Integer> answer = new LinkedList<>();
	static List<int[]> match = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		A B C D E F
//
//		A | B C D E F
//		B | C D E F
//		C | D E F
//		D | E F
//		E | F
		
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				match.add(new int[] {i, j});
			}
		}

		
		for (int i = 0; i < 4; i++) {
			answer.add(input());
			list.clear();
		}

		for (int ans : answer) {
			System.out.print(ans + " ");
		}
		
	}
	
	static int input() throws IOException {
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < 6; j++) {
			int[] now = new int[3];
			now[0] = Integer.parseInt(st.nextToken());
			now[1] = Integer.parseInt(st.nextToken());
			now[2] = Integer.parseInt(st.nextToken());
			list.add(now);
		}
		
//		System.out.println(list.toString());
		// 만약 6승이상이나 6패이상이 있다면 return 
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (list.get(i)[j] >= 6) {
					return 0;
				}
			}
		}
		
		if(dfs(0) == false) {
			return 0;
		}
		else {
		return 1;
		}
	}

	static boolean dfs(int n) {
		if (n == 15) {
			boolean flag = true;
			// 전체 팀을 돌면서 모두 0 0 0이 아니면 불가능한 결과이기 때문에 return false
			for (int[] score : list) {
				if(score[0] != 0 || score[1] != 0 || score[2] != 0) {
					flag = false;
					break;
				}
			}
			return flag;
		}
		
		else {
			// 정답 판정을 위한 
			boolean flag_1 = false;
			// 승 무 패 비교	
			for (int i = 0; i < 3; i++) {
				// 첫번째 매치의 A(첫번째) 팀과 B(두번째) 팀을 비교하는데 두 팀이 만약 0 0이면 무시
				if (list.get(match.get(n)[0])[i] == 0 || list.get(match.get(n)[1])[2 - i] == 0) {
					continue;
				}
				
				
				list.get(match.get(n)[0])[i] -= 1;
				list.get(match.get(n)[1])[2 - i] -= 1;
				
				flag_1 = dfs(n + 1);
				
				if(flag_1 == true) return flag_1;
				
				list.get(match.get(n)[0])[i] += 1;
				list.get(match.get(n)[1])[2 - i] += 1;
			}
			
			return false;
		}
	}

}