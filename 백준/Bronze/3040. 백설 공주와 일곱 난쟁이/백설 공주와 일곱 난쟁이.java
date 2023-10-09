import java.util.Scanner;

public class Main {
	
	static int[] dwarf = new int[10];
	static int[] isSelected = new int[7];
	static int[] result = new int[7];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 난쟁이 9명의 모자값 입력받기
		for(int i = 0; i < 9; i++) {
			dwarf[i] = sc.nextInt();
		}
		
		// 조합
		Com(0,0);
		
		// 결과 출력
		for(int i = 0; i < 7; i++) {
			sb.append(result[i] + "\n");
		}
		System.out.println(sb);
	}
	
	public static void Com(int cnt, int start) {
		
		if(cnt == 7) { // 7명 선택
			int sum = 0;
			
			// 7명의 모자가 100인지 확인
			for(int i = 0; i < 7; i++) {
				sum += dwarf[isSelected[i]];
			}
			
			// 모자 합이 100이면 결과에 저장
			if(sum == 100) { 
				for(int i = 0; i < 7; i++) {
					result[i] = dwarf[isSelected[i]];
				}
			}
			return;
		}
		
		for(int i = start; i < 9 ;i++) {
			isSelected[cnt] = i;
			Com(cnt + 1, i + 1);
		}
	}
}
