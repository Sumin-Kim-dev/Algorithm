import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> map = new HashMap<>();
		map.put('U', 0);
		map.put('D', 1);
		map.put('F', 2);
		map.put('B', 3);
		map.put('L', 4);
		map.put('R', 5);
		while (t-- > 0) {
			Cube cube = new Cube();
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (n-- > 0) {
				String cmd = st.nextToken();
				int face = map.get(cmd.charAt(0));
				int dir = cmd.charAt(1) == '+' ? 1 : -1;
				cube.rotate(face, dir);
			}
			sb.append(cube.toString());
		}
		System.out.println(sb);
	}
	
	static class Cube {
		char[] faces;
		Edge[] edges;
		Vertex[] vertices;
		
		// 회전 면, 순서, first 변화
		static int[][][] re = {
			{{0, 0}, {1, 0}, {2, 0}, {3, 0}}, // 위 : 뒤왼앞오
			{{11, 0}, {10, 0}, {9, 0}, {8, 0}}, // 아 : 오앞왼뒤
			{{2, 1}, {5, 0}, {10, 1}, {6, 1}}, // 앞 : 위왼아오
			{{7, 0}, {8, 1}, {4, 1}, {0, 1}}, // 뒤 : 오아왼위
			{{1, 1}, {4, 0}, {9, 1}, {5, 1}}, // 왼 : 위뒤아앞
			{{6, 0}, {11, 1}, {7, 1}, {3, 1}}, // 오 : 앞아뒤위
		};
		
		// 회전 면, 순서, first 변화
		static int[][][] rv = {
			{{0, 0}, {1, 0}, {2, 0}, {3, 0}}, // 위 : 뒤왼앞오
			{{6, 2}, {5, 2}, {4, 2}, {7, 2}}, // 아 : 오앞왼뒤
			{{1, 2}, {5, 0}, {6, 1}, {2, 1}}, // 앞 : 위왼아오
			{{7, 0}, {4, 1}, {0, 1}, {3, 2}}, // 뒤 : 오아왼위
			{{0, 2}, {4, 0}, {5, 1}, {1, 1}}, // 왼 : 위뒤아앞
			{{6, 0}, {7, 1}, {3, 1}, {2, 2}}, // 오 : 앞아뒤위
		};
		
		public Cube() {
			// 면 번호 위아앞뒤왼오 순
			faces = new char[] {'w', 'y', 'r', 'o', 'g', 'b'};
			
			edges = new Edge[12];
			edges[0] = new Edge(0, new int[] {0, 3});
			edges[1] = new Edge(0, new int[] {0, 4});
			edges[2] = new Edge(0, new int[] {0, 2});
			edges[3] = new Edge(0, new int[] {0, 5});
			edges[4] = new Edge(0, new int[] {4, 3});
			edges[5] = new Edge(0, new int[] {2, 4});
			edges[6] = new Edge(0, new int[] {5, 2});
			edges[7] = new Edge(0, new int[] {3, 5});
			edges[8] = new Edge(0, new int[] {1, 3});
			edges[9] = new Edge(0, new int[] {1, 4});
			edges[10] = new Edge(0, new int[] {1, 2});
			edges[11] = new Edge(0, new int[] {1, 5});
			
			vertices = new Vertex[8];
			vertices[0] = new Vertex(0, new int[] {0, 3, 4});
			vertices[1] = new Vertex(0, new int[] {0, 4, 2});
			vertices[2] = new Vertex(0, new int[] {0, 2, 5});
			vertices[3] = new Vertex(0, new int[] {0, 5, 3});
			vertices[4] = new Vertex(0, new int[] {4, 3, 1});
			vertices[5] = new Vertex(0, new int[] {2, 4, 1});
			vertices[6] = new Vertex(0, new int[] {5, 2, 1});
			vertices[7] = new Vertex(0, new int[] {3, 5, 1});
		}

		public void rotate(int face, int dir) {
			Edge te = edges[re[face][0][0]];
			for (int i = 0; i < 3; i++) {
				edges[re[face][((i - 2) * dir + 2) % 4][0]] = edges[re[face][((i - 1) * dir + 2) % 4][0]];
				Edge ce = edges[re[face][((i - 2) * dir + 2) % 4][0]];
				int fDiff = re[face][((i - 1) * dir + 2) % 4][1] - re[face][((i - 2) * dir + 2) % 4][1];
				ce.first = (ce.first + fDiff + 2) % 2;
			}
			edges[re[face][dir + 2][0]] = te;
			Edge ee = edges[re[face][dir + 2][0]];
			ee.first = (ee.first + re[face][0][1] - re[face][dir + 2][1] + 2) % 2;
			
			Vertex tv = vertices[rv[face][0][0]];
			for (int i = 0; i < 3; i++) {
				vertices[rv[face][((i - 2) * dir + 2) % 4][0]] = vertices[rv[face][((i - 1) * dir + 2) % 4][0]];
				Vertex cv = vertices[rv[face][((i - 2) * dir + 2) % 4][0]];
				int fDiff = rv[face][((i - 1) * dir + 2) % 4][1] - rv[face][((i - 2) * dir + 2) % 4][1];
				cv.first = (cv.first + fDiff + 3) % 3;
			}
			vertices[rv[face][dir + 2][0]] = tv;
			Vertex ev = vertices[rv[face][dir + 2][0]];
			ev.first = (ev.first + rv[face][0][1] - rv[face][dir + 2][1] + 3) % 3;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(faces[vertices[0].color[vertices[0].first]]);
			sb.append(faces[edges[0].color[edges[0].first]]);
			sb.append(faces[vertices[3].color[vertices[3].first]]);
			sb.append('\n');
			sb.append(faces[edges[1].color[edges[1].first]]);
			sb.append(faces[0]);
			sb.append(faces[edges[3].color[edges[3].first]]);
			sb.append('\n');
			sb.append(faces[vertices[1].color[vertices[1].first]]);
			sb.append(faces[edges[2].color[edges[2].first]]);
			sb.append(faces[vertices[2].color[vertices[2].first]]);
			sb.append('\n');
			return sb.toString();
		}
	}
	
	static class Edge {
		// 위뒤 위왼 위앞 위오 0 1 2 3
		// 왼뒤 앞왼 오앞 뒤오 4 5 6 7
		// 아뒤 아왼 아앞 아오 8 9 10 11
		int first;
		int[] color;
		
		public Edge(int first, int[] color) {
			this.first = first;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Edge [first=" + first + ", color=" + Arrays.toString(color) + "]";
		}
	}
	
	static class Vertex {
		// 위뒤왼 위왼앞 위앞오 위오뒤 0 1 2 3
		// 왼뒤아 앞왼아 오앞아 뒤오아 4 5 6 7
		int first;
		int[] color;
		
		public Vertex(int first, int[] color) {
			this.first = first;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Vertex [first=" + first + ", color=" + Arrays.toString(color) + "]";
		}
	}
}