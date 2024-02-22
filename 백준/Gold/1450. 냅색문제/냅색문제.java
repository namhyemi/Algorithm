import java.io.*;
import java.util.*;

public class Main {

    static int N, C;
    static int[] items;
    static int answer = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /* 입력 데이터 저장 */
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        items = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }


        /* 완전 탐색 (중간만나기 준비 단계) */
        ArrayList<Integer> leftItems = new ArrayList<>();
        ArrayList<Integer> rightItems = new ArrayList<>();

        dfs(0, N/2, 0, leftItems);
        dfs(N/2+1, N-1, 0, rightItems);


        /* 이분 탐색 (중간 만나기) */
        Collections.sort(leftItems);
        Collections.sort(rightItems);

        int count = rightItems.size();
        for (Integer leftItem : leftItems) {
            while (count >= 0 && leftItem + rightItems.get(count-1) > C) {
                count--;
            }
            answer += count;
        }
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int start, int end, int sum, ArrayList<Integer> list){

        if(sum > C) return;
        if(start > end){
            list.add(sum);
            return;
        }
        dfs(start+1, end, sum, list);
        dfs(start+ 1, end, sum + items[start], list);
    }

}