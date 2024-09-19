package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class 탈출_3055 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D_i = 0, D_j=0, S_i=0, S_j=0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] map = new int[R][C];

        for (int r = 0; r < R; r++) {
            char[] arr = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (arr[c] == '.') {
                    map[r][c] = 3000;
                } else if (arr[c] == '*') {
                    queue.offer(new int[] {r,c,0});
                } else if (arr[c] == 'X') {
                    map[r][c] = -2;
                } else if (arr[c] == 'D') {
                    map[r][c] = -3;
                    D_i = r;
                    D_j = c;
                } else {
                    map[r][c] = 3000;
                    S_i = r;
                    S_j = c;
                }
            }
        }
        final int[] di = {-1,0,1,0};
        final int[] dj = {0,1,0,-1};
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            for(int z=0; z<4; z++){
                int ni = node[0] + di[z];
                int nj = node[1] + dj[z];
                if(ni>=0 && ni<R && nj>=0 && nj<C && map[ni][nj] == 3000){
                    map[ni][nj] = node[2]+1;
                    queue.offer(new int[]{ni,nj,node[2]+1});
                }
            }
        }

        boolean[][] visit = new boolean[R][C];
        visit[S_i][S_j] = true;
        queue.offer(new int[]{S_i,S_j,0});
        done: while(!queue.isEmpty()){
            int[] node = queue.poll();
            for(int z=0; z<4; z++){
                int ni = node[0] + di[z];
                int nj = node[1] + dj[z];
                if(ni>=0 && ni<R && nj>=0 && nj<C && map[ni][nj] != -2 &&!visit[ni][nj]){
                    visit[ni][nj] = true;
                    if(map[ni][nj] == -3){
                        map[ni][nj] = node[2]+1;
                        break done;
                    }else if(map[ni][nj] > node[2]+1){
                        queue.offer(new int[]{ni,nj,node[2]+1});
                    }
                }
            }
        }
        System.out.println(map[D_i][D_j]==-3?"KAKTUS":map[D_i][D_j]);
        br.close();
    }
}