import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] parents;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점개수
        M = Integer.parseInt(st.nextToken()); // 간선개수

        parents = new int[N + 1]; // 부모노드

        make();

        int answer = N;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(union(from, to)) answer--;
        }

        System.out.println(answer);
    }

    private static void make() { // 부모 정점 초기화
        for(int n = 1; n <= N; n++) parents[n] = n;
    }

    private static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot; // 연결
        return true;
    }
}