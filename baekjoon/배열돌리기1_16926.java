package baekjoon;

import java.io.*;
import java.util.*;

public class 배열돌리기1_16926 {
    static int[] di = { 1, 0, -1, 0 };
    static int[] dj = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int R = Integer.parseInt(st.nextToken());
        int min = N < M ? N/2 : M/2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int count = -1;
        while (0 < min--) {
            int C = N * 2 + (M - 2) * 2;
            int i = ++count, j = count;
            int z = 0;
            int re = R;

            LinkedList<Integer> queue = new LinkedList<>();
            queue.offer(arr[i][j]);
            while (0 < C || !queue.isEmpty()) {
                int ni = i + di[z];
                int nj = j + dj[z];
                if (ni < count || N + count - 1 < ni || nj < count || M + count - 1 < nj) {
                    ni = i + di[z = (z + 1) % 4];
                    nj = j + dj[z];
                }
                i = ni;
                j = nj;
                if(0 < C--) queue.offer(arr[ni][nj]);
                if (--re <= 0) arr[i][j] = queue.poll();
            }
            N -= 2;
            M -= 2;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}