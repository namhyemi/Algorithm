import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
1. summary 
	임의의 한 위치부터 k개 접시 연속해서 먹을 경우 할인
	각 고객은 초밥의 한 종류의 쿠폰을 받는다. 
	k개 연속해서 먹는 경유 해당 종류의 초밥 하나를 무료로 제공(벨트 위에 없으면 새로 만들어 제공)
	손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하자
2. strategy : 투포인터 (슬라이딩 윈도우)
	쿠폰 번호에 해당하는 초밥을 제외하고서 k개를 먹을 수 있는 개수가 있다면 최대값
3. note 
	밸트 위에 같은 초밥이 둘 이상 있을 수 있다.
	2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d
*/
public class Main {

	static int board[], useFoodCnt[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 접시 개수 
		int d =  Integer.parseInt(st.nextToken()); // 초밥 가지수 
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수 
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 
		
		// 초밥 정보 저장
		board = new int[N];
		for(int i = 0; i < N; i++) {
			board[i] = Integer.parseInt(br.readLine());
		}
		useFoodCnt = new int[d+1]; // 초밥 종류 사용 여부 배열
		
		// 초기화
		int kind = 0; // 최대 초밥 가짓수
		boolean useCoupon = false; // 쿠폰 사용 여부
		
		for(int i = 0; i < k; i++) {
			useFoodCnt[board[i]]++; // 초밥 종류 섭취 횟수 표기
			if(useFoodCnt[board[i]] == 1) kind++; // 처음 먹은 초밥이면 횟수 증가
		}
		if(useFoodCnt[c] == 0) {  // 쿠폰 번호 초밥 섭취 안했으면 무료 증정 
			kind++;
			useCoupon = true;
		}
		
		int maxKind = kind;
		int leftPoint = 0;
		int rightPoint = k-1;
		
		// 구현 (초밥 연속 k개 선택 개수
		while(true) {
			if(rightPoint == N-1) rightPoint = -1;
			
			 // 왼쪽 포인터가 가리키고 있던 초밥 섭취 횟수가 1이면 종류(kind)를 줄인다.
			if(useFoodCnt[board[leftPoint]] == 1) kind--;
			useFoodCnt[board[leftPoint++]]--; // 왼쪽 포인터가 가리키던 초밥 종류 감소 후 오른쪽 한칸 이동
			
			useFoodCnt[board[++rightPoint]]++; // 오른쪽 포인터 한 칸 이동 후 초밥 사용 종류 증가
			// 오른쪽 포인터가 가리키고 있던 초밥을 처음 먹은거면(1) 섭취
			if(useFoodCnt[board[rightPoint]] == 1) kind++; 
		
			if(!useCoupon && useFoodCnt[c] == 0) { // 쿠폰 번호 초밥 섭취 안했으면
				kind++; //  무료 증정
				useCoupon = true;
			}
			if(useCoupon && useFoodCnt[c] != 0) {  // 쿠폰 썼었는데 쿠촌 초밥 추가 되면 무료 초밥 회수
				kind--;
				useCoupon = false;
			}
			
			maxKind = Math.max(maxKind, kind);
			
			if(leftPoint == N) break;
		}
		
		// 결과 출력
		System.out.println(maxKind);
	}
}
