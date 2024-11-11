import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] DP = new int[5001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        DP[1] = -1;
        DP[2] = -1;
        DP[3] = 1;
        DP[4] = -1;
        DP[5] = 1;

        for(int n = 6; n <= N; n++) {
            int min = 5000;

            if(DP[n - 3] != -1) min = DP[n-3] + 1;
            if(DP[n - 5] != -1) min = Math.min(DP[n-5] + 1, min);
            if(min == 5000) min = -1;

            DP[n] = min;
        }

        System.out.println(DP[N]);

        br.close();
    }
}