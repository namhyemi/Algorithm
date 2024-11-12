import java.util.*;
import java.io.*;

public class Main {
    static int N, M, startPoint;
    static boolean[] visited;
    static List<Integer> result = new ArrayList<>();
    static ArrayList<Integer>[] graph;
    static StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer input = new StringTokenizer(br.readLine());
        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());
        startPoint = Integer.parseInt(input.nextToken());

        graph = new ArrayList[N + 1];
        
        for(int n = 0; n <= N; n++) {
            graph[n] = new ArrayList<Integer>();
        }

        for(int m = 0; m < M; m++) {
            input = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for(int n = 0; n <= N; n++) {
            Collections.sort(graph[n]);
        }

        visited = new boolean[N + 1];
        DFS(startPoint);

        answer.append("\n");
        
        visited = new boolean[N + 1];
        BFS();

        System.out.println(answer);
        br.close();
    }

    public static void DFS(int current) {
        answer.append(current).append(" ");
        visited[current] = true;
        
        for(int next : graph[current]) {
            if(visited[next]) continue;
            DFS(next);
        }
    }

    public static void BFS() {
        Queue<Integer> q = new ArrayDeque<>();

        q.add(startPoint);
        visited[startPoint] = true;

        while(!q.isEmpty()) {
            int current = q.poll();

            answer.append(current).append(" ");

            for(int next : graph[current]) {
                if(visited[next]) continue;

                q.add(next);
                visited[next] = true;
            }
        }
    }
}
