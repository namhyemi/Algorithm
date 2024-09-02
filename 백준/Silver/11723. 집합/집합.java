import java.util.*;
import java.io.*;

public class Main {

    static int M, N = 0;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        M = Integer.parseInt(br.readLine());

        int current = 0;
        
        for(int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String action = st.nextToken();

            switch (action) {
                case "add" :
                    N = Integer.parseInt(st.nextToken()) - 1;
                
                    if((current & (1 << N)) == 0) 
                        current |= (1 << N);
                    break;
                case "remove" :
                    N = Integer.parseInt(st.nextToken()) - 1;
                
                    if((current & (1 << N)) != 0) 
                        current &= ~(1 << N);
                    break;
                case "check" : 
                    N = Integer.parseInt(st.nextToken()) - 1;
                    if ((current & (1 << N)) != 0) {
                        answer.append(1);
                    } else {
                        answer.append(0);
                    }
                    answer.append('\n');
                    break;
                case "toggle" :
                    N = Integer.parseInt(st.nextToken()) - 1;

                    if((current & (1 << N)) == 0) current |= (1 << N);
                    else current &= ~(1 << N);
                    break;
                case "all" : current = (1 << 20) - 1; break;
                case "empty" : current = 0; break; 
            }
        }

        System.out.println(answer);
    }
}