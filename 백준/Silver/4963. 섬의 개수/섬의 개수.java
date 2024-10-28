import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    static int N, M;
    static boolean[][] board, visited;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // 이동방향
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        while(true) {
            StringTokenizer input = new StringTokenizer(br.readLine());

            M = Integer.parseInt(input.nextToken());
            N = Integer.parseInt(input.nextToken());

            if(N == 0 || M == 0) break; // 종료 조건

            // 좌표 입력
            board = new boolean[N][M];
            visited = new boolean[N][M];

            Queue<Point> lands = new ArrayDeque<>();
            for(int n = 0; n < N; n++) {
                input = new StringTokenizer(br.readLine());
                for(int m = 0; m < M; m++) {
                    board[n][m] = (input.nextToken().equals("1") ? true : false); // 땅(1) 이면 true, 바다(0) 이면 false

                    if(board[n][m]) lands.add(new Point(n, m));
                }
            }

            // 섬 순회
            int cnt = 0;
            while(!lands.isEmpty()) {
                Point startLand = lands.poll();

                if(visited[startLand.x][startLand.y]) continue; // 이미 방문

                BFS(startLand);
                cnt++;
            }

            answer.append(cnt).append("\n");
        }

        System.out.println(answer);
        br.close();
    }

    private static void BFS(Point startLand) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(startLand);
        visited[startLand.x][startLand.y] = true; // 방문 처리

        while(!q.isEmpty()) {
            Point currentLand = q.poll();

            for(int[] d : dir) {
                int mx = currentLand.x + d[0];
                int my = currentLand.y + d[1];

                if(mx < 0 || my < 0 || mx >= N || my >= M) continue; // 범위 벗어남
                if(visited[mx][my]) continue; // 이미 방문
                if(!board[mx][my]) continue; // 바다

                visited[mx][my] = true; // 방문처리
                q.add(new Point(mx, my));
            }
        }
    }
}