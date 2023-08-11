import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int n;
	static List<int[]> list = new LinkedList<int[]>();
	static int start, end;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			start = sc.nextInt();
			end = sc.nextInt();
			list.add(new int[] {start, end});
		}
		
		Collections.sort(list, Comparator.comparing((int[] arr) -> arr[1]).thenComparing(arr -> arr[0]));
		
		int answer = 0;
		int endtime = 0;
		
		for (int[] meeting : list) {
            if (meeting[0] >= endtime) {
                endtime = meeting[1];
                answer++;
            }
        }
		System.out.println(answer);
	}
}