import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] board = new int[21][21]; // 가장자리에 0 padding
	static int[][] deltas = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 19; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				if (board[i][j] == 0) continue;
				for (int d = 0; d < 4; d++) {
					int beforeI = i - deltas[d][0];
					int beforeJ = j - deltas[d][1];
					if (board[i][j] == board[beforeI][beforeJ]) continue;
					boolean isFive = true;
					for (int k = 1; k < 5; k++) {
						int nextI = i + deltas[d][0] * k;
						int nextJ = j + deltas[d][1] * k;
						if (!isIn(nextI, nextJ)) {
							isFive = false;
							break;
						}
						isFive &= (board[i][j] == board[nextI][nextJ]);
					}
					int nextI = i + deltas[d][0] * 5;
					int nextJ = j + deltas[d][1] * 5;
					if (isFive && isIn(nextI, nextJ)) {
						isFive &= !(board[i][j] == board[nextI][nextJ]);
					}
					if (isFive) {
						System.out.println(board[i][j]);
						System.out.println(i + " " + j);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}
	
	private static boolean isIn(int i, int j) {
		return i >= 1 && i <= 19 && j >= 1 && j <= 19;
	}
}
