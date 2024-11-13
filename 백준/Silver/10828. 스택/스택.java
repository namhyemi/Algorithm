import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayDeque<Integer> stack = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());

        for(int n = 0; n < N; n++) {
            StringTokenizer input = new StringTokenizer(br.readLine());

            String s = input.nextToken();

            if(s.equals("push")) stack.add(Integer.parseInt(input.nextToken()));
            else if(s.equals("size")) answer.append(stack.size());
            else if(s.equals("top")) {
                if(stack.isEmpty()) answer.append(-1);
                else answer.append(stack.peekLast());
            }
            else if(s.equals("empty")) {
                if(stack.isEmpty()) answer.append(1);
                else answer.append(0);
            } 
            else if(s.equals("pop")) {
                if(stack.isEmpty()) answer.append(-1);
                else answer.append(stack.pollLast());
            }

            if(!s.equals("push")) answer.append("\n");
        }
        
        System.out.println(answer);
    }
}