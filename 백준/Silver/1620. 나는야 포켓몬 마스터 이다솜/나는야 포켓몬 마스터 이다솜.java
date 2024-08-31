import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static String[] pocketNumber;
    static Map<String, Integer> pocketName;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pocketNumber = new String[N + 1];
        pocketName = new HashMap<>();

        for(int idx = 0; idx < N; idx++) {
            String pocket = br.readLine();
            
            pocketNumber[idx + 1] = pocket;
            pocketName.put(pocket, idx + 1);
        }

        /* logic */
        for(int idx = 0; idx < M; idx++) {
            String pocket = br.readLine();

            if(pocket.charAt(0) > '0' && pocket.charAt(0) <= '9') { // 포켓몬 번호
                answer.append(pocketNumber[Integer.parseInt(pocket)]).append("\n");
            } else { //  포켓몬 이름
                answer.append(pocketName.get(pocket)).append("\n");
            }
        }

        /* output */
        System.out.println(answer);
        
        br.close();
    }
}