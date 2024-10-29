import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    static int answer;
    static int N, R, L;
    static int[][] board;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    static Queue<Point> group = new ArrayDeque<>();
    static boolean flag = false;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        StringTokenizer input = new StringTokenizer(br.readLine());
        N = Integer.parseInt(input.nextToken());
        L = Integer.parseInt(input.nextToken());
        R = Integer.parseInt(input.nextToken());

        board = new int[N][N];
        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        while(true) {
            flag = false;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) BFS(i, j);
                }
            } 
            if(flag) answer++;
            else break;
        }

        System.out.println(answer);
    }

    public static void BFS(int x, int y) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        
        group.clear();
        group.add(new Point(x, y)); 
    
        int people = board[x][y];
        int cnt = 1;
        while(!q.isEmpty()) {
            Point current = q.poll();
            for(int[] d : dir) {
                int mx = current.x + d[0];
                int my = current.y + d[1];

                if(mx < 0 || my < 0 || mx >= N || my >= N) continue; // 범위 벗어남

                int diff = Math.abs(board[current.x][current.y] - board[mx][my]);
                if(diff < L || diff > R) continue; // 인구 수 차가 범위 벗어남
                if(visited[mx][my]) continue; // 방문여부 확인

                visited[mx][my] = true; // 방문
                q.add(new Point(mx, my));
                
                group.add(new Point(mx, my)); 
                cnt++;
                people += board[mx][my];
            }
        }

        if(cnt > 1) {
            flag = true; // 연합 구성
            move(people / cnt); // 인구 이동
        }
    }

    public static void move(int num) {
        while(!group.isEmpty()) {
            Point nation = group.poll();

            board[nation.x][nation.y] = num;
        }
    }
}