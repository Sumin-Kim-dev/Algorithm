import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ2887 {
    int n;
    Planet[] planets;
    PriorityQueue<int[]> edges;
    int[] sets;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        planets = new Planet[n];
        StringTokenizer st;
        int x, y, z;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(i, new Point(x, y, z));
        }
    }

    void setEdges() {
        edges = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < 3; i++) {
            int index = i;
            List<Planet> sortedPlanets = Arrays.stream(planets)
                    .sorted(Comparator.comparingInt(p -> p.point.point[index]))
                    .collect(Collectors.toList());
            for (int j = 1; j < n; j++) {
                Planet first = sortedPlanets.get(j);
                Planet second = sortedPlanets.get(j - 1);
                edges.offer(new int[]{second.planetNo, first.planetNo,
                        first.point.point[index] - second.point.point[index]});
            }
        }
    }

    long Kruskal() {
        sets = new int[n];
        Arrays.fill(sets, -1);
        long cost = 0;
        int count = 0;
        while (count < n && !edges.isEmpty()) {
            int[] currEdge = edges.poll();
            if (union(currEdge[0], currEdge[1])) {
                count++;
                cost += currEdge[2];
            }
        }
        return cost;
    }

    int findSet(int a) {
        if (sets[a] < 0) return a;
        return sets[a] = findSet(sets[a]);
    }

    boolean union(int a, int b) {
        int setA = findSet(a);
        int setB = findSet(b);
        if (setA == setB) return false;
        if (-sets[setA] < -sets[setB]) {
            int temp = setA;
            setA = setB;
            setB = temp;
        }
        sets[setA] += sets[setB];
        sets[setB] = setA;
        return true;
    }

    void solution() throws IOException {
        input();
        setEdges();
        System.out.println(Kruskal());
    }

    public static void main(String[] args) throws IOException {
        new BOJ2887().solution();
    }

    static class Point {
        int[] point = new int[3];

        public Point(int x, int y, int z) {
            point[0] = x;
            point[1] = y;
            point[2] = z;
        }
    }

    static class Planet {
        int planetNo;
        Point point;

        public Planet(int planetNo, Point point) {
            this.planetNo = planetNo;
            this.point = point;
        }
    }
}
