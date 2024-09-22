import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] parents;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 점 개수
        M = Integer.parseInt(st.nextToken()); // 턴 수
        parents = new int[N];

        make();

        int answer = 1;
        for(; answer <= M; answer++) {
            st = new StringTokenizer(br.readLine());

            int dotOne = Integer.parseInt(st.nextToken());
            int dotTwo = Integer.parseInt(st.nextToken());
            
            if(union(dotOne, dotTwo)) break;
        }
        System.out.println(answer == M + 1 ? 0 : answer);
    }
    private static void make() { // 부모 초기화
         for(int n = 0; n < N; n++) {
             parents[n] = n;
         }
    }
    
    private static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]); // 경로 압축
    }
    
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return true; // 이미 연결되어 있음
            
        parents[bRoot] = aRoot;
        return false;
    }
}