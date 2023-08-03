import java.util.*;
import java.io.*;

public class Main {

	static int n, S[], B[];
	static long result = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		S = new int[n+1];
		B = new int[n+1];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		sub(0, 0, 1, 0);
		
		System.out.println(result);
	}
	
	public static void sub(int cnt, int tot, long sm, long bm) {
		
		if(cnt == n) {
			if(tot > 0 && Math.abs(sm - bm) < result) {
				result = Math.abs(sm - bm);
			}
			return;
		}
		
		sm *= S[cnt];
		bm += B[cnt];
		sub(cnt + 1, tot + 1, sm, bm);
		sm /= S[cnt];
		bm -= B[cnt];
		sub(cnt + 1, tot, sm, bm);
	}
}