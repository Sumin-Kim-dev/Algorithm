import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(solution(n, k));
    }

    int solution(int n, int k) {
        if (n < 10) return -1;
        if (n < 100 && n % 10 == 0) return -1;
        return sort(n, k);
    }

    int sort(int n, int k) {
        int length = toArray(n).length;
        int diff = diff(n);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, 0});
        int[] swapK = {n, 0};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[1] == k || curr[1] == length) continue;
            int[] currN = toArray(curr[0]);
            for (int i = curr[1]; i < currN.length; i++) {
                int maxDigit = currN[i];
                for (int j = i + 1; j < currN.length; j++) {
                    if (currN[j] > maxDigit) maxDigit = currN[j];
                }
                if (currN[i] == maxDigit) continue;
                for (int j = i + 1; j < currN.length; j++) {
                    if (currN[j] != maxDigit) continue;
                    int nextN = swap(curr[0], i, j);
                    if (nextN <= curr[0]) continue;
                    int[] next = {nextN, curr[1] + 1};
                    queue.offer(next);
                    if (swapK[0] < next[0]) swapK = next;
                }
                break;
            }
        }
        if (swapK[1] <= k && (k - swapK[1]) % 2 == 1) {
            if (diff == length) {
                swapK[0] = swap(swapK[0], length - 2, length - 1);
            }
        }
        return swapK[0];
    }

    int swap(int n, int i, int j) {
        int[] arr = toArray(n);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return toInt(arr);
    }

    int[] toArray(int n) {
        String num = Integer.toString(n);
        int[] digits = new int[num.length()];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = num.charAt(i) - '0';
        }
        return digits;
    }

    int toInt(int[] digits) {
        int number = 0;
        for (int digit : digits) {
            number *= 10;
            number += digit;
        }
        return number;
    }

    int diff(int n) {
        Set<Integer> set = new HashSet<>();
        while (n > 0) {
            set.add(n % 10);
            n /= 10;
        }
        return set.size();
    }
}
