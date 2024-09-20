import java.util.*;
import java.io.*;

public class Main {

    static Map<Character, Integer> map = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
    static Map<Integer, String> first = Map.of(0, "", 1, "I", 2, "II", 3, "III", 4, "IV", 5, "V", 6, "VI", 7, "VII", 8, "VIII", 9, "IX");
    static Map<Integer, String> second = Map.of(0, "", 1, "X", 2, "XX", 3, "XXX", 4, "XL", 5, "L", 6, "LX", 7, "LXX", 8, "LXXX", 9, "XC");
    static Map<Integer, String> third = Map.of(0, "", 1, "C", 2, "CC", 3, "CCC", 4, "CD", 5, "D", 6, "DC", 7, "DCC", 8, "DCCC", 9, "CM");
    static Map<Integer, String> forth = Map.of(0, "", 1, "M", 2, "MM", 3, "MMM");
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int num1 = RomtoArab(br.readLine());
        int num2 = RomtoArab(br.readLine());
        int answer = num1+num2;

        result.append(answer).append("\n");
        result.append(ArabtoRom(answer));
        
        System.out.println(result.toString());
        br.close();
    }

    public static int RomtoArab(String str) {

        int number = 0;
        int past = 0; // 임시저장
        for(char c : str.toCharArray()) {
            int current = map.get(c);

            if(past == 0) { //
                past = current;
                continue;
            }
            if(past < current){ // 현재 숫자가 이전 숫자보다 큰 경우 빼기
                number += (current - past);
                past = 0; // 이전 수 초기화
            } else if(past >= current) { // 현재 숫자가 이전 숫자보다 작거나 같은 경우
                number += past; // 이전에 보류한 숫자 사용
                past = current;  // 잠시 보류
            }
        }
        number += past;
        
        return number;
    }

    public static String ArabtoRom(int num) {

        StringBuilder sb = new StringBuilder();

        sb.append(forth.get(num / 1000)); num %= 1000; // 천의 자리수
        sb.append(third.get(num / 100)); num %= 100; // 백의 자리수
        sb.append(second.get(num / 10)); num %= 10; // 십의 자리수
        sb.append(first.get(num)); // 일의 자리수

        return sb.toString();
    }
}