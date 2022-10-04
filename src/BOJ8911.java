import java.io.*;

public class BOJ8911 {
    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String cmd = br.readLine();
            System.out.println(solution(cmd));
        }
    }

    int solution(String cmd) {
        final int[][] DIRECTION = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int currX = 0, currY = 0;
        int minX = 0, minY = 0, maxX = 0, maxY = 0;
        int direction = 0;
        for (char c : cmd.toCharArray()) {
            switch (c) {
                case 'F': {
                    currX += DIRECTION[direction][0];
                    currY += DIRECTION[direction][1];
                    break;
                }
                case 'B': {
                    currX -= DIRECTION[direction][0];
                    currY -= DIRECTION[direction][1];
                    break;
                }
                case 'L': {
                    direction = (direction + 1) % 4;
                    break;
                }
                case 'R': {
                    direction = (direction + 3) % 4;
                    break;
                }
                default:
                    break;
            }
            minX = Math.min(minX, currX);
            minY = Math.min(minY, currY);
            maxX = Math.max(maxX, currX);
            maxY = Math.max(maxY, currY);
        }
        return (maxX - minX) * (maxY - minY);
    }

    public static void main(String[] args) throws IOException {
        new BOJ8911().io();
    }
}
