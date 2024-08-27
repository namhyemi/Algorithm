import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    
    static int dir[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    static int N = 0, M = 0, answer = 0;
    static int[][] map;
    static int [][] visited;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* input */
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for (int n = 0; n < N; n++) {
            String[] s = br.readLine().split("");
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(s[m]);
            }
        }

        /* logic */
        answer = BFS();
        
        /* output */
        System.out.println(answer); 

        br.close();
    }

    public static int BFS() {
        Queue<Point> q = new ArrayDeque<>();
        
        visited[0][0] = 1;
        q.add(new Point(0,0));
        

        while(!q.isEmpty()) {
            Point current = q.poll();
            
            for(int[] d : dir) {
                int mx = current.x + d[0];
                int my = current.y + d[1];

                if(mx < 0 || mx >= N || my < 0 || my >= M) continue; // map index range
                if(map[mx][my] == 0 || visited[mx][my] != 0) continue; // 벽, 방문 여부 확인

                visited[mx][my] = visited[current.x][current.y] + 1; // 방문처리
                q.add(new Point(mx, my)); 
            }

            if(current.x == N-1 && current.y == M-1) break; // 도착 했으면 즉시 종료
        }
        return visited[N-1][M-1];
    }
}