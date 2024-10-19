import java.io.*;
import java.util.*;

public class Main {

    static class Printer {
        int num;
        int order;

        public Printer(int num, int order) {
            this.num = num;
            this.order = order;
        }
    }
    
    static int N, M, T;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            // 값 입력
            StringTokenizer input = new StringTokenizer(br.readLine());

            N = Integer.parseInt(input.nextToken());
            M = Integer.parseInt(input.nextToken());

            Queue<Integer> rank = new PriorityQueue<>(); // Collections.reverseOrder()
            Queue<Printer> q = new ArrayDeque();
            
            input = new StringTokenizer(br.readLine());
            for(int n = 0; n < N; n++) {
                int num = Integer.parseInt(input.nextToken());

                rank.add(-num);
                q.add(new Printer(num, n));
            }

            // logic
            int check = 0;
            boolean flag = false;
            while(!q.isEmpty()) {
                int maxNum = -rank.poll();
                check++;

                while(true) {
                    Printer current = q.poll();
                    
                    if(current.num == maxNum) { // 현재 수가 최대 수이면 출력
                        if(current.order == M) flag = true; // 현재수가 목표한 특정 숫자면 출력
                        break; 
                    } else {
                        q.add(current);
                    }
                }

                if(flag) break; // 이미 숫자 찾았으면 반복문 종료
            }

            answer.append(check).append("\n");
        }
        
        System.out.println(answer);
        
        br.readLine();
    }
}