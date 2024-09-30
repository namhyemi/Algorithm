import java.util.*;
import java.io.*;

public class Main {

    static char[] inputArray;
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputArray = br.readLine().toCharArray();

        for(int idx = 0; idx < inputArray.length; idx++) {
            if(inputArray[idx] == 'c') {
                if(idx < inputArray.length - 1 && (inputArray[idx + 1] == '=' || inputArray[idx + 1] == '-')) idx++;
            } 
            else if(inputArray[idx] == 'l' || inputArray[idx] == 'n') {
                if(idx < inputArray.length - 1 && inputArray[idx + 1] == 'j') idx++;
            }
            else if(inputArray[idx] == 's' || inputArray[idx] == 'z') {
                if(idx < inputArray.length - 1 && inputArray[idx + 1] == '=') idx++;
            } 
            else if(inputArray[idx] == 'd' && idx < inputArray.length - 1) {
                if(inputArray[idx + 1] == '-') idx++;
                else if(inputArray[idx + 1] == 'z') {
                    if(idx < inputArray.length - 2 && inputArray[idx + 2] == '=') idx+=2;
                }
            }

            answer++;
        }

        System.out.println(answer);
    }
}