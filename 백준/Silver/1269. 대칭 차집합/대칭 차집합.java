import java.util.*;
import java.io.*;

public class Main {
    static int A, B;
    static List<Integer> AList = new ArrayList<>(), BList = new ArrayList();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine());
        A = Integer.parseInt(input.nextToken());
        B = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        for(int a = 0; a < A; a++) {
            AList.add(Integer.parseInt(input.nextToken()));
        }

        input = new StringTokenizer(br.readLine());
        for(int b = 0; b < B; b++) {
            BList.add(Integer.parseInt(input.nextToken()));
        }

        Collections.sort(AList);
        Collections.sort(BList);

        int Aanswer = AList.size(), Banswer = BList.size();
        int Aidx = 0, Bidx = 0;
        while(Aidx < AList.size() && Bidx < BList.size()) {
            int a = AList.get(Aidx);
            int b = BList.get(Bidx);
            
            if(a == b) {
                Aidx++; Bidx++;
                Aanswer--;
                Banswer--;
            } else if(a < b) {
                Aidx++;
            } else {
                Bidx++;
            }
        }

        System.out.println(Aanswer + Banswer);

        br.close();
    }
}