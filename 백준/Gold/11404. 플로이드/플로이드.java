import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static final int INF = 100000 * 100;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i != j) map[i][j] = INF;
            }
        }
        
        for(int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            map[i][j] = Math.min(Integer.parseInt(st.nextToken()), map[i][j]);
        }

       for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(map[i][j] == INF) map[i][j] = 0;
                
                answer.append(map[i][j]).append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}