package baekjoon;

import java.io.*;
import java.util.*;

public class 연구소3_17142 {
    static int MIN,N,M;
    static char board[][];
    static int[] di={-1,0,1,0}, dj={0,1,0,-1};
    static ArrayList<int[]> virus;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][N];
        virus =new ArrayList<>();
        MIN=Integer.MAX_VALUE;

        int blank_cnt=0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if(board[i][j]=='2') virus.add(new int[]{i,j});
                if(board[i][j]=='0') blank_cnt++;
            }
        }

        if(blank_cnt==0) {
            System.out.println(0);
        }else{
            com(0,0,virus.size(),new int[M], blank_cnt);
            if(MIN==Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(MIN);
        }
        br.close();
    }

    static void com(int st, int cnt, int v_len, int[] numbs, int blank_cnt){
        if(cnt==M){
            bfs(numbs,blank_cnt);
            return;
        }

        for(int i=st; i<=v_len-M+cnt;i++){
            numbs[cnt]=i;
            com(i+1,cnt+1,v_len,numbs,blank_cnt);
        }

    }

    static void bfs(int[] numbs, int blank_cnt){
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(int i=0; i<numbs.length; i++){
            visited[virus.get(numbs[i])[0]][virus.get(numbs[i])[1]]=true;
            queue.offer(new int[] {virus.get(numbs[i])[0],virus.get(numbs[i])[1],0});
        }

        while(!queue.isEmpty()){
            int[] ij=queue.poll();

            for(int z=0; z<4;z++){
                int ni=ij[0]+di[z];
                int nj=ij[1]+dj[z];
                if(0<=ni&&ni<N && 0<=nj&&nj<N && board[ni][nj] !='1' && !visited[ni][nj]){
                    visited[ni][nj]=true;
                    if(board[ni][nj]=='0') {
                        if(--blank_cnt==0) {
                            if(ij[2]+1<MIN) MIN=ij[2]+1;
                            return;
                        }
                    }
                    queue.offer(new int[] {ni,nj,ij[2]+1});
                }
            }

        }

    }
}