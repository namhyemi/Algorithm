import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static Set<String> listen = new HashSet<>();
    static Set<String> see = new TreeSet<>();
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int n = 0; n < N; n++) {
            listen.add(br.readLine());
        }

        for(int m = 0; m < M; m++) {
            String name = br.readLine();
            if(listen.contains(name)) see.add(name);
        }
        
        answer.append(see.size()).append("\n");
        for(String person : see){
            answer.append(person).append("\n");
        }
        System.out.println(answer);

    }
}