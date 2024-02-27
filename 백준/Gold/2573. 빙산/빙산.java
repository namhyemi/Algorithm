import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dirList = {{1,0},{0,1},{-1,0},{0,-1}};
    static int N, M;

    static int[][] iceArr;
    static int year;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 데이터 저장 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        iceArr = new int[N][M];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                iceArr[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            int cnt = countedIceArr();
            if(cnt == 0){
                year = 0;
                break;
            }
            if(cnt >= 2){
                break;
            }
            changedIceArr();
            year++;
        }


        bw.write(year + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // 빙하 영역 개수 확인
    static int countedIceArr(){
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(!visited[n][m] && iceArr[n][m] != 0){
                    cnt++;
                    dfs(n, m, visited);
                }
            }
        }
        return cnt;
    }

    // 빙하 영역 확인
    static void dfs(int x, int y, boolean[][] visited){

        visited[x][y] = true;

        for(int[] dir : dirList){
            int mx = x + dir[0];
            int my = y + dir[1];

            if(!visited[mx][my] && iceArr[mx][my] != 0){
                dfs(mx, my, visited);
            }
        }
    }

    /* 빙하 녹임 */
    static void changedIceArr(){
        ArrayDeque<Point> ice = new ArrayDeque<>();
        boolean[][] isIced = new boolean[N][M];

        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(iceArr[n][m] != 0){
                    isIced[n][m] = true; // 얼음 영역 기록
                    ice.add(new Point(n,m));
                }
            }
        }

        while(!ice.isEmpty()){
            Point current = ice.poll();

            for(int[] dir : dirList){
                int mx = current.x + dir[0];
                int my = current.y + dir[1];

                if(mx < 0 || my < 0 || mx >= N || my >= M) continue;
                if(!isIced[mx][my]){ // 주변이 얼음이 아니면
                    if(iceArr[current.x][current.y] == 0) continue;
                    else iceArr[current.x][current.y]--;
               }
            }
        }
    }
}