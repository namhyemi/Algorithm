import java.io.*;
import java.util.*;

class Solution
{
	static final int MAX = 2000005;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int result = -1;
			
			st = new StringTokenizer(br.readLine());
			int snackCnt = Integer.parseInt(st.nextToken());
			int maxGram = Integer.parseInt(st.nextToken());
			
			int[] snackList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int snackOne = snackCnt-1;
			int snackTwo = snackCnt-2;

			Arrays.sort(snackList);
			
			int sum = snackList[snackOne] + snackList[snackTwo];
			while(true) {
				if(sum <= maxGram) {
					result = Math.max(result, sum);
				}
		
				sum -= snackList[snackTwo];
				if(snackTwo == 0) {
					sum -= snackList[snackOne--];
					if(snackOne == 0) break;
					sum += snackList[snackOne];
					snackTwo = snackOne - 1;
				}
				else snackTwo--;
				sum += snackList[snackTwo];
			}
			
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.print(sb);
	}
}