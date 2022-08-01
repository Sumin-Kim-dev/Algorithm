import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3955 {
    final int MAX = 1_000_000_000;
    final long[][] IDENTITY = {{1, 0}, {0, 1}};
    final long[][] SWAP = {{0, 1}, {1, 0}};
    final String IMPOSSIBLE = "IMPOSSIBLE";

    long[][] gcdMatrix = new long[2][2];
    long[][] curr = new long[2][1];

    String ans(int k, int c) {
        if (c == 1 && k == MAX) return IMPOSSIBLE;
        else if (c == 1) return String.valueOf(k + 1);
        curr[0][0] = k;
        curr[1][0] = c;
        if (k > c) gcdMatrix = IDENTITY.clone();
        else gcdMatrix = SWAP.clone();
        curr = matMul(gcdMatrix, curr);
        long gcd = gcd();
        if (gcd > 1) return IMPOSSIBLE;
        while (gcdMatrix[0][1] <= 0)
            gcdMatrix[0][1] += k;
        while (gcdMatrix[0][1] > MAX)
            gcdMatrix[0][1] -= k;
        return String.valueOf(gcdMatrix[0][1]);
    }

    long gcd() {
        if (curr[1][0] == 0) return curr[0][0];
        long[][] mat = {{0, 1}, {1, -curr[0][0] / curr[1][0]}};
        gcdMatrix = matMul(mat, gcdMatrix);
        curr = matMul(mat, curr);
        return gcd();
    }

    long[][] matMul(long[][] mat1, long[][] mat2) {
        if (mat1[0].length != mat2.length) return null;
        long[][] matMul = new long[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                for (int k = 0; k < mat2.length; k++)
                    matMul[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
        return matMul;
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int k = Integer.parseInt(str[0]);
            int c = Integer.parseInt(str[1]);
            System.out.println(ans(k, c));
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ3955().solution();
    }
}
