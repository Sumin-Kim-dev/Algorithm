import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        int N, M;
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
		    N = Integer.parseInt(st.nextToken());
		    M = Integer.parseInt(st.nextToken());
		    sb.append(Combination(M, N)+"\n");
        }
        bw.write(sb.toString());
		bw.close();
	}

	static int Combination(int N, int K) {
		int combination[] = new int[K + 1];
		int temp[] = new int[K + 1];

		// 0C0 = 1, 0C1, ..., 0Ck = 0
		combination[0] = 1;

		// nCk = (n-1)C(k-1)+(n-1)Ck
		int i = 1;
		while (i <= N) {
			temp = combination.clone();
			for (int j = 1; j < K + 1; j++)
				combination[j] = (temp[j - 1] + temp[j]);
			i++;
		}
		return combination[K];
	}
}
