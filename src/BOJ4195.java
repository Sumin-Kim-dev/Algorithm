import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ4195 {
    ArrayList<Person> people = new ArrayList<>();

    class Person implements Comparable<Person> {
        int level, size;
        String id;
        Person parent;

        Person(String id) {
            this.level = 0;
            this.size = 1;
            this.id = id;
            this.parent = this;
        }

        void setParent(Person parent) {
            this.parent = parent;
        }

        @Override
        public int compareTo(Person o) {
            return this.id.compareTo(o.id);
        }
    }

    Person findID(String id) {
        Person person = new Person(id);
        int i = Collections.binarySearch(people, person);
        if (i >= 0) return people.get(i);
        people.add(-(i + 1), person);
        return person;
    }

    Person findSet(Person person) {
        Person curr = person;
        while (curr.parent != curr)
            curr = curr.parent;
        return curr;
    }

    int union(Person person1, Person person2) {
        Person set1 = findSet(person1);
        Person set2 = findSet(person2);
        if (set1.compareTo(set2) == 0) return set1.size;
        if (set1.level >= set2.level) {
            set2.parent = set1;
            if (set1.level == set2.level)
                set1.level++;
            set1.size += set2.size;
            return set1.size;
        }
        set1.parent = set2;
        set2.size += set1.size;
        return set2.size;
    }

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            people = new ArrayList<>();
            int f = Integer.parseInt(br.readLine());
            while (f-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String id1 = st.nextToken();
                String id2 = st.nextToken();
                Person person1 = findID(id1);
                Person person2 = findID(id2);
                sb.append(union(person1, person2)).append('\n');
            }
        }
        bw.write(sb.toString());
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new BOJ4195().solution();
    }
}
