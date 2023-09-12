import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static Stack<int[]> laser = new Stack<>();
	// public static Stack<Integer> laser = new Stack<>();
	// public static List<Integer> heightList = new ArrayList<>();
	public static int[] heightList;
	public static int N;
	
	public static void main(String[] args) throws Exception {	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); 
		heightList = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		laser.add(new int[] {0,0});
		
		for(int i = 0; i < N; i++) {

			int top = Integer.parseInt(st.nextToken());

			while(!laser.isEmpty()) {		
				if(laser.peek()[0] < top){
					laser.pop();
					heightList[i] = 0;
				}
				else {
					heightList[i] = laser.peek()[1];
					break;
				}			
			}
			laser.push(new int[] {top, i+1});
		}
		
		for(int i = 0; i < N; i++) {
			bw.write(heightList[i] + " ");
		}
		bw.flush();
	}
}