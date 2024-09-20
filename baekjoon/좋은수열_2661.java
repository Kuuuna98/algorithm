import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 좋은수열_2661 {
    static int N;
    static char[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        arr = new char[N];
        makeSeq(0);
        System.out.print(String.valueOf(arr));
    }

    static boolean makeSeq(int idx){
        if(idx==N){
            return true;
        }
        for(char i='1'; i<='3'; i++){
            if(checkGoodSeq(idx, i)){
                arr[idx]=i;
                if(makeSeq(idx+1)) return true;
            }
        }
        return false;
    }

    static boolean checkGoodSeq(int idx, char num){
        check: for(int len=1,half = (idx+1)/2; len<=half; len++){
            if(arr[idx-len]==num){
                int i = idx-len;
                for(int n=1; n<len; n++){
                    if(arr[i-n]!=arr[idx-n]) continue check;
                }
                return false;
            }
        }
        return true;
    }

}