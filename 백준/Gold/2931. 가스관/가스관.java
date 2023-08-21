import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int r; // 행 개수
	static int c; // 열 개수
	static int[][] map; // 구획 정보

	static int[] dr = { -1, 0, 1, 0 }; // 상우하좌 순
	static int[] dc = { 0, 1, 0, -1 }; // 상우하좌 순
	// | : 상하 연결, - : 좌우 연결, + : 상하좌우 연결, 1 : 우하 연결, 2 : 상우 연결, 3 : 상좌 연결, 4 : 하좌 연결
	static int[][] blocks = { { 0, 2 }, { 1, 3 }, { 0, 1, 2, 3 }, { 1, 2 }, { 0, 1 }, { 0, 3 }, { 2, 3 } };

	static boolean[][] visited; // 방문 체크
	static List<int[]> endPoints; // 끝 점 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림 열기
		StringBuilder sb = new StringBuilder(); // 답 출력용 StringBuilder
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력 공백 구분해서 받기
		r = Integer.parseInt(st.nextToken()); // 행 입력
		c = Integer.parseInt(st.nextToken()); // 열 입력
		map = new int[r + 1][c + 1]; // 구획 정보 r x c
		visited = new boolean[r + 1][c + 1];
		endPoints = new ArrayList<>(); // 끝 점 정보 리스트
		for (int i = 1; i <= r; i++) { // 행 정보 입력 받기
			String row = br.readLine(); // 행 정보
			for (int j = 1; j <= c; j++) { // 각 열 정보 입력 받기
				char c = row.charAt(j - 1); // i행 j열에 해당하는 정보
				switch (c) { // 각 입력 마다
				case '|': { // | 블럭은 0번으로 저장
					map[i][j] = 0;
					break;
				}
				case '-': { // - 블럭은 1번으로 저장
					map[i][j] = 1;
					break;
				}
				case '+': { // + 블럭은 2번으로 저장
					map[i][j] = 2;
					break;
				}
				case '.': { // 공백은 -1로 저장
					map[i][j] = -1;
					break;
				}
				case 'M': { // 집은 -2로 저장
					map[i][j] = -2;
					break;
				}
				case 'Z': { // 유치원은 -3으로 저장
					map[i][j] = -3;
					break;
				}
				default: { // 1, 2, 3, 4블럭은 각각 3, 4, 5, 6으로 저장
					map[i][j] = c - '1' + 3;
					break;
				}
				}
			}
		}
		System.out.println(result());
	}

	private static String result() {
		// 0~3 : 각 방향별 끝점
		int mr = 0; // 집 행번호
		int mc = 0; // 집 열번호
		int zr = 0; // 유치원 행번호
		int zc = 0; // 유치원 열번호
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (visited[i][j])
					continue;
				if (map[i][j] == -1)
					continue; // 공백 칸은 아무것도 하지 않는다
				if (map[i][j] == -2) { // 집 좌표 저장
					mr = i;
					mc = j;
					continue;
				}
				if (map[i][j] == -3) { // 유치원 좌표 저장
					zr = i;
					zc = j;
					continue;
				}
				bfs(i, j); // 블럭인 경우
			}
		}
		int er = 0; // 끝 점 행 번호
		int ec = 0; // 끝 점 열 번호
		boolean[] ed = new boolean[4]; // 끝 점 방향
		int cnt = 0; // 끝 점과 연결 된 블럭 개수
		for (int[] end : endPoints) { // 끝 점들에 대해
			if (end[0] == mr && end[1] == mc || end[0] == zr && end[1] == zc)
				continue; // 끝 점이 출발지 or 도착지면 스킵
			er = end[0]; // 끝 점 행 저장
			ec = end[1]; // 끝 점 열 저장
			ed[end[2]] = true; // 끝 점 방향 저장
			cnt++;
		}
		if (cnt > 2) { // 끝 점에 연결된 블럭이 3개 이상 = 해당 칸에 + 블럭이 와야한다
			return er + " " + ec + " " + '+';
		}
		if (cnt == 2) { // 끝 점에 연결된 블럭이 2개
			if (ed[0] && ed[2])
				return er + " " + ec + " " + '|'; // 세로 방향
			if (ed[1] && ed[3])
				return er + " " + ec + " " + '-'; // 가로 방향
			if (ed[0] && ed[3])
				return er + " " + ec + " " + '1'; // 1
			if (ed[2] && ed[3])
				return er + " " + ec + " " + '2'; // 2
			if (ed[1] && ed[2])
				return er + " " + ec + " " + '3'; // 3
			if (ed[0] && ed[1])
				return er + " " + ec + " " + '4'; // 4
		}
		if (cnt == 1) {
			int dd = 0;
			for (int d = 0; d < 4; d++) {
				int mnr = mr + dr[d];
				int mnc = mc + dc[d];
				int znr = zr + dr[d];
				int znc = zc + dc[d];
				if (mnr == er && mnc == ec || znr == er && znc == ec) {
					dd = d;
					break;
				}
			}
			if (ed[0] && dd == 2 || dd == 0 && ed[2])
				return er + " " + ec + " " + '|'; // 세로 방향
			if (ed[1] && dd == 3 || dd == 1 && ed[3])
				return er + " " + ec + " " + '-'; // 가로 방향
			if (ed[0] && dd == 3 || dd == 0 && ed[3])
				return er + " " + ec + " " + '1'; // 1
			if (ed[2] && dd == 3 || dd == 2 && ed[3])
				return er + " " + ec + " " + '2'; // 2
			if (ed[1] && dd == 2 || dd == 1 && ed[2])
				return er + " " + ec + " " + '3'; // 3
			if (ed[0] && dd == 1 || dd == 0 && ed[1])
				return er + " " + ec + " " + '4'; // 4
		}
		if (cnt == 0) { // 끝 점이 없다면 : 출발지와 도착지가 바로 연결되어야 한다
			// 1, 2, 3, 4 블럭은 올 수 없다 : 아니면 경우의 수가 유일하지 않음
			int dr = zr - mr; // 행 차이
			int dc = zc - mc; // 열 차이
			if (dr == 0)
				return ((mr + zr) / 2) + " " + ((mr + zc) / 2) + " " + '-'; // 평균이 빠진 칸, 가로 방향 연결
			if (dc == 0)
				return ((mr + zr) / 2) + " " + ((mr + zc) / 2) + " " + '|'; // 평균이 빠진 칸, 세로 방향 연결
		}
		return "";
	}

	private static void bfs(int i, int j) { // (i, j) 부터 bfs
		Queue<int[]> queue = new ArrayDeque<>(); // bfs를 위한 큐 생성
		queue.offer(new int[] { i, j }); // 시작 점 넣어줌
		visited[i][j] = true; // 시작 점 방문 체크
		while (!queue.isEmpty()) { // bfs 시작
			int[] curr = queue.poll(); // 현재 점 뽑아냄
			int r = curr[0]; // 현재 점 행번호
			int c = curr[1]; // 현재 점 열번호
			for (int d : blocks[map[r][c]]) { // 연결된 블럭 방향
				int nr = r + dr[d]; // 연결된 위치 행번호
				int nc = c + dc[d]; // 연결된 위치 열번호
				if (!isIn(nr, nc))
					continue; // 영역 벗어나면 아무것도 안함
				if (map[nr][nc] < 0) { // 연결된 지점이 공백이거나 끝 점이면
					endPoints.add(new int[] { nr, nc, d }); // 끝점 목록에 추가
					continue;
				}
				if (map[nr][nc] >= 0) { // 연결된 지점이 블럭이면
					if (visited[nr][nc])
						continue;
					queue.offer(new int[] { nr, nc }); // 연결 지점 큐에 넣음
					visited[nr][nc] = true; // 연결 지점 방문 처리
					continue;
				}
			}
		}
	}

	private static boolean isIn(int i, int j) { // 해당 칸이 구획 안에 있는지 체크
		return i >= 1 && i <= r && j >= 1 && j <= c; // 반환 값
	}

}