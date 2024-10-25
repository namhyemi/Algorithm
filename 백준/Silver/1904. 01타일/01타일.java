import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        DP = new int[N+1];

        DP[1] = 1; // 1

        if(N > 1) DP[2] = 2; // 11 00

        for(int n = 3; n <= N; n++) {
            DP[n] = (DP[n - 1] + DP[n - 2]) % 15746;
        }

        System.out.println(DP[N]);
    }
}