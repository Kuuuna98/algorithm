package baekjoon;

import java.io.*;
import java.util.*;

public class 연구소_14502 {
    static final int[] di = { -1, 0, 1, 0 };
    static final int[] dj = { 0, 1, 0, -1 };

    static int arr[][], count, MAX, N, M;
    static ArrayList<int[]> virus;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 2) virus.add(new int[] { i, j });
                else if (arr[i][j] == 0) count++;
            }
        }
        count -= 3;
        make(0,0);

        System.out.print(MAX);
        br.close();
    }

    static void make(int cnt, int idx) {
        if (cnt == 3) {
            int c = count;
            Queue<Integer> queue = new LinkedList<Integer>();
            boolean[][] visited = new boolean[N][M];

            for (int[] v : virus) {
                visited[v[0]][v[1]] = true;
                queue.offer(v[0]);
                queue.offer(v[1]);
            }

            while (!queue.isEmpty()) {
                int i = queue.poll(), j = queue.poll();
                for (int z = 0; z < 4; z++) {
                    int ni = i + di[z], nj = j + dj[z];
                    if (0 <= ni && ni < N && 0 <= nj && nj < M && !visited[ni][nj] && arr[ni][nj] == 0) {
                        c--;
                        visited[ni][nj] = true;
                        queue.offer(ni);
                        queue.offer(nj);
                    }
                }
            }
            if (MAX < c) MAX = c;
            return;
        }

        for (; idx < N * M; idx++) {
            int i = idx / M, j = idx % M;
            if (arr[i][j] == 0) {
                arr[i][j] = 1;
                make(cnt + 1, idx + 1);
                arr[i][j] = 0;
            }
        }

    }
}