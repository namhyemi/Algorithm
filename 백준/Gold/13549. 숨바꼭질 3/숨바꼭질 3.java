import java.util.*;
import java.io.*;

public class Main {

    static int MAX = 100000;
    static int N, K;
    static int DP[];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        DP = new int[MAX + 1];
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        DP[N] = 1;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            if(K == current) break;

            if(current * 2 <= MAX && DP[current * 2] == 0) {
                DP[current * 2] = DP[current]; 
                queue.add(current * 2);
            }

            if(current - 1 >= 0 && DP[current - 1] == 0) { // 걷기
                DP[current - 1] = DP[current] + 1; 
                queue.add(current - 1); // x - 1; 
            }

            if(current + 1 <= MAX && DP[current + 1] == 0) { // 걷기
                DP[current + 1] = DP[current] + 1; 
                queue.add(current + 1); // x + 1;
            }
        }

        System.out.println(DP[K] - 1);
    }
}