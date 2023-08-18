import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int d;
	static int[][] map;
	
	static int[] combi = new int[3];
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[n - 1 - i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combi(0, 0);
		System.out.println(max);
	}

	private static void combi(int cnt, int start) {
		if (cnt == 3) {
			max = Math.max(max, score());
			return;
		}
		for (int i = start; i < m; i++) {
			combi[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	private static int score() {
		int score = 0; // 점수 저장
		// map 깊은 복사
		int[][] currMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			currMap[i] = Arrays.copyOf(map[i], m);
		}
		// 시간에 따라서
		for (int t = 0; t < n; t++) {
			// 각 열에서 성과 가장 가까운 적의 위치
			int[] front = findFront(currMap);
			// 각 열의 성과 가장 가까운 적 - 궁수 사이 거리
			int[][] dists = new int[m][3];
			// 궁수에서 가장 가까운 적까지의 거리
			int[] minDist = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
			// 궁수에서 가장 가까운 적의 열
			int[] minCol = new int[3];
			for (int i = 0; i < m; i++) {
				// 적의 위치
				int curr = front[i];
				for (int j = 0; j < 3; j++) {
					// 적과 궁수의 거리 : curr + 1이 행 차이
					int dist = curr + 1 + Math.abs(i - combi[j]);
					dists[i][j] = dist;
					if (minDist[j] > dist) {
						minDist[j] = dist;
						minCol[j] = i;
					}
				}
			}
			// 궁수가 적 제거하기
			for (int j = 0; j < 3; j++) {
				if (minDist[j] <= d) {
					int c = minCol[j];
					int r = front[c];
					if (currMap[r][c] == 1) {
						currMap[r][c] = 0;
						score++;
					}
				}
			}
			// 적 한 칸 전진
			move(currMap);
		}
		return score;
	}

	// 적 한 칸 전진
	private static void move(int[][] currMap) {
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n - 1; i++) {
				currMap[i][j] = currMap[i + 1][j];
			}
			currMap[n - 1][j] = 0;
		}
	}

	// 열 별로 맨 앞의 적의 위치 반환(없으면 d : 궁수가 공격할 수 없음) 
	private static int[] findFront(int[][] currMap) {
		int[] front = new int[m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (currMap[j][i] == 1) {
					front[i] = j;
					break;
				}
				front[i] = d; // 해당 열에 적이 없는 경우
			}
		}
		return front;
	}
	
}