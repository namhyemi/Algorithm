import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int check[] = new int[4];
	static int DNA[] = new int[4]; //{'A', 'C', 'G', 'T'};
	static int ans = 0;
	
	static int total_len, sub_len;
	static String usertext;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		total_len = Integer.parseInt(st.nextToken());
		sub_len = Integer.parseInt(st.nextToken());
		
		usertext = br.readLine();
		
		st = new StringTokenizer(br.readLine()); // 규칙 조건 배열에 저장
		for (int i = 0; i < 4; i++) {
			check[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i < sub_len; i++) {
			char c = usertext.charAt(i);
	
			if(c == 'A') DNA[0]++;
			else if(c == 'C') DNA[1]++;
			else if(c == 'G') DNA[2]++;
			else if(c == 'T') DNA[3]++;
		}
		
		
		for(int i = 0; i < total_len - sub_len; i++) {
			char c = usertext.charAt(i);
			if(DNA[0] >= check[0] && DNA[1] >= check[1] && DNA[2] >= check[2] && DNA[3] >= check[3]) {
				ans++;
			}

			if(c == 'A') DNA[0]--;
			else if(c == 'C') DNA[1]--;
			else if(c == 'G') DNA[2]--;
			else if(c == 'T') DNA[3]--;
			
			c = usertext.charAt(i + sub_len);
			
			if(c == 'A') DNA[0]++;
			else if(c == 'C') DNA[1]++;
			else if(c == 'G') DNA[2]++;
			else if(c == 'T') DNA[3]++;			
		}
	
		if(DNA[0] >= check[0] && DNA[1] >= check[1] && DNA[2] >= check[2] && DNA[3] >= check[3]) {
			ans++;
		}
		
		System.out.println(ans);
	}
}