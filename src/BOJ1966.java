import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		Queue<Document> printer = new LinkedList<Document>();
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				Document doc = new Document(j, Integer.parseInt(st.nextToken()));
				printer.add(doc);
			}

			int printIndex = 0;
			while (!printer.isEmpty()) {
				if (isMax(printer.peek(), printer)) {
					int printedDocIndex = printer.poll().index;
					printIndex++;
					if (printedDocIndex == M)
						break;
				} else {
					printer.add(printer.poll());
				}
			}
			bw.write(printIndex + "\n");
			printer.clear();
		}
		bw.close();
	}

	static boolean isMax(Document doc, Queue<Document> printer) {
		Object copy[] = printer.toArray();
		Arrays.sort(copy, Collections.reverseOrder());
		if (doc.priority == ((Document) copy[0]).priority)
			return true;
		return false;
	}
}

class Document implements Comparable<Document> {
	int index, priority;

	Document(int index, int priority) {
		this.index = index;
		this.priority = priority;
	}

	@Override
	public int compareTo(Document doc) {
		return this.priority - doc.priority;
	}
}