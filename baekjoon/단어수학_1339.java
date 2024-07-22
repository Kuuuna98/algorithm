package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 단어수학_1339 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] arr = new int['Z' - 'A' + 1];
        String[] inputs = new String[N];
        for (int n = 0; n < N; n++) {
            inputs[n] = br.readLine().trim();
            int number = 1;
            for (int i = inputs[n].length() - 1; 0 <= i; i--) {
                arr[inputs[n].charAt(i) - 'A'] += number;
                number *= 10;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i <= 'Z' - 'A'; i++) {
            pq.add(arr[i]);
        }

        int number = 9;
        int answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll() * number--;
        }
        System.out.println(answer);
    }
}
