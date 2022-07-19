import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12100 {
    final int MAX_DEPTH = 5;

    int size;
    int[][] initialBoard;
    ArrayList<Integer> maxNumber = new ArrayList<>();

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        initialBoard = new int[size][size];
        StringTokenizer st;
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                initialBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    int[][] move(int[][] initialBoard, Direction direction) {
        if (direction.equals(Direction.LEFT))
            return moveLeftRight(initialBoard, 1);
        if (direction.equals(Direction.RIGHT))
            return moveLeftRight(initialBoard, -1);
        if (direction.equals(Direction.UP))
            return moveUpDown(initialBoard, 1);
        if (direction.equals(Direction.DOWN))
            return moveUpDown(initialBoard, -1);
        return initialBoard;
    }

    int[][] moveLeftRight(int[][] initialBoard, int order) {
        int[][] currBoard = new int[size][size];
        int start = 0;
        if (order == -1) start = size - 1;
        for (int i = 0; i < size; i++) {
            Queue<Integer> row = new LinkedList<>();
            for (int j = 0; j < size; j++) {
                if (initialBoard[i][start + order * j] == 0) continue;
                row.add(initialBoard[i][start + order * j]);
            }
            Queue<Integer> newRow = new LinkedList<>();
            while (!row.isEmpty()) {
                int before = row.poll();
                if (row.isEmpty()) {
                    newRow.add(before);
                    break;
                }
                int after = row.peek();
                if (before == after) {
                    newRow.add(before + after);
                    row.poll();
                } else {
                    newRow.add(before);
                }
            }
            int curr = start;
            while (!newRow.isEmpty()) {
                currBoard[i][curr] = newRow.poll();
                curr += order;
            }
        }
        return currBoard;
    }

    int[][] moveUpDown(int[][] initialBoard, int order) {
        int[][] currBoard = new int[size][size];
        int start = 0;
        if (order == -1) start = size - 1;
        for (int j = 0; j < size; j++) {
            Queue<Integer> column = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                if (initialBoard[start + order * i][j] == 0) continue;
                column.add(initialBoard[start + order * i][j]);
            }
            Queue<Integer> newColumn = new LinkedList<>();
            while (!column.isEmpty()) {
                int before = column.poll();
                if (column.isEmpty()) {
                    newColumn.add(before);
                    break;
                }
                int after = column.peek();
                if (before == after) {
                    newColumn.add(before + after);
                    column.poll();
                } else {
                    newColumn.add(before);
                }
            }
            int curr = start;
            while (!newColumn.isEmpty()) {
                currBoard[curr][j] = newColumn.poll();
                curr += order;
            }
        }
        return currBoard;
    }

    void backtracking(int depth, int[][] currBoard) {
        if (depth == MAX_DEPTH) {
            maxNumber.add(maxNumber(currBoard));
            return;
        }
        for (Direction direction : Direction.values()) {
            int[][] newBoard = move(currBoard, direction);
            backtracking(depth + 1, newBoard);
        }
    }

    int maxNumber(int[][] board) {
        int max = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (max < board[i][j]) max = board[i][j];
            }
        }
        return max;
    }

    int answer() {
        int max = 0;
        for (Integer integer : maxNumber) {
            if (max < integer) max = integer;
        }
        return max;
    }

    void solution() throws IOException {
        input();
        backtracking(0, initialBoard);
        System.out.println(answer());
    }

    public static void main(String[] args) throws IOException {
        new BOJ12100().solution();
    }

    enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}