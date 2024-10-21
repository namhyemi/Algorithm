import java.util.*;
import java.io.*;

public class Main {

    static int N; // 홀수
    static int[] arr;
    static int[] cntArr = new int[8001]; // -4000 ~ 4000
    static double sum = 0;
    static int maxNum = -4000, minNum = 4000;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for(int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());

            sum += num; // 합
            arr[n] = num; 

            cntArr[num + 4000]++; // 최빈값
            
            maxNum = Math.max(maxNum, num); // 최대값
            minNum = Math.min(minNum, num); // 최소값
        }

        answer.append((int) Math.round(sum / N)).append("\n"); // 산술평균

        Arrays.sort(arr);
        answer.append(arr[N/2]).append("\n"); // 중간값

        int cntMaxNum = 0;
        boolean flag = false; // 두번째로 작은 값 여부
        
        for(int n = 1; n <= 8000; n++) {
            int cntNum = cntArr[n]; // n (0~8000) 값 등장 횟수

            if(cntArr[cntMaxNum] < cntNum) { // 최빈값 등장
                flag = false; // 첫 등장
                cntMaxNum = n;
            } else if(cntArr[cntMaxNum] == cntNum) { // 최빈값 중복 등장
                if(flag) continue; // 이미 두번째

                cntMaxNum = n;
                flag = true;
            }
        }
        answer.append(cntMaxNum - 4000).append("\n");

        answer.append(maxNum - minNum);
        
        System.out.println(answer);

        br.close();
    }
}