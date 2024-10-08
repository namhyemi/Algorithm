import java.util.*;
import java.io.*;

public class Main {

    static int answer = 0;
    static boolean flag = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine(), "+-", true);

        while(input.hasMoreTokens()) {
            String str = input.nextToken();

            if(str.equals("-")) flag = false;
            else if(str.equals("+")) ;
            else {
                if(flag) answer += Integer.parseInt(str);
                else answer -= Integer.parseInt(str);
            }
        }
        System.out.println(answer);
    }
}