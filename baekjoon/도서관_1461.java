package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 도서관_1461 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");

        while (N-- > 0) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp > 0) {
                plus.add(tmp);
            } else {
                minus.add(-1 * tmp);
            }
        }
        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus, Collections.reverseOrder());

        System.out.println(plus.size() > 0 && (minus.size() == 0 || plus.get(0) > minus.get(0)) ?
                check(minus, plus, M) : check(plus, minus, M));
    }

    static int check(List<Integer> a, List<Integer> b, int M) {
        int answer = 0;
        int idx = 0;

        while (idx < a.size()) {
            answer += a.get(idx) * 2;
            idx += M;
        }
        idx = 0;
        while (idx < b.size()) {
            answer += b.get(idx) * 2;
            idx += M;
        }
        answer -= b.get(0);
        return answer;
    }
}
