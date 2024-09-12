import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] eggs;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 계란 개수
        eggs = new int[N][2];
        
        for(int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            eggs[n][0] = Integer.parseInt(st.nextToken()); // 내구도
            eggs[n][1] = Integer.parseInt(st.nextToken()); // 무게
        }

        DFS(0);

        System.out.println(answer);
    }

    public static void DFS(int eggNum) { // 현재 선택한 계란 순번
        if(eggNum == N) { // 종료조건

            int sum = 0;
            for(int i = 0; i < N; i++) {
                if(eggs[i][0] <= 0) sum++; // 깨진 계란
            }
            answer = Math.max(sum, answer);
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(eggNum == i) continue; // 동일한 계란 선택하는 경우 패스
            if(eggs[eggNum][0] > 0 && eggs[i][0] > 0) { // 계란 깨기 가능
                eggs[eggNum][0] -= eggs[i][1]; // 손에 든 계란 내구도 감소
                eggs[i][0] -= eggs[eggNum][1]; // 내리친 계란 내구도 감소
                DFS(eggNum + 1);
                eggs[eggNum][0] += eggs[i][1]; // 손에 든 계란 내구도 회복
                eggs[i][0] += eggs[eggNum][1]; // 내리친 계란 내구도 회복
            } 
            else DFS(eggNum + 1); // 계란 깨기 패스
        }
    }
}