package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1043 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Party party = new Party(n, m);
		// 진실을 알고 있는 사람의 목록 = personTrue
		st = new StringTokenizer(br.readLine());
		int numTrue = Integer.parseInt(st.nextToken());
		int[] personTrue = new int[numTrue];
		for (int i = 0; i < numTrue; i++)
			personTrue[i] = Integer.parseInt(st.nextToken()) - 1;
		// partyList[], party[] 만들기
		for (int i = 0; i < m; i++) {
			party.party[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int partyPeople = Integer.parseInt(st.nextToken());
			for (int j = 0; j < partyPeople; j++) {
				int person = Integer.parseInt(st.nextToken()) - 1;
				party.partyList[person].add(i);
				party.party[i].add(person);
			}
		}
		bw.write(max(personTrue, party) + "");
		bw.close();
	}

	static int max(int[] personTrue, Party party) {
		int max = party.m;
		boolean[] checkPerson = new boolean[party.n];
		boolean[] checkParty = new boolean[party.m];
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < personTrue.length; i++) {
			queue.add(personTrue[i]);
			checkPerson[personTrue[i]] = true;
		}
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			Iterator<Integer> iter = party.partyList[curr].iterator();
			while (iter.hasNext()) {
				int partyIndex = iter.next();
				if (checkParty[partyIndex])
					continue;
				checkParty[partyIndex] = true;
				max--;
				Iterator<Integer> iter2 = party.party[partyIndex].iterator();
				while (iter2.hasNext()) {
					int person = iter2.next();
					if (checkPerson[person])
						continue;
					checkPerson[person] = true;
					queue.add(person);
				}
			}
		}
		return max;
	}
}

class Party {
	ArrayList<Integer> partyList[], party[];
	int n, m;

	Party(int n, int m) {
		partyList = new ArrayList[n];
		for (int i = 0; i < n; i++)
			partyList[i] = new ArrayList<>();
		party = new ArrayList[m];
		for (int i = 0; i < m; i++)
			party[i] = new ArrayList<>();
		this.n = n;
		this.m = m;
	}
}
