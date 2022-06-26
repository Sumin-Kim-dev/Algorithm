import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ10816 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> cards = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int newCard = Integer.parseInt(st.nextToken());
			if (cards.containsKey(newCard))
				cards.replace(newCard, cards.get(newCard) + 1);
			else
				cards.put(newCard, 1);
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int cardNum = Integer.parseInt(st.nextToken());
			if (cards.get(cardNum) == null)
				bw.write("0 ");
			else
				bw.write(cards.get(cardNum) + " ");
		}
		bw.close();
	}
}
