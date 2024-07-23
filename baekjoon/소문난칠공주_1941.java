package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 소문난칠공주_1941 {
    final static int N = 5;
    final static int[] di = new int[]{0, -1, 0, 1};
    final static int[] dj = new int[]{-1, 0, 1, 0};
    static char[][] map;
    static boolean[][] visited, checked;
    static int answer, total, countS;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        checked = new boolean[N][N];
        answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checked[i][j] = true;
                makeTeam(1, i, j + 1);
                checked[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    static void makeTeam(int index, int st_i, int st_j) {
        if (index == 7) {
            visited = new boolean[N][N];
            visited[st_i][st_j - 1] = true;
            total = 1;
            countS = map[st_i][st_j - 1] == 'S' ? 1 : 0;
            checkTeam(st_i, st_j - 1);
            return;
        }

        for (int j = st_j; j < N; j++) {
            checked[st_i][j] = true;
            makeTeam(index + 1, st_i, j + 1);
            checked[st_i][j] = false;
        }

        for (int i = st_i + 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checked[i][j] = true;
                makeTeam(index + 1, i, j + 1);
                checked[i][j] = false;
            }
        }
    }

    static void checkTeam(int i, int j) {
        if (total == 7) {
            if (4 <= countS) {
                answer++;
            }
            return;
        }

        for (int z = 0; z < 4; z++) {
            int ni = i + di[z];
            int nj = j + dj[z];
            if (checkRange(ni, nj) && checked[ni][nj] && !visited[ni][nj]) {
                visited[ni][nj] = true;
                total++;
                if (map[ni][nj] == 'S') countS++;
                checkTeam(ni, nj);
            }
        }
    }

    static boolean checkRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}
