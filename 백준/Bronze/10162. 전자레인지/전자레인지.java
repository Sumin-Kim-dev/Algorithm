import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // 입력 스트림 생성
		int t = sc.nextInt(); // 시간 t 입력 받음
		int[] button = {300, 60, 10}; // A버튼 5분 = 300초, B버튼 1분 = 60초, C버튼 10초
		
		if (t % 10 != 0) { // t가 10의 배수가 아니면 불가능하다(최소 시간 단위가 10초)
			System.out.println(-1); // 불가능하면 -1 출력
			return; // 아래를 보지 않고 main을 끝낸다
		}
		
		// 300은 60의 배수이고 60은 10의 배수이므로 그리디 알고리즘 적용 가능
		for (int i = 0; i < 3; i++) { // 각 버튼
			System.out.print(t / button[i] + " "); // 그리디 : 시간 긴 버튼을 최대한 많이 써야 버튼 사용횟수가 최소, 현재 버튼 사용 횟수를 출력한다
			t %= button[i]; // 남은 시간
		}

	}

}