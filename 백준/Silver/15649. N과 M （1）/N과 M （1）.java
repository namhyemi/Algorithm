import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] result;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        result = new int[M];

        per(0);
    }

    public static void per(int cnt) {
        if(cnt == M) {

            StringBuilder sb = new StringBuilder();
            
            for(int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");    
            }
            System.out.println(sb.toString());
            
            return; 
        }

        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            result[cnt] = i + 1;
            per(cnt + 1);
            visited[i] = false;
        }
    }
}