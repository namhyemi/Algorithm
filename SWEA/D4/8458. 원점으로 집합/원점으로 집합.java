import java.io.*;
import java.util.*;
import java.awt.*;

/*
1. summary : 모든 점들을 (0,0) 으로 이동시키시 위해서 움직여야 하는 이동 횟수
	한번의 움직에 모든 점들이 이동한다. 
		- i번째 움직임에서는 i만큼 반드시 이동해야 한다. (상하좌우 자유롭게 이동 가능)
2. strategy : 
	각 점들의 원점과의 거리(맨해튼 거리)들을 배열에 저장
	각 거리가 모두 홀수이거나 모두 짝수이거나 하는 경우에만 원점 도달 가능 
3. note
	1 ≤ N (격자점 개수) ≤ 10
	-10^9 ≤ xi, yi (격자점 좌표) ≤ 10^9
*/

public class Solution {

	public static void main(String[] args) throws Exception {

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] dist = new int[N];
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				dist[i] = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
			}
			for (int i = 0; i < N; i++) {
				if (i >= 1 && dist[i] % 2 != dist[i - 1] % 2) {
					max = -1;
					break;
				} else {
					max = Math.max(dist[i], max);
				}
			}
			int idx = 0;

			if (max != -1) {
				long sum = 0;
				while (true) {
					sum += idx;
					if (sum >= max && (sum - max) % 2 == 0) {
						break;
					}
					idx++;
				}
			} else {
				idx = -1;
			}
			System.out.println("#" + t + " " + idx);
		}
	}
}