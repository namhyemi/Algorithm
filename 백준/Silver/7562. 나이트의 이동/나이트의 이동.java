import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    static int T, N;
    static int[][] visited;
    static Point startPoint = new Point(0, 0), endPoint = new Point(0, 0);
    static int[][] dir = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer input;

        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            input = new StringTokenizer(br.readLine());
            startPoint.x = Integer.parseInt(input.nextToken());
            startPoint.y = Integer.parseInt(input.nextToken());
            
            input = new StringTokenizer(br.readLine());
            endPoint.x = Integer.parseInt(input.nextToken());
            endPoint.y = Integer.parseInt(input.nextToken());

            visited = new int[N][N];
            answer.append(BFS()).append("\n");
        }

        System.out.println(answer);
        
        br.close();
    }

    private static int BFS() {
        Queue<Point> q = new ArrayDeque<>();
        
        q.add(startPoint);
        visited[startPoint.x][startPoint.y] = 1;
        
        while(!q.isEmpty()) {
            Point current = q.poll();

            if(current.x == endPoint.x && current.y == endPoint.y) break;
            
            for(int[] d : dir) {
                int mx = current.x + d[0];
                int my = current.y + d[1];

                if(mx < 0 || my < 0 || mx >= N || my >= N) continue; // 범위 체크
                if(visited[mx][my] > 0) continue; // 방문 여부 체크
                
                q.add(new Point(mx, my));
                visited[mx][my] = visited[current.x][current.y] + 1; // 방문
            }
        }
        
        return visited[endPoint.x][endPoint.y] - 1;
    }
}