import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            int[][] sudoku = new int[9][9];
            for (int j = 0; j < 9; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 9; k++) {
                    sudoku[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append('#').append(i).append(' ').append(result(sudoku)).append('\n');
        }
        System.out.println(sb);
    }
    
    private static int result(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            Set<Integer> row = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                row.add(sudoku[i][j]);
            }
            if (row.size() != 9) return 0;
        }
        for (int i = 0; i < 9; i++) {
            Set<Integer> column = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                column.add(sudoku[j][i]);
            }
            if (column.size() != 9) return 0;
        }
        for (int i = 0; i < 9; i++) {
            Set<Integer> box = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                box.add(sudoku[(i / 3) * 3 + j / 3][(i % 3) * 3 + j % 3]);
            }
            if (box.size() != 9) return 0;
        }
        return 1;
    }
}