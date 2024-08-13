import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {

    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] dic = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static int result = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int height = 0; height < n ; height++){
            st = new StringTokenizer(br.readLine());
            for (int weight = 0; weight < m; weight++){
                arr[height][weight] = Integer.parseInt(st.nextToken());
            }
        }
        
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && arr[i][j] == 1){
                    cnt++;
                    BFS(i, j);
                }
            }
        }
        sb.append(cnt).append("\n").append(result);

        System.out.println(sb.toString());
        
        br.close();
    }

    private static void BFS(int x, int y){
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        
        int sum = 0;
        
        while(!queue.isEmpty()) {
            Point current = queue.poll();
            
            sum++;
            
            for(int d = 0; d < dic.length; d++) {
                    int mx = current.x + dic[d][0];
                    int my = current.y + dic[d][1];

                    if(mx < 0 || mx >= n || my < 0 || my >= m) continue;
                    if(visited[mx][my]) continue;

                    if(arr[mx][my] == 1){
                        queue.add(new Point(mx, my));
                        visited[mx][my] = true;
                    }
            }
        }

        result = Math.max(result, sum);
    }
}