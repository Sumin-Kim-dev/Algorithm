import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int odd_sum = 0;
			for (int i = 0; i < 10; i++) {
                int curr = sc.nextInt();
                if (curr % 2 == 1) odd_sum += curr;
            }
            System.out.printf("#%d %d\n", test_case, odd_sum);
		}
	}
}