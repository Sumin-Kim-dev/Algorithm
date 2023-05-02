import java.util.Scanner;
public class Main {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();
        int n;
        if(C > B) n = A/(C-B) + 1;
        else n = -1;
        System.out.println(n);
    }
}