import java.util.*;
import java.io.*;

public class Main {
    
    static boolean[] arr = new boolean[10001];
    public static void main(String[] args) throws IOException {

        StringBuilder answer = new StringBuilder();

        for(int n = 1; n < 10001; n++) {
            if(arr[n]) continue; // 셀프 넘버 X 

            int num = n;
            int sum = n;
            while(num < 10001) { // 셀프넘버 무한수열 (10000 이하)
                int i = num;
                while(i > 0) { // 각 자리수 더하기
                    sum += (i % 10);
                    i /= 10;
                }

                if(sum > 10000) break; // 범위 벗어남
                    
                if(arr[sum]) break; // 이미 방문
                else arr[sum] = true;

                num = sum;
            }
        }

        for(int n = 1; n < 10001; n++) 
            if(!arr[n]) answer.append(n).append("\n");
        
        System.out.println(answer);
    }
}