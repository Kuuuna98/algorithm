package baekjoon;

import java.io.*;
import java.util.*;

public class 탑_2493 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> main_stack = new Stack<>();
        Stack<Integer> print = new Stack<>();

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            int idx = main_stack.size();
            main_stack.push(temp);
            while (0 < idx) {
                if (temp < main_stack.get(idx - 1)) {
                    break;
                } else {
                    idx = print.get(idx - 1);
                }
            }
            print.push(idx);
            sb.append(print.peek()).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }
}