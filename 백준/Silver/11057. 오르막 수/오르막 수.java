import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[][] DP; 
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N][10];

        for(int k = 0; k < 10; k++) DP[0][k] = 1;

        for(int n = 1; n < N; n++) {
            for(int k = 0; k < 10; k++) { // 각 자리마다 0~9 추가가
                for(int l = k; l < 10; l++) {
                    DP[n][k] += DP[n-1][l];
                    DP[n][k] %= 10007;
                }
            }
        }

        int sum = 0;
        for(int k = 0; k < 10; k++) sum += DP[N-1][k]; // N 자리수 경우의 수 합산

        System.out.println(sum % 10007);
    }
}