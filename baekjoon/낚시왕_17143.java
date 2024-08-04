package baekjoon;

import java.io.*;
import java.util.*;

public class 낚시왕_17143 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int result=0;

        Shark[][] map = new Shark[R][C];
        for (int i = 0; i <M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            Shark shark = new Shark(Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()));

            //상하우좌에서 상하좌우로 순서변경
            if(shark.d==2) shark.d=3;
            else if(shark.d==3) shark.d=2;

            map[shark.r][shark.c]=shark;
        }

        PriorityQueue<Shark> pq = new PriorityQueue<>();
        int cur=-1; //낚시왕 위치
        while(++cur<C) {

            //낚시왕이 잡을 상어 선택
            for (int i = 0; i < R; i++) {
                if(map[i][cur]==null) continue;
                result+=map[i][cur].z;
                map[i][cur]=null;
                break;
            }

            //map안에 상어들 pq에 넣기
            for (int i = 0; i <R; i++) {
                for (int j = 0; j <C; j++) {
                    if(map[i][j]==null) continue;
                    pq.offer(map[i][j]);
                    map[i][j]=null;
                }
            }
            if(pq.isEmpty())break;

            //상어 이동
            while(!pq.isEmpty()) {
                Shark shark = pq.poll();
                int s=shark.s;
                int d=shark.d;

                if(d<2) { //상하로 움직이는 상어 -> 행 값만 변경
                    int[] arr=move(R,shark.r,s,d);
                    shark.r=arr[0];
                    shark.d=arr[1];
                }else {  //좌우로 움직이는 상어 -> 열 값만 변경
                    int[] arr=move(C,shark.c,s,d);
                    shark.c=arr[0];
                    shark.d=arr[1];
                }
                map[shark.r][shark.c]=shark; //map에 이동한 상어 저장
                // 이미 상어가 있는 경우 현재 상어가 잡어 먹음
            }
        }

        System.out.print(result);
        br.close();
    }

    static int[] move(int T, int t, int s, int d) {
        int[] arr = new int[2]; // 0: 위치 1: 방향
        int T1=T-1;
        if(d%2==0) {     // idx 0을 향해 이동
            if(s<=t) {   // 0<=t-s :방향 변경 없이 이동 가능
                t-=s;
            }else {      //한번이상 방향을 변경해야하는 경우
                s-=t;    // 속력에서 상어가 idx 0으로 가야하는 만큼을 뺌
                t=0;     // idx 0으로 이동 시킴

                //(상어가 끝에서 끝까지 몇번 움직이는 지) 왕복으로 움이는지 [왕복+]T1만큼 움직이는 지
                if((s/T1)%2==0) {  //상어가 왕복으로 이동
                    d++;           //방향 변경
                    t=s%T1;        //최종 상어 위치: idx 0에서 (s%T1)만큼 이동

                }else { 		   //상어가 [왕복+]T1만큼 이동
                    t=T1-(s%T1);   //최종 상어 위치: idx T-1에서 (s%T1)만큼 이동
                }
            }
        }else {           // idx T1을 향해 이동
            if(s<=T1-t) { //s+t<=T1
                t+=s;
            }else {
                s-=(T1-t);
                t=T1;

                if((s/T1)%2==0) {
                    d--;
                    t=T1-(s%T1);
                }else {
                    t=s%T1;
                }
            }
        }
        arr[0]=t;
        arr[1]=d;
        return arr;
    }

    static class Shark implements Comparable<Shark>{
        int r;
        int c;
        int s;
        int d;
        int z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s; //d<2?s%(2*(R-1)):s%(2*(C-1));
            this.d = d;
            this.z = z;
        }
        @Override
        public int compareTo(Shark o) {
            return Integer.compare(this.z, o.z);
        }
    }
}