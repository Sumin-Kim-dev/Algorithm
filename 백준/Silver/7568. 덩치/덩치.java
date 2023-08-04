import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Person implements Comparable<Person> {
		int weight;
		int height;
		
		Person(int weight, int height) {
			this.weight = weight;
			this.height = height;
		}
		
		@Override
		public int compareTo(Person o) {
			if (weight < o.weight && height < o.height) return -1;
			if (weight > o.weight && height > o.height) return 1;
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Person[] people = new Person[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			people[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int[] rank = new int[n];
		for (int i = 0; i < n; i++) {
			rank[i] = 1;
			for (int j = 0; j < n; j++) {
				if (people[i].compareTo(people[j]) < 0) rank[i]++;
			}
			System.out.print(rank[i] + " ");
		}
	}
}