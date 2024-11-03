import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] arggs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int change = 0;
        char current = str.charAt(0);
        boolean flag = true;
        for(char next : str.toCharArray()) {
            if(current != next && flag) {
                change++;
                flag = false;
            } else if(current == next) flag = true;
        }

        System.out.println(change);
    }
}