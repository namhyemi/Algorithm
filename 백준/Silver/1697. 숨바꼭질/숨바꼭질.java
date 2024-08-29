import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[] location;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /* input */
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        
        location = N > K ? new int[(N+1) * 2] : new int[(K+1) * 2];

        /* logic */
        BFS();

        /* output */
        System.out.println(location[K] - 1);
    }

    public static void BFS() {
        Queue<Integer> queue = new ArrayDeque<>();
        
        location[N] = 1; // 시작위치
        queue.add(N);
        
        while(!queue.isEmpty()){
            int current = queue.poll();

            int back = current - 1;
            int front = current + 1;
            int teleport = current * 2;

            if(current > 0 && location[back] == 0) { // 처음 방문, 양의 위치
                location[back] = location[current] + 1;
                queue.add(back); 
            }
            if(current < K && location[front] == 0) { // 처음 방문, K위치보다 크지 않음
                location[front] = location[current] + 1;
                queue.add(front); 
            }
            if(current < K && location[teleport] == 0) { // 처음 방문, K위치보다 크지 않음
                location[teleport] = location[current] + 1;
                queue.add(teleport); 
            }

            if(location[K] != 0) break;
        } 
    }
}