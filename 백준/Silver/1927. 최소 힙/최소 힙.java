import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        for(int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                if(!minPQ.isEmpty()) num = minPQ.poll();

                answer.append(num).append("\n");
            } else {
                minPQ.add(num);
            }
        }

        System.out.println(answer.toString());
    }
}