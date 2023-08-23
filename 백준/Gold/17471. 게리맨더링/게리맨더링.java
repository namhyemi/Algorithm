import java.util.*;
import java.io.*;

public class Main {
    
	static int N;//구역수
	static int[] people;//구역 별인구수
	static int[][] map;//구역별 인접 정보
	static int min = Integer.MAX_VALUE;//답
	static int[] district;//부분집합 만들 때 사용할 배열.각 선거구로 나눈 결과가 들어 있음[1,0,0,1,0,1]	
	static boolean[] visit;//dfs
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();//1~N
		people = new int[N+1];
		district = new int[N+1];
		
		//구역별 인구 입력
		for (int i = 1; i <= N; i++) {
			people[i] = sc.nextInt();
		}
		
		//구역간 연결 정보 입력
		map = new int[N+1][];
		for (int i = 1; i <= N; i++) {
			int cnt = sc.nextInt();//한 구역과 인접한 구역 수
			map[i] = new int[cnt];
			
			for (int j = 0; j < cnt; j++) {
				map[i][j] = sc.nextInt();//구역 i는 구역 j와 연결되어 있다.
 			}
		}//input
		
		subset(1);//구역 번호. 구역 번호가 1부터 시작하니까.
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
		
	}

	private static void subset(int num) {
		if(num == N+1) {//부분집합 하나 완성[0,1,1,0,...]
			//두개의 선거구가 제대로 나눠졌나 체크
			if(wellDivided(0) && wellDivided(1)) {//두 선거구가 제대로 나눠졌는지 && 선거구 내 구역들이 다 연결되어 있는지
				int result = diff();//선거구 간 인구차이
				min = Math.min(min, result);
			}	
			return;//*****
		}
		
		district[num] = 0;
		subset(num + 1);
		
		district[num] = 1;
		subset(num + 1);		
	}

	private static int diff() {
		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < district.length; i++) {
			if(district[i] == 0)
				sum1 += people[i];
			else
				sum2 += people[i];		
		}
		return Math.abs(sum1 - sum2);
	}

	//district <= [1,0,0,0,0,...]
	private static boolean wellDivided(int group) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if(district[i] == group)
				list.add(i);//[1,3,5], [2,4,6]
		}
		
		if(list.size() > 0 && list.size() != N) {//정상적으로 나눠져 있음
			//선거구 내 구역들이 다 연결되어 있나요? => DFS()
			visit = new boolean[N+1];
			dfs(list.get(0), list);//list의 첫 구역부터 시작해서 list안의 모든 구역이 연결되어 있는지 체크
			
			for (int x : list) {//선거구의 구역 하나를 꺼내고
				if(!visit[x])
					return false;
			}
			return true;		
		}	
		return false;//선거구 구분이 비정상
	}

	private static void dfs(int x, ArrayList<Integer> list) {
		visit[x] = true;
		for (int i = 0; i <map[x].length; i++) {//x와 인접한 정점들 조사
			int one = map[x][i];//x, one과 연결되어 있고
			if(!visit[one]) {
				for (int y : list) {
					if(one == y)//한 선거구에 속해 있다
						dfs(one, list);
				}
			}
		}		
	}
}