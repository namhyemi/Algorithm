import java.io.*;
import java.util.*;

public class Main {
	static int computerNum;
	static int connectNum;
	static boolean[] visitedComputer;
	static boolean[][] computerConnections;
	static int answer;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		/* 입력 데이터 저장 */
		computerNum = Integer.parseInt(br.readLine());
		connectNum = Integer.parseInt(br.readLine());


		visitedComputer = new boolean[computerNum+1];
		computerConnections = new boolean[computerNum+1][computerNum+1];

		for (int i = 0; i < connectNum; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());

			int computer = Integer.parseInt(st.nextToken());
			int otherComputer = Integer.parseInt(st.nextToken());

			computerConnections[computer][otherComputer] = true;
			computerConnections[otherComputer][computer] = true;
		}

		answer = 0;

		Queue<Integer> virus = new ArrayDeque<>();
		virus.add(1);
		visitedComputer[1] = true;
		while(!virus.isEmpty()){
			int currentComputer = virus.poll();
			for(int next = 0; next <= computerNum; next++){
				if(computerConnections[currentComputer][next] && !visitedComputer[next]){
					virus.add(next);
					visitedComputer[next] = true; // 방문처리
					answer++;
				}
			}
		}

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}