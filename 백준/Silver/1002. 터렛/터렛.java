import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            
            sb.append(func(x1, y1, r1, x2, y2, r2)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int func(int x1,int y1,int r1,int x2,int y2,int r2) {

        double dist = Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2);

        if(x1 == x2 && y1 == y2 && r1 == r2) return -1; 

        if(dist < Math.pow(r1 + r2, 2) && dist > Math.pow(r2 - r1, 2)) return 2;
        if(dist == Math.pow(r2 + r1, 2) || dist == Math.pow(r2 - r1, 2)) return 1;
        else return 0;
    }
}