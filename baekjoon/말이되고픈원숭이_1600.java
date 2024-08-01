package baekjoon;

import java.io.*;
import java.util.*;

public class 말이되고픈원숭이_1600 {

    static final int[] fdi = { -1, -2, -2, -1, 1, 2, 2, 1 };
    static final int[] fdj = { -2, -1, 1, 2, -2, -1, 1, 2 };
    static final int[] sdi = { -1, 0, 1, 0 };
    static final int[] sdj = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        if (W==1&&H==1) {
            System.out.println(0);
        }else {

            char[][] map = new char[H][W];
            boolean[][][] visited = new boolean[H][W][K+1];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    map[i][j] = st.nextToken().charAt(0);
                }
            }

            ArrayDeque<Node> queue = new ArrayDeque<>();
            visited[0][0][K]=true;
            queue.offer(new Node(0,0,K,0));
            int result = -1;

            done: while (!queue.isEmpty()) {
                Node node = queue.poll();
                int num= node.num+1;
                if(0<node.cnt) {
                    for (int z = 0; z < 8; z++) {
                        int ni = node.i + fdi[z];
                        int nj = node.j + fdj[z];
                        if (0 <= ni && ni < H && 0 <= nj && nj < W && map[ni][nj]=='0' && !visited[ni][nj][node.cnt-1]) {
                            visited[ni][nj][node.cnt-1]=true;
                            if (ni==H-1&&nj==W-1) {
                                result=num;
                                break done;
                            }
                            queue.offer(new Node(ni,nj,node.cnt-1,num));
                        }
                    }
                }

                for (int z = 0; z < 4; z++) {
                    int ni = node.i + sdi[z];
                    int nj = node.j + sdj[z];
                    if (0 <= ni && ni < H && 0 <= nj && nj < W && map[ni][nj]=='0'&& !visited[ni][nj][node.cnt] ) {
                        visited[ni][nj][node.cnt]=true;
                        if (ni==H-1&&nj==W-1) {
                            result=num;
                            break done;
                        }
                        queue.offer(new Node(ni,nj,node.cnt,num));
                    }
                }



            }
            System.out.println(result);
        }
        br.close();
    }

    static class Node{
        int i,j,cnt,num;

        public Node(int i, int j, int cnt, int num) {
            super();
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.num=num;
        }

    }
}