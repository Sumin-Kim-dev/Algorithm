package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10814 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Person people[] = new Person[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			people[i] = new Person(age, name);
		}
		Arrays.sort(people, (p1, p2) -> p1.age - p2.age);
		for (int i = 0; i < N; i++) {
			bw.write(people[i].age + " " + people[i].name + "\n");
		}
		bw.close();
	}

}

class Person {
	Integer age;
	String name;

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
}
