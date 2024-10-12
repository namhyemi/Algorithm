import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static boolean[] width, sumCross, subCross;
    static long answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        width = new boolean[N];
        sumCross = new boolean[2*N];
        subCross = new boolean[2*N];

        NQueen(0); // 0 행 시작

        System.out.println(answer);

        br.close();
    }

    public static void NQueen(int row) {

        if(row == N) { // 종료조건
            answer++;
            return; 
        }

        for(int col = 0; col < N; col++) {
            if(width[col] || sumCross[row + col] || subCross[(N-1) + row - col]) continue;

            width[col] = true;
            sumCross[row + col] = true;
            subCross[(N-1) + row - col] = true;

            NQueen(row + 1);
            
            width[col] = false;
            sumCross[row + col] = false;
            subCross[(N-1) + row - col] = false;
        }
    }
}