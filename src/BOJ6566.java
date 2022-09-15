import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6566 {
    static class Word {
        String word;
        Count count;

        Word(String word) {
            this.word = word;
            count = new Count(word);
        }
    }

    static class Count {
        int[] alphabets = new int[26];

        Count(String word) {
            for (int i = 0; i < word.length(); i++) {
                alphabets[word.charAt(i) - 'a']++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Count count = (Count) o;
            return Arrays.equals(alphabets, count.alphabets);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(alphabets);
        }
    }

    static class Group implements Comparable<Group> {
        int size;
        ArrayList<String> words;

        Group() {
            size = 0;
            words = new ArrayList<>();
            words.add("");
        }

        void add(Word word) {
            int position = ++size;
            int end = 0;
            while (position > end + 1) {
                int mid = (position + end) / 2;
                if (words.get(mid).compareTo(word.word) > 0) position = mid;
                else end = mid;
            }
            words.add(position, word.word);
        }

        @Override
        public int compareTo(Group o) {
            if (this.size != o.size) return o.size - this.size;
            else return this.words.get(1).compareTo(o.words.get(1));
        }
    }

    ArrayList<Word> words;
    HashMap<Count, Group> groups;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        words = new ArrayList<>();
        String str = br.readLine();
        while (str != null && !str.isBlank()) {
            words.add(new Word(str));
            str = br.readLine();
        }
    }

    void setGroups() {
        groups = new HashMap<>();
        for (Word word : words) {
            if (!groups.containsKey(word.count))
                groups.put(word.count, new Group());
            Group group = groups.get(word.count);
            group.add(word);
        }
    }

    void printGroup(Group group) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Group of size %d: ", group.size));
        for (int i = 1; i <= group.size; i++) {
            String currWord = group.words.get(i);
            if (currWord.equals(group.words.get(i - 1))) continue;
            sb.append(currWord).append(' ');
        }
        sb.append('.');
        System.out.println(sb);
    }

    void solution() throws IOException {
        input();
        setGroups();
        ArrayList<Group> groupList = new ArrayList<>(groups.values());
        Collections.sort(groupList);
        for (int i = 0; i < 5; i++) {
            if (i >= groupList.size()) break;
            printGroup(groupList.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ6566().solution();
    }
}
