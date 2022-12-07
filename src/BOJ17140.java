import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ17140 {
    public static void main(String[] args) throws IOException {
        new BOJ17140().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(arr, r, c, k));
    }

    int solution(int[][] arr, int r, int c, int k) {
        if (r < arr.length && c < arr[0].length && arr[r][c] == k) return 0;
        for (int t = 1; t <= 100; t++) {
            if (arr.length >= arr[0].length) arr = R(arr);
            else arr = C(arr);
            if (r < arr.length && c < arr[0].length && arr[r][c] == k) return t;
        }
        return -1;
    }

    int[][] R(int[][] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sort(arr[i]);
            if (max < arr[i].length) max = arr[i].length;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = fill(arr[i], max);
        }
        return arr;
    }

    int[][] C(int[][] arr) {
        return rotate(R(rotate(arr)));
    }

    int[][] rotate(int[][] arr) {
        int[][] rotate = new int[arr[0].length][arr.length];
        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[0].length; j++) {
                rotate[i][j] = arr[j][i];
            }
        }
        return rotate;
    }

    int[] sort(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : arr) {
            if (i == 0) continue;
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        List<Integer> freqList = freq.keySet().stream()
                .sorted((o1, o2) -> {
                    int freq1 = freq.getOrDefault(o1, 0);
                    int freq2 = freq.getOrDefault(o2, 0);
                    if (freq1 != freq2) return freq1 - freq2;
                    return o1 - o2;
                })
                .limit(50)
                .collect(Collectors.toList());
        int[] sort = new int[freqList.size() * 2];
        for (int i = 0; i < freqList.size(); i++) {
            sort[2 * i] = freqList.get(i);
            sort[2 * i + 1] = freq.get(sort[2 * i]);
        }
        return sort;
    }

    int[] fill(int[] arr, int max) {
        if (max == arr.length) return arr;
        int[] fill = new int[max];
        System.arraycopy(arr, 0, fill, 0, arr.length);
        return fill;
    }
}
