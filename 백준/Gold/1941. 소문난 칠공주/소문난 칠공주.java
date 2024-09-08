import java.util.*;
import java.io.*;

public class Main {

    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    static char[][] board = new char[5][5];
    static int[] team = new int[7]; // 사람(좌석) 기록
    static boolean[] visited = new boolean[25]; // 사람(좌석) 방문 여부 기록
    
    static int result = 0;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 5; i++) {
            String str = br.readLine();
            for(int j = 0; j < 5; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        comb(0, 0);

        System.out.println(result);
    }

    /* 25명 중 7명 조합 */
    public static void comb(int cnt, int start) { 
        if(cnt == 7) {
            if(check()) result++;
            return; 
        }

        for(int i = start; i < 25; i++) { 
            visited[i] = true;
            team[cnt] = i; 
            comb(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    public static boolean check() {
        // team 인원 중 4명이 임도연파인지 체크
        int Y = 0;
        for(int person : team) {
            if(board[person/5][person%5] == 'Y') Y++;
        }
        if(Y >= 4) return false; // 종료

        /* 좌석이 붙어 있는지 확인 */
        boolean[][] select = new boolean[5][5]; 
        for(int person : team) { // BFS 방문 처리용 초기설정
            select[person/5][person%5] = true;
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(team[0]);
        select[team[0]/5][team[0]%5] = false; 

        int sum = 1;
        while(!q.isEmpty()) {
            int current = q.poll();

            for(int[] d : dir) { // 4방 체크
                int mx = current / 5 + d[0];
                int my = current % 5 + d[1];

                if(mx < 0 || mx >= 5 || my < 0 || my >= 5) continue;
                if(select[mx][my] == false) continue;

                select[mx][my] = false;
                q.offer(mx * 5 + my);
                sum++;
            }
        }

        if(sum == 7) return true; 
        else return false;
    }
}