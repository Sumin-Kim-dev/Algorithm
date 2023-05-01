import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Color[] resist = new Color[3];
        for (int i = 0; i < resist.length; i++) {
            resist[i] = Color.valueOf(br.readLine().toUpperCase());
        }
        System.out.println((resist[0].value * 10 + resist[1].value) * resist[2].product);
    }

    enum Color {
        BLACK, BROWN, RED, ORANGE, YELLOW,
        GREEN, BLUE, VIOLET, GREY, WHITE;

        final String name;
        final long value;
        final long product;

        Color() {
            name = name().toLowerCase();
            value = ordinal();
            product = pow(value);
        }

        private long pow(long value) {
            long init = 1;
            for (int i = 0; i < value; i++) {
                init *= 10;
            }
            return init;
        }
    }
}
