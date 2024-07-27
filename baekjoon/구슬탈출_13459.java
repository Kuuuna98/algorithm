package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 구슬탈출_13459 {
    final static int[] di = new int[]{1, 0, -1, 0};
    final static int[] dj = new int[]{0, 1, 0, -1};
    static int N, M;
    static int Bi, Bj, Ri, Rj;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(playGame(0, Ri, Rj, Bi, Bj) ? 1 : 0);
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        N = Integer.parseInt(input.substring(0, input.indexOf(' ')));
        M = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    Ri = i;
                    Rj = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    Bi = i;
                    Bj = j;
                    map[i][j] = '.';
                }
            }
        }
    }

    static boolean playGame(int move_cnt, int Ri, int Rj, int Bi, int Bj) {
        if (move_cnt == 10) {
            return false;
        }

        for (int z = 0; z < 4; z++) {
            int nBi = Bi, nBj = Bj;
            while (map[nBi][nBj] == '.') {
                nBi += di[z];
                nBj += dj[z];
            }
            if (map[nBi][nBj] == 'O') {
                continue;
            } else {
                nBi -= di[z];
                nBj -= dj[z];
            }

            int nRi = Ri, nRj = Rj;
            while (map[nRi][nRj] == '.') {
                nRi += di[z];
                nRj += dj[z];
            }
            if (map[nRi][nRj] == 'O') {
                return true;
            } else {
                nRi -= di[z];
                nRj -= dj[z];
            }

            if (nBi == nRi && nBj == nRj) {
                if (z == 0) {
                    if (Bi < Ri) nBi--;
                    else nRi--;
                } else if (z == 1) {
                    if (Bj < Rj) nBj--;
                    else nRj--;
                } else if (z == 2) {
                    if (Bi < Ri) nRi++;
                    else nBi++;
                } else {
                    if (Bj < Rj) nRj++;
                    else nBj++;
                }
            }
            if (Bi == nBi && Bj == nBj && Ri == nRi && Rj == nRj) continue;
            if (playGame(move_cnt + 1, nRi, nRj, nBi, nBj)) return true;
        }

        return false;
    }

}
