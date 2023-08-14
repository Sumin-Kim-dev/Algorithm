import java.util.Scanner;

public class Main {
	static int spaces[][];
	static int white, green;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		spaces = new int [n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				spaces[i][j] = sc.nextInt();
			}
		}
		
		makeSpace(0, 0, n);
		System.out.println(white);
		System.out.println(green);
	}
	
	private static void makeSpace(int sr, int sc, int size) {
		
		int sum = 0;
		for (int r = sr; r < sr + size; r++) {
			for (int c = sc; c < sc + size; c++) {
				sum += spaces[r][c];
			}
		}
		
		if(sum == 0) {	// 모두 하얀색인 공간
			white ++;
		}else if(sum == size * size) {	// 모두 초록색인 공간
			green++;
		}else {	// 두 색이 섞여있는 공간
			int half = size/2;
			makeSpace(sr, sc, half);	//1
			makeSpace(sr, sc + half, half);		//2
			makeSpace(sr + half, sc, half);		//3
			makeSpace(sr + half, sc + half, half);		//3
		}
	}

}