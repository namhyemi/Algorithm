import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static Edge[] edgeList;
    static int[] parents; 
    static int answer = 0;

    private static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        edgeList = new Edge[M];
        for(int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            edgeList[m] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList); // 오름차순 정렬

        make(); 

        int cnt = 0;
        for(int m = 0; m < M; m++) {
            if(edgeList[m].from == edgeList[m].to) continue; // 자기자신을 가리킴
            
            if(union(edgeList[m].from, edgeList[m].to)) {
                answer += edgeList[m].weight;
                cnt += 2;
            }
            if(cnt == N * 2) break;
        }

        System.out.println(answer);
    }

    private static void make() {
        for(int n = 0; n < N; n++) parents[n] = n;
    }

    private static int find(int n) {
        if(n == parents[n]) return n;
        return parents[n] = find(parents[n]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}