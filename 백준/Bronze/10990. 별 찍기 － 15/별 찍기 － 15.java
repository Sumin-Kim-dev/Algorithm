import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] stars = new int[n][];
        for (int i = 0; i < n; i++) {
            stars[i] = new int[n + i];
            stars[i][n - 1 - i] = 1;
            stars[i][n - 1 + i] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < stars[i].length; j++) {
                sb.append(stars[i][j] == 1 ? "*" : " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}