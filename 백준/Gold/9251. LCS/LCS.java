import java.util.*;
import java.io.*;

public class Main {

    static String str1, str2;
    static int[][] DP;
    
    public static void main(String[] args) throws IOException {    

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine();
        str2 = br.readLine();

        DP = new int[str1.length() + 1][str2.length() + 1]; // 0행, 0열은 indexError 방지용. (1,1) 부터 사용

        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) { 
                    DP[i][j] = DP[i-1][j-1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
                }
            }
        }

        System.out.println(DP[str1.length()][str2.length()]);
    }
}