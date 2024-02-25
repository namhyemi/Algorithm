import java.util.List;
import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {

    static int[][] dirList = {{1,0},{0,1},{-1,0},{0,-1}};
    static int N;
    static int count;
    static int[][] houseArr;

    static boolean[][] visited;
    static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        houseArr = new int[N][N];
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                houseArr[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[N][N];
        for(int x = 0; x < N; x++){
            for(int y = 0; y < N; y++){
                if(houseArr[x][y] == 1 && !visited[x][y]){
                    count = 1;
                    DFS(x, y);
                    resultList.add(count);
                }
            }
        }

        bw.write(resultList.size() + "\n");

        Collections.sort(resultList);
        for(int result : resultList) bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static void DFS(int x, int y){
        visited[x][y] = true;

        for (int[] dir : dirList) {
            int mx = x + dir[0];
            int my = y + dir[1];

            if (mx < 0 || my < 0 || mx >= N || my >= N) continue;
            if (visited[mx][my] || houseArr[mx][my] == 0) continue;

            count++;
            DFS(mx, my);
        }
    }
}