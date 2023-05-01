import java.util.Scanner;
public class Main {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int cycle = 0;
        int a = N;
        do {
            a = (a % 10) * 10 + ((a / 10) + (a % 10)) % 10;
            cycle++;
        } while(a != N);
        System.out.println(cycle);
    }
}