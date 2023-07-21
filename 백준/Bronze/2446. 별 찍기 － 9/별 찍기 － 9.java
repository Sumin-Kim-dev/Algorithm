import java.util.Scanner;

public class Main {
	
	static int N;
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt() - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 2 * N; i++) {
        	for (int j = 0; j < N - Math.abs(i - N); j++) {
        		sb.append(' ');
        	}
            for (int j = 0; j < 2 * Math.abs(i - N) + 1; j++) {
            	sb.append('*');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
	
}
