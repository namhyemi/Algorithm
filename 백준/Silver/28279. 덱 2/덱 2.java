import java.util.*;
import java.io.*;

public class Main {
    static int N, num, action;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        Deque<Integer> dq = new ArrayDeque<>();
        for(int n = 0 ; n < N; n++) {
            StringTokenizer input = new StringTokenizer(br.readLine());

            action = Integer.parseInt(input.nextToken());
            if(action == 1) {
                num = Integer.parseInt(input.nextToken());
                dq.addFirst(num);
            } else if(action == 2) {
                num = Integer.parseInt(input.nextToken());
                dq.addLast(num);
            } else if(action == 3) {
                if(dq.isEmpty()) answer.append(-1).append("\n");
                else answer.append(dq.pollFirst()).append("\n");
            } else if(action == 4) {
                if(dq.isEmpty()) answer.append(-1).append("\n");
                else answer.append(dq.pollLast()).append("\n");
            } else if(action == 5) {
                answer.append(dq.size()).append("\n");
            } else if(action == 6) {
                if(dq.isEmpty()) answer.append(1).append("\n");
                else answer.append(0).append("\n");
            } else if(action == 7) {
                if(dq.isEmpty()) answer.append(-1).append("\n");
                else answer.append(dq.peekFirst()).append("\n");
            } else {
                if(dq.isEmpty()) answer.append(-1).append("\n");
                else answer.append(dq.peekLast()).append("\n");
            }
        }

        System.out.println(answer);
        br.close();
    }
}