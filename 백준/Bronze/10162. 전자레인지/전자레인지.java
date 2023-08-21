import java.util.Scanner;

public class Main {
	static int t;
	static int a, b, c;
	static int a_cnt, b_cnt, c_cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 요리시간 입력받기
		t = sc.nextInt();
		// 분을 초로 변경해서 저장
		a = 300;
		b = 60;
		c = 10;
		
		// a, b, c 개수를 카운팅할 변수 선언
		a_cnt = 0;
		b_cnt = 0;
		c_cnt = 0;
		

		while(true) {
			// 10초 미만은 쿠킹불가
			if(t < 10) {
				break;
			}
			// 만약 5분이상 남았다면
			if(t >= 500) {
				t -= a;
				a_cnt += 1;
				continue;
			// 1분 이상 남았다면
			}else if(t >= 60) {
				// 빼고
				t -= 60;
				// 카운팅
				b_cnt += 1;
				continue;
			// 10초 이상 남았다면
			}else if(t >= 10) {
				// 빼고
				t -= 10;
				// 카운팅
				c_cnt += 1;
			}
		}
		// 모두 계산이 끝났는데 0초가 남았다면
		if(t == 0) {
			System.out.println(a_cnt + " " + b_cnt + " " + c_cnt);
		}
		// 잔여초가 있다면
		else {
			System.out.println(-1);
		}
		

		

	}

}