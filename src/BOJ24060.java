import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ24060 {
    public static void main(String[] args) throws IOException {
        new BOJ24060().io();
    }

    void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(a, 0, n - 1, k);
        System.out.println(saveCount == k ? save : -1);
    }

    void mergeSort(int[] a, int p, int r, int k) {
        if (saveCount >= k) return;
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(a, p, q, k);
            mergeSort(a, q + 1, r, k);
            merge(a, p, q, r, k);
        }
    }

    int saveCount = 0;
    int save = 0;
    void merge(int[] a, int p, int q, int r, int k) {
        if (saveCount >= k) return;
        int i = p;
        int j = q + 1;
        int t = 0;
        int[] temp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) temp[t++] = a[i++];
            else temp[t++] = a[j++];
        }
        while (i <= q) temp[t++] = a[i++];
        while (j <= r) temp[t++] = a[j++];
        for (t = 0; t < r - p + 1; t++) {
            a[t + p] = temp[t];
            if (saveCount < k) {
                save = temp[t];
                saveCount++;
            }
            else return;
        }
    }
}
