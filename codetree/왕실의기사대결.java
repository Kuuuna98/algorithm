package codetree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 왕실의기사대결 {
    static int L, N, Q;
    static int[][] map;
    static int[][] map_player;
    static Player[] players;
    static int[][] orders;
    static boolean[] visited;
    static Map<Integer, Integer> infos;

    static class Player {
        int i, r, c, h, w, k, d;
        boolean damage;

        public Player(int i, int r, int c, int h, int w, int k) {
            this.i = i;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.d = 0;
        }

        @Override
        public String toString() {
            return "Player [i=" + i + ", r=" + r + ", c=" + c + ", h=" + h + ", w=" + w + ", k=" + k + ", d=" + d
                    + ", damage=" + damage + "]";
        }

    }

    public static void main(String[] args) throws Exception {
        init();

        for (int q = 0; q < Q; q++) {
//			print();

            for (int n = 1; n <= N; n++) {
                if (0 < players[n].k) {
                    if (n == orders[q][0]) {
                        visited = new boolean[N + 1];
                        visited[n] = true;
                        // 기사이동
                        if (orders[q][1] == 0) { // 위
                            if (checkUpPlayer(n)) {
                                upPlayer(n);
                            }
                        } else if (orders[q][1] == 1) { // 오른쪽
                            if (checkRightPlayer(n)) {
                                rightPlayer(n);
                            }
                        } else if (orders[q][1] == 2) { // 아래
                            if (checkDownPlayer(n)) {
                                downPlayer(n);
                            }
                        } else {
                            if (checkLeftPlayer(n)) { // 왼쪽
                                leftPlayer(n);
                            }
                        }

                        players[n].damage = false;
                    }
                }

            }

            // 대결 대미지
            for (int n = 1; n <= N; n++) {
                if (players[n].damage) {
                    players[n].damage = false;
                    checkPlayer(n);
                    if (players[n].k <= 0)
                        removePlayer(n);
                }
            }
        }

        int answer = 0;
        for (int n = 1; n <= N; n++) {
            if (0 < players[n].k) {
                answer += players[n].d;
            }
        }
        System.out.println(answer);
    }

    static void init() throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map_player = new int[L + 2][L + 2];
        map = new int[L + 2][L + 2];
        infos = new HashMap<Integer, Integer>();
        for (int i = 0; i < L + 2; i++) {
            map[0][i] = map[L + 1][i] = map[i][0] = map[i][L + 1] = 2;
        }

        for (int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        players = new Player[N + 1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            players[n] = new Player(n, r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            initPlayer(n);
        }

        orders = new int[Q][2];
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine(), " ");
            orders[q][0] = Integer.parseInt(st.nextToken());
            orders[q][1] = Integer.parseInt(st.nextToken());
        }

    }

    static void initPlayer(int p) {
        int pr = players[p].r;
        int pc = players[p].c;
        int ph = players[p].h;
        int pw = players[p].w;

        for (int r = pr; r <= L && r <= pr + ph - 1; r++) {
            for (int c = pc; c <= L && c <= pc + pw - 1; c++) {
                map_player[r][c] = p;
            }
        }
    }

    static void checkPlayer(int p) {
        int pr = players[p].r;
        int pc = players[p].c;
        int ph = players[p].h;
        int pw = players[p].w;
        int count = 0;

        for (int r = pr; r <= L && r <= pr + ph - 1; r++) {
            for (int c = pc; c <= L && c <= pc + pw - 1; c++) {
                if (map[r][c] == 1) {
                    count++;
                }
            }
        }
        players[p].k -= count;
        players[p].d += count;
    }

    static void removePlayer(int p) {
        int pr = players[p].r;
        int pc = players[p].c;
        int ph = players[p].h;
        int pw = players[p].w;

        for (int r = pr; r <= L && r <= pr + ph - 1; r++) {
            for (int c = pc; c <= L && c <= pc + pw - 1; c++) {
                map_player[r][c] = 0;
            }
        }
    }

    static boolean checkUpPlayer(int p) {
        Player player = players[p];
        int nr = player.r - 1;
        int nc = player.c;

        for (int c = nc; c <= player.c + player.w - 1; c++) {
            if (map[nr][c] == 2) {
                return false;
            }

            if (0 < map_player[nr][c] && !visited[map_player[nr][c]]) {
                visited[map_player[nr][c]] = true;
                if (!checkUpPlayer(map_player[nr][c])) {
                    return false;
                }
            }
        }
        return true;
    }

    static void upPlayer(int p) {
        Player player = players[p];
        int nr = player.r - 1;
        int nc = player.c;

        for (int c = nc; c <= player.c + player.w - 1; c++) {
            if (0 < map_player[nr][c]) {
                upPlayer(map_player[nr][c]);
            }
            map_player[nr][c] = p;
            map_player[player.r + player.h - 1][c] = 0;
        }

        player.r--;
        player.damage = true;
    }

    static boolean checkLeftPlayer(int p) {
        Player player = players[p];
        int nr = player.r;
        int nc = player.c - 1;

        for (int r = nr; r <= player.r + player.h - 1; r++) {
            if (map[r][nc] == 2) {
                return false;
            }
            if (0 < map_player[r][nc] && !visited[map_player[r][nc]]) {
                visited[map_player[r][nc]] = true;
                if (!checkLeftPlayer(map_player[r][nc])) {
                    return false;
                }
            }
        }
        return true;
    }

    static void leftPlayer(int p) {
        Player player = players[p];
        int nr = player.r;
        int nc = player.c - 1;

        for (int r = nr; r <= player.r + player.h - 1; r++) {
            if (0 < map_player[r][nc]) {
                leftPlayer(map_player[r][nc]);
            }
            map_player[r][nc] = p;
            map_player[r][player.c + player.w - 1] = 0;
        }

        player.c--;
        player.damage = true;
    }

    static boolean checkDownPlayer(int p) {
        Player player = players[p];
        int nr = player.r + player.h;
        int nc = player.c;

        for (int c = nc; c <= player.c + player.w - 1; c++) {
            if (map[nr][c] == 2) {
                return false;
            }
            if (0 < map_player[nr][c] && !visited[map_player[nr][c]]) {
                visited[map_player[nr][c]] = true;
                if (!checkDownPlayer(map_player[nr][c])) {
                    return false;
                }
            }
        }
        return true;
    }

    static void downPlayer(int p) {
        Player player = players[p];
        int nr = player.r + player.h;
        int nc = player.c;

        for (int c = nc; c <= player.c + player.w - 1; c++) {
            if (0 < map_player[nr][c]) {
                downPlayer(map_player[nr][c]);
            }
            map_player[nr][c] = p;
            map_player[player.r][c] = 0;
        }
        player.r++;
        player.damage = true;
    }

    static boolean checkRightPlayer(int p) {
        Player player = players[p];
        int nr = player.r;
        int nc = player.c + player.w;

        for (int r = nr; r <= player.r + player.h - 1; r++) {
            if (map[r][nc] == 2) {
                return false;
            }
            if (0 < map_player[r][nc] && !visited[map_player[r][nc]]) {
                visited[map_player[r][nc]] = true;
                if (!checkRightPlayer(map_player[r][nc])) {
                    return false;
                }
            }
        }
        return true;
    }

    static void rightPlayer(int p) {
        Player player = players[p];
        int nr = player.r;
        int nc = player.c + player.w;

        for (int r = nr; r <= player.r + player.h - 1; r++) {
            if (0 < map_player[r][nc]) {
                rightPlayer(map_player[r][nc]);
            }
            map_player[r][nc] = p;
            map_player[r][player.c] = 0;
        }

        player.c++;
        player.damage = true;
    }

    static void print() {
        for (int i = 0; i < L + 2; i++) {
            System.out.println(Arrays.toString(map_player[i]));
        }
    }
}