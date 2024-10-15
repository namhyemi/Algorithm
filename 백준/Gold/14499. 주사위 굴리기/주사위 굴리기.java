import java.util.*;
import java.io.*;

public class Main {

    static int N, M, T;
    static int[][] board;
    static int[] dice = new int[6]; // 위, 아래, 상, 하, 좌, 우
    static int[] currentPos = new int[2];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());
        currentPos[0] = Integer.parseInt(input.nextToken());
        currentPos[1] = Integer.parseInt(input.nextToken());
        T = Integer.parseInt(input.nextToken());

        board = new int[N][M];
        for(int n = 0; n < N; n++) {
            input = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++) {
                board[n][m] = Integer.parseInt(input.nextToken());
            }
        }

        input = new StringTokenizer(br.readLine());
        for(int t = 0; t < T; t++) {
            int dir = Integer.parseInt(input.nextToken());

            if(moveDice(dir)) continue;

            rollDice(dir); // 다이스 이동

            int boardNum = board[currentPos[0]][currentPos[1]];
            if(boardNum != 0) {
                dice[1] = board[currentPos[0]][currentPos[1]]; // 주사위 숫자 변경
                board[currentPos[0]][currentPos[1]] = 0;
            } else {
                board[currentPos[0]][currentPos[1]] = dice[1]; // 주사위 숫자 지도에 복사
            }
            
            answer.append(dice[0]).append("\n");
        }

        System.out.println(answer);
    }

    private static boolean moveDice(int dir) {

        int[] nextPos = new int[2];
        
        nextPos[0] = currentPos[0];
        nextPos[1] = currentPos[1];

        if(dir == 1) nextPos[1] += 1; // 동
        else if(dir == 2) nextPos[1] -= 1; // 서
        else if(dir == 3) nextPos[0] -= 1; // 북
        else if(dir == 4) nextPos[0] += 1; // 남
        
        if(nextPos[0] < 0 || nextPos[0] >= N || nextPos[1] < 0 || nextPos[1] >= M) return true; // 이동 불가능

        currentPos = nextPos; // 이동 가능
        return false;
    }

    private static void rollDice(int dir) {
        if(dir == 1) swapDice(0, 4, 1, 5); // 동
        else if(dir == 2) swapDice(0, 5, 1, 4); // 서
        else if(dir == 3) swapDice(0, 3, 1, 2); // 북
        else if(dir == 4) swapDice(0, 2, 1, 3); // 남
    }

    private static void swapDice(int a, int b, int c, int d) {
        int tmp = dice[a];
        dice[a] = dice[b];
        dice[b] = dice[c];
        dice[c] = dice[d];
        dice[d] = tmp;
    }
}