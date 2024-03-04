import java.util.List;
import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {

    static int answer = 0;
    static int[] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = N-1; i > 0; i--){
            int change = 0;
            if(arr[i-1] >= arr[i]) change = (arr[i-1] - arr[i]) + 1;
            arr[i-1] -= change;
            answer += change;
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}