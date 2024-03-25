import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[] snack;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //  조카 수 1 ≤ M ≤ 1,000,000
        N = Integer.parseInt(st.nextToken()); //  과자 수 N 1 ≤ N ≤ 1,000,000
        snack = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            snack[n] = Integer.parseInt(st.nextToken()); // 과자 길이 1 ≤ L1, L2, ..., LN ≤ 1,000,000,000
        }

        Arrays.sort(snack);

        int start = 1;
        int end = snack[N-1];
        int result = 0;
        while (end >= start) {
            int mid = (end + start) / 2;

            if (checkSnack(mid)){
                start = mid + 1;
                result = mid;
            }
            else {
                end = mid - 1;
            }
        }
        System.out.println(result);
        br.close();
    }

    private static boolean checkSnack(int length){ // 과자 준비 가능한지 체크
        int cnt = 0;
        for(int n = 0; n < N; n++){
            if(cnt >= M) break; // 과자 준비 완료
            if(snack[N-1-n] < length){ // 남은 과자들 길이 부족
                cnt = 0;
                break;
            }
            cnt += (snack[N-1-n] / length);
        }
        return cnt >= M;
    }
}