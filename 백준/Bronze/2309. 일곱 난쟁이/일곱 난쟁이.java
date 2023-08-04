import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] heights = new int[9];
		int diff = -100;
		for (int i = 0; i < 9; i++) {
			heights[i] = sc.nextInt();
			diff += heights[i];
		}
		Arrays.sort(heights);
		
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (heights[i] + heights[j] == diff) {
					for (int k = 0; k < 9; k++) {
						if (k == i || k == j) continue;
						System.out.println(heights[k]);
					}
					return;
				}
			}
		}
	}
}