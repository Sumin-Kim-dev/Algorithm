import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int t;
	static int m, a;
	static int[] usera;
	static int[] userb;
	// 이동 x, 상 우 하 좌
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	static int[][] graph = new int[10][10];
	static List<int[]> BC = new LinkedList<int[]>();
	static int[] pos_a;
	static int[] pos_b;
	static int answer;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		t = Integer.parseInt(br.readLine());
		
		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			usera = new int[m];
			userb = new int[m];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				usera[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				userb[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				BC.add(new int[] {y, x, c, p});
			}
			
			pos_a = new int[] {0, 0};
			pos_b = new int[] {9, 9};
			answer = 0;
			int idx = -1;
			
			while(idx != m) {
				int temp = 0;
				
				int ax = pos_a[0];
				int ay = pos_a[1];
				int bx = pos_b[0];
				int by = pos_b[1];
				
				for (int i = 0; i < BC.size(); i++) {
					int BC_x = BC.get(i)[0];
					int BC_y = BC.get(i)[1];
					int BC_c = BC.get(i)[2];
					int BC_p = BC.get(i)[3];
					int temp_a = 0;
					
					if(Math.abs(ax - BC_x) + Math.abs(ay - BC_y) <= BC_c) {
						temp_a += BC_p;
					}
					
					for (int j = 0; j < BC.size(); j++) {
						int BC_x_1 = BC.get(j)[0];
						int BC_y_1 = BC.get(j)[1];
						int BC_c_1 = BC.get(j)[2];
						int BC_p_1 = BC.get(j)[3];
						int temp_b = 0;
						
						if(Math.abs(bx - BC_x_1) + Math.abs(by - BC_y_1) <= BC_c_1) {
							if(i == j && temp_a != 0) continue;
							else {
								temp_b += BC_p_1;
							}
						}
						
						 if (temp < temp_a + temp_b) {
						   temp = temp_a + temp_b; 
						 }
					}
				
					
				}
				idx ++;
				
				if (idx != m) {
					pos_a[0] = ax + dx[usera[idx]];
					pos_a[1] = ay + dy[usera[idx]];
					pos_b[0] = bx + dx[userb[idx]];
					pos_b[1] = by + dy[userb[idx]];
				}
				
				answer += temp;
				
			}
			
			System.out.println("#" + test + " " + answer);
			BC.clear();
		}

	}

}