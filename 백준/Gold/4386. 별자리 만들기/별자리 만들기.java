import java.util.*;

class Main {
    static int[] parent;

    public static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        parent = new int[n + 1];
        List<double[]> stars = new ArrayList<>();
        List<double[]> edges = new ArrayList<>();
        double result = 0;

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            stars.add(new double[]{x, y});
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = stars.get(i)[0] - stars.get(j)[0];
                double dy = stars.get(i)[1] - stars.get(j)[1];
                double distance = Math.sqrt(dx * dx + dy * dy);
                edges.add(new double[]{distance, (double) i, (double) j});
            }
        }

        edges.sort(Comparator.comparingDouble(a -> a[0]));

        for (double[] edge : edges) {
            double cost = edge[0];
            int a = (int) edge[1];
            int b = (int) edge[2];
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }

        System.out.printf("%.2f\n", result);
    }
}