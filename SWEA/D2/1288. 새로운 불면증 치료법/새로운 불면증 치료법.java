import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
1	
N의 배수 숫자의 각 자리수를 체크한다. 
최소 어떤 숫자를 셌을때 각 자리수가 0~9 모두 발견할 수 있는지 구하자
(몇 번째 배수인지가 아니라 그 숫자가 결과값이다.)

N은 최대 1,000,000 이지만 자리수로 보면 7자리이다. => 자리수를 체크해야 한다.
*/
public class Solution {
 
	static int end = (1 << 10)-1; // 0b111111111, (1 << 10) != 1000000000
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
		
			int startNum = Integer.parseInt(br.readLine());

			int flag = 0;
			int checkNum = 0;
			while(true) {
				checkNum += startNum; 

				int tmpNum = checkNum;
				while(tmpNum != 0) {
					int num = tmpNum % 10; // 자리수 체크
					tmpNum /= 10;

					flag |= (1 << num);
				}

				if(flag == end) break;
			}
			
			sb.append("#" + tc + " " + checkNum + "\n");
		}
		System.out.println(sb);
	}
}