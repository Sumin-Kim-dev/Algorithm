package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1358 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int x, y, players = 0;
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if (isIn(x, y, W, H, X, Y))
				players++;
		}
		bw.write(players + "");
		bw.close();
	}

	static boolean isIn(int x, int y, int W, int H, int X, int Y) {
		if (x < X)
			return (X - x) * (X - x) + (Y + H / 2 - y) * (Y + H / 2 - y) <= (H / 2) * (H / 2);
		else if (x > X + W)
			return (X + W - x) * (X + W - x) + (Y + H / 2 - y) * (Y + H / 2 - y) <= (H / 2) * (H / 2);
		else
			return y >= Y && y <= Y + H;
	}
}
