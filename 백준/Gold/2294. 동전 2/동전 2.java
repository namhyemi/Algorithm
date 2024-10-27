import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[] coins, DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine());
        N = Integer.parseInt(input.nextToken());
        K = Integer.parseInt(input.nextToken());

        coins = new int[N];
        DP = new int[K + 1];
        for(int n = 0; n < N; n++) {
            int coin = Integer.parseInt(br.readLine());

            coins[n] = coin;
        }

        for(int currentNum = 1; currentNum <= K; currentNum++) {
            
            int minUseDice = 100_000;
            for(int n = 0; n < N; n++) {
                if(currentNum < coins[n]) continue;

                int useCurrentDice = DP[currentNum - coins[n]] + 1;
                minUseDice = Math.min(useCurrentDice, minUseDice);
            }

            DP[currentNum] = minUseDice;
        }

        System.out.println(DP[K] == 100000 ? -1: DP[K]);

        br.close();
    }
}