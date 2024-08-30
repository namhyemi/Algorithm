import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static String[] list;
    static String[] number;
    static boolean[] numberUsed = new boolean[10];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        /* Input */
        N = Integer.parseInt(br.readLine());
        list = br.readLine().split(" ");
        number = new String[N + 1];

        /* logic */
        for (int i = 9; i >= 0; i--) {
            Arrays.fill(number, "");
            Arrays.fill(numberUsed, false);
            
            number[0] = String.valueOf(i);
            numberUsed[i] = true;

            if (backtracking(0, true)) {
                answer.append(String.join("", number)).append("\n");
                break;
            }
        }

        for (int i = 0; i <= 9; i++) {
            Arrays.fill(number, "");
            Arrays.fill(numberUsed, false);
            
            number[0] = String.valueOf(i);
            numberUsed[i] = true;
            
            if (backtracking(0, false)) {
                answer.append(String.join("", number));
                break;
            }
        }

        /* Output */
        System.out.println(answer.toString());
    }
    
    public static boolean backtracking(int currentIdx, boolean isLargest) {
        if (currentIdx == N) {
            return true;
        } 


        int start = isLargest ? 9 : 0;
        int end = isLargest ? 0 : 9;
        int step = isLargest ? -1 : 1;


        if (list[currentIdx].equals("<")) {
            for (int i = start; isLargest ? i >= end : i <= end; i += step) {
                if (!numberUsed[i] && (Integer.parseInt(number[currentIdx]) < i)) {
                    numberUsed[i] = true;
                    number[currentIdx + 1] = String.valueOf(i);

                    if (backtracking(currentIdx + 1, isLargest)) {
                        return true;
                    }

                    numberUsed[i] = false;
                }
            }
        } else if (list[currentIdx].equals(">")) {
            for (int i = start; isLargest ? i >= end : i <= end; i += step) {
                if (!numberUsed[i] && (Integer.parseInt(number[currentIdx]) > i)) {
                    numberUsed[i] = true;
                    number[currentIdx + 1] = String.valueOf(i);

                    if (backtracking(currentIdx + 1, isLargest)) {
                        return true;
                    }

                    numberUsed[i] = false;
                }
            }
        }

        return false;
    }
}
