import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> point = new HashMap<>();
        point.put("TT", 75);
        point.put("TX", 50);
        point.put("PR", 80);
        point.put("RT", 30);
        point.put("AP", 25);
        point.put("PX", 60);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int w, n;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (w == 0 && n == 0) break;
            Map<String, Integer> score = new LinkedHashMap<>();
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String code = st.nextToken();
                if (!score.containsKey(name)) {
                    score.put(name, 0);
                }
                score.put(name, score.get(name) + point.get(code));
            }
            List<String> names = new ArrayList<>();
            for (String name : score.keySet()) {
                if (score.get(name) < 100) continue;
                names.add(name);
            }
            if (!names.isEmpty()) {
                System.out.println("Week " + w + " " + String.join(",", names));
            } else {
                System.out.println("Week " + w + " No phones confiscated");
            }
        }
    }
}