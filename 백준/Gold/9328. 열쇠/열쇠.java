import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static char[][] map;
	static int[][] visited;
	static List<int[]> info;
	static int[] set;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()) + 2;
			m = Integer.parseInt(st.nextToken()) + 2;
			map = new char[n][m];
			Arrays.fill(map[0], '.');
			for (int i = 1; i < n - 1; i++) {
				map[i][0] = map[i][m - 1] = '.';
				String str = br.readLine();
				for (int j = 1; j < m - 1; j++) {
					map[i][j] = str.charAt(j - 1);
				}
			}
			Arrays.fill(map[n - 1], '.');
			int key = 0;
			char[] keys = br.readLine().toCharArray();
			for (char c : keys) {
				if (c == '0') break;
				key |= (1 << (c - 'a'));
			}
			sb.append(docs(key)).append('\n');
		}
		System.out.println(sb);
	}

	private static int docs(int key) {
		// 문 정보 저장
		List<int[]>[] doors = new ArrayList[26];
		for (int i = 0; i < 26; i++) {
			doors[i] = new ArrayList<>();
		}
		// 그룹 개수
		int count = 0;
		// 방문 처리
		visited = new int[n][m];
		// 그룹 정보 저장 : (key, 문서 개수), 1번 그룹은 바깥과 연결된 그룹
		info = new ArrayList<>();
		info.add(null);
		// 그룹 정보 가져오기 + 문 정보 저장
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '*') continue;
				if (visited[i][j] > 0) continue;
				if (Character.isUpperCase(map[i][j])) {
					doors[map[i][j] - 'A'].add(new int[] {i, j});
				}
				int[] infoCount = bfs(i, j, ++count);
				if (count == 1) infoCount[0] |= key; // 처음 들고 있는 키도 바깥에서 가져왔다고 취급
				info.add(infoCount);
			}
		}
		// 구역 별 집합 세팅 : 0번은 쓰지 않는다
		set = new int[count + 1];
		Arrays.fill(set, -1);
		// 현재까지 키 정보, 문서 개수 저장
		int[] curr;
		// 연 문의 개수
		int open = 0;
		do {
			// 현재 접근 가능한 정보 업데이트
			curr = info.get(find(1));
			// 문 열기
			open = 0;
			for (int i = 0; i < 26; i++) {
				if ((curr[0] & (1 << i)) == 0) continue;
				if (doors[i].size() == 0) continue;
				open++;
				for (int[] door : doors[i]) {
					int start = visited[door[0]][door[1]];
					for (int d = 0; d < 4; d++) {
						int nr = door[0] + dr[d];
						int nc = door[1] + dc[d];
						if (!isIn(nr, nc)) continue;
						if (map[nr][nc] == '*') continue;
						int end = visited[nr][nc];
						union(start, end);
					}
				}
				doors[i].clear();
			}
		} while (open > 0);
		return curr[1];
	}

	private static void union(int x, int y) { // x는 항상 문 : 자기 자신이 root
		x = find(x);
		y = find(y);
		if (x == y) return;
		set[y] = x;
		int[] infoX = info.get(x);
		int[] infoY = info.get(y);
		infoX[0] |= infoY[0];
		infoX[1] += infoY[1];
	}

	private static int find(int x) {
		if (set[x] < 0) return x;
		return set[x] = find(set[x]);
	}

	private static int[] bfs(int i, int j, int no) {
		int[] info = new int[2]; // key, 문서 개수
		visited[i][j] = no;
		if (Character.isUpperCase(map[i][j])) return info;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			if (Character.isLowerCase(map[r][c])) {
				info[0] |= (1 << (map[r][c] - 'a'));
			} else if(map[r][c] == '$') {
				info[1]++;
			}
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc)) continue;
				if (visited[nr][nc] > 0) continue;
				if (map[nr][nc] == '*') continue;
				if (Character.isUpperCase(map[nr][nc])) continue;
				visited[nr][nc] = no;
				queue.offer(new int[] {nr, nc});
			}
		}
		return info;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

}