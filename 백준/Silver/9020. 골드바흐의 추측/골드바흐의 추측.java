import java.util.*;
import java.io.*;

public class Main {

    static int T, N;
    static boolean[] Decimal = new boolean[10_001]; // false : 소수
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        T = Integer.parseInt(br.readLine());

        findDecimal();
        
        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            for(int num1 = N / 2; num1 <= N; num1++) {
                if(!Decimal[num1] && !Decimal[N - num1]) { // 두 수가 모두 소수이면 답 (가장 먼저 등장한 답이 수의 차가 적음)
                    answer.append(N - num1).append(" ").append(num1).append("\n");
                    break;
                }
            }
        }

        System.out.println(answer);

        br.close();
    }

    private static void findDecimal() {
        Decimal[1] = true; 
        for(int i = 2; i <= 100; i++) {
            if(Decimal[i]) continue; 
            for(int j = i * i; j <= 10000; j+=i) {
                Decimal[j] = true; 
            }
        }
    }
}