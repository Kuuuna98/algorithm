package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시_15683 {
    static int N, M, min;
    static ArrayList<Node> list_bf;
    static final int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
    static final boolean[][][] check_z = {
            {{true, false, false, false}, {false, true, false, false}, {false, false, true, false}, {false, false, false, true}},
            {{true, false, true, false}, {false, true, false, true}},
            {{true, true, false, false}, {false, true, true, false}, {false, false, true, true}, {true, false, false, true}},
            {{true, true, true, false}, {true, true, false, true}, {true, false, true, true}, {false, true, true, true}}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int blank = 0;
        ArrayList<Node> list_com = new ArrayList<>();
        list_bf = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (tmp == 6) {
                    continue;
                } else if (tmp == 0) {
                    blank++;
                } else if (tmp == 5
                        || (tmp == 4 && (i == 0 || j == 0 || i == N - 1 || j == M - 1))
                        || (tmp == 3 && ((i == 0 || i == N - 1) && (j == 0 || j == M - 1)))) {
                    list_com.add(new Node(i, j, tmp));
                } else {
                    list_bf.add(new Node(i, j, tmp));
                }
            }
        }

        for (int n = 0, len = list_com.size(); n < len; n++) {
            Node node = list_com.get(n);
            for (int z = 0; z < 4; z++) {
                blank -= check(map, node.i, node.j, z);
            }
        }

        min = blank;
        bf(map, 0, blank);
        System.out.println(min);
        br.close();
    }

    static void bf(int[][] map, int n, int blank) {
        if (blank == 0 || n == list_bf.size()) {
            min = Math.min(blank, min);
            return;
        }
        int[][] tmpmap = new int[N][];
        int tmpblank;
        Node node = list_bf.get(n);
        for (int cz = 0,len=check_z[node.num - 1].length; cz < len; cz++) {
            tmpblank = blank;
            for (int i = 0; i < N; i++) {
                tmpmap[i] = map[i].clone();
            }
            for (int z = 0; z < 4; z++) {
                if (check_z[node.num - 1][cz][z]) tmpblank -= check(tmpmap, node.i, node.j, z);
            }
            bf(tmpmap, n + 1, tmpblank);
            if (min == 0) return;
        }
    }

    static int check(int[][] map, int i, int j, int z) {
        int cnt = 0;
        while (0 <= i && i < N && 0 <= j && j < M && map[i][j] < 6) {
            if (map[i][j] == 0) {
                cnt++;
                map[i][j] = -1;
            }
            i += di[z];
            j += dj[z];
        }
        return cnt;
    }

    static class Node {
        int i, j, num;

        Node(int i, int j, int num) {
            this.i = i;
            this.j = j;
            this.num = num;
        }
    }
}