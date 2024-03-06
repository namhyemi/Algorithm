import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int N, M;

    static Map<Integer, Integer> family = new HashMap<>();  // 키(자식) 값(부모)
    static int[] visited;

    static int one, another;
    static int result = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        visited = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        one = Integer.parseInt(st.nextToken());
        another = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            
            family.put(child, parent);
        }

        visited[one] = 1;
        while(true){
            if(!family.containsKey(one)) break;

            int parent = family.get(one);
            visited[parent] = visited[one]+1;
            one = parent;
        }

        while(true){
            if(visited[another] != 0){
                result += (visited[another] - 1);
                break;
            }

            if(!family.containsKey(another)){
                result = -1;
                break;
            }

            int parent = family.get(another);
            another = parent;
            result++;
        }


        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}