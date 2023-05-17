import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double credits = 0;
        double sum = 0;
        for (int i = 0; i < 20; i++) {
            String str = br.readLine();
            String[] split = str.split(" ");
            if (split[2].equals("P")) continue;
            double credit = Double.parseDouble(split[1]);
            credits += credit;
            sum += credit * switch (split[2]) {
                case "A+" -> 4.5;
                case "A0" -> 4.0;
                case "B+" -> 3.5;
                case "B0" -> 3.0;
                case "C+" -> 2.5;
                case "C0" -> 2.0;
                case "D+" -> 1.5;
                case "D0" -> 1.0;
                default -> 0.0;
            };
        }
        System.out.println(sum / credits);
    }
}
