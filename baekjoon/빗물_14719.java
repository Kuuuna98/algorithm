package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물_14719 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int H = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st.nextToken());
        int[] arr = new int[W];
        int[] block = new int[W];

        int result=0, max=0;
        st = new StringTokenizer(br.readLine()," ");
        for(int w=0; w<W;w++){
            arr[w] = Integer.parseInt(st.nextToken());
            if(max<arr[w]){
                max=arr[w];
            }
            block[w] = max;
        }
        max=0;
        for(int w=W-1; 0<=w;w--){
            if(max<arr[w]){
                max=arr[w];
            }
            if(block[w]<max) result+=block[w]-arr[w];
            else result+=max-arr[w];
        }

        System.out.print(result);
        br.close();
    }
}