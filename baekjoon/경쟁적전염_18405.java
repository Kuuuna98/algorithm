package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 경쟁적전염_18405 {
    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int K =Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        List<Node> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(0<map[i][j]){
                    list.add(new Node(i,j,map[i][j]));
                }
            }
        }
        Collections.sort(list);

        st = new StringTokenizer(br.readLine()," ");
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken())-1;
        int Y = Integer.parseInt(st.nextToken())-1;
        int sec=0;
        int[] di = {-1,0,0,1},dj = {0,-1,1,0};
        while(sec++<S){
            List<Node> tmp = new ArrayList<>();
            for(int n=0; n<list.size(); n++){
                Node node = list.get(n);
                for(int z=0;z<4;z++){
                    int ni = node.i+di[z];
                    int nj = node.j+dj[z];
                    if(0<=ni&&ni<N&&0<=nj&&nj<N&&map[ni][nj]==0){
                        map[ni][nj]=node.num;
                        tmp.add(new Node(ni,nj,node.num));
                    }
                }
            }
            if(0<map[X][Y])break;
            list=tmp;
        }

        System.out.print(map[X][Y]);
        br.close();
    }
    static class Node implements Comparable<Node>{
        int i,j,num;
        Node(int i, int j, int num){
            this.i=i;
            this.j=j;
            this.num=num;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.num,o.num);
        }
    }
}