import java.util.*;
import java.io.*;

class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());
            int output = 0;
            
            if(num == 0) {
                if(!q.isEmpty()) output = q.poll();
                    
                sb.append(output).append("\n");
            }
            else q.add(num);
        }

        System.out.println(sb.toString());
    }
}