package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작_15684 {
    static int N, M, H, answer;
    static int[][] ladder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        answer = -1;

        ladder = new int[H][N + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken());
            ladder[i][j]=2;
            ladder[i][j+1]=ladder[i][j-1]=1;
        }

        drawLine(0, 0, 1);
        System.out.println(answer);
        br.close();
    }

    static void drawLine(int cnt, int ci, int cj){

        if(playLadderGame()){
            answer =cnt;
            return;
        }
        if((0< answer && answer <=cnt) || cnt==3){
            return;
        }

        for(int j=cj; j<N; j++){
            if(ladder[ci][j]==0){
                ladder[ci][j]=2;
                ladder[ci][j+1]=ladder[ci][j-1]=1;
                drawLine(cnt+1, ci, j+1);
                ladder[ci][j]=0;
                if(j-2<0||ladder[ci][j-2]!=2){
                    ladder[ci][j-1]=0;
                }
                if(N<j+2||ladder[ci][j+2]!=2){
                    ladder[ci][j+1]=0;
                }
            }
        }

        for(int i=ci+1; i<H; i++){
            for(int j=1; j<N; j++){
                if(ladder[i][j]==0){
                    ladder[i][j]=2;
                    ladder[i][j+1]=ladder[i][j-1]=1;
                    drawLine(cnt+1, i, j+1);
                    ladder[i][j]=0;
                    if(j-2<0||ladder[i][j-2]!=2){
                        ladder[i][j-1]=0;
                    }
                    if(N<j+2||ladder[i][j+2]!=2){
                        ladder[i][j+1]=0;
                    }
                }
            }
        }
    }

    static boolean playLadderGame(){
        for(int n=0; n<N; n++){
            int target = n;
            for(int i=0; i<H; i++){
                if(ladder[i][target]==2){
                    target--;
                }else if(ladder[i][target+1]==2){
                    target++;
                }
            }
            if(target!=n){
                return false;
            }
        }
        return true;
    }
}