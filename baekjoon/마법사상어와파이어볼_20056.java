package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼_20056 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        LinkedList<Node> stack = new LinkedList<>();
        for(int m=0; m<M; m++){
            st= new StringTokenizer(br.readLine()," ");
            stack.push(new Node(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        int[] dr={-1,-1,0,1,1,1,0,-1}, dc={0,1,1,1,0,-1,-1,-1};

        for(int k=0; k<K; k++){
            Node[][] map = new Node[N][N];
            while(!stack.isEmpty()){
                Node node = stack.pop();
                node.r = (node.r+node.s*dr[node.d])%N;
                node.c = (node.c+node.s*dc[node.d])%N;
                if(node.r<0) node.r+=N;
                if(node.c<0) node.c+=N;

                if(map[node.r][node.c]==null){
                    map[node.r][node.c]=new Node(node.m, node.s, node.d, 1);
                }else{
                    map[node.r][node.c].m+=node.m;
                    map[node.r][node.c].s+= node.s;
                    if(map[node.r][node.c].cnt==1) map[node.r][node.c].d%=2;
                    if(map[node.r][node.c].d!=-1 && map[node.r][node.c].d!=node.d%2) map[node.r][node.c].d=-1;
                    map[node.r][node.c].cnt++;
                }
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j]!=null) {
                        if(map[i][j].cnt==1){
                            stack.push(new Node(i,j,map[i][j].m,map[i][j].s,map[i][j].d));
                        }else{
                            int m = map[i][j].m/5;
                            int s = map[i][j].s/map[i][j].cnt;
                            if(m==0)continue;
                            if(map[i][j].d==-1){
                                stack.push(new Node(i,j,m,s,1));
                                stack.push(new Node(i,j,m,s,3));
                                stack.push(new Node(i,j,m,s,5));
                                stack.push(new Node(i,j,m,s,7));
                            }else{
                                stack.push(new Node(i,j,m,s,0));
                                stack.push(new Node(i,j,m,s,2));
                                stack.push(new Node(i,j,m,s,4));
                                stack.push(new Node(i,j,m,s,6));
                            }
                        }
                    }
                }
            }
        }

        int result=0;
        while(!stack.isEmpty()){
            result+=stack.pop().m;
        }
        System.out.println(result);
        br.close();
    }

    static class Node{
        int r,c;
        int m,s,d;
        int cnt;

        public Node( int m, int s, int d, int cnt) {
            this.m = m;
            this.s = s;
            this.d = d;
            this.cnt = cnt;
        }

        public Node(int r, int c, int m, int s,int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}