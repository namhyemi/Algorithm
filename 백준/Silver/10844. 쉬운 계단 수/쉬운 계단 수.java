import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[] DP = new long[10]; // 맨 앞자리 숫자 (0~9)
    static long answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < 10; i++) DP[i] = 1; // N = 1

        long[] tempDP = new long[10];
        
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < 10; j++) tempDP[j] = DP[j]; // 이전 계단 길이 정보 임시 저장

            for(int j = 0; j < 10; j++) {
                if(j == 0) DP[0] = tempDP[1]; // 앞자리 0 추가시 DP[1] 활용
                else if(j == 9) DP[9] = tempDP[8]; // 앞자리 9 추가시 DP[8] 활용
                else {
                    DP[j] = tempDP[j-1] % 1_000_000_000 + tempDP[j+1] % 1_000_000_000; // 앞자리 j 인 N=i 길이 계단은 N=i-1 길이 계단의 앞자리 j-1, j+1 계단 사용
                }
            }
        }

        // output
        for(int i = 1; i < 10; i++) answer += (DP[i] % 1_000_000_000); 
        
        System.out.println(answer % 1_000_000_000);
    }
}