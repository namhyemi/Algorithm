import java.util.*;
import java.io.*;

public class Main {

    static int startNum = 100;
    static int endNum, N, minDiff;
    static boolean[] arr = new boolean[10];
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = {};
        endNum = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        if(N != 0) str= br.readLine().split(" ");
        for(String s : str) {
            int num = Integer.parseInt(s);
            arr[num] = true;
        }

        if(endNum == 100) {
            System.out.println(0);
            return;
        }
        
        minDiff = Math.abs(endNum - startNum); // 오직 +, - 으로 100부터이동

        if(!arr[0]) minDiff = Math.min(minDiff, endNum + 1); // 0에서 부터 시작 (별도 체크)
        for(int n = endNum; n > 0; n--) { // endNum ~ 1
            int cnt = checkDiff(n); // 버튼 클릭 횟수

            if(flag) { // 버튼 클릭 가능
                minDiff = Math.min(minDiff, cnt);
                break;
            }
            if(cnt > minDiff) break; // 더 볼 필요 없음  
        }
        for(int n = endNum + 1; n < 1_000_000; n++) { // // endNum + 1 ~ 1_000_000
            int cnt = checkDiff(n); // 버튼 클릭 횟수
            
            if(flag) { // 버튼 클릭 가능
                minDiff = Math.min(minDiff, cnt);
                break;
            }
            
            if(cnt > minDiff) break; // 더 볼 필요 없음
        }

        
        
        System.out.println(minDiff);

        br.close();
    }

    private static int checkDiff(int num) { // 버튼 클릭 횟수 체크
        int cnt = 0, n = num;
        
        flag = true;
        while(num > 0) { // 모든 자리수 체크 
            cnt++;
            if(arr[num % 10]) { // 버튼 클릭 불가능
                flag = false; 
            } 
            num /= 10;
        }
        cnt += Math.abs(endNum - n);
        
        return cnt;
    }
}