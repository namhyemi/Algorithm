import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] count = new int[10001];
    static List<Integer> num = new ArrayList<>();
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int current = Integer.parseInt(st.nextToken());

            if(count[current] == 0) num.add(current);
            count[current]++;
        }

        Collections.sort(num);
        
        DFS(0);

        System.out.println(sb.toString());
    }

    public static void DFS(int cnt) {
        if(cnt == M) {
            for(int current : result) {
                sb.append(current).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int n : num) {
            if(count[n] == 0) continue;

            count[n]--;
            result[cnt] = n;
            DFS(cnt + 1);
            count[n]++;
        }
    }
}