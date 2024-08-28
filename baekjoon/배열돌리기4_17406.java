package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기4_17406 {
    static int N, M, K;
    static int answer;
    static int[][] A;
    static int[][] rcs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        rcs = new int[K][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine(), " ");
            rcs[k] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) };
        }
        br.close();

        answer = 5000;
        perm(0,new int[K], new boolean[K]);
        System.out.println(answer);
    }

    static void perm(int idx, int[] order, boolean[] visited) {
        if (idx == K) {
            rotate(order);
        }
        for (int v = 0; v < K; v++) {
            if (!visited[v]) {
                visited[v] = true;
                order[idx] = v;
                perm(idx + 1, order, visited);
                visited[v] = false;
            }
        }
    }

    static void rotate(int[] order) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = A[i][j];
            }
        }

        for (int k = 0; k < K; k++) {
            int o = order[k];

            int min_i = rcs[o][0] - rcs[o][2];
            int min_j = rcs[o][1] - rcs[o][2];
            int max_i = rcs[o][0] + rcs[o][2];
            int max_j = rcs[o][1] + rcs[o][2];
            int width = max_j - min_j;
            int height = max_i - min_i;

            while (width > 0 && height > 0) {

                int LD_item = tmp[max_i][min_j];
                int RU_item = tmp[min_i][max_j];

                for (int j = 0; j < width; j++) {
                    tmp[min_i][max_j - j] = tmp[min_i][max_j - j - 1];
                    tmp[max_i][min_j + j] = tmp[max_i][min_j + j + 1];
                }

                for (int i = 0; i < height - 1; i++) {
                    tmp[max_i - i][max_j] = tmp[max_i - i - 1][max_j];
                    tmp[min_i + i][min_j] = tmp[min_i + i + 1][min_j];
                }

                tmp[min_i++ + 1][max_j--] = RU_item;
                tmp[max_i-- - 1][min_j++] = LD_item;

                width = max_j - min_j;
                height = max_i - min_i;
            }
        }
        sum(tmp);
    }

    static void sum(int[][] rotateResult) {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += rotateResult[i][j];
            }
            if (sum < answer)
                answer = sum;
        }
    }
}