import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		String code = br.readLine();
		int[] min = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			min[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] nums = new int[s];
		for (int i = 0; i < s; i++) {
			switch(code.charAt(i)) {
			case 'A':
				nums[i] = 0;
				break;
			case 'C':
				nums[i] = 1;
				break;
			case 'G':
				nums[i] = 2;
				break;
			case 'T':
				nums[i] = 3;
				break;
			}
		}
		
		int count = 0;
		int[] cnts = new int[4];
		for (int i = 0; i < p; i++) {
			cnts[nums[i]]++;
		}
		int i = p - 1;
		while (i < s){
			boolean isValid = true;
			for (int k = 0; k < 4; k++) {
				if (cnts[k] < min[k]) {
					isValid = false;
					break;
				}
			}
			if (isValid) count++;
			i++;
			if (i == s) break;
			cnts[nums[i]]++;
			cnts[nums[i - p]]--;
		}
		System.out.println(count);
	}
}