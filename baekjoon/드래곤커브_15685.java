package baekjoon;

import java.io.*;
import java.util.*;

public class 드래곤커브_15685 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] board = new boolean[101][101];
        int[][] dragons = new int[N][];
        int maxg = 0;

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            dragons[n] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
            board[dragons[n][0]][dragons[n][1]] = true;
            if (maxg < dragons[n][3])
                maxg = dragons[n][3];
        }

        int[] dx = { 1, 0, -1, 0 };
        int[] dy = { 0, -1, 0, 1 };
        int d = 0, x = 0, y = 0;
        ArrayList<Integer> list = new ArrayList<>();

        x += dx[d];
        y += dy[d];
        for (int[] dragon : dragons) {
            int dragonX = 0, dragonY = 0;
            switch (dragon[2]) {
                case 0:
                    dragonX = dragon[0] + x;
                    dragonY = dragon[1] + y;
                    break;
                case 1:
                    dragonX = dragon[0] + y;
                    dragonY = dragon[1] - x;
                    break;
                case 2:
                    dragonX = dragon[0] - x;
                    dragonY = dragon[1] - y;
                    break;
                default:
                    dragonX = dragon[0] - y;
                    dragonY = dragon[1] + x;
                    break;

            }
            if (0 <= dragonX && dragonX <= 100 && 0 <= dragonY && dragonY <= 100 && !board[dragonX][dragonY])
                board[dragonX][dragonY] = true;
        }

        list.add((d + 1) % 4);

        for (int gi = 1; gi <= maxg; gi++) {
            for (int i = list.size() - 1; 0 <= i; i--) {
                int dd = list.get(i);

                x += dx[dd];
                y += dy[dd];
                for (int[] dragon : dragons) {
                    if (dragon[3] < gi)
                        continue;

                    int dragonX = 0, dragonY = 0;
                    switch (dragon[2]) {
                        case 0:
                            dragonX = dragon[0] + x;
                            dragonY = dragon[1] + y;
                            break;
                        case 1:
                            dragonX = dragon[0] + y;
                            dragonY = dragon[1] - x;
                            break;
                        case 2:
                            dragonX = dragon[0] - x;
                            dragonY = dragon[1] - y;
                            break;
                        default:
                            dragonX = dragon[0] - y;
                            dragonY = dragon[1] + x;
                            break;

                    }
                    if (0 <= dragonX && dragonX <= 100 && 0 <= dragonY && dragonY <= 100 && !board[dragonX][dragonY])
                        board[dragonX][dragonY] = true;
                }

                list.add((dd + 1) % 4);
            }

        }

        int[] di = { 1, 1, 0 };
        int[] dj = { 0, 1, 1 };
        int answer = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (!board[i][j])
                    continue;
                int cnt = 1;
                for (int z = 0; z < 3; z++) {
                    int zi=i+di[z];
                    int zj=j+dj[z];
                    if (zi<0||100<zi||zj<0||100<zj||!board[zi][zj])
                        break;
                    cnt++;
                }
                if (cnt == 4) answer++;
            }
        }
        System.out.println(answer);
        br.close();
    }

}