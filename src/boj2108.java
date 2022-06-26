

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class boj2108 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int numbers[] = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		long average = Math.round(average(numbers));
		int median = median(numbers);
		int mode = mode(numbers);
		int range = range(numbers);
		bw.write(average + "\n" + median + "\n" + mode + "\n" + range);
		bw.close();
	}

	static double average(int numbers[]) {
		double average = 0;
		for (int i = 0; i < numbers.length; i++) {
			average += numbers[i];
		}
		average /= numbers.length;
		return average;
	}

	static int median(int numbers[]) {
		Arrays.sort(numbers);
		return numbers[numbers.length / 2];
	}

	static int mode(int numbers[]) {
		Arrays.sort(numbers);
		ArrayList<Frequency> frequency = new ArrayList<Frequency>();
		for (int i = 0; i < numbers.length; i++) {
			if (i == 0 || numbers[i - 1] != numbers[i]) {
				Frequency newFrequency = new Frequency(numbers[i], 1);
				frequency.add(newFrequency);
			} else {
				Frequency newFrequency = frequency.get(frequency.size() - 1).addFrequency();
				frequency.add(newFrequency);
			}
		}
		Collections.sort(frequency);
		if (frequency.size() > 1 && frequency.get(0).frequency == frequency.get(1).frequency)
			return frequency.get(1).number;
		return frequency.get(0).number;
	}

	static int range(int numbers[]) {
		Arrays.sort(numbers);
		return numbers[numbers.length - 1] - numbers[0];
	}
}

class Frequency implements Comparable<Frequency> {
	int number, frequency;

	Frequency(int number, int frequency) {
		this.number = number;
		this.frequency = frequency;
	}

	Frequency addFrequency() {
		return new Frequency(this.number, this.frequency + 1);
	}

	@Override
	public int compareTo(Frequency f) {
		if (this.frequency != f.frequency)
			return f.frequency - this.frequency; // ��������
		else
			return this.number - f.number; // ��������
	}
}