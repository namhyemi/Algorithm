import java.util.*;
import java.io.*;

public class Main {

    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        int num = 665;
        int cnt = 0;
        while(cnt != N) {
            num++;
            if(String.valueOf(num).contains("666")) cnt++;
        }

        System.out.println(num);
    }
}