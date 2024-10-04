import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] parents;
    static List<Integer>[] tree;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        tree = new ArrayList[N + 1];
        for(int n = 1; n <= N; n++) tree[n] = new ArrayList<>();

        for(int n = 1; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        parents[1] = 1;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next : tree[current]) {
                if(parents[next] != 0) continue; // 이미 방문한 노드

                parents[next] = current;
                queue.add(next);
            }
        }

        for(int i = 2; i <= N; i++) {
            answer.append(parents[i]).append("\n");
        }

        System.out.println(answer.toString());
    }
}