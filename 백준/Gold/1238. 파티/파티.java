import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int to, weight;
    
    public Edge (int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
    
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {

    static int INF = Integer.MAX_VALUE;
    static int N, M, X;
    static List<Edge>[] go, back;
    static int[] goDist, backDist;

    static int answer = 0; 
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        go = new ArrayList[N + 1];
        back = new ArrayList[N + 1];

        for(int n = 0; n <= N; n++) {
            go[n] = new ArrayList<Edge>(); // X까지 가는 비용
            back[n] = new ArrayList<Edge>(); // X에서 오는 비용
        }
        
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            go[start].add(new Edge(end, weight));
            back[end].add(new Edge(start, weight));
        }

        goDist = dijkstra(go);
		backDist = dijkstra(back);

        for(int n = 1; n <= N; n++) {
            answer = Math.max(answer, goDist[n] + backDist[n]);
        }
            
        System.out.println(answer);

        br.close();
    }
    private static int[] dijkstra(List<Edge>[] town) {

        Queue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];

        Arrays.fill(dist, INF);

        pq.add(new Edge(X, 0)); // 시작위치
        dist[X] = 0;
        
        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.to]) continue; // 이미 방문
            visited[current.to] = true;
            
            for(Edge next : town[current.to]) {
                if(dist[current.to] + next.weight < dist[next.to]) {
                    dist[next.to] = dist[current.to] + next.weight;
                    pq.add(new Edge(next.to, dist[next.to]));
                } 
            }
        }
        return dist;
    }
}