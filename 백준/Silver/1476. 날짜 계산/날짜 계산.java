import java.util.*;
import java.io.*;

public class Main {
    static int E,S,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        E = Integer.parseInt(input.nextToken());
        S = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());

        E = (E == 15) ? 0 : E;
        S = (S == 28) ? 0 : S;
        M = (M == 19) ? 0 : M;
        
        int i = 1;
        while(true) {
            if((i % 15 == E) && (i % 28 == S) && (i % 19 == M)) break;
            i++;
        }

        System.out.println(i);
    }
}