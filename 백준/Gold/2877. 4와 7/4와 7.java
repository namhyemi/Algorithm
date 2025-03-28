import java.util.*;
import java.io.*;

public class Main {
    static int N, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        String S = Integer.toBinaryString(N + 1);
        S = S.substring(1);
        S = S.replace("0", "4").replace("1", "7");
        
        System.out.println(S);
        
        br.close();
    }
}